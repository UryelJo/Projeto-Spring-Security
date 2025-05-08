# 🛡️ API de Segurança com Spring Boot, Clean Architecture & DDD

>## _Este projeto demonstra a implementação robusta de segurança em uma aplicação Spring Boot, aplicando os princípios da Arquitetura Limpa e Design Orientado a Domínio (DDD) para criar uma API segura e bem estruturada._

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

**Índice**
- [📦 Bibliotecas Java Utilizadas](#-📦-bibliotecas-java-utilizadas-e-suas-versões:)
- [✨ Funcionalidades Implementadas](#-✨-funcionalidades-implementadas-)
- [🛠️ Como Começar](#-🛠️-como-começar-)
    - [⚙️ Pré-requisitos](#-⚙️-pré-requisitos-)
    - [⚙️ Instalação](#-⚙️-instalação-)
    - [▶️ Execução](#-▶️-execução-)
- [📖 Uso / Como Usar](#-📖-uso--como-usar-)
    - [👤 Registro de Novo Usuário](#-👤-registro-de-novo-usuário-)
    - [🔑 Login de Usuário Existente](#-🔑-login-de-usuário-existente-)
    - [🛡️ Acesso a Endpoints Protegidos](#-🛡️-acesso-a-endpoints-protegidos-)
- [🧑‍💻 Autor](#-🧑‍💻-autor-)
- [📜 Licença](#-📜-licença-)

## 📦 Bibliotecas Java Utilizadas e suas Versões:

- [Java JWT - 4.5.0](https://mvnrepository.com/artifact/com.auth0/java-jwt/4.5.0)
- [Flyway Core - 11.6.0](https://mvnrepository.com/artifact/org.flywaydb/flyway-core/11.6.0)
- [Flyway Database Postgresql - 11.6.0](https://mvnrepository.com/artifact/org.flywaydb/flyway-database-postgresql/11.6.0)
- [Apache Commons - 3.14.0](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.14.0)
- [Lombok - 1.18.36](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.36)
- [Postgresql](https://mvnrepository.com/artifact/org.postgresql/postgresql)
- [Spring Boot JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Spring Boot Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)


## ✨ Funcionalidades Implementadas

- ### ⚙️ Criação Automática da Tabela de Usuários com Flyway
    - A tabela de usuários deste projeto é criada e gerenciada automaticamente através do Flyway, garantindo a evolução controlada do schema do banco de dados. 💾

- ### 🛡️ Autorização de Usuários via Regras (RULES)
    - A autorização de usuários neste projeto é implementada através de um sistema baseado em regras (RULES), oferecendo flexibilidade e controle granular sobre o acesso. 🔑

- ### 🔑 Geração e Validação de Tokens JWT
    - A autenticação e autorização seguras são garantidas através da geração e validação de tokens JWT (JSON Web Tokens). 🛡️

- ### 👤 Registro e Login Básico de Usuários
    - Implementação das funcionalidades essenciais para o registro de novas contas e o login de usuários existentes no sistema. ✅


## 🛠️ Como Começar

Esta seção guiará você na configuração do ambiente para executar e explorar este projeto Spring Boot Security.

### ⚙️ Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

  * **Java Development Kit (JDK):** Versão 17 ou superior (recomendado). Você pode verificar sua versão executando `java -version` no terminal.
    ```bash
    java -version
    ```
  * **Maven:** Versão 3.8.0 ou superior. Utilize `mvn -v` para verificar a instalação.
    ```bash
    mvn -v
    ```
  * **Git:** Para clonar o repositório do projeto. Você pode verificar com `git --version`.
    ```bash
    git --version
    ```
  * **PostgreSQL:** Uma instância do PostgreSQL em execução. Você pode instalá-lo localmente ou usar um container Docker (instruções abaixo).
  * **Docker (Opcional, para executar o PostgreSQL em container):** Se você preferir executar o PostgreSQL em um container Docker, certifique-se de tê-lo instalado. Verifique com `docker --version`.
    ```bash
    docker --version
    ```

### ⚙️ Instalação

Siga estes passos para obter uma cópia do projeto e configurar seu ambiente:

1.  **Clone o Repositório:**
    Abra seu terminal e navegue até o diretório onde você deseja clonar o projeto. Execute o seguinte comando:

    ```bash
    git clone https://github.com/UryelJo/Projeto-Spring-Security.git
    cd Projeto-Spring-Security
    ```

2.  **Configuração das Variáveis de Ambiente:**

    A configuração do banco de dados PostgreSQL e outras configurações sensíveis são feitas através de variáveis de ambiente. Crie um arquivo `.env` na raiz do seu projeto (ou no diretório onde você pretende executar a aplicação) e defina as seguintes variáveis:

    ```dotenv
      DB_URL=<sua_url_de_ligação>
      DB_USERNAME=<seu_usuario>
      DB_PASSWORD=<sua_senha>

      JWT_SECRET="Spring Security"
      URL_FRONTS="https://localhost:8080"
    ```

    * Substitua `<sua_url_de_ligação>`, `<seu_usuario>` e `<sua_senha>` pelas credenciais do seu banco de dados PostgreSQL.
      * Exemplo Url: `jdbc:postgresql://localhost:5432/banco`
      * Exemplo Usuario: `postgres`
      * Exemplo Senha: `1234`
    * `JWT_SECRET` é a chave secreta usada para assinar os tokens JWT. Mantenha este valor seguro e não o compartilhe publicamente.
    * `URL_FRONTS` define a URL do seu frontend, que pode ser usada para configurações de CORS (Compartilhamento de Recursos de Origem Cruzada), por exemplo.   
    * Se o seu PostgreSQL estiver rodando em um container Docker ou em um host diferente, ajuste o valor de `<sua_url_de_ligação>` de acordo.

    **Opção Adicional: Usando PostgreSQL com Docker**

      * Se você optar por usar Docker, certifique-se de ter um arquivo `docker-compose.yml` configurando o PostgreSQL. As variáveis de ambiente definidas no `.env` podem ser utilizadas no `docker-compose.yml` para configurar o container do PostgreSQL também.

3.  **Build do Projeto:**
    Na raiz do diretório do projeto, execute o seguinte comando Maven para construir a aplicação:

    ```bash
    mvn clean install
    ```

    Este comando irá baixar as dependências, compilar o código e executar os testes (se houver).

### ▶️ Execução

Após a instalação e o build bem-sucedidos, você pode executar a aplicação Spring Boot de duas maneiras:

**Executando via Maven**

Na raiz do diretório do projeto, execute o seguinte comando:

```bash
mvn spring-boot:run
```

## 📖 Uso / Como Usar 

Esta seção detalha como interagir com as funcionalidades cruciais de **autenticação** 🛡️ e **registro** 👤 de usuários fornecidas por esta API segura.

### 👤 Registro de Novo Usuário

Para criar uma nova conta de usuário em nosso sistema, envie uma requisição `POST` para o endpoint `/users/register`.

**📍 Endpoint:** `/users/register`

**Método HTTP:** `POST`

**Corpo da Requisição (JSON):**
```json
{
  "username": "nome_do_usuario",
  "login": "novo_usuario",
  "password": "senha_forte",
  "roleUser": "role_do_usuario"
  // Outros campos de registro podem ser necessários dependendo dos requisitos da sua implementação.
}
```
* `login`: O **nome do usuário** ✏ selecionado para a nova conta.
* `login`: O **login do usuário** 🔑 desejado para a nova conta. Seja criativo e único!
* `password`: A **senha** 🔒 para a nova conta. Recomendamos o uso de senhas fortes e complexas para maior segurança.
* `roleUser`: A **Rule do usuário** ⚙ (ex: "ADMIN", "USER")

**Exemplo de Requisição (usando `curl` no seu terminal):**

```bash
curl -X POST \
  http://localhost:8080/users/register \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "uryel Jó",
    "login": "uryel@gmail.com",
    "password": "senhaSecreta1234",
    "roleUser": "ADMIN"
  }'
```

**✅ Resposta:**

* Em caso de sucesso, o servidor responderá com um status HTTP `201 Created`, sinalizando que a conta do usuário foi criada com sucesso. O corpo da resposta estará vazio ∅.

### 🔑 Login de Usuário Existente

Para acessar os recursos protegidos da API, você precisará obter um token JWT realizando o login através do endpoint `/users/login`.

**📍 Endpoint:** `/users/login`

**Método HTTP:** `POST`

**Corpo da Requisição (JSON):**
```json
{
  "login": "nome_do_usuario",
  "password": "senha_do_usuario"
}
```

* `login`: O **nome de usuário** 👤 da conta que você deseja acessar.
* `password`: A **senha** 🔒 associada a essa conta.

**Exemplo de Requisição (usando `curl`):**

```bash
curl -X POST \
  http://localhost:8080/users/login \
  -H 'Content-Type: application/json' \
  -d '{
    "login": "uryel@gmail.com",
    "password": "senhaSecreta1234"
  }'
```

**✅ Resposta:**

* Se as credenciais estiverem corretas, o servidor retornará uma resposta com o status HTTP `200 OK` e um corpo JSON contendo o seu precioso **token JWT**:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJjb2Rlcl9uZWV3YmllIiwibmFtZSIiOiJOZXdlciBDb2RlciIsImlhdCI6MTY3ODg4NjQwMH0.your_awesome_jwt_token_here"
}
```

Guarde este token com carinho! Você o usará para provar sua identidade em todas as requisições a recursos protegidos.

### 🛡️ Acesso a Endpoints Protegidos

Para acessar as áreas restritas da API, você deverá incluir o **token JWT** que recebeu durante o login no cabeçalho `Authorization` da sua requisição HTTP.

**Exemplo de Requisição a um Recurso Protegido (usando `curl`):**

Suponha que você queira obter informações de um endpoint `/protected-data`. A requisição seria assim:

```bash
curl -X GET \
  http://localhost:8080/protected-data \
  -H 'Authorization: seu_token_jwt_gerado_no_login'
```

>*(Lembre-se de substituir `seu_token_jwt_gerado_no_login` pelo token real que você obteve na resposta de login).*

Se o seu token for válido e não tiver expirado, o servidor processará sua requisição e retornará os dados solicitados. Caso contrário, você receberá uma resposta com o status HTTP `401 Unauthorized` e a seguinte mensagem em JSON:

```json
{
  "error": "Nao autorizado!"
}
```

Isso indica que seu token é inválido, expirou ou não foi fornecido, e você não tem permissão para acessar o recurso. Certifique-se de fazer login novamente para obter um novo token! 😉

## 🧑‍💻 Autor

 **Uryel Jó de Lucca Araujo de Oliveira** ([@UryelJo](https://github.com/UryelJo)) - Criador e Mantenedor Principal
  > email: uryeljodelucca18@gmail.com
  > linkedin: [UryelJo](https://www.linkedin.com/in/uryeljo/)

## 📜 Licença

Este projeto é distribuído sob a [MIT License](LICENSE). Consulte o arquivo `LICENSE` para obter mais detalhes.
