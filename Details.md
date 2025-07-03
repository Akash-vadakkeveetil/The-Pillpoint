# Details of this project 

### Database Schema 
```schema
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
```