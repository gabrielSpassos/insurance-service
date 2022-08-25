# Insurance Service

- [API Docs](http://localhost:8080/webjars/swagger-ui/index.html#/)

## How to run

#### Quick way

 - ``` chmod +x start.sh ```
 - ``` sh start.sh ```

 #### Detail way

 - Execute unit tests
- ``` cd insurance-service ```
- ``` ./gradlew clean test ```
- Report at ./build/reports/tests/test/index.html
<br>

- Execute mutation tests
- ``` cd insurance-service ```
- ``` ./gradlew pitest ```
- Report at ./build/reports/pitest/index.html
<br>

- Build project
- ``` cd insurance-service ```
- ``` ./gradlew clean build ```
<br>

- Run project 
- ``` docker-compose build ```
- ``` docker-compose up ```
<br>

- Execute integration tests
- ``` cd insurance-integration-tests ```
- ``` ./gradlew clean test ``` 
- Report at ./build/reports/tests/test/index.html
<br>

## Technologies
* **SpringBoot Webflux**: SpringBoot reactive framework to increase I/O performance. 
* **JUnit 5**: new version of test framework, has some new features to help at unit tests
* **Pitest**: dependency to use at mutation tests
* **Karate Framework**: BDD tests framework, abstration to help with integration tests. [Docs](https://karatelabs.github.io/karate/)
* **Lombok**: development plugin
* **Swagger**: endpoints documentation
* **Docker**: tool used to create the enviroment of the solution with all infraestructure dependencies 
* **Docker Compose**: containers orchestration