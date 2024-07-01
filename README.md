# Order Service Management

## Descrição

Este projeto é um sistema de gerenciamento de ordens de serviço com sua arquitetura baseada em microserviços, desenvolvido utilizando Spring. O sistema contará com diversos módulos que trabalharão em conjunto para entregar agilidade, funcionalidade e escalabilidade para o agendamento e execução de ordens de serviço.

## Tecnologias Utilizadas

- **Java**
- **Spring Framework**
- **MySQL**: Utilizado como banco de dados.
- **Docker**: O banco de dados foi containerizado por ser mais ágil para testar.
- **Postman**: Utilizado para testar a API.
- **Conceitos**: Clean Code, Clean Architecture, REST.

## Funcionalidades

As API's permite as seguintes operações:

### Customer

- **Create**: Adicionar um novo cliente.
- **Read**: Buscar um cliente pelo ID ou listar todos os clientes.
- **Update**: Atualizar as informações de um cliente existente.
- **Delete**: Remover um cliente.

### Technician

- **Create**: Adicionar um novo técnico.
- **Read**: Buscar um técnico pelo ID ou listar todos os técnicos.
- **Update**: Atualizar as informações de um técnico existente.
- **Delete**: Remover um técnico.

## Estrutura do Projeto

A estrutura do projeto segue os princípios de Clean Architecture, garantindo que o código seja modular, fácil de manter e escalável.


## Configuração do Banco de Dados

No arquivo `application.properties`, configure suas credenciais do MySQL:

```
spring.datasource.url=your_url
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Testando a API
Utilize o Postman para testar os endpoints da API.

### Endpoints:

### Customers:
- **POST** /customers: Adicionar um novo cliente.
- **GET** /customers/{id}: Buscar um cliente pelo ID.
- **GET** /customers: Listar todos os cliente.
- **PUT** /customers/{id}: Atualizar um cliente existente.
- **DELETE** /customers/{id}: Remover um cliente.
- **DELETE** /customers: Remover um cliente.

### Technicians:
- **POST** /technicians: Adicionar um novo técnico.
- **GET** /technicians/{id}: Buscar um técnico pelo ID.
- **GET** /technicians: Listar todos os técnicos.
- **PUT** /technicians/{id}: Atualizar um técnico existente.
- **DELETE** /technicians/{id}: Remover um técnico.
- **DELETE** /technicians: Remover um técnico.

