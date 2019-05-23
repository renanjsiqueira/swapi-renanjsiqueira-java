# swapi-renanjsiqueira-java
Star Wars Api Rest Fork Project


  ***Requisitos para importar o projeto***
        • uma IDE (IntelliJ IDEA , Eclipse ou Spring Tool IDE)
        obs: o projeto foi desenvolvido utilizando IntelliJ IDEA
	
        •Java 8
	
        •Maven
	
        •Spring Boot
	
        •um Container/instância de banco de dados NoSql  Mongo DB 3.4+
	
        obs:para configurar a conexão ao Mongo DB é no Application.properties, por padrão está no perfil de integração.
        obs :a api está sem autenticação foi realizado os testes usando o PostMan


    **Para consumir  O serviço da api  **
    	• as Chamadas de API deve ser no formato JSON(Application/Json)
1 - Adicionar um planeta (com nome, clima e terreno) 
	•"POST" para URL <host>/api/planets/
   	
       {
        "name": "Tatooine",    
        "climate": "arid",      
        "terrain": "desert"       
        }
	
	
	
    obs: caso o registro ja exista ele vai atualizar com as novas informações passadas,
    se o planeta for encontrado na api do swapi vai adicionar a quantidade de apariçoes de filmes,
    lembrando que a busca é pelo nome do planeta.
	

2 - Listar planetas do banco de dados
    • "GET" para URL /api/planets

3 - Listar planetas da API do Star Wars
    • "GET" para URL /api/swapi/planets

4 - Buscar por nome no banco de dados
    •  "GET" para URL /api/planets/name/"nomedoplaneta"

5 - Buscar por ID no banco de dados
    •  "GET" para URL /api/planets/"id registro"

6 - Remover planeta do banco de dados
    • "DELETE" para URL /api/planets/"id registro"

7 - Atualizar planeta do banco de dados
    • deve ser feito uma requisição PUT para /api/planets/"id registro"


    obs 1: os códigos e pacote desenvolvido por min da Api Rest esta em "com.apisw"

    obs 2: as chamadas da api a "ttps://swapi.co/" está no pacote "com.swapi" essa parte foi realizado um fork do projeto open source
     em https://github.com/ghusta/swapi-java-client e importado ao projeto,
     e criado uma classe Util "ApiSWUtil no pacote "com.apisw" para abstrair essa api.
