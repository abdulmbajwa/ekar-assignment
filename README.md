# ekar-assignment

Ekar engineering interview assignment

# Build & Run

To run the application use command
``./mvnw spring-boot:run`` on linux and ``./mvnw.cmd spring-boot:run`` on windows.

To run application in docker environment use
``docker-compose up``

# HTTP Endpoints

Application has 3 GET HTTP endpoints available

- `/update-threads`
  Receives two request parameters, the first one will increase the number of producer threads. The second parameter will
  increase the number of consumer threads. Sample curl command for this endpoint is provided below <br/>

  `curl --location --request GET 'localhost:8080/update-threads?incrementProducer=2&incrementConsumer=10'`
- `/reset-count`
  Resets counter to default value of 50. Sample curl command for this endpoint is provided below <br/>
  `curl --location --request GET 'localhost:8080/reset-count`
- `/set-counter`
  Sets counter to value provided. Sample curl command for this endpoint is provided below <br/>
  `curl --location --request GET 'localhost:8080/set-counter?value=20'`

HTTP endpoint information is also available as an open-api.yaml file in the codebase.