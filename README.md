# chat_server
  ## 类QQ聊天室服务端

  ### 主要使用的第三方技术  
  *  Netty，通信网关  
  *  spring，作为IOC容器  
  *  MyBatics，作为持久化方案  

  ### 功能列表  
  *  用户注册/登录(包括用户界面)  
  *  Groovy业务代码热部署  
  *  用户请求消息的处理与响应  
  *  通过http后台请求管理服务进程  
  *  用户数据的持久化  
  *  日志系统

  ### ToDoList  
  *  加入事件驱动机制   
  *  处理用户消息的线程模型  
  *  异步处理用户数据的持久化       

  ### QuickStart  
  1. 安装git后，使用命令 git clone https://github.com/kingston-csj/chat_server  
  2. 新建数据库chat_room，导入根目录下的chat_room.sql   
  3. 安装配置maven环境变量，运行根目录下start.bat脚本
  4. 另起新目录，下载客户端代码 git clone https://github.com/kingston-csj/chat_client  
  5. 启动ClientStartup类    


  栏目教程请移步：--> [csdn博客](http://blog.csdn.net/column/details/16455.html)
  
  欢迎star/fork，欢迎学习/使用，期待一起贡献代码！！

  ## 欢迎交流讨论
  QQ：641711541