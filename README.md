### Utilizando a The Cat API
Aplicação realizada para o processo seletivo no Itaú

### Sobre 
O projeto consiste na utilização de uma API externa que disponibiliza diversas informações sobre diferentes raças de gatos. A partir de uma requisição HTTP, o cliente obterá dados customizados sobre raças específicas.

### Tecnologias utilizadas 
- Spring Boot
- Spring Data
- Spring Web
- Banco de dados H2
- Postman

### Funcionalidades 
Como dito anteriormente, é possível realizar consultas personalizadas a partir de uma requisição HTTP do tipo GET, considerando como URI base "{host}:8080/find-cat". São elas:
- Listagem de todas as raças de gatos a partir do endpoint "/all-cats"
- Listagem de todas as informações de uma raça de gato específica a partir do endpoint "/breed/{name}" - sendo o "name" o nome da raça (exemplo: Siberian, Balinese, Abyssinian, etc)
- Listagem de todas as raças de gato a partir de um temperamento especifico a partir do endpoint "/temperament/{temperament}" - sendo "temperament" o temperamento escolhido (exemplo: Gentle, Independent, Active, etc)
- Listagem de todas as raças de gato a partir de uma origem específica a partir do endpoint "/origin/{origin}" - sendo "origin" a origem (país) escolhida (exemplo: France, United States, Egypt, etc)
*** as informações trafegadas sobre os gatos são: nome (raça), id, temperamento, origem e descrição. Além disso, para cada consulta *por raça* realizada, é realizada a persistência dos dados

### Sobre a estrutura do projeto
*Design*
A aplicação segue um padrão de atribuição de responsabilidades para cada classe implementada, que pode ser encontrada na pasta src.

*Estrutura de pastas criadas e responsabilidades*
- Model: é a classe modelo, onde é atribuída a entidade e que recebe as requisições vindas da controller.
- Controller: aqui é por onde passa toda requisição feita pela usuário, além de ser a classe que se comunica com a model a partir dessa requisição.
- Service: nessa clase é feita a ponte com a repository para realização da persistência dos dados no banco de dados.
- Repository: utilizando um repositório, é garantido uma única camada de acesso aos dados.

### Utilização
*Para executar e testar o projeto, você precisará: *
- Ter instalado em sua máquina o Java 11 ou superior
- Ter instalado um API Client (Postman)

*Passo a passo*
1. Realize o clone do repositório 
2. Configure o arquivo application.properties (pasta resources), para ajuste do banco de dados, com os seguintes comandos: 

spring.datasource.url=jdbc:h2:file:./data/desafio
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=bruno
spring.datasource.password=password

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

3. Execute a classe DesafioApplication

*Consultando persistências no banco de dados*
Ao utilizar o console do banco de dados H2 (http://localhost:8080/h2-console), é possível, com comandos SQL, realizar uma consulta para saber quantas vezes uma raça X foi consultada e também já ter o resultado de quais foram as mais consultadas (claro, apenas após haver realizado buscas por raça no endpoint indicado anteriormnente):

1. Preencher os campos login (bruno) e password (password)
2. No campo de busca, escrever o comando "SELECT NAME, COUNT (*) FROM CAT GROUP BY NAME ORDER BY NAME ASC"
3. Apertar o botão "Run"
