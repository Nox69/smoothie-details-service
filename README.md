## Introduction

This is the details Service for Smoothie Application

## The Smoothie Detail Service

The service has a few requirements that is been implemented. It has the JWT Filter on Generic level which parses and validates the JWT Authentication header passed and verifies the signature as well.

### 1. Create Smoothie 
Create Smoothie with the request which is passed. Only Business owners are allowed to create Smoothies. So functional check on role is done and Smoothie is persisted in DB

###  2. Retrieve Smoothie
Retrieve Smoothie works in two different ways. If loggedin User is business-owner he/she views smoothies he has created only as it's used for managing purpose.
if loggedinUser is end-user he/she can view all smoothies available so that they can order it right away.

###  3. Update Smoothie
Update Smoothies is executed by business-owner role also. Based on the JWT Token provided in header the role is identified and updates the Database accordingly.

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

Run the MongoDB database using the command below: (Docker must be installed)

```sh
docker-compose up
```

Import the service in your favourite IDE, i.e. IntelliJ IDEA and execute the main class `com.nox.SmoothieServiceApplication` to start the service.

## Running the service without an IDE

Clone the service to your computer using the command below:

```sh
git clone https://github.com/Nox69/smoothie-details-service.git
cd smoothie-details-service
```

Start/update the service using the command below:

```sh
docker-compose up
mvn clean -DskipTests=true install
docker build -t smoothie-details-service .
docker run -p 8085:8085 smoothie-details-service
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

Run the following cURL command to create a new smoothie in the service.Only business-owner can create

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

Run the following cURL command to retrieve all smoothies for business-owner or end-user.

```sh
curl -X GET \
    -H "Content-Type: application/json"
    -H "Authorization: Bearer {token}" \
    	http://localhost:8085/v1/api/smoothies -v
```

Run the following cURL command to update an existing smoothie. Only business-owner can update

```sh
curl -X PUT \
    -H "Content-Type: application/json"
    -H "Authorization: Bearer {token}" \
 	-d '{
	    "smoothieId": "320f6443-a897-4c0f-9bdd-207f216cf4b1",
	    "smoothieIngredients": "DAMN SURE IT AWESOME",
	    "smoothieNutritionValue": 22.11,
	    "smoothiePrice": 100.98
    }' http://localhost:8085/v1/api/smoothie -v
```