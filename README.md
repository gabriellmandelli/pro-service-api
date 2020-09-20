# pro-service-api
Projeto para 

* Estrutura inicial criada no Lucidchart [Aqui](https://app.lucidchart.com/documents/view/6ea3307a-4101-428a-98a0-7faa121aaff1).
* Veja o projeto desse repositório [Aqui](https://github.com/gabriellmandelli/pro-service-api/projects).
## Requisitos
- Java 8+
- Gradle

## Para executar
 - Obter o repositório:
```
git clone https://github.com/gabriellmandelli/pro-service-api.git
```

 - Para rodar a aplicação utilizando Docker:
```
cd pro-service-api
sudo docker build -t proserviceapi .	
sudo docker images	
docker run -t -p 8000:8080 --name dockerize proserviceapi
```

- Para rodar a aplicação utilizando IntelliJIDE:
```
File/Open..
Selecionar a pasta "pro-service-api"
O gradle atualizara as dependencias, após terminar, configurar projeto tipo "Application"
Selecionar a main class "com.proService.maintanance.MaintananceApplication"
Selecionar o Use classpath of module "maintanance.main"
Selecionar JRE Java 8+
Rodar a aplicação
```

- Com a aplicação rodando:
 *  Para acessar a documentação criada com o Swagger [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/).
 * Para importar no insomnia, selecionar a opção "Import Data" selecionar "From URL" e botar o link [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs) para ser importado todos os endpoints.

## Dependências utilizadas
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
- [Swagger](https://swagger.io/)
- [Lombok](https://projectlombok.org/)

## Ferramentas Utilizadas
- [Lucidchart](https://app.lucidchart.com/)
- [Spring Initializr](https://start.spring.io/)
- IntelliJIDE
- Insomnia