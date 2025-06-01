
# ✅ PillPoint Spring Boot API Testing Guide

---

## 🔐 1. Authentication Endpoints

### ➕ Register a New User
**POST** `/api/auth/register`  
**Request Body:**
```json
{
  "username": "testadmin",
  "email": "test@admin.com",
  "password": "password123",
  "role": "ADMIN",
  "organizationName": "Health Ministry",
  "location": "City Center",
  "licenseNumber": "ADM001"
}
```

### 🔑 Login
**POST** `/api/auth/login`  
**Request Body:**
```json
{
  "username": "testadmin",
  "password": "password123"
}
```
✅ **Save the JWT token** from the response for all protected endpoints.

---

## 💊 2. Medicine Endpoints

### 🔍 Get All Medicines
**GET** `/api/medicines`  
**Header:** `Authorization: Bearer YOUR_JWT_TOKEN`

### ➕ Create a Medicine (Admin Only)
**POST** `/api/medicines`  
**Request Body:**
```json
{
  "name": "Paracetamol 500mg",
  "category": "Pain Relief",
  "unit": "tablets",
  "thresholdQuantity": 100
}
```

### 🔍 Get Medicine by ID
**GET** `/api/medicines/{id}`

### 🔍 Get Medicines by Category
**GET** `/api/medicines/category/{category}`

---

## 🏥 3. Hospital Endpoints

### 🔍 Get All Hospitals (Admin Only)
**GET** `/api/hospitals`

### 🔍 Get Hospital by ID
**GET** `/api/hospitals/{id}`

### 📦 Get Hospital Inventory
**GET** `/api/hospitals/{id}/inventory`

### 🔄 Update Hospital Inventory
**PUT** `/api/hospitals/{id}/inventory?medicineId={id}&quantity={qty}&expiryDate=YYYY-MM-DDTHH:MM:SS`

### 📝 Create Order (Hospital User)
**POST** `/api/hospitals/{id}/orders`  
**Request Body:**
```json
{
  "medicineId": 1,
  "quantity": 100,
  "urgency": "HIGH"
}
```

### 📄 Get Hospital Orders
**GET** `/api/hospitals/{id}/orders`

### ⚠️ Get Low Stock Items
**GET** `/api/hospitals/{id}/low-stock`

---

## 🚚 4. Supplier Endpoints

### 🔍 Get All Suppliers
**GET** `/api/suppliers`

### 📦 Get Supplier Orders
**GET** `/api/suppliers/{id}/orders`

### 📌 Assign Order to Supplier
**PUT** `/api/suppliers/orders/{orderId}/assign`  
**Request Body:**
```json
{
  "supplierId": 1,
  "estimatedValue": 5000.00
}
```

### 🔄 Update Order Status
**PUT** `/api/suppliers/orders/{orderId}/status?status=FULFILLED`

### ⭐ Update Supplier Rating
**PUT** `/api/suppliers/{id}/rating?rating=4.5`

---

## 📦 5. Order Endpoints

### 🔍 Get All Orders (Admin Only)
**GET** `/api/orders`

### 🔍 Get Orders by Status
**GET** `/api/orders/status/{status}`

### 🔍 Get Orders by Urgency
**GET** `/api/orders/urgency/{urgency}`

---

## 🧾 6. Inventory Endpoints

### 🔍 Get All Inventories (Admin Only)
**GET** `/api/inventory`

### ⚠️ Get Low Stock Inventories
**GET** `/api/inventory/low-stock`

---

## 📊 7. Report Endpoints

### 📈 Admin Dashboard
**GET** `/api/reports/admin/dashboard`

### 🏥 Hospital Dashboard
**GET** `/api/reports/hospital/dashboard`

### 🚚 Supplier Dashboard
**GET** `/api/reports/supplier/dashboard`

---

## 🧪 Testing Tools & Setup

### ✅ Using Postman
1. **Create a Collection**
2. **Set Environment Variables:**
    - `baseUrl`: `http://localhost:8080`
    - `token`: *(Set after login)*

3. **Login First** → Get and save JWT token
4. **Set Authorization:**  
   `Authorization: Bearer {{token}}`

---

### 🌐 Using Browser (GET only)
Paste endpoint in browser after adding token manually in browser dev tools.  
Example:  
`http://localhost:8080/api/medicines`

---

### 💻 Using cURL

**Login**
```bash
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d '{"username":"admin","password":"password"}'
```

**Get Medicines**
```bash
curl -X GET http://localhost:8080/api/medicines -H "Authorization: Bearer YOUR_TOKEN"
```

---

## 🧾 Default Test Data

- **Users:** `admin/password`, `hospital/password`, `supplier/password`
- **Roles:** `ADMIN`, `HOSPITAL`, `SUPPLIER`
- **Order Statuses:** `NEW`, `REVIEWING`, `ACCEPTED`, `DECLINED`, `FULFILLED`
- **Urgency Levels:** `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`

---

## 🔄 Testing Sequence

1. **Register/Login** → Get JWT token
2. **Create Medicines** (as Admin)
3. **Register Hospital/Supplier Accounts**
4. **Add Inventory to Hospitals**
5. **Create Orders from Hospitals**
6. **Assign Orders to Suppliers**
7. **Update Order Status → Fulfillment**
8. **Check Reports/Dashboard Analytics**

---

📌 **Remember:** Replace `YOUR_JWT_TOKEN` with the actual token from login response.
