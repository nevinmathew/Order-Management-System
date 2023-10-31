# Order-Management-System

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

## Table of Contents
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Configuration](#configuration)

## Getting Started
To run the Order Management System, build and run the Spring Boot application using Maven:
```sh
mvn spring-boot:run
```

## Usage
The Order Management System offers the following functionalities:

  ● Create customers.

  ● Customers can create orders. It is assumed that the customer has already made the payment while creating the order.

  ● Customers are categorised as regular, gold, platinum.

  ● By default, a customer is regular.
  
    ○ Customer is promoted to gold when he has placed 10 orders
    
    ○ Customer is promoted to platinum when he has placed 20 orders

  ● Gold tier customers get 10% discount, platinum tier customers get 20% discount.

  ● When a customer creates an order, if he is a gold customer, an automatic 10% discount is applied on the order. 20% for platinum customers.

  ● Since it is assumed that the customer has already made the full payment during creation of the order, this discount information has to be     kept safe by the application. We need to keep track of how much discount is given to which customer and for which order, so that customers      can claim money back later.

  ● Cron job to send emails to customers when they are approaching the gold/platinum barriers.
  
  ● Utilized the math library for calculations related to payment in the service logic. 

  ● Swagger UI for  API documentation and designing. Link: http://localhost:8080/swagger-ui/index.html
  
  ● Spring boot actuator for monitoring, gathering metrics, understanding traffic, and know the state of our database
 
### Example API Documentation:

    Swagger UI for API documentation and designing is available at http://localhost:8080/swagger-ui/index.html.

## Configuration
### `ApplicationStartup` Configuration

The ApplicationStartup configuration provides information about the network availability of your application. It prints the application's network access URL when the application starts.

### `AsyncConfiguration` Configuration

The AsyncConfiguration configuration sets up an executor for handling asynchronous tasks in the application. It specifies the core pool size, maximum pool size, and other settings for handling asynchronous tasks.

### `OpenAPIConfiguration` Configuration

The OpenAPIConfiguration configuration is used to customize the OpenAPI documentation for your application. It sets the application's title, version, description, terms of service, and licensing information for the Swagger API documentation.

These configurations are important for the proper functioning and documentation of your application. Make sure to include them in your project and customize them according to your needs.
