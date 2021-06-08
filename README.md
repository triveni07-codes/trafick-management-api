# Trafik-management-api

- functionality to get top 10 buslines having maximum number of bus stops 
  by including to and from direction. ✨ ✨

## Features
- Get list of top 10 bus lines with maximum number of bus stop on route including return path.

This application holds backend part only: written using Java and Spring boot framework mainly.

## Tech
- [Spring boot] - Backend service.
- [Swagger ui] - A super cool UI for backend endpoints testing or interaction.

## Installation

Backend requires Java SDK 11, Maven build tool version 3.6.2 to build and run.

Install the dependencies and devDependencies and start the server.
Replace value of ${API_KEY} in local-properties file with YOUR_API_KEY value

```sh
cd trafick-management-api
mvn clean install
mvn sping-boot:run
```

Once we start both the applications locally, backend is available on [http://localhost:8080/swagger-ui.html]

