# The-Pillpoint-Java

# Pillpoint
### Technologies planning to use

### The structure

### we will be also planning to give an updates page which will basically say about the versions and their releases like the one for gemini ai
# The evolution of Idea
- https://www.indiatoday.in/india/story/no-medicine-shortage-patients-last-stage-maharashtra-nanded-government-hospital-deaths-2443689-2023-10-03
- https://www.indiaspend.com/more-indians-die-of-poor-quality-care-than-due-to-lack-of-access-to-healthcare-1-6-million-64432/
- https://www.biospectrumindia.com/news/17/14709/27-deaths-in-india-caused-by-poor-access-to-drugs-and-knowledge-medicus-.html

These where the main reasons which paved for the development of this project

# The concpet
- We are planning to create a software , that is web or web app inorder to monitor the stock of medicines in the pharmacy of a particular hospital .
- How the medicines are supplied to a hospital form a particular manufacturer -- The medical represnetative from a particular company visits the senior doctor of a hospital and introduces about his/her medicine and provides sample to doctor inorder to test it out . The doctor tests it and if he likes he can give further order of these medicines to the hospital to that particular manufacturer or company . So if a medicine is out of stock then or if the medicine has very less amnount in stock , then it is the duty of the pharmacy team to order that medicine manually .
- The main problem which arises is when the hospitals do not make the order of an adequate amount of medicine for them , that is inorder to save money the do not order every medicine and if medicines are not relevant at a current time for example chicken pox , dengue fever etc , they are not gonna order it until a case gets reported on their hospitals .
- So what our software does is , we are gonnna keep track of each and every medicine in a pharmcacy of each and every individual hospitals and then demand that there should a threshold amount or minimum amount of medicine requeired in a hospital , we can make that mandatory . And whenever the amount of medicine goes below out of stock then a request will be sent automatically to their manufactures and can send confirmation order for their request . And in case if any hospital doesnot accpet the confirmation order or refuese to keep the particular amount in their inventory then strict actions will be taken from the govt side . That is we can make this even a criteria for maintaing the license of hopstials . 

#### Block diagram
![BlockDiagram](IMG/blockdiagram0.png)

#### Use case diagram
![USE-CASE](IMG/Usecase.png)

## 🩺 PillPoint – Centralized Medicine Stock Monitoring System

### 💡 Concept
**PillPoint** is a web-based platform designed to monitor and manage the inventory of medicines in hospital pharmacies. It ensures that every hospital maintains a minimum stock level of essential medicines, and automates the restocking process by notifying suppliers when the inventory falls below set thresholds.

### 🏥 Current Problem
1. Medicine ordering is currently **manual** and **reactive**.
2. Hospitals avoid ordering certain seasonal/disease-specific medicines (e.g., for dengue, chickenpox) unless a case arises—leading to delays in treatment.
3. To cut costs, pharmacies sometimes **understock**, compromising preparedness.
4. No strict regulation or tracking on inventory levels by health authorities.

### ✅ Solution by PillPoint
- Track inventory levels **in real-time** for each hospital.
- Enforce a **minimum stock threshold** for each medicine.
- Automatically generate restocking **requests to suppliers** when stock is low.
- Alert admins (government agencies) about non-compliant hospitals.
- Enable strict actions if stock requirements are ignored—possibly affecting hospital licensing.

---

### 🧠 System Roles & Functionalities

#### 1. **Admin (Government Health Agency)**
- Monitor inventory across all hospitals.
- Set or update **threshold quantities** for each medicine.
- Issue **warnings**, track repeated violations, and take necessary action.
- Audit medicine availability for license compliance.

#### 2. **Pharmacy (Hospital Pharmacy)**
- **CRUD operations** on medicine inventory.
- View stock alerts and **respond to supplier restock requests**.
- Acknowledge or reject restock notifications (with valid reasons).

#### 3. **Supplier (Pharmaceutical Company)**
- Get notified when a pharmacy's stock is low.
- View and respond to medicine orders.
- Track demand trends from different hospitals.

---

### 🧑‍💻 User Requirements

- **Hospitals:**
    - Registers their hospital and pharmacy.
    - Regularly updates stock information for each medicine.

- **Supplier:**
    - Registers their company and lists medicines supplied.
    - Receives automatic low-stock alerts.
    - Fulfills or negotiates restocking requests.

- **Admin:**
    - Manages thresholds for critical medicines.
    - Oversees all pharmacy inventories.
    - Initiates audits and penal actions for consistent understocking.


### Microservice Modules
- Hospital service module - create/update/delete/view list of all hospitals

