# <a href="https://imgbb.com/"><img src="https://i.ibb.co/wNCRx9z/image-2025-01-04-T23-13-02-901-Z.png" alt="image-2025-01-04-T23-13-02-901-Z" border="0" width =150 heigth = 100></a> Desenvolva + : API REST

#### Desenvolvido na linguagem Java por:
- [Anderson Alves Santos](https://github.com/santos-anderson)

## Principais Tecnologias

- <img width="70px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" title = "Java" /> <b>Java 21 :</b> Utilizamos a versão LTS mais recente do Java para tirar vantagem das últimas inovações que essa linguagem robusta e amplamente utilizada oferece;
- <img width="70px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/intellij/intellij-original.svg" title = "IntelliJIDEA" /> <b>IntelliJIDEA :</b> Utilizamos o IntelliJIDEA como a IDEA para fazer os programas em Java;
- <img width="80px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original-wordmark.svg" title = "Postman"/> <b>Postman :</b> Utilizamos a versão 11.40.4;
- <img width="80px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg" title = "Spring boot"/><b>Spring boot :</b> Utilizamos a versão 3.4.4 para testar a API;
- <img width="70px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/azuresqldatabase/azuresqldatabase-original.svg" title = "H2 DataBase" /><b>H2 DataBase :</b> Utilizamos o banco de dados em memória;



## Ada Tech - API REST : IMDB

### Evoluir o projeto do último módulo para uma API REST em Spring Boot com os seguintes itens:
- Persistência em banco de dados (H2 ou Postgres);
- Consumo de uma API externa pública; (opcional);
- Autenticação Básica (opcional);
- Swagger (opcional);
- Frontend (opcional);

### Entregaveis
- O projeto pode ser feito opcionalmente em grupos de 3 até 5 pessoas;
- O projeto deverá ser entregue no github ou zipado no LMS;
- Documentação no REAMDE.md, com os diagramas das classes principais e guia de uso;
- Realizar uma apresentação com os pontos mais desafiadores do projeto, os perrengues passados e um resumo do que foi entregue e o que não pode ser entregue;

## Diagrama de classes
<p align = center>
<a href="https://ibb.co/kgBFG7bR"><img src="https://i.ibb.co/4ZtxFqv6/Ada-Tech-IMDB-Final.jpg" alt="Ada-Tech-IMDB-Final" border="0"></a>
</p>

## Tema do projeto
- Desenvolvemos uma aplicação que busca o nome original do filme através da api: OMDb API


## Site da api: OMDB

<p align = center>
<a href="https://ibb.co/FL5M2R4X"><img src="https://i.ibb.co/rRxSYD23/OMDB-API.png" alt="OMDB-API" border="0"></a><br><br>
  
</p>

### Endereco da api do OMDB
https://www.omdbapi.com/

## Endpoints da aplicação

| Nome do Endpoint         | URL do método                                     | Método no Postman |
| ------------------------ | ------------------------------------------------- | ----------------- |
| Buscar o filme pelo nome | http://localhost:8080/APIFilme/filme/omdb/Title   | GET               |
| Salvar filme             | http://localhost:8080/APIFilme/filme              | POST              |
| Buscar lista de filmes   | http://localhost:8080/APIFilme/filme/listarFilmes | GET               |
| Buscar o filme pelo ID   | http://localhost:8080/APIFilme/filme/1            | GET               |
| Deletar o filme pelo ID  | http://localhost:8080/APIFilme/filme/1            | DELETE            |

## Banco de dados H2 DataBase

### Endereco da url do banco de dados
http://localhost:8080/APIFilme/h2

## Swagger (OpenAPI) da API OMDB

### Endereco da url do Swagger (OpenAPI) da API OMDB
http://localhost:8080/APIFilme/swagger-ui/index.html
