# UrbanRide

UrbanRide is a backend ride-booking system developed using Java and
Spring Boot. The application provides REST APIs for customer and
captain management and supports the basic ride-booking lifecycle.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- REST API
- Postman

## Architecture

The project follows a layered backend architecture:

Controller → Service → Repository → MySQL Database

### Layers

- **Controller** – Handles REST API requests and responses.
- **Service** – Contains the application's business logic.
- **Repository** – Performs database operations using Spring Data JPA.
- **Model** – Represents database entities and enumerations.

## Features

### User Management

- Create a customer or captain account.
- Prevent duplicate mobile-number registration.
- Update user name.
- Validate user type.

### Captain Management

- Register captain/vehicle details.
- Associate captain details with an existing captain account.
- Store vehicle number, vehicle name and licence number.

### Ride Management

- Request a new ride.
- View available/requested rides.
- Captain can accept a ride.
- Complete an accepted ride.
- Captain can cancel a ride.
- Customer can cancel a ride.
- Prevent a customer from having multiple active rides.

### Ride Status

A ride can have the following statuses:

- `requested`
- `in_progress`
- `completed`
- `cancelled_by_captain`
- `cancelled_by_customer`

## REST API Endpoints

### User APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/user/userdetails` | Create a user |
| PUT | `/user/{id}/name` | Update user name |

### Captain APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/captain/captaindetails/{id}` | Add captain and vehicle details |

### Ride APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/ride/riderequest/{id}` | Create a ride request |
| GET | `/ride/availablerides` | View available rides |
| POST | `/ride/accept/{ride_id}/{captain_id}` | Accept a ride |
| POST | `/ride/complete/{rideId}/{captainId}` | Complete a ride |
| POST | `/ride/cancel/captain/{rideId}/{captainId}` | Cancel ride as captain |
| POST | `/ride/cancel/customer/{rideId}/{customerId}` | Cancel ride as customer |

## Database

UrbanRide uses MySQL with Spring Data JPA/Hibernate.

### Main Tables

- `user_details`
- `captain_details`
- `ride_details`

### User Details

Stores:

- User ID
- Mobile number
- Password
- User type
- Name

### Captain Details

Stores:

- User ID
- Vehicle number
- Vehicle name
- Licence number

### Ride Details

Stores:

- Ride ID
- Customer ID
- Captain ID
- Pickup location
- Drop location
- Fare
- Ride status

## Project Structure

```text
UrbanRide/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── project/ridebooking/urbanride/
│   │   │       ├── UrbanrideApplication.java
│   │   │       ├── usercontroller.java
│   │   │       ├── userservices.java
│   │   │       ├── userrepository.java
│   │   │       ├── captaincontroller.java
│   │   │       ├── captainservices.java
│   │   │       ├── captainrepository.java
│   │   │       ├── ridecontroller.java
│   │   │       ├── rideservices.java
│   │   │       ├── riderepository.java
│   │   │       └── models/
│   │   │           ├── UserDetails.java
│   │   │           ├── CaptainDetails.java
│   │   │           ├── RideDetail.java
│   │   │           ├── status.java
│   │   │           └── userType.java
│   │   │
│   │   └── resources/
│   │       ├── application-example.properties
│   │       ├── static/
│   │       └── templates/
│   │
│   └── test/
│
├── pom.xml
├── mvnw
├── mvnw.cmd
├── functional_requirements.txt
├── technical_requirements.txt
└── .gitignore