# API Sócio Torcedor (campanha-service)

API para cadastrar e validar os dados do Sócio Torcedor. Consome os dados da API [Campanha](https://github.com/rraminelli/campanha-socio-torcedor/tree/master/campanha-service) para listar as Campanhas cadastradas e solicitar relacionamentos Campanha x Sócio Torcedor através do Time do Coração selecionado.

Dependência: [ns-commons-model](https://github.com/rraminelli/campanha-socio-torcedor/tree/master/ns-commons-model) 

## Instalação

O projeto utiliza o banco de dados [MongoDB](https://www.mongodb.com/download-center?ct=header#community), por isso é preciso ter este banco de dados instalado.

As configurações do banco de dados e porta do servidor estão no arquivo application.properties.

A API utiliza Sprint Boot com tomcat embarcado, portanto para executá-la é preciso utilizar a classes SocioTorcedorApplication.

	public class SocioTorcedorApplication {
		public static void main(String[] args) {
			SpringApplication.run(SocioTorcedorApplication.class, args);
		}
	} 

## Funcionalidades

