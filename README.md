# Projeto Ecommerce de Discos de Vinil - Bruno Moreira

######  Criada com Java 21 e Springboot

---
##### Pontos iniciais

- Essa API possui uma documentação gerada pelo Swagger para auxiliar na compreensão das requisições, porém será necessário utilizar um serviço como Postman, pois grande partes das requisições necessitam do token de autenticação ou de alguma implementação feita pelo Middleware. está acessível na URI: */swagger-ui/index.html#/*.
-  O ambiente de desenvolvimento da Api de Users está na porta 8081.
-  O ambiente de desenvolvimento da Api de Integration está na porta 8082.

##### Como executar a aplicação
É necessário ter o Docker instalado para executar a aplicação localmente.
  - ```git clone https://github.com/Maxixee/record-sales-backend.git ``` Para clonar o repositório na sua IDE
  - ```docker-compose -f docker-compose.yml build ``` Para baixar as imagens do Postgres, RabbitMQ e das 2 APIs
  - ```docker-compose -f docker-compose.yml up ``` Para criar os containers.

- Realizados estes comandos, basta buildar e executar o backend na sua IDE.

# Documentação

  - Ambas as APIs foram documentadas pelo swagger, as documentações podem ser acessadas nas URLs: 
  - API User: ```http://localhost:8081/api/swagger-ui/index.html#/```
  - API Integration: ```http://localhost:8082/api/swagger-ui/index.html#/```

---

# Banco de dados.

###### O banco de dados utilizado na aplicação é o Postgresql

---
# Serviço Autenticação
###### A autenticação é realizada através do endpoint '/api/users/auth', onde realiza o verbo POST, obtendo o email e a senha, retornando os dados do usuários, além  de gerar um token de autenticação.

---
### Token de autenticação

###### O token de autenticação é gerado utilizando Basic Auth do SpringSecurity

---
