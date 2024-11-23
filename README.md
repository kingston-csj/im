  ## im(仿QQ聊天室服务端)

  ### 主要使用的第三方技术  
  *  jforgame-socket-netty，通信网关  
  *  springboot全家桶  
  *  MybaticsPlus，作为持久化方案  

  ### 功能列表  
  *  用户注册/登录
  *  好友搜索与添加
  *  点对点私聊(在线离线均可)
  *  讨论组  

  ### ToDoList  
  *  使用SpringCloud打造为分布式系统   
  *  在线/离线文件传输   
  *  开发更多的交互功能        

  ### QuickStart  
  1. 安装git后，使用命令 git clone https://github.com/kingston-csj/im 
  2. 新建数据库im，导入im-chat/src/test/resources/ddl目录下的im.sql   
  3. 安装minio文件存储服务, 首次本地部署，取消ServerStartup关于updateEmojiResource的代码注释，安装im-chat/src/test/resources/emoji目录下的表情包
  4. 在application.yml文件配置本地数据库连接属性，启动ServerStartup
  5. 另起新目录，下载客户端代码 git clone https://github.com/kingston-csj/wechat
  6. 启动ClientStartup类, 即可看到登录界面（临时密码为000）  
     (多人聊天，需要开启多个ClientStartup客户端即可)


  ### 部分客户端运行效果
  登录界面  
  ![](/screenshots/login.png "登录界面")  

  主界面  
  ![](/screenshots/main.png "主界面")  

  聊天界面  
  ![](/screenshots/privateChat.png "单人聊天界面")  
  ![](/screenshots/emoji.jpeg "表情包界面")
  
  讨论组界面  
  ![](/screenshots/discussion.jpg "讨论组界面")  　　

  ###  案例教程 
  栏目教程 --> [csdn专栏博客](https://blog.csdn.net/littleschemer/article/category/9269527)  
  wiki说明 --> [wiki](https://github.com/kingston-csj/im/wiki)
   

  ### 客户端源代码  
  --> [客户端wechat](https://github.com/kingston-csj/wechat)
  
  ## 欢迎交流讨论
  欢迎star/fork，欢迎学习/使用，期待一起贡献代码 !!  
  如果您发现bug，或者有任何疑问，请提交issue !!   
  

