# PicPay Simplificado
## Desafio Backend PicPay

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

Este projeto é a construção de uma API utilizando as stacks **Java e o framework Spring.**

Realizei o desenvolvimento do Microservice com base no vídeo [Youtube Channel](https://www.youtube.com/watch?v=QXunBiLq2SM&ab_channel=FernandaKipper%7CDev), o qual foi demonstrado a resolução do [PicPay Challenge](https://github.com/uber-archive/coding-challenge-tools/blob/master/coding_challenge.md).

## Aprendizado

Com a mentoria de Fernanda Kipper, consegui obter e aplicar vários conhecimentos novos de regras de negócio, código limpo e tratamento de exceções durante a resolução do desafio e ter a oportunidade de levar o sistema para outro nível.

## Sumário

- [Instalação](#installation)
- [Configuração](#configuration)
- [Uso](#usage)
- [API Endpoints](#api-endpoints)

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/JaasielAntunes/picpay-challenger.git
```

2. Instale as dependências com o Maven

3. Atualize o `application.properties` inserindo o acesso ao H2 Database

```yaml
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```
## Uso

1. Inicie a aplicação com o Maven
2. A API é acessível na porta http://localhost:8080

## API Endpoints
A API fornece os seguintes endpoints:

**POST USER**
```markdown
POST /users/cadastrar - Cadastrar um usuário
```

**GET USER**
```markdown
POST /users/listar - Listar usuários cadastrados
```

**POST TRANSACTION**
```markdown
POST /transactions - Realizar uma transação
```

**BODY da requisição - POST USER**
```json
{
  "firstName": "João",
  "lastName": "Silva",
  "document": "0912676",
  "email": "joao@gmail.com",
  "password": "0912676",
  "userType": "COMMON",
  "balance": 100
}
```

**BODY da requisição - POST TRANSACTION**
```json
{
  "senderId": 1,
  "receiverId": 2,
  "value": 25
}
```
