# 使用Homebrew部署RabbitMQ

```shell
brew install erlang
brew install rabbitmq
```

若是使用 `brew services start rabbitmq`无法正常启动, 且尝试修复和重新安装Homebrew也不成功, 可以使用以下命令启动RabbitMQ

```shell
rabbitmq-server #直接运行

rabbitmq-server -detached #后台运行

# 若rabbitmq-server不在环境变量中, 可以考虑使用绝对路径或添加环境变量
# 可以使用find命令字来查询该文件
find /usr -name rabbitmq-server # 这种方式查询较慢, homebrew的安装路径多半在/usr目录下, 千万不要直接使用根目录查询
```

### RabbitMQ权限管理

下方代码中 使用root为用户名, 123456为密码 作为案例

```shell
rabbitmqctl add_user root 123456 #添加用户名和密码
rabbitmqctl ser_user_tags root administrator #设置administrator标签, 这个标签通常用于授予用户管理RabbitMQ管理界面的权限
rabbitmqctl set_permissions -p / ".*" ".*" ".*" #用于配置用户在特定虚拟主机(vhost)上的权限
```

`-p /`   指定了要设置权限的虚拟主机, 这里的`/`表示默认的虚拟主机名称。

权限按顺序由`配置` `写入` `读取`组成

##### 配置权限(Configure)

- `.*`  允许用户对所有资源进行操作
- `^`   允许用户创建新的资源(如队列、交换机、绑定)。
- `p`   允许用户删除资源
- `f`   允许用户在资源上执行`force`操作, 例如强制删除队列
- `s`   允许用户查看资源的状态和设置某些属性
- `none`    不允许用户执行任何配置操作

##### 写权限(Write)

- `^`   允许用户发布消息到队列
- `none`    不允许用户向任何消息队列发布消息

##### 读权限(Read)

- `^`   允许用户从队列获取消息
- `p`   允许用户查看队列的消息(不包括获取消息)。
- `none`    不允许用户从队列获取消息或查看队列中的消息

除了`.*`之外，还可以使用具体的资源名称来限制权限。例如，如果想要限制用户只能操作特定的队列或交换机，可以在权限字符串中指定这些资源的名称。例如：

```shell
rabbitmqctl set_permissions -p / myuser "myqueue" "myqueue" "myqueue"
```

# SpringBoot项目中集成RabbitMQ

本文档依赖JDK版本为`17`, Spring版本为`3.0.2`

### 引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

### Provider代码
```java
import com.example.entity.MsgEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProviderService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(MsgEntity entity) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(entity);
        rabbitTemplate.convertAndSend("testQueue", json);
    }
}
```

### Consumer代码

```java
import com.example.entity.MsgEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @RabbitListener(queues = "testQueue")
    public void receiveMessage(final String msg) {
        MsgEntity entity = objectMapper.readValue(msg, MsgEntity.class);
        /* 处理entity的逻辑 */
    }
}
```

# SpringBoot项目集成RabbitMQ并开启手动确认机制

由于本demo使用的SpringBoot版本为`3.0.2`并依赖Spring的版本依赖管理, 所以不能只能使用修改配置文件的方法来开启手动确认模式

### RabbitMQConfig

```java
import lombok.Data;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMQConfig {
    private String host;
    private int port;
    private String username;
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        return new RabbitTemplate(factory);
    }
}
```

### RabbitListenerConfiguration

```java
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitListenerConfiguration {

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}
```

### Consumer

```java
import com.example.entity.MsgEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageConsumerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "testQueue", containerFactory = "simpleRabbitListenerContainerFactory")
    public void receiveMessage(final Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            byte[] body = message.getBody();
            MsgEntity entity = objectMapper.readValue(body, MsgEntity.class);
            /* 此处写具体处理逻辑 */
            // 确认消息
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
            // 异常打回死信队列
            channel.basicNack(deliveryTag, false, true);
        }
    }
}
```
