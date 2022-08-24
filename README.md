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