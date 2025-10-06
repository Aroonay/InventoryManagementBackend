# Inventory Management System

A **microservices-based Inventory Management System** built with **Spring Boot**.  
The system consists of multiple services including Product, Category, Inventory, Notification, API Gateway, and Service Registry (Eureka).  

This project demonstrates a real-world event driven microservices architecture with **service discovery, RESTful APIs, Kafka messaging, and independent deployment**.

---

## **Project Structure**

- **ServiceRegistry** – Eureka Server for service discovery  
- **ProductService** – Manages products  
- **CategoryService** – Manages product categories  
- **InventoryService** – Tracks stock levels  
- **NotificationService** – Sends alerts and notifications  
- **APIGateway** – Single entry point for client requests  

---

## **Prerequisites**

Before running the project, make sure you have:

- **Java JDK 17+** (or 21) installed  
- **Apache Maven 3.9+** installed and added to PATH  
- **MySQL Server** installed and running  
- **Docker Desktop** installed and running (to ensure Docker Engine is active)  
- An IDE such as **IntelliJ IDEA** or **Eclipse**  
- **Postman** (optional, for testing APIs)  

---

## **Download and Setup**

1. **Clone the repository**

  git clone https://github.com/Aroonay/InventoryManagementBackend.git
  

2. **Configure Database**

  Create a database for the project:
  
  CREATE DATABASE inventory_db;

  Update application.yml in each service with your MySQL credentials:

  spring:
    datasource:
      url: jdbc:mysql://localhost:3306/inventory_db
      username: root
      password: <your-password>
    jpa:
      hibernate:
        ddl-auto: update
      show-sql: true
      

3. **Start kafka and zookeper**
    Download docker desktop and then run
    docker-compose up -d

4. **OpenIntellij**
    Step-by-Step: Run each microservice with mvnw

    Open terminal in your project root (InventoryManagement).

    Run each microservice one by one:

      # Service Registry (Eureka)
      cd ServiceRegistry
      ./mvnw spring-boot:run
      # leave this running or open a new terminal for the next service
      
      # Product Service
      cd ../ProductService
      ./mvnw spring-boot:run
      
      # Category Service
      cd ../CategoryService
      ./mvnw spring-boot:run
      
      # Inventory Service
      cd ../InventoryService
      ./mvnw spring-boot:run
      
      # Notification Service
      cd ../NotificationService
      ./mvnw spring-boot:run
      
      # API Gateway
      cd ../APIGateway
      ./mvnw spring-boot:run
   
6. **Usage**

  a. Access Eureka Service Registry: http://localhost:<ServiceRegistry-port>/
  
  b. API Gateway URL: http://localhost:<APIGateway-port>/
  
  c. Test APIs using Postman or any REST client.


      


