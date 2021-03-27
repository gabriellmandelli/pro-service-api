# `pro-service-api` - API for equipament maintenance control

## 🚀 About this project
* Initial structure created in Lucidchart [Here](https://app.lucidchart.com/documents/view/6ea3307a-4101-428a-98a0-7faa121aaff1).
* Project with tasks [Here](https://github.com/gabriellmandelli/pro-service-api/projects).

## 📋 Dependencies
These are some dependencies used in this repository:
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
- [Spring Test](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-testing)
- [Swagger](https://swagger.io/)
- [Lombok](https://projectlombok.org/)

## ⚒ Requirements
- Java 8+
- Gradle
- PostgreSql

## Running
 - Clone repository:
```
git clone https://github.com/gabriellmandelli/pro-service-api.git
```

 - Running with Docker:
```
cd pro-service-api
sudo docker build -t proserviceapi .	
sudo docker images	
docker run -t -p 8000:8080 --name dockerize proserviceapi
```

- Running with IntelliJIDE:
```
File/Open..
select path "pro-service-api"
Update dependencies with gradle
select the main class "com.proService.maintanance.MaintananceApplication"
select to use classpath of module "maintanance.main"
select JRE Java 8+
Run api
```

- Running the application you can:
 * To access the documentation created with Swagge [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/).
 * To import in insomnia, select the option "Import Data" and then "From URL" and put the link [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs) to be imported all the endpoints to your Workspace.


## :gear: Tools Useds
- [Lucidchart](https://app.lucidchart.com/)
- [Spring Initializr](https://start.spring.io/)
- IntelliJIDE
- Insomnia
