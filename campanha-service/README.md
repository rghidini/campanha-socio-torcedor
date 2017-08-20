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

- **Lista todas as campanhas ativas**

	
	GET http://localhost:8090/campanhas
	
	
- **Insere uma nova campanha**

	
	POST http://localhost:8090/campanhas
	
	{
        "nome":"Campanha Inicial",
        "dataInicio":"2017-01-01",
        "dataTermino":"2017-03-01",
        "timeCoracao":
        {
            "id":"5997c383cc063915a83cb551"
        }
	}
	

- **Atualiza os dados de uma campeão**

	
	PUT http://localhost:8090/campanhas/5997c384cc063915a83cb552
	
	{
		"id": "5997c383cc063915a83cb551"
        "nome":"Campanha Alterada",
        "dataInicio":"2017-01-01",
        "dataTermino":"2017-03-01",
        "timeCoracao":
        {
            "id":"5997c383cc063915a83cb551"
        }
	}
	
	
- **Busca os dados de uma campanha**
	
	
	GET http://localhost:8090/campanhas/5997c384cc063915a83cb552	
	
	
- **Exclui uma campanha**
	
	
	DELETE http://localhost:8090/campanhas/5997c384cc063915a83cb552
	
	
- **Busca os dados de um time**
	
	
	GET http://localhost:8090/times/5997c383cc063915a83cb551
	
	
- **Lista todos os times**
	
	
	GET http://localhost:8090/times
	
	
- **Insere um novo time**
	
	
	POST http://localhost:8090/times
	
	{
        "nome":"Nome do Time do Coração"
	}
	
	
- **Associa um socio com as campanhas ativas para o time do coração selecionado**


	PUT http://localhost:8090/associa-socio-campanha/{idSocio}/{idTimeCoracao}

	
- **Recupera todas as campanhas relacionados com o sócio torcedor**


	GET http://localhost:8090/campanhas/socio/{idSocio}
	