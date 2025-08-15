# PS‑Emakers‑BackEnd — API de Biblioteca

API REST em **Spring Boot 3** para gerenciar **livros**, **pessoas (usuários)** e **empréstimos**, com **autenticação JWT**, documentação **Swagger/OpenAPI** e persistência em **PostgreSQL**.

> **Swagger**: `http://localhost:8080/swagger-ui/index.html`  
> **Versão Java**: 17+ (recomendado 17)  
> **Build**: Maven  
> **Banco**: PostgreSQL

---

## Sumário
- [Arquitetura & Stack](#arquitetura--stack)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Modelo de Dados (ER)](#modelo-de-dados-er)
- [Validações & DTOs](#validações--dtos)
- [Repositórios (JPA)](#repositórios-jpa)
- [Serviços (Regras de Negócio)](#serviços-regras-de-negócio)
- [Controllers & Endpoints](#controllers--endpoints)
  - [Autenticação (`/auth`)](#autenticação-auth)
  - [Pessoas (`/pessoa`)](#pessoas-pessoa)
  - [Livros (`/livro`)](#livros-livro)
  - [Empréstimos (`/emprestimo`)](#empréstimos-emprestimo)
  - [Senha (`/password`)](#senha-password)
- [Autenticação & Segurança](#autenticação--segurança)
- [Integração Externa (CEP)](#integração-externa-cep)
- [Rodando o Projeto](#rodando-o-projeto)
  - [Configuração do `application.properties`](#configuração-do-applicationproperties)
  - [Executando com Maven](#executando-com-maven)
- [Testando com Insomnia](#testando-com-insomnia)
- [Usando no Swagger](#usando-no-swagger)
- [Tratamento de Erros](#tratamento-de-erros)
- [Dicas úteis](#dicas-úteis)

---

## Arquitetura & Stack
- **Spring Boot 3 (Maven)**
- **Spring Web** (controllers REST)
- **Spring Data JPA** (repositórios e persistência)
- **Spring Security + JWT**
- **Bean Validation (Jakarta Validation)**
- **OpenAPI/Swagger** (springdoc-openapi)
- **PostgreSQL** (driver oficial)
- **Lombok** (getters/setters/builders)
- **Devtools** (hot reload em dev)

---

## Estrutura do Projeto

projetoBackEnd/
├─ src/
│ ├─ main/
│ │ ├─ java/emakersProjetoBackEnd/
│ │ │ ├─ Start.java
│ │ │ ├─ config/
│ │ │ │ └─ SwaggerConfig.java
│ │ │ ├─ controller/
│ │ │ │ ├─ AuthenticationController.java
│ │ │ │ ├─ PessoaController.java
│ │ │ │ ├─ LivroController.java
│ │ │ │ ├─ EmprestimoController.java
│ │ │ │ └─ ChangePasswordController.java
│ │ │ ├─ data/
│ │ │ │ ├─ dto/
│ │ │ │ │ ├─ request/
│ │ │ │ │ │ ├─ LoginRequestDTO.java
│ │ │ │ │ │ ├─ PessoaRequestDTO.java
│ │ │ │ │ │ ├─ AdminPessoaRequestDTO.java
│ │ │ │ │ │ ├─ LivroRequestDTO.java
│ │ │ │ │ │ ├─ EmprestimoRequestDTO.java
│ │ │ │ │ │ └─ ChangePasswordRequestDTO.java
│ │ │ │ │ └─ response/
│ │ │ │ │ ├─ LoginResponseDTO.java
│ │ │ │ │ ├─ PessoaResponseDTO.java
│ │ │ │ │ ├─ AdminPessoaResponseDTO.java
│ │ │ │ │ ├─ LivroResponseDTO.java
│ │ │ │ │ ├─ EmprestimoResponseDTO.java
│ │ │ │ │ └─ CepResponseDTO.java
│ │ │ │ └─ entity/
│ │ │ │ ├─ Pessoa.java
│ │ │ │ ├─ Livro.java
│ │ │ │ ├─ Emprestimo.java
│ │ │ │ └─ Roles.java
│ │ │ ├─ infra/security/
│ │ │ │ ├─ SecurityConfig.java
│ │ │ │ └─ SecurityFilter.java
│ │ │ ├─ repository/
│ │ │ │ ├─ PessoaRepository.java
│ │ │ │ ├─ LivroRepository.java
│ │ │ │ └─ EmprestimoRepository.java
│ │ │ └─ service/
│ │ │ ├─ AuthorizationService.java
│ │ │ ├─ TokenService.java
│ │ │ ├─ PessoaService.java
│ │ │ ├─ LivroService.java
│ │ │ ├─ EmprestimoService.java
│ │ │ ├─ ChangePasswordService.java
│ │ │ └─ CepService.java
│ │ └─ resources/
│ │ └─ application.properties
└─ pom.xml

**src/main/java/emakersProjetoBackEnd** – código da aplicação dividido em pacotes:

**config** – configura o Swagger/OpenAPI; define a documentação e metadados da API.

**controller** – classes REST que mapeiam os endpoints.

**data**
**entity** – entidades JPA que representam as tabelas do banco.

**dto/request e dto/response** – objetos de transferência usados para receber ou retornar dados.

**exceptions** – exceções personalizadas para erros de domínio (ex.: livro emprestado, CPF duplicado etc.).

**infra/security** – configurações de segurança (SecurityConfig), filtro para validar tokens (SecurityFilter) e providers de autenticação.

**repository** – interfaces do Spring Data JPA para interagir com o banco.

**service** – classes de serviço que encapsulam a lógica de negócios.

**src/main/resources/application.properties** – define a configuração do banco e ajustes do Hibernate. A aplicação utiliza PostgreSQL como banco de dados (URL jdbc:postgresql://localhost:5432/api‑emakers com usuário postgres e senha 7854), exibe o SQL gerado e atualiza o esquema automaticamente. O server.port padrão é 8080 e o segredo usado para gerar tokens vem de 
api.securitytoken.secret.

**Start.java** – classe principal que inicializa o Spring Boot.

## Dependências Principais
O pom.xml inclui starters do Spring Boot para web, JPA, validação, segurança, Lombok, driver do PostgreSQL e a biblioteca com.auth0.jwt para geração e verificação de tokens. Também usa springdoc-openapi para gerar a interface Swagger.

## Modelo de Dados e Banco de Dados
