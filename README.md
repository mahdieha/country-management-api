# Country Management (Java Spring Boot)

## Description

This is a simple REST API to represent Countries based on density or bordering region. It is built using Java Spring Boot and Swagger.

It uses the [REST Countries API](https://gitlab.com/restcountries/restcountries) project to get the data.

## Table of Contents

  - [Installation](#installation)
    - [Prerequisites](#prerequisites)
    - [Steps](#steps)
  - [Testing](#testing)
  - [Run with Docker-Compose](#run-with-docker-compose)
  - [Usage](#usage)
    - [Swagger](#swagger)
    - [Endpoints](#endpoints)
        - [GET /api/v1/population-density](#get-apiv1population-density)
        - [GET /api/v1/most-bordering/{region}](#get-apiv1most-borderingregion)
  - [License](#license)

## Installation

### Prerequisites

- Java 11
- Maven 3.6.3

### Steps

1. Clone the repository
2. Run `mvn clean install` in the root directory
3. Run `mvn spring-boot:run` in the root directory
4. Open `http://localhost:8080/swagger-ui.html` in your browser

## Testing

1. Clone the repository
2. Run `mvn clean install` in the root directory
3. Run `mvn test` in the root directory

## Run with Docker-Compose

1. Clone the repository
2. Run `docker-compose up --build` in the root directory
4. Open `http://localhost:8080/swagger-ui.html` in your browser

## Usage

### Swagger

Swagger is used to document the API. It can be accessed at `http://localhost:8080/swagger-ui.html`.

### Endpoints

#### GET /api/v1/population-density

Returns sorted list of countries by population density in descending order.

example:

```
curl -X GET "http://localhost:8080/api/v1/population-density" -H "accept: */*"
```

#### GET /api/v1/most-bordering/{region}

Returns the country with the most bordering countries in the given region.

example:

```
curl -X GET "http://localhost:8080/api/v1//most-bordering/asia" -H "accept: */*"
```

## License

[MIT](https://choosealicense.com/licenses/mit/)


