<h1> retail-rwd-custom-calc</h1>

Simple custom reward calculator application implemented using 
- [Spring Boot](http://projects.spring.io/spring-boot/)
- H2 In-memory database 
- Java 8 
- Lombok and some third party libraries

## Features
Application would help calculate the rewards for each customer in accordance with their transaction activity.

The application consists of 3 GET endpoints 

~~~~
/Rewards
~~~~
This GET endpoint would return all the rewards details along with the customer details for each customer.
~~~~
/Rewards/{id}
~~~~
This GET endpoint would return the specific customer details along with the rewards calculated for a given customer.
This endpoint would throw exception when user is not found
~~~~
/transactions
~~~~
This GET endpoint would return the transactions of all customers


## Requirements

For building and running the application you need:

- [JDK 1.17](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## To run tests
The application contains test suite with unit, Integration and acceptance tests
~~~~
mvn test
~~~~

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.example.retailrwdcustomcalc.RetailRwdCustomCalcApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
<br>Application will run by default on port : 8080

## Testing application in local
Using the port 8080, calling the 3 endpoints spring-boot application would return responses accordingly.

<br>Sample request for rewards for all customers
~~~~
http://localhost:8080/Rewards
~~~~
Sample response
```javascript
[ {
  "id" : 1,
  "name" : "Jack",
  "rewards" : 24,
  "transactions" : [ {
    "id" : 1,
    "transactionTotal" : 74.00,
    "transactionDate" : "13-12-2022"
  }, .... ]
}, ..... ]
```

<br>Sample request for rewards for a specific customer
~~~~
http://localhost:8080/Rewards/{id}
~~~~

Sample response 
```javascript
{
  "id" : 1,
  "name" : "Jack",
  "rewards" : 24,
  "transactions" : [ {
    "id" : 1,
    "transactionTotal" : 74.00,
    "transactionDate" : "13-12-2022"
  }, ... ]
}
```
<br>Sample request to get all transactions
~~~~
http://localhost:8080/transactions
~~~~
Sample response
```javascript
[ {
  "id" : 1,
  "transactionTotal" : 74.00,
  "transactionDate" : "13-12-2022"
}, {
  "id" : 2,
  "transactionTotal" : 74.00,
  "transactionDate" : "20-08-2022"
}, {
  "id" : 3,
  "transactionTotal" : 42.00,
  "transactionDate" : "04-01-2022"
}, ... ]
```

