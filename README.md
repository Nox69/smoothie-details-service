## Introduction

This is the details Service for Smoothie Application

## The Smoothie Detail Service

The service has a few requirements that is been implemented. It has the JWT Filter on Generic level which parses and validates the JWT Authentication header passed and verifies the signature as well.

### 1. Create Smoothie 
Create Smoothie with minimum details and added to the database by adminId. If no JWT token passed or Invalid JWT token passed, the service stops execution.

###  2. Retrieve Smoothie
Retrieve Smoothies based on the admin Id. If no JWT token passed or Invalid JWT token passed, the service stops execution.

## Technical Details

The service is written in Java and uses Spring Boot as an underlying framework. 
I used MongoDB to manage database migrations and [JPA].

### Code Structure

All API endpoints are located in `com.nox.controller` package. Open API.yml to be added soon.
Functionalities to be updated and Junit Test to be added.

## Running the service with an IDE (i.e. IntelliJ IDEA, STS Eclipse)

Clone the service to your computer using the command below:
```sh
git clone https://github.com/Nox69/smoothie-details-service.git
cd smoothie-details-service
```

Run the Postgres database using the command below: (Docker must be installed)
```sh
docker-compose up
```

Import the service in your favourite IDE, i.e. IntelliJ IDEA and execute the main class `com.nox.SmoothieServiceApplication` to start the service.

## Running the service without an IDE

Clone the service to your computer using the command below:
```sh
git clone https://github.com/Nox69/smoothie-authentication-service.git
cd smoothie-details-service
```

Start/update the service using the command below:

```sh
./ docker-compose up -d --build
mvn clean install
java -jar app.jar
```

Stop the service using the command below:

```sh
docker-compose down
```

Watch the application logs using the command below

```sh
docker logs smoothie-service -f
```

## Interacting with the API

### Smoothie Detail endpoints 

Run the following cURL command to create a new smoothie in the service.

```sh
curl -X POST \
    -H "Content-Type: application/json"
    -H "Authorization: Bearer {token}" \
    -d '{
    	"smoothieName": "Orange Protien Smoothie",
    	"smoothieIngredients": "Orange, Milk, Greek Yoghurt, Strawberry Icecream",
    	"smoothieNutritionValue": "194.90" ,
    	"smoothiePrice": "40.01"
    }' http://localhost:8085/v1/api/smoothie -v
```

Run the following cURL command to retrieve all smoothies.

```sh
curl -X GET \
    -H "Content-Type: application/json"
    -H "Authorization: Bearer {token}" \
    	http://localhost:8085/v1/api/smoothies -v
```