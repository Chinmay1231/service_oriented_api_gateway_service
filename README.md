# api-gateway

## Introduction
The api-gateway service is used to serve as single-entry point to access all backend microservices. It routes incoming requests to individual microservices based on URL path. For example,
1. http://localhost:8090/auth will route to auth-service running on http://localhost:8091/auth

The api-gateway provide basic security mechanism. This security is based on JWT token based authentication. The requests which has 'Authorization' header with valid JWT token is forwarded to respective microservice. 

The api-gateway also allows to configure endpoint for backend microservice. This allows api-gateway to route request to appropriate backend microservice.

## How to run?
### Pre-requisite: Install Java 17. Set java in path variable to allow “java” command to be executed from any folder.
1.	Checkout api-gateway code
2.	Open terminal window and change directory to <checkout-dir>/api-gateway/database
3.	Run batch file “h2-server” to start H2 database.
    -	To access database open URL http://localhost:8082. This will bring login screen.
    -	On login screen fill following details
        - Saved Settings: Generic H2 (Server)
        - Driver Class: org.h2.Driver
        - JDBC URL: jdbc:h2:tcp://localhost:9092/~/API_GATEWAY_DB
        - Username: sa
        - Password: sa
    - Click “Connect” button.
4.	Open another terminal window and change directory to <checkout-dir>/api-gateway/executable.
5.	Run batch file “run-service” to run “api-gateway” service. This will run api-gateway on port 8090.

## How to manage route details?
### Pre-requisite: Install Postman or SOAP-UI tool to fire HTTP requests with POST, PUT, DELETE methods.

1. **List all routes**
   ```
   GET http://localhost:8090/routes
   ```
2. **Create or update route**
   ```
   POST http://localhost:8090/routes
   {
    	"name": "customer-service",
    	"uri": "http://localhost:8080/",
    	"path": "/customers/**"
   }
   ```
 3. **Delete route**
    ```
    DELETE http://localhost:8090/routes/1
    ```
    Note: Route id is specified in URL itself.
    
## How to invoke backend microservice through API Gateway?
Suppose we have customer service running at **http://localhost:8080/customer/** and authentication service running on **http://localhost:8091/auth/**.
First step is to register endpoints of these microservices with api-gateway. So we will use following REST request to register endpoints.
1. **Register endpoint for auth service**
   Note: The auth service has two endpoints - /auth and /users.
   POST http://localhost:8090/auth
   {
    	"name": "auth-service",
    	"uri": "http://localhost:8091/",
    	"path": "/auth/**"
   }

   POST http://localhost:8090/users
   {
    	"name": "auth-service-users",
    	"uri": "http://localhost:8091/",
    	"path": "/users/**"
   }

3. **Create user account to access backend microservices**
    ```
    POST http://localhost:8090/users
    {
      "firstName": "Chinmay",
      "lastName": "Ramdasi",
      "email": "2022mt93008@wilp.bits-pilani.ac.in",
      "username": "chinmay1231",
      "password": "pass123",
      "role": "user",
      "enabled": true
    }
    ```
4. **Generate token**
   ```
   POST http://localhost:8090/auth
   {
      "username": "chinmay1231",
      "password": "pass123"
   }
   ```
   Note: This is only request that api-gateway forwards with JWT token added to 'Authorization' header.
   The response is JWT token received in body. Copy it to set in header in following requests.
   
   **Sample JWT Token:**
   
eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdE5hbWUiOiJBZG1pbiIsImxhc3ROYW1lIjoiQWRtaW4iLCJyb2xlIjoicm9sZSIsInVzZXJJZCI6IjIiLCJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTkxMDA3NiwiZXhwIjoxNjk1OTEwNjc2fQ.DoBVuOfElig4Le12Z85BZ_UVdV07KwRdShvf7oKZpUU

5. **Access backend microservice with JWT token**
   We will fire request to list all customers. If this request is without JWT token, you will receive error message saying 'Authorization header is missing'.
   
   
