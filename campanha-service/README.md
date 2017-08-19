# API Campanha (campanha-service)

API para administrar os dados da Campanha e fornecer mecanismos para INCLUIR, CONSULTAR, ATUALIZAR e DELETAR.

Dependência: [ns-commons-model](https://github.com/rraminelli/campanha-socio-torcedor/tree/master/ns-commons-model) 

## Instalação

O projeto utiliza o banco de dados [MongoDB](https://www.mongodb.com/download-center?ct=header#community), por isso é preciso ter este banco de dados instalado.

As configurações do banco de dados e porta do servidor estão no arquivo application.properties.

A API utiliza Sprint Boot com tomcat embarcado, portanto para executá-la é preciso utilizar a classes CampanhaApplication.

	public class CampanhaApplication {
		public static void main(String[] args) {
			SpringApplication.run(CampanhaApplication.class, args);
		}
	} 

## Funcionalidades

