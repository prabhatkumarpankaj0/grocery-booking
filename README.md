# Grocery Booking

The Grocery Booking is a Java Spring Boot application designed to manage grocery items and allow users to book groceries. It provides two roles: Admin and User.

## Features
### Admin Responsibilities
* Add / Update Grocery Items
* View Grocery Items
* Remove Grocery Items
* Manage Inventory Level: Update the stock levels of grocery items.

### User Responsibilities
* View the list of available grocery items
* Ability to book multiple grocery items in a single order

## Technology Stack
* Backend: Java Spring Boot
* Database: H2 Database (in-memory relational database)
* API Testing: Postman

## Steps to Run the Application
* Clone the repository
* Build the project: mvn clean install
* Run the application: mvn spring-boot:run

### H2 Database Access
When you run the application, the H2 console is accessible at:
URL: http://localhost:8080/h2-console
* Username: sa
* Password: (leave blank)
When application starts, it creates two users in the "users" table with User Role: Admin & User.