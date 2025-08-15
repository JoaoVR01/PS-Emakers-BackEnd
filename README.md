# PS-Emakers-BackEnd – API de Biblioteca

## Visão geral
PS-Emakers-BackEnd é uma aplicação RESTful escrita em Java com Spring Boot. Ela expõe uma API que permite gerenciar livros, usuários (pessoas) e empréstimos de uma biblioteca. O código é organizado em módulos MVC (controllers, services, repositories e entidades) e utiliza autenticação baseada em JWT. As principais dependências encontram-se no pom.xml, como Spring Boot starters (web, JPA, validação), driver do PostgreSQL, segurança, Lombok, biblioteca de tokens JWT e a integração com Swagger para documentação.

## Estrutura do projeto
A raiz da aplicação (`projetoBackEnd`) contém:

**src/main/java/emakersProjetoBackEnd** – código da aplicação dividido em pacotes:
- **config** – configura o Swagger/OpenAPI; define a documentação e metadados da API.
- **controller** – classes REST que mapeiam os endpoints.
- **data**
  - **entity** – entidades JPA que representam as tabelas do banco.
  - **dto/request** e **dto/response** – objetos de transferência usados para receber ou retornar dados.
- **exceptions** – exceções personalizadas para erros de domínio.
- **infra/security** – configurações de segurança, filtro JWT e autenticação.
- **repository** – interfaces do Spring Data JPA para o banco.
- **service** – lógica de negócios.

**src/main/resources/application.properties** – configurações do banco PostgreSQL, porta e segredo do token.

**Start.java** – classe principal que inicializa o Spring Boot.

## Dependências principais
Incluem:
- Spring Boot starters (web, JPA, validação, segurança)
- PostgreSQL driver
- Lombok
- com.auth0.jwt
- springdoc-openapi

## Modelo de dados
### Pessoa
- idPessoa (PK)
- name
- cpf (único)
- cep, logradouro, complemento, bairro, localidade, uf
- email (único)
- senha (BCrypt)
- role (ADMIN ou USER)

### Livro
- idLivro (PK)
- name
- autor
- data_lancamento

### Emprestimo
- idEmprestimo (PK)
- id_pessoa (FK)
- id_livro (FK)
- dataEmprestimo
- dataDevolucao
- status (boolean)

<img width="992" height="633" alt="Captura de tela 2025-08-15 171243" src="https://github.com/user-attachments/assets/90852b41-c09c-4d67-b338-5d651b4e9889" />

#### Query PostgresSQL
```bash
BEGIN;


CREATE TABLE IF NOT EXISTS public.livro
(
    id_livro bigserial NOT NULL,
    autor character varying(100) COLLATE pg_catalog."default" NOT NULL,
    "data_lançamento" date NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT livro_pkey PRIMARY KEY (id_livro)
);

CREATE TABLE IF NOT EXISTS public.emprestimo
(
    id_emprestimo bigserial NOT NULL,
    data_devolucao date,
    data_emprestimo date,
    status boolean,
    id_livro bigint,
    id_pessoa bigint,
    CONSTRAINT emprestimo_pkey PRIMARY KEY (id_emprestimo)
);

CREATE TABLE IF NOT EXISTS public.pessoa
(
    id_pessoa bigserial NOT NULL,
    bairro character varying(100) COLLATE pg_catalog."default",
    cep character varying(9) COLLATE pg_catalog."default" NOT NULL,
    complemento character varying(100) COLLATE pg_catalog."default",
    cpf character varying(11) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    localidade character varying(100) COLLATE pg_catalog."default",
    logradouro character varying(100) COLLATE pg_catalog."default",
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(100) COLLATE pg_catalog."default" NOT NULL,
    uf character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT pessoa_pkey PRIMARY KEY (id_pessoa),
    CONSTRAINT uk_nlwiu48rutiltbnjle59krljo UNIQUE (cpf)
);

ALTER TABLE IF EXISTS public.emprestimo
    ADD CONSTRAINT fk9o80s7i3wn6ks727ytgmudti4 FOREIGN KEY (id_livro)
    REFERENCES public.livro (id_livro) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.emprestimo
    ADD CONSTRAINT fkeujhum1ufew05uykqc54c9dg FOREIGN KEY (id_pessoa)
    REFERENCES public.pessoa (id_pessoa) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

END;
```

