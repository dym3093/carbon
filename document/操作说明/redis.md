1、 redis在操作  
1) 启动和关闭  
安装redis之后  
在命令行窗口中输入 redis-server redis.windows.conf 启动redis
关闭命令行窗口就是关闭 redis。

redis作为windows服务启动方式
redis-server --service-install redis.windows.conf
启动服务：redis-server --service-start
停止服务：redis-server --service-stop
