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