## Repositórios
- **PessoaRepository**
- **LivroRepository**
- **EmprestimoRepository**

## Serviços
- **PessoaService** – CRUD de pessoas, validações, exclusão condicional.
- **LivroService** – CRUD de livros, exclusão condicionada a empréstimos.
- **EmprestimoService** – fluxo de empréstimo e devolução.
- **ChangePasswordService** – alteração de senha com validação.
- **CepService** – integração com ViaCEP.
- **TokenService** – geração e validação JWT.
- **AuthorizationService** – carregamento de usuário para autenticação.

## Segurança
- **SecurityConfig** – define regras de acesso.
- **SecurityFilter** – valida JWT.
- **Pessoa** – implementa UserDetails.

## Endpoints
| Endpoint                          | Método | Role   | Descrição |
|-----------------------------------|--------|--------|-----------|
| /auth/login                       | POST   | Público| Login |
| /auth/register                    | POST   | Público| Registro |
| /livro/getall                      | GET    | ADMIN  | Lista livros |
| /livro/{idLivro}                   | GET    | ADMIN  | Detalha livro |
| /livro                             | POST   | ADMIN  | Cria livro |
| /livro/{idLivro}                   | PUT    | ADMIN  | Atualiza livro |
| /livro/delete/{idLivro}            | DELETE | ADMIN  | Remove livro |
| /pessoa/getall                     | GET    | ADMIN  | Lista pessoas |
| /pessoa/{idPessoa}                 | GET    | ADMIN  | Detalha pessoa |
| /pessoa                            | POST   | ADMIN  | Cria pessoa |
| /pessoa/{idPessoa}                 | PUT    | ADMIN  | Atualiza pessoa |
| /pessoa/{idPessoa}                 | DELETE | ADMIN  | Remove pessoa |
| /emprestimo/allEmprestimos         | GET    | ADMIN  | Lista ativos |
| /emprestimo/allDevoluções          | GET    | ADMIN  | Lista devoluções |
| /emprestimo/emprestar              | POST   | USER   | Empresta livro |
| /emprestimo/devolver               | POST   | USER   | Devolve livro |
| /emprestimo/privateEmprestimos     | GET    | USER   | Lista do usuário |
| /password/changepassword           | POST   | USER   | Troca senha |

## Swagger
Acesse:
- http://localhost:8080/swagger-ui.html
- http://localhost:8080/swagger-ui/index.html

<img width="1192" height="738" alt="Captura de tela 2025-08-15 170800" src="https://github.com/user-attachments/assets/bff523b7-2d85-48fd-b0dc-5b5d07a6a307" />

## Como executar
### Pré-requisitos
- JDK 17+
- Maven ou Maven Wrapper
- PostgreSQL rodando com banco `api-emakers`

### Passos
```bash
git clone https://github.com/JoaoVR01/PS-Emakers-BackEnd.git
cd PS-Emakers-BackEnd/projetoBackEnd
mvn clean install
mvn spring-boot:run
```
Acesse Swagger em `http://localhost:8080/swagger-ui.html`.

## Tutorial Insomnia

<img width="1346" height="713" alt="Captura de tela 2025-08-15 170854" src="https://github.com/user-attachments/assets/4d161ab2-7f96-4d74-9586-d6da5f2bf07c" />

1. **Registrar** (`POST /auth/register`)
```json
{
  "name": "Usuário Teste",
  "cpf": "12345678912",
  "cep": "30110001",
  "email": "user@example.com",
  "senha": "senhaSegura123"
}
```
2. **Login** (`POST /auth/login`)
```json
{
  "email": "user@example.com",
  "senha": "senhaSegura123"
}
```
3. **Autenticação** – enviar `Authorization: Bearer <token>`.
4. **Emprestar** (`POST /emprestimo/emprestar`)
```json
{
  "livro": { "idLivro": 1 }
}
```
5. **Devolver** (`POST /emprestimo/devolver`)
```json
{
  "livro": { "idLivro": 1 }
}
```
6. **Alterar senha** (`POST /password/changepassword`)
```json
{
  "senhaAtual": "senhaSegura123",
  "newSenha": "novaSenha456"
}
```

## Considerações finais
- Sempre enviar JWT no header.
- ViaCEP preenche endereço automaticamente.
- Use Swagger para testar e documentar a API.
