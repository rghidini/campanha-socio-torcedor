# API Campanha (campanha-service)

API para administrar os dados da Campanha e fornecer mecanismos para INCLUIR, CONSULTAR, ATUALIZAR e DELETAR.

[Acesso ao Projeto](https://github.com/rraminelli/campanha-socio-torcedor/tree/master/campanha-service)

# API Socio Torcedor (socio-torcedor-service)

API para controlar o cadastro de Sócio Torcedor, consome os dados os dados da API campanha-service.

[Acesso ao Projeto](https://github.com/rraminelli/campanha-socio-torcedor/tree/master/socio-torcedor-service)

# ns-commons-model

Projeto que fornece classes comuns entre as duas APIs campanha e socio-torcedor 

[Acesso ao Projeto](https://github.com/rraminelli/campanha-socio-torcedor/tree/master/ns-commons-model)

## Tecnologias

- [Java 8](http://www.oracle.com/technetwork/pt/java/javase/documentation/index.html)
- [Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Spring Data](http://projects.spring.io/spring-data/)
- [Banco de dados MongoDB](https://www.mongodb.com/)
- [Maven](https://maven.apache.org/)
- [jUnit](http://junit.org/junit4/)

## Testes

- **Testes unitários**
 - Utilizar os testes criados com jUnit.

 ![Teste com junit1](https://github.com/rraminelli/campanha-socio-torcedor/blob/master/test_jmeter/junit1.png)

 ![Teste com junit2](https://github.com/rraminelli/campanha-socio-torcedor/blob/master/test_jmeter/junit2.png)


- **Teste de Performance**
 - Foi utilizado o [jMeter](http://jmeter.apache.org) para garantir o requisito não funcional de 100 requisições por segundo.

![Teste com jMeter4](https://github.com/rraminelli/campanha-socio-torcedor/blob/master/test_jmeter/jmeter4.png)

![Teste com jMeter1](https://github.com/rraminelli/campanha-socio-torcedor/blob/master/test_jmeter/jmeter1.png)

![Teste com jMeter2](https://github.com/rraminelli/campanha-socio-torcedor/blob/master/test_jmeter/jmeter2.png)

![Teste com jMeter3](https://github.com/rraminelli/campanha-socio-torcedor/blob/master/test_jmeter/jmeter3.png)