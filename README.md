# Projeto Ecommerce de Discos de Vinil - Bruno Moreira

<h2>O que é</h2>
O projeto é um ecommerce de discos de vinil que utiliza a API do Spotify para buscar pelos albums e discos.

É possivel procurar, pesquisar, comprar e remover discos da sua conta.<br>
É possivel também criar, logar e deslogar de uma conta de um usuário.<br>
Além de ser possível adicionar créditos a sua carteira

<h2>Como executar</h2>
Baixe os arquivos do repositório ou execute o comando git clone em uma pasta de sua escolha. Para evitar possíveis erros, garanta que a pasta raíz (onde está o arquivo docker-compose.yml) esteja nomeada como bruno-moreira-backend.<br><br>

Em seguida, instale as dependências de ambas as APIs utilizando clean + install do Maven em cada projeto e depois recarregue-os.<br>

No caminho "vinyl-ecommerce-backend/app-integration-api/src/main/java/br/com/sysmap/bootcamp/domain/service/integration", adicione seu id e segredo do spotify apis.<br>

Por último, execute o comando "docker compose up" a partir da pasta raíz do projeto.

OBS.: Para evitar bugs, é recomendado começar os testes apenas depois de garantir que todos os módulos da aplicação estejam iniciados.

<h2>Documentação dos endpoints</h2>
Para ver a documentação das APIs, suba a aplicação e acesse os seguintes links:<br>

http://localhost:8081/api/swagger-ui/index.html<br>
http://localhost:8082/api/swagger-ui/index.html
