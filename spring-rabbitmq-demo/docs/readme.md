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