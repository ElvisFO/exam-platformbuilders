# MVP de um cadastro de clientes

### Objetivo
O objetivo é criar um API para cadastro de clientes.
Não foi abordado nenhuma arquitetura robusta tendo em vista que se trata de um projeto simples.
Nesse projeto não foi usado `ModelMapper` para conversão de entidades em dto. Há um tratamento 
de exceções personalizado.
No projeto tem uma collection do postman para teste.
O projeto se encontra totalmente dockerizado.

* [Swagger](http://localhost:8080/api/swagger-ui/index.html?configUrl=/api/api-docs/swagger-config#/)

### Tecnologia usadas

* Java 11
* SpringBoot
* Postgres
* Flyway
* Docker

# Como executar o projeto

## Requisitos necessários para execução do projeto

Ter o `docker` e o `maven` instalados em seu ambiente.

## Comandos

* Na raiz do projeto executar `mvn clean install`. Será gerado um `app.jar` na pasta `target
* Na pasta `docker` executar o comando `docker-compose up -d`. Esse processo  irá inicializar o banco e aplicação.
E ainda irá executar o flyway para aplicar os migrations.


