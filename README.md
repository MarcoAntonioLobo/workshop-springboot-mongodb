# 🚀 Workshop Spring Boot + MongoDB

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green?logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-5.0-green?logo=mongodb&logoColor=white)

## 🔹 Sobre o Projeto

Este projeto é um **workshop de Spring Boot** que demonstra o desenvolvimento de uma **API RESTful** com integração **MongoDB**, incluindo:

- Criação de **entidades e DTOs** (`User`, `Post`, `AuthorDTO`, `CommentDTO`)
- **Endpoints REST** completos (`GET`, `POST`, `PUT`, `DELETE`)
- **Consulta personalizada** com Spring Data MongoDB
- Tratamento de erros com **exceções customizadas** (`ObjectNotFoundException`)
- **Filtragem por texto e datas**
- Boas práticas de arquitetura com pacotes bem organizados

Este projeto serve como exemplo para **backend developers**, mostrando conhecimento em **Java, Spring Boot, MongoDB, REST APIs e tratamento de exceções**.

---

## 📂 Estrutura do Projeto

```
com.example.demo
│
├─ config
│  └─ Instantiation.java                 # Popula o banco com dados iniciais
│
├─ domain
│  ├─ Post.java                          # Entidade Post
│  └─ User.java                          # Entidade User
│
├─ dto
│  ├─ AuthorDTO.java                     # DTO para autor
│  ├─ CommentDTO.java                    # DTO para comentários
│  └─ UserDTO.java                        # DTO para usuário
│
├─ repository
│  ├─ PostRepository.java                # Repositório de Post com queries customizadas
│  └─ UserRepository.java                # Repositório de User
│
├─ resources
│  ├─ PostResource.java                  # Endpoints REST para Post
│  ├─ util
│  │  └─ URL.java                        # Utilitários de URL e datas
│  └─ exception
│     ├─ StandardError.java              # Estrutura padrão para erros
│     └─ ResourceExceptionHandler.java   # Handler global de exceções
│
├─ services
│  ├─ PostService.java                   # Lógica de negócio dos posts
│  ├─ UserService.java                   # Lógica de negócio dos usuários
│  └─ exceptions
│     └─ ObjectNotFoundException.java    # Exceção customizada
│
└─ WorkShopSpringBootMongoApplication.java  # Classe principal Spring Boot
```

---

## ⚡ Funcionalidades

### Endpoints REST
```
| Método | Endpoint                     | Descrição                                      |
|--------|-----------------------------|-----------------------------------------------|
| GET    | `/posts`                     | Retorna todos os posts                         |
| GET    | `/posts/{id}`                | Retorna um post por ID                          |
| GET    | `/posts/user/{userId}`       | Retorna todos os posts de um usuário           |
| GET    | `/posts/search?text=...`     | Busca posts por texto em título, corpo ou comentários |
| GET    | `/posts/datesearch?start=yyyy-MM-dd&end=yyyy-MM-dd` | Busca posts entre datas                       |
| POST   | `/posts`                     | Insere um novo post                             |
| PUT    | `/posts/{id}`                | Atualiza um post existente                      |
| DELETE | `/posts/{id}`                | Remove um post                                  |
```
---

### Exemplos de Requisição

**Buscar post por texto no título, corpo ou comentário:**

```http
GET /posts/search?text=viagem
```

**Buscar posts entre datas:**

```http
GET /posts/datesearch?start=2018-03-21&end=2018-03-23
```

**Inserir um novo post:**

```http
POST /posts
Content-Type: application/json

{
  "date": "2025-09-28T00:00:00.000+00:00",
  "title": "Novo Post",
  "body": "Conteúdo do post",
  "author": { "id": "68d9702e2bb1349313271d65", "name": "Maria Brown" }
}
```

---

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data MongoDB**
- **MongoDB**
- **DTOs e mapeamento de dados**
- **Tratamento de exceções customizadas**
- **Arquitetura de pacotes organizada**

---

## 📌 Diferenciais

- Tratamento de **datas e filtros avançados**
- Pesquisa por **texto em título, corpo e comentários**
- Uso de **DTOs** para separação entre domínio e representação
- Boas práticas de **REST API**
- Estrutura modular que facilita **escala e manutenção**

---

## 💡 Observações

- Projeto preparado para testes via **Postman** ou qualquer cliente HTTP.
- Permite evolução para recursos como **autenticação, pagination e caching**.

