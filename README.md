
# JV Car Sharing Service

The **JV Car Sharing Service** is a web-based application designed to manage a car-sharing service, including inventory management, car rentals, customer management, and payment handling. The system is built to be scalable and secure, supporting both administrators and customers.

## Functional Requirements

### 1. Web-Based Application
- The application will be accessible through a web interface, allowing users to manage car sharing and rental activities.

### 2. Manage Car Sharing Inventory
- Administrators can add, update, delete, and view details of cars available in the inventory.
- Cars have attributes such as model, brand, type, available inventory, and daily fee.

### 3. Manage Car Rentals
- Users (customers) can rent cars from the available inventory.
- Administrators can view and manage all rentals.
- The system will track rental start and return dates, and calculate the total fee.

### 4. Manage Customers
- Users can register and update their profiles.
- Administrators can assign roles (Manager or Customer) to users.

### 5. Display Notifications
- Notifications will be sent for new rentals, overdue returns, and successful payments.
- Notifications will be sent to administrators via Telegram.

### 6. Handle Payments
- The application will integrate with the Stripe API for secure payment processing.
- Payments will be tracked, and users can view their payment history.

## Architecture Overview

The **JV Car Sharing Service** is built using a microservices architecture with the following components:

- **Authentication Service:** Handles user registration, login, and JWT token generation.
- **User Management Service:** Manages user profiles and roles.
- **Car Inventory Service:** Manages CRUD operations for cars.
- **Rental Management Service:** Handles car rentals, including tracking rental status and managing inventory counts.
- **Payment Service:** Integrates with Stripe for payment processing.
- **Notification Service:** Sends notifications via Telegram to administrators.

## Data Models

### 1. Car
- **Model:** String
- **Brand:** String
- **Type:** Enum: SEDAN | SUV | HATCHBACK | UNIVERSAL
- **Inventory:** int (number of cars available)
- **Daily Fee:** decimal (in $USD)

### 2. User (Customer)
- **Email:** String
- **First Name:** String
- **Last Name:** String
- **Password:** String
- **Role:** Enum: MANAGER | CUSTOMER

### 3. Rental
- **Rental Date:** LocalDate
- **Return Date:** LocalDate
- **Actual Return Date:** LocalDate
- **Car ID:** Long
- **User ID:** Long

### 4. Payment
- **Status:** Enum: PENDING | PAID
- **Type:** Enum: PAYMENT | FINE
- **Rental ID:** Long
- **Session URL:** String (URL for the payment session with Stripe)
- **Session ID:** String (ID of the payment session)
- **Amount to Pay:** decimal (in $USD)

## API Controllers

### 1. Authentication Controller
- **POST** `/register` - Register a new user.
- **POST** `/login` - Authenticate a user and obtain JWT tokens.

### 2. Users Controller
- **PUT** `/users/{id}/role` - Update a user's role.
- **GET** `/users/me` - Retrieve the authenticated user's profile information.
- **PUT/PATCH** `/users/me` - Update the authenticated user's profile information.

### 3. Cars Controller
- **POST** `/cars` - Add a new car (MANAGER only).
- **GET** `/cars` - Retrieve a list of available cars.
- **GET** `/cars/{id}` - Get detailed information about a specific car.
- **PUT/PATCH** `/cars/{id}` - Update information about a specific car (MANAGER only).
- **DELETE** `/cars/{id}` - Delete a specific car (MANAGER only).

### 4. Rentals Controller
- **POST** `/rentals` - Create a new rental and decrease car inventory by 1.
- **GET** `/rentals` - Retrieve rentals based on user ID and rental status.
- **GET** `/rentals/{id}` - Retrieve details of a specific rental.
- **POST** `/rentals/{id}/return` - Mark a rental as returned and increase car inventory by 1.

### 5. Payments Controller
- **GET** `/payments` - Retrieve payments based on user ID.
- **POST** `/payments` - Create a new payment session.
- **GET** `/payments/success/{sessionId}` - Confirm successful payment.
- **GET** `/payments/cancel/{sessionId}` - Handle payment cancellation.

### 6. Notifications Service
- Integrates with the Telegram API to send notifications about new rentals, overdue rentals, and successful payments.
