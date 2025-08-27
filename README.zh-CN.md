# General Service Framework 通用服务框架 (GSF)

[English](README.md) | [简体中文](README.zh-CN.md)

GSF是一个轻量级通用低代码服务框架，使用Java语言和Spring Boot开发，旨在帮助开发者快速构建易维护、可扩展的服务端应用。

通过GSF框架，您可以快速完成以下任务：
- 创建标准RESTful风格的API接口
- 提供统一CRUD操作的服务
- 标准Controller-Service-Repository分层架构的应用程序
- 集成Spring Security进行安全认证和授权
- 通过Filter机制实现复杂数据查询和分页
- 统一事件机制处理实体数据变更
- 全局HTTP异常处理，提供友好的错误响应

GSF基于以下技术栈构建：
- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security
- Snowflake ID

## 🛡️ 授权协议

本项目基于 **Apache License 2.0** 进行授权。

您可以自由地：

- 出于**商业目的**和**非商业目的**使用、修改和分发本软件（需保留版权声明）；
- 学习源代码并为项目做出贡献。

若希望获得官方支持或定制开发服务，请联系我们。

## 快速开始

- 克隆项目代码至本地目录
- 进入项目所在目录，运行命令 `mvn clean install -Dmaven.test.skip=true`
- 检查命令运行日志，确认jar文件已成功打包并复制到本地Maven仓库
- 在您的项目中添加以下依赖：
```xml
<dependency>
    <groupId>com.shimi</groupId>
    <artifactId>shimi-gsf-core</artifactId>
    <version>1.1.1-SNAPSHOT</version>
</dependency>
```

## Bug提交

加入“GSF”项目，提交bug：[https://www.gogoscrum.com/invitations/9tXDjj4CyM4M]

## 交流社区

微信扫码加入GSF开发者交流群：\
<img src="https://gogoscrum.oss-cn-hangzhou.aliyuncs.com/materials/wechat-qr-gsf-dev-group.png" width="100">