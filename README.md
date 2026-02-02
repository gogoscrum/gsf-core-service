# General Service Framework (GSF)

[English](README.md) | [简体中文](README.zh-CN.md)

GSF is a lightweight, general-purpose low-code service framework developed using Java and Spring Boot. It aims to help developers easily build maintainable and scalable server-side applications.

With the GSF framework, you can quickly accomplish the following tasks:

- Create standardized RESTful API interfaces 
- Provide services with unified CRUD operations 
- Standard Controller-Service-Repository layered architecture 
- Integrate Spring Security for authentication and authorization 
- Implement complex data queries and pagination through a filter-based mechanism 
- Handle entity data changes via a unified event mechanism 
- Global HTTP exception handling to provide user-friendly error responses

GSF is built on the following technology stack:
- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security
- Snowflake ID

## 🛡️ License

This project is licensed under the **Apache License 2.0**.

You are free to:

- Use, modify, and distribute the software for **non-commercial** and **commercial** purposes (need to keep the logo and copyright info);
- Study the source code and contribute to the project.

## Quick start

- Clone the project to your local directory
- Enter the project folder and run `mvn clean install -Dmaven.test.skip=true`
- Check the command log to confirm that the jar file has been successfully packaged and copied to your local Maven repository
- In your project, add the following dependency:
```xml
<dependency>
    <groupId>com.shimi</groupId>
    <artifactId>shimi-gsf-core</artifactId>
    <version>1.1.2-SNAPSHOT</version>
</dependency>
```

## Report bugs

Join “GSF” project to submit bugs: [https://www.gogoscrum.com/invitations/9tXDjj4CyM4M]

## Developers community

Join GSF developer group on Discord: [https://discord.gg/FBdf43BZ]