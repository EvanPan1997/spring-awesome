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