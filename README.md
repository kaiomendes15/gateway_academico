# Gateway Acadêmico (Mini Projeto)

[![Java CI](https://img.shields.io/badge/Java-21-blue.svg)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Build](https://img.shields.io/badge/Build-Maven-red.svg)](https://maven.apache.org)

Projeto acadêmico da disciplina **[T200] Projeto de Arquitetura 
de Sistemas**, da Universidade de Fortaleza (UNIFOR).

## 1. Objetivo
Desenvolver uma aplicação monolítica em Spring Boot que atua como uma fachada (API Gateway) para três microsserviços acadêmicos simulados (Discente, Disciplina e Biblioteca). O sistema agrega informações desses serviços e provê uma API unificada para o usuário final, além de simular operações de escrita localmente.

## 2. Escopo Funcional

### 2.1. Funcionalidades de Consulta (Leitura)
* Consultar dados consolidados do discente (ID, nome, curso, status, etc.).
* Listar todas as disciplinas oferecidas pelos cursos.
* Listar todos os livros do acervo da biblioteca e seu status de disponibilidade.

### 2.2. Funcionalidades de Simulação (Escrita Local)
O estado das simulações é **volátil** e existe apenas enquanto a aplicação está em execução.
* Simular matrícula de um discente em uma disciplina.
* Simular cancelamento de matrícula.
* Simular reserva de um livro.
* Simular cancelamento de reserva de livro.

## 3. Arquitetura e Decisões de Design

A arquitetura deste projeto foi guiada pelos padrões **MVC (Model-View-Controller)**, **SOLID** e **GRASP**, com foco em Baixo Acoplamento e Alta Coesão.

### 3.1. Estrutura de Camadas

O projeto segue uma arquitetura em camadas bem definida para aderir ao princípio **SRP (Single Responsibility Principle)**:

* **`controller`**: Camada de entrada da API (MVC). Responsável por receber requisições HTTP, validar entradas básicas e formatar respostas (incluindo tratamento de erros com `ResponseEntity` e DTOs de erro).
* **`service`**: Camada de negócio (GRASP Controller). Orquestra as ações, aplica as regras de negócio (ex: "discente não pode se matricular em mais de 5 disciplinas") e coordena os `Repositories` e `Clients`.
* **`client`**: Camada de integração. Sua única responsabilidade é consumir as APIs externas (Microsserviços). Utiliza interfaces (`Client<T>`) para desacoplar a implementação (`RestTemplate`, `WebClient`, etc.) do `Service`.
* **`repository`**: Camada de acesso a dados (simulada). Simula a persistência em memória (Repositório em memória) usando um `Map`. Também utiliza interfaces genéricas (`Repository<T>`) para a lógica de CRUD.
* **`config`**: Contém classes de configuração do Spring, como o `DataLoader` (`CommandLineRunner`) que popula os repositórios em memória na inicialização.
* **`dto`**: Objetos de Transferência de Dados (`Identifiable`) usados para trafegar dados entre as camadas e na API.

### 3.2. Padrões Aplicados (SOLID e GRASP)

* **SRP (Princípio da Responsabilidade Única):**
    * O `DiscenteClient` só se preocupa em buscar dados da API externa.
    * O `DiscenteRepository` só se preocupa em gerenciar os dados em memória.
    * O `DiscenteService` só se preocupa com as regras de negócio.
    * O `DataLoader` só se preocupa em popular os dados na inicialização.

* **DIP (Princípio da Inversão de Dependência):**
    * **Princípio Central:** Módulos de alto nível (`Service`) não dependem de módulos de baixo nível (`ClientImpl`, `RepositoryImpl`). Ambos dependem de abstrações (interfaces).
    * **Aplicação:** O `DiscenteService` depende da interface `DiscenteRepository`, não da implementação `InMemoryRepository`. Isso permite trocar a implementação (ex: de `HashMap` para um banco H2) sem alterar o `Service`.
    * **Aplicação:** O `DataLoader` depende das interfaces `DiscenteClient` e `DiscenteRepository`, não de suas implementações concretas.

* **GRASP: Baixo Acoplamento (Low Coupling):**
    * Alcançado diretamente pela aplicação do **DIP**. As camadas são conectadas por interfaces, minimizando a dependência entre elas.

* **GRASP: Alta Coesão (High Cohesion):**
    * Alcançado pelo **SRP**. Cada classe e pacote tem um propósito claro e focado (ex: `client` só lida com integração, `repository` só com persistência).

* **GRASP: Controller:**
    * O `DiscenteController` atua como o mediador entre a interface (HTTP/JSON) e o domínio (camada de `Service`), garantindo que as requisições sejam delegadas à camada correta.

## 4. Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.5.7
* **Dependências:**
    * `spring-boot-starter-web`: Para criar a API REST.
    * `spring-boot-starter-validation`: Para validação de dados.
    * `lombok`: Para reduzir boilerplate (Getters, Setters, etc.).
* **Build:** Maven

## [cite_start]5. Instruções de Execução [cite: 65]

**Pré-requisitos:**
* Java JDK 21 (ou superior)
* Apache Maven 3.5.7

**Executando:**

1.  Clone este repositório:
    ```bash
    git clone https://github.com/kaiomendes15/gateway_academico.git
    cd [NOME-DA-PASTA]
    ```

2.  Compile e execute o projeto usando o Maven Wrapper:
    * No Linux/macOS:
        ```bash
        ./mvnw spring-boot:run
        ```
    * No Windows:
        ```bash
        ./mvnw.cmd spring-boot:run
        ```

3.  A aplicação estará disponível em `http://localhost:8080`.

## 7. Exemplos de Endpoints

* **Consultar Discente:** `GET /discentes/{id}`
* **Listar Disciplinas:** `GET /disciplinas`
* **Listar Livros:** `GET /livros`