pillpoint-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── pillpoint/
│   │   │           ├── PillPointApplication.java
│   │   │           ├── config/
│   │   │           │   ├── SecurityConfig.java
│   │   │           │   ├── JwtConfig.java
│   │   │           │   └── WebConfig.java
│   │   │           ├── controller/
│   │   │           │   ├── AuthController.java
│   │   │           │   ├── HospitalController.java
│   │   │           │   ├── SupplierController.java
│   │   │           │   ├── MedicineController.java
│   │   │           │   ├── InventoryController.java
│   │   │           │   ├── OrderController.java
│   │   │           │   └── ReportController.java
│   │   │           ├── model/
│   │   │           │   ├── User.java
│   │   │           │   ├── Hospital.java
│   │   │           │   ├── Supplier.java
│   │   │           │   ├── Medicine.java
│   │   │           │   ├── Inventory.java
│   │   │           │   └── Order.java
│   │   │           ├── repository/
│   │   │           │   ├── UserRepository.java
│   │   │           │   ├── HospitalRepository.java
│   │   │           │   ├── SupplierRepository.java
│   │   │           │   ├── MedicineRepository.java
│   │   │           │   ├── InventoryRepository.java
│   │   │           │   └── OrderRepository.java
│   │   │           ├── service/
│   │   │           │   ├── AuthService.java
│   │   │           │   ├── HospitalService.java
│   │   │           │   ├── SupplierService.java
│   │   │           │   ├── MedicineService.java
│   │   │           │   ├── InventoryService.java
│   │   │           │   ├── OrderService.java
│   │   │           │   └── ReportService.java
│   │   │           ├── dto/
│   │   │           │   ├── request/
│   │   │           │   │   ├── LoginRequest.java
│   │   │           │   │   ├── RegisterRequest.java
│   │   │           │   │   ├── HospitalRequest.java
│   │   │           │   │   ├── SupplierRequest.java
│   │   │           │   │   ├── MedicineRequest.java
│   │   │           │   │   ├── InventoryRequest.java
│   │   │           │   │   └── OrderRequest.java
│   │   │           │   └── response/
│   │   │           │       ├── AuthResponse.java
│   │   │           │       ├── HospitalResponse.java
│   │   │           │       ├── SupplierResponse.java
│   │   │           │       ├── MedicineResponse.java
│   │   │           │       ├── InventoryResponse.java
│   │   │           │       ├── OrderResponse.java
│   │   │           │       └── ReportResponse.java
│   │   │           ├── exception/
│   │   │           │   ├── GlobalExceptionHandler.java
│   │   │           │   ├── ResourceNotFoundException.java
│   │   │           │   ├── UnauthorizedException.java
│   │   │           │   └── BadRequestException.java
│   │   │           └── util/
│   │   │               ├── JwtUtil.java
│   │   │               └── PasswordUtil.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-prod.properties
│   └── test/
│       └── java/
│           └── com/
│               └── pillpoint/
│                   ├── controller/
│                   ├── service/
│                   └── repository/
└── pom.xml




API Endpoints Plan
Authentication
POST /api/auth/register - Register a new user
POST /api/auth/login - Login and get JWT token
GET /api/auth/me - Get current user info
Hospitals
GET /api/hospitals - Get all hospitals
GET /api/hospitals/{id} - Get hospital by ID
POST /api/hospitals - Create new hospital
PUT /api/hospitals/{id} - Update hospital
DELETE /api/hospitals/{id} - Delete hospital
Suppliers
GET /api/suppliers - Get all suppliers
GET /api/suppliers/{id} - Get supplier by ID
POST /api/suppliers - Create new supplier
PUT /api/suppliers/{id} - Update supplier
DELETE /api/suppliers/{id} - Delete supplier
Medicines
GET /api/medicines - Get all medicines
GET /api/medicines/{id} - Get medicine by ID
POST /api/medicines - Create new medicine
PUT /api/medicines/{id} - Update medicine
DELETE /api/medicines/{id} - Delete medicine
Inventory
GET /api/inventory - Get all inventory items
GET /api/inventory/hospital/{hospitalId} - Get inventory by hospital
GET /api/inventory/low-stock - Get low stock items
POST /api/inventory - Add inventory item
PUT /api/inventory/{id} - Update inventory
DELETE /api/inventory/{id} - Delete inventory item
Orders
GET /api/orders - Get all orders
GET /api/orders/{id} - Get order by ID
GET /api/orders/hospital/{hospitalId} - Get orders by hospital
GET /api/orders/supplier/{supplierId} - Get orders by supplier
POST /api/orders - Create new order
PUT /api/orders/{id}/status - Update order status
DELETE /api/orders/{id} - Delete order
Reports
GET /api/reports/dashboard - Get dashboard stats
GET /api/reports/inventory - Get inventory reports
GET /api/reports/orders - Get order reports

Database Schema

Users Table
id (PK)
name
email (unique)
password (hashed)
role (ADMIN, HOSPITAL, SUPPLIER)
created_at
updated_at

Hospitals Table
id (PK)
name
location
license_number
contact_info
email (unique)
user_id (FK to Users)
created_at
updated_at

Suppliers Table
id (PK)
name
contact_person
contact_info
email (unique)
address
user_id (FK to Users)
created_at
updated_at

Medicines Table
id (PK)
name
description
threshold_quantity
unit
created_at
updated_at

Inventory Table
id (PK)
medicine_id (FK to Medicines)
hospital_id (FK to Hospitals)
quantity
expiry_date
batch_number
supplier_id (FK to Suppliers)
last_updated
created_at
updated_at

Orders Table
id (PK)
medicine_id (FK to Medicines)
hospital_id (FK to Hospitals)
supplier_id (FK to Suppliers)
requested_quantity
status (PENDING, CONFIRMED, DECLINED, DELIVERED)
request_date
last_updated
created_at
updated_at


Core Dependencies:

- spring-boot-starter-web           // For building web applications including RESTful APIs
- spring-boot-starter-data-jpa      // For JPA and Hibernate support
- mysql-connector-java              // MySQL database connector
- lombok                           // To reduce boilerplate code
Security Dependencies:

- spring-boot-starter-security     // For authentication and authorization
- jjwt-api                        // For JWT token handling
- jjwt-impl                       // JWT implementation
- jjwt-jackson                    // JWT JSON serialization
Validation & Documentation:

- spring-boot-starter-validation   // For input validation
- springdoc-openapi-starter-webmvc-ui  // For Swagger/OpenAPI documentation
Testing Dependencies:

- spring-boot-starter-test        // For unit and integration testing
- h2database                      // In-memory database for testing
Additional Useful Dependencies:

- commons-lang3                   // Apache Commons utilities
- modelmapper                     // For object mapping between DTOs and entities
- spring-boot-starter-actuator    // For monitoring and metrics