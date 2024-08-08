# Clínica Backend

## Introdução
Esse projeto implementa uma API Rest para uma clínica médica.

Usa **Java 20.0.2**, **Spring 3.3.2** e banco de dados **Postgres**

## Configuração

O banco de dados está configurado no arquivo [application.properties](/src/main/resources/application.properties).
O arquivo pode ser modificado, mas é preciso que o banco, usuário e permissões sejam configuradas anteriormente.

Configuração inicial:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/clinica
spring.datasource.username=clinica
spring.datasource.password=123456
```
