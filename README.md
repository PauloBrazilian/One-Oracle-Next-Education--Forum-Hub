# FórumHub API

Bem-vindo ao **FórumHub**! Este projeto é um desafio proposto pela **Oracle** e **Alura**, desenvolvido usando **Java** e o framework **Spring Boot** para criar uma API REST completa de fórum de discussão.

## 📋 Descrição

O FórumHub é uma API REST que replica o funcionamento de um fórum de discussão, permitindo aos usuários criar, visualizar, atualizar e excluir tópicos de discussão. A API inclui autenticação JWT, validações de dados e um banco de dados relacional para persistência.

## ✨ Funcionalidades

* **🔐 Autenticação JWT**: Sistema seguro de autenticação com tokens JWT
* **📝 CRUD de Tópicos**: Criação, leitura, atualização e exclusão de tópicos
* **🎯 Validações**: Validações robustas de dados com Bean Validation
* **📊 Paginação**: Listagem de tópicos com paginação e filtros
* **🔍 Filtros**: Busca por curso, ano ou combinação de ambos
* **📋 Documentação**: Documentação automática com Swagger/OpenAPI
* **🛡️ Segurança**: Controle de acesso baseado em roles (ALUNO, MODERADOR, ADMIN)
* **🗄️ Migrations**: Controle de versão do banco com Flyway

## 🛠️ Tecnologias Utilizadas

* **Java 17** - Linguagem de programação
* **Spring Boot 3.5.4** - Framework principal
* **Spring Security** - Autenticação e autorização
* **JWT** - Tokens de autenticação
* **MySQL** - Banco de dados relacional
* **Flyway** - Migrations do banco de dados
* **Spring Data JPA** - Persistência de dados
* **SpringDoc OpenAPI** - Documentação da API
* **Lombok** - Redução de código boilerplate
* **Maven** - Gerenciamento de dependências

## 📦 Estrutura do Projeto

```
Forum-Hub/
├── src/main/java/com/forumhub/
│   ├── config/          # Configurações (Security, etc.)
│   ├── controller/      # Controladores REST
│   ├── dto/            # Data Transfer Objects
│   ├── exception/      # Tratamento de exceções
│   ├── model/          # Entidades JPA
│   ├── repository/     # Repositórios Spring Data
│   ├── security/       # Configurações de segurança
│   ├── service/        # Lógica de negócio
│   └── ForumHubApplication.java
├── src/main/resources/
│   ├── db/migration/   # Scripts Flyway
│   └── application.properties
└── pom.xml
```

## 🚀 Como Executar o Projeto

### Pré-requisitos
* Java 17+
* MySQL 8.0+
* Maven 3.6+

### 1. Clone o repositório
```sh
git clone https://github.com/seu-usuario/Forum-Hub.git
cd Forum-Hub
```

### 2. Configure o banco de dados
Crie um banco MySQL chamado `forumhub` e atualize as credenciais no `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
```

### 3. Execute a aplicação
```sh
# Compilar e executar
mvn spring-boot:run

# Ou compile primeiro e depois execute
mvn clean package
java -jar target/Forum-Hub-0.0.1-SNAPSHOT.jar
```

### 4. Acesse a aplicação
A API estará disponível em: `http://localhost:8080`

---

<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Oracle_logo.svg/512px-Oracle_logo.svg.png" alt="Oracle Logo" width="150"/>
  &nbsp;&nbsp;&nbsp;
  <img src="https://www.alura.com.br/assets/img/home/alura-logo.svg" alt="Alura Logo" width="55"/>
</p>
