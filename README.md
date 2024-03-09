# Spring Boot Application of  Ticket Management Application with MongoDB CRUD Operations

## Overview
This is a Spring Boot application for Ticket management, utilizing MongoDB for data storage. The application supports basic CRUD (Create, Read, Update, Delete) operations for managing product information.

## Sequence Diagram
![Alt Text](seqDiagram.png)

## Technologies Used
- **Spring Boot:** Framework for creating Java-based, production-grade Spring-based applications.
- **MongoDB:** A NoSQL database for storing product data.
- **Spring Security:** Provides authentication and access control.
- **Postman:** API development and testing tool for interacting with the Spring Boot application.



## Project Structure
- **Controller:** Handles incoming HTTP requests, manages product-related endpoints.
- **Service:** Contains business logic for product operations.
- **Entity:** Represents the product entity.

## Controller

### 1. Create Ticket
**Endpoint:** `POST /ticket`
- **Description:** Creates one or more tickets.
- **Access:** Requires USER role.
- **Request Body:** List of tickets.
- **Response:** ApiResponse containing the saved tickets.

### 2. Update Ticket
**Endpoint:** `PUT /ticket`
- **Description:** Updates ticket details by ID.
- **Request Parameters:** `id` - Ticket ID to update.
- **Request Body:** Ticket details to update.
- **Response:** ApiResponse containing the updated ticket.

### 3. Delete Ticket
**Endpoint:** `DELETE /ticket`
- **Description:** Deletes multiple tickets by their IDs.
- **Request Body:** List of ticket IDs to delete.
- **Response:** Success message or an error message.

### 4. Get All Tickets
**Endpoint:** `GET /ticket`
- **Description:** Retrieves a paginated list of tickets with optional filters and sorting.
- **Request Parameters:**
    - `filters` - Optional filters for ticket attributes.
    - `page` - Page number.
    - `size` - Number of items per page.
    - `sorting` - Attribute to sort by.
    - `sortDirection` - Sorting direction (asc/desc).
- **Response:** ApiResponse containing a paginated list of tickets.

## Security
- **Endpoint Security:** The `/ticket` endpoints require the USER role for access.

## Logging
- **Logger:** SLF4J is used for logging. Logs are generated for errors and other relevant events.

## Exception Handling
- **Error Handling:** Custom error messages are provided for different scenarios.

## How to Run
1. **Configure MongoDB Connection:**
- Open `src/main/resources/application.properties`.
    - Update the MongoDB connection properties based on your MongoDB server configuration.

2. **Build and Run the Spring Boot Application:**
- Run that application using the IDE `Intelij idea`
3. **Access the API Using Postman:**
- Open [Postman](https://www.postman.com/) or download and install it if you haven't.
    - Use the following endpoints to interact with the API:
        - **Create Ticket (POST):**
- Endpoint: `http://localhost:8081/tickets`
  - Method: `POST`
  - Headers:
  - `Content-Type: application/json`
  - `Authorization: Basic Auth`
  - Body: JSON array of tickets.

    - **Update Product (PUT):**
- Endpoint: `http://localhost:8081/ticket?id=TICKET_ID`
  - Method: `PUT`
  - Headers:
  - `Content-Type: application/json`
  - `Authorization: Bearer YOUR_ACCESS_TOKEN` (if using OAuth2)
  - Body: JSON object with updated ticket details.

    - **Delete Tickets (DELETE):**
- Endpoint: `http://localhost:8081/ticket`
  - Method: `DELETE`
  - Headers:
  - `Content-Type: application/json`
  - `Authorization: Bearer YOUR_ACCESS_TOKEN` (if using OAuth2)
  - Body: JSON array of ticket IDs to delete.

    - **Get All Tickets (GET):**
- Endpoint: `http://localhost:8081/ticket`
  - Method: `GET`
  - Headers:
  - `Authorization: Bearer YOUR_ACCESS_TOKEN` (if using OAuth2)
  - Query Parameters (Optional):
  - `filters`: JSON object for optional filters.
  - `page`: Page number.
  - `size`: Number of items per page.
  - `sorting`: Attribute to sort by.
  - `sortDirection`: Sorting direction (asc/desc).




## Notes
- Make sure to handle security configurations and roles appropriately.
- Adjust MongoDB connection details based on your environment.

Feel free to customize and extend this project to fit your specific requirements.
 