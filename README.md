## im(仿QQ聊天室服务端)

使用springcloud alibaba微服务框架，以及netty通信框架，实现了类似QQ的聊天室服务端。聊天/推送使用netty通信框架，其他功能使用springcloud
alibaba微服务框架。

## 主要使用的第三方技术

* jforgame-socket-netty，通信网关
* springcloud alibaba 微服务框架
* MybaticsPlus，持久化方案

## 主要使用的中间件

* mysql，用户，讨论群，消息等数据的持久化方案
* minio，头像，多媒体消息的文件存储系统
* redis，用户数据快速缓存
* nacos，服务注册发现，配置管理

## 模块组织结构

``` git
im-server
├── im-account          --分布式节点，存储用户基础数据 
├── im-business         --依赖模块，业务实体定义
├── im-chat             --分布式节点，实时聊天服务，实时推送      
├── im-common           --依赖模块，公共数据，包括工具类，常量类等        
├── im-gateway          --分布式节点，springcloud gate搭建的网关       
├── im-infrastructure   --依赖模块，公共基础设施，主要是权限相关
├── im-oss              --依赖模块，oss文件存储服务，例如头像，多媒体消息等  
├── im-security         --分布式节点，统一权限中心 
├── im-web              --分布式节点，http统一入口，提供http接口
```

## 功能列表

* 用户注册/登录
* 好友搜索与添加
* 点对点私聊(在线离线均可)
* 讨论组
* 聊天支持发送离线文件(在线用户支持在线高效传输)
* 聊天支持从剪贴板复制图片或其他文件

## QuickStart

1. 安装git后，使用命令 git clone https://github.com/kingston-csj/im
2. 安装docker-compose, 新建im文件目录,拷贝document目录下的docker-compose.yml文件到该目录，新建子目录im/data,在im目录下执行docker-compose
   up -d启动mysql,minio,redis,nacos服务
3. 执行docker cp命令拷贝im-chat/src/test/resources/ddl目录下的im.sql到mysql容器内，新建数据库im，导入im.sql文件
4. 首次本地部署，配置application.yml的emojiPath,avatarPath参数，安装im-chat/src/test/resources/目录下的表情包,头像包
5. 进行nacos管理后台 http://ip:8848/nacos ,导入document目录下nacos配置(nacos_config_export.zip)，检查所有配置的信息，改为本地ip端口。
   分别启动im-chat,im-gateway,im-web,im-account服务(使用IDEA编辑器的Services工具栏可实现批量启动)
6. 另起新目录，下载客户端代码 git clone https://github.com/kingston-csj/wechat
7. 启动ClientStartup类, 即可看到登录界面（临时密码为000）  
   (多人聊天，开启多个ClientStartup客户端即可)

## 部分客户端运行效果

登录界面  
![](/screenshots/login.png "登录界面")

主界面  
![](/screenshots/main.png "主界面")

聊天界面  
![](/screenshots/privateChat.jpg "单人聊天界面")

讨论组界面  
![](/screenshots/discussion.jpg "讨论组界面")

## 案例教程

栏目教程 --> [csdn专栏博客](https://blog.csdn.net/littleschemer/article/category/9269527)  
wiki说明 --> [wiki](https://github.com/kingston-csj/im/wiki)

## 客户端源代码

--> [客户端wechat](https://github.com/kingston-csj/wechat)

## 欢迎交流讨论

欢迎star/fork，欢迎学习/使用，期待一起贡献代码 !!  
如果您发现bug，或者有任何疑问，请提交issue !!   
  

