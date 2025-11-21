# Travel Booking Service

This project is a **Spring Boot microservice** for managing travel bookings and payment events.

Base URL: `http://localhost:8080`

---

## 🏃 How to Run the Application

### Prerequisites
- Java 17+
- Maven 3.8+

### Build & Run

#### Using Maven
```bash
mvn clean package
java -jar target/travel-booking-service-0.0.1-SNAPSHOT.jar
````


## 🔧 API Endpoints & Example Curl Commands

### 1. Webhook: Receive Payment Event

**POST** `/webhooks/payments`

**Headers:**

```
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
```

**Request Body (`PaymentEventDTO`):**

```json
{
  "transactionId": "uuid",
  "bookingId": "uuid",
  "amountPaid": 1000.0,
  "paymentTime": "2025-11-21T12:00:00Z"
}
```

**Curl Example:**

```bash
curl -X POST http://localhost:8080/webhooks/payments \
-H "Authorization: Bearer <JWT_TOKEN>" \
-H "Content-Type: application/json" \
-d '{
  "transactionId": "a1b2c3d4-e5f6-7890-abcd-1234567890ef",
  "bookingId": "b1c2d3e4-f5a6-7890-bcde-0987654321fe",
  "amountPaid": 1000,
  "paymentTime": "2025-11-21T12:00:00Z"
}'
```

---

### 2. Get All Bookings

**GET** `/api/bookings`

**Curl Example:**

```bash
curl -X GET http://localhost:8080/api/bookings
```

**Response (`BookingResponse[]`):**

```json
[
  {
    "bookingId": "uuid",
    "customerName": "John Doe",
    "destination": "Paris",
    "amountDue": 1200.0,
    "paymentStatus": "PAID"
  }
]
```

---

### 3. Create Booking

**POST** `/api/bookings`

**Request Body (`CreateBookingRequest`):**

```json
{
  "customerName": "John Doe",
  "destination": "Paris",
  "amountDue": 1200.0
}
```

**Curl Example:**

```bash
curl -X POST http://localhost:8080/api/bookings \
-H "Content-Type: application/json" \
-d '{
  "customerName": "John Doe",
  "destination": "Paris",
  "amountDue": 1200
}'
```

**Response (`BookingResponse`):**

```json
{
  "bookingId": "uuid",
  "customerName": "John Doe",
  "destination": "Paris",
  "amountDue": 1200.0,
  "paymentStatus": "PENDING"
}
```

---

### 4. Get Booking by ID

**GET** `/api/bookings/{bookingId}`

**Path Parameter:**

```
bookingId: UUID of the booking
```

**Curl Example:**

```bash
curl -X GET http://localhost:8080/api/bookings/<bookingId>
```

---

### 5. Get Payment Events for Booking

**GET** `/api/bookings/{bookingId}/payments`

**Path Parameter:**

```
bookingId: UUID of the booking
```

**Curl Example:**

```bash
curl -X GET http://localhost:8080/api/bookings/<bookingId>/payments
```

**Response (`PaymentEventDTO[]`):**

```json
[
  {
    "transactionId": "uuid",
    "bookingId": "uuid",
    "amountPaid": 1000.0,
    "paymentTime": "2025-11-21T12:00:00Z"
  }
]
```

---

## 🔹 Notes

* Replace `<JWT_TOKEN>` with a valid token.
* All endpoints requiring authentication must include the `Authorization` header.
* All timestamps use ISO 8601 format.

---

## 📄 Full API Reference

For the complete API schema, see [openapi.json](./openapi.json) or open Swagger UI at:

```
http://localhost:8080/swagger-ui.html

