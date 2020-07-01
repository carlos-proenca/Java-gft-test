# GFT Avaliation Project

In this project, I am demonstrating a Spring Batch configuration to load the initial database and keeping the code easier to extend when a new integration feature is needed, such as a product queue integration or that data coming from the web services integration.

## Getting Started 

To start this project we need to install as pre requirements the following technologies:

- Java 8
- maven
- docker

## The first step is run inside a bash terminal with following command:

_docker-compose up_

## The second step is execute junit tests and compile the code:

_mvn clean install_

## The last step is execute application with:

_mvn spring-boot:run_

## To call the Order Preview API we can execute the GET HTTP operation as following:

_curl --request GET 'http://localhost:8090/api/orders/preview?quantityShopkeeper=3&productName=EMMS'_

