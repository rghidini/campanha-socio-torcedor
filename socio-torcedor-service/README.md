# API Campanha (campanha-service)

API para administrar os dados da Campanha e fornecer mecanismos para INCLUIR, CONSULTAR, ATUALIZAR e DELETAR.

# API Socio Torcedor (socio-torcedor-service)

API para controlar o cadastro de Sócio Torcedor, consome os dados os dados da API campanha-service.

# ns-commons-model

Projeto que fornece classes comuns entre as duas APIs campanha e socio-torcedor 

#### Tecnologias

- Spring MVC
- Spring Boot
- Spring Data
- Banco de dados MongoDB
- jUnit

### Instalação

O projeto utiliza o banco de dados MongoDB, por isso é preciso ter este banco de dados instalado.

As configurações do banco de dados e porta do servidor estão no arquivo application.properties.

As APIs utilizam Sprint Boot com tomcat embarcado, portanto para executá-las é preciso utilizar as classes CampanhaApplication e SocioTorcedorApplication. 

### Funcionalidades

##### campanha-service

##### socio-torcedor-service

#### Testes

##### Testes unitários

Para utilizar é recomendado utilizar as classes de testes com jUnit.

##### Teste de Carga

Foi utilizado o jMeter para testar o requisito não funcional 100 acessos por segundos