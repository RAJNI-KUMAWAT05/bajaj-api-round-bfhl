# Bajaj Finserv Health REST API (BFHL API Challenge)

A production-ready Spring Boot REST API built for the **Bajaj Finserv Health API Challenge** round.

---

## 👤 Author Details
- **Name:** Rajni Kumawat
- **Enrollment Number:** 0827CI231106
- **Email:** [rajnikumawat230476@acropolis.in](mailto:rajnikumawat230476@acropolis.in)
- **User ID:** `rajni_kumawat_17092003`

---

## 🚀 Live Demo & Deployment
- **Interactive Web UI (Netlify):** [https://bajaj-api-round-bfhl.netlify.app](https://bajaj-api-round-bfhl.netlify.app)
- **Backend API Base URL (Railway):** [https://bajaj-api-round-bfhl-production.up.railway.app](https://bajaj-api-round-bfhl-production.up.railway.app)
- **Direct API Endpoint:** [https://bajaj-api-round-bfhl-production.up.railway.app/bfhl](https://bajaj-api-round-bfhl-production.up.railway.app/bfhl)

---

## 🛠 Tech Stack
- **Language:** Java 17
- **Framework:** Spring Boot 3.2.5 (Spring MVC, Web, Actuator, Validation)
- **Build Tool:** Maven 3.x
- **Architecture:** Multi-layered Enterprise Architecture (Controller -> Service -> DTO)
- **Testing:** JUnit 5, MockMvc, AssertJ
- **Platform/Hosting:** Railway (Public cloud container platform)
- **API Client/Test Suite:** Postman (Collection included in workspace)

---

## 🌟 Features
1. **Separation of Odd and Even Numbers:** Accurately classifies numeric strings as odd or even.
2. **Uppercase Conversion:** Converts all extracted alphabet strings to uppercase.
3. **Special Character Extraction:** Segregates non-alphanumeric special characters.
4. **Sum of Numerical Values:** Calculates the sum of all numeric values present in the input.
5. **Dynamic Concat String Rule:** Concatenates all alphabetical characters, reverses the string, and applies alternating caps (starting with uppercase at index 0).
6. **Mixed Strings Parsing:** Correctly parses mixed tokens (e.g. `"12a"`, `"a$1"`) by breaking them down into constituent elements.
7. **Robust Exception Handling:** Global REST error advice returning standard error payload for invalid input, bad formatting, or server errors.
8. **Automated Testing:** 100% test coverage for success scenarios, edge cases, and custom exceptions.

---

## 📋 API Endpoints

### 1. POST `/bfhl`
Processes the input array and extracts numbers, alphabets, special characters, sums, and formatted concatenated strings.

- **Request Headers:**
  - `Content-Type: application/json`

- **Request Body Format:**
  ```json
  {
    "data": ["a", "1", "334", "4", "R", "$"]
  }
  ```

- **Response Body Format (HTTP 200 OK):**
  ```json
  {
    "is_success": true,
    "user_id": "rajni_kumawat_17092003",
    "email": "rajnikumawat230476@acropolis.in",
    "roll_number": "0827CI231106",
    "odd_numbers": ["1"],
    "even_numbers": ["334", "4"],
    "alphabets": ["A", "R"],
    "special_characters": ["$"],
    "sum": 339,
    "concat_string": "Ra"
  }
  ```

### 2. GET `/bfhl`
Simple health / info query returning a hardcoded operation code response.

- **Response Body Format (HTTP 200 OK):**
  ```json
  {
    "operation_code": 1
  }
  ```

---

## 🏗 Project Directory Structure
```text
C:.
├── pom.xml
├── postman_collection.json
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── bajajfinserv
    │   │           └── bfhl
    │   │               ├── BfhlApiApplication.java
    │   │               ├── config
    │   │               ├── controller
    │   │               │   └── BfhlController.java
    │   │               ├── dto
    │   │               │   ├── request
    │   │               │   │   └── BfhlRequest.java
    │   │               │   └── response
    │   │               │       ├── BfhlResponse.java
    │   │               │       └── ErrorResponse.java
    │   │               ├── exception
    │   │               │   ├── GlobalExceptionHandler.java
    │   │               │   └── InvalidPayloadException.java
    │   │               ├── service
    │   │               │   ├── BfhlService.java
    │   │               │   └── impl
    │   │               │       └── BfhlServiceImpl.java
    │   │               └── util
    │   └── resources
    │       └── application.properties
    └── test
        └── java
            └── com
                └── bajajfinserv
                    └── bfhl
                        ├── BfhlApiApplicationTests.java
                        ├── controller
                        │   └── BfhlControllerIntegrationTest.java
                        └── service
                            └── BfhlServiceTest.java
```

---

## 📇 Exception Handling & Edge Cases
The application leverages `@RestControllerAdvice` in [GlobalExceptionHandler](file:///c:/Users/saiya/Downloads/Rajni_Folder/Bajaj_API_Round/src/main/java/com/bajajfinserv/bfhl/exception/GlobalExceptionHandler.java) to catch:
- `InvalidPayloadException`: Handled with HTTP 400 Bad Request if the request body or list is null.
- `HttpMessageNotReadableException`: Handled with HTTP 400 Bad Request for malformed JSON formatting.
- `Exception`: Generic catch-all returning HTTP 500 Internal Server Error.

All error responses follow this JSON schema:
```json
{
  "is_success": false,
  "error_code": 400,
  "error_message": "Invalid JSON payload format."
}
```

---

## 📥 Postman Workspace Collection
A Postman collection with configured environments, validation test scripts, and test data is available inside [postman_collection.json](file:///c:/Users/saiya/Downloads/Rajni_Folder/Bajaj_API_Round/postman_collection.json).
- **Active Workspace:** `RAJNI KUMAWAT's Workspace` (ID: `f82fd92c-b797-4bcd-8080-bf6304aaba74`)
- **Collection ID:** `aa3879e0-2c69-466e-9e9d-0d615952e886`
- **Environment ID:** `3b110206-6e6b-4cbe-9c52-ac4055a82dcb`

---

## 🔨 Setup & Run Instructions

### Prerequisites
- JDK 17 installed
- Maven 3.x installed

### Local Run Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/RAJNI-KUMAWAT05/bajaj-api-round-bfhl.git
   cd bajaj-api-round-bfhl
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the API locally at: `http://localhost:8080/bfhl`

### Testing Instructions
To execute JUnit tests and integration test suites:
```bash
mvn test
```
Tests check:
- Standard array elements processing (`["a", "1", "334", "4", "R", "$"]`).
- Concat strings and alternating caps logic (`["a", "b", "c", "d"]` -> `"DcBa"`).
- Mixed alphanumeric strings tokenization (`["12a", "a$1", "XYZ"]`).
- Null request bodies and empty list cases.
- Malformed JSON exceptions.
