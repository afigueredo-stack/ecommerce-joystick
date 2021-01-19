# Ecommerce - Joystick Loja
Backend com Endpoints para servir loja virtual com Java e Spring Boot.
### Detalhes da API RESTful
A API RESTful de Ecommerce - Joystick Loja contém as seguintes características:  
* Projeto criado com Spring Boot e Java 8
* Banco de dados PostgreSQL com JPA e Spring Data JPA
* Autenticação e autorização com Spring Security e tokens JWT (JSON Web Token)
* Migração de banco de dados com Flyway
* Testes unitários e de integração com JUnit e Mockito
* Caching com EhCache
* Integração contínua com TravisCI
### Como executar a aplicação
Projeto alterado para rodar usando o Spring 2
Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git.
```
git clone https://https://github.com/afigueredo-stack/ecommerce-joystick
cd ecommerce-joystick
Configure a porta e nome do banco de dados em application.properties
mvn spring-boot:run
Acesse os endpoints através da url http://localhost:8080
```
### Importando o projeto no Eclipse ou STS
No terminal, execute a seguinte operação:
```
mvn eclipse:eclipse
```
No Eclipse/STS, importe o projeto como projeto Maven.