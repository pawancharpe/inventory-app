# 📦 Inventory Tracker — Full Stack App

## Tech Stack
| Layer    | Technology              |
|----------|-------------------------|
| Frontend | HTML + CSS + JavaScript |
| Backend  | Java 17 + Spring Boot 3 |
| Database | MySQL 8                 |

---

## Prerequisites — Install These First

| Tool        | Download                                  |
|-------------|-------------------------------------------|
| Java 17+    | https://adoptium.net                      |
| Maven 3.8+  | https://maven.apache.org/download.cgi     |
| MySQL 8     | https://dev.mysql.com/downloads/installer |

---

## Step 1 — Setup the Database

Open MySQL Workbench (or terminal) and run:

```sql
source database/setup.sql
```

Or manually:
```bash
mysql -u root -p < database/setup.sql
```

---

## Step 2 — Configure Database Password

Edit `backend/src/main/resources/application.properties`:

```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD_HERE
```

---

## Step 3 — Run the Java Backend

```bash
cd backend
mvn spring-boot:run
```

You should see:
```
Started InventoryApplication on port 8080
```

Test it works:
```
http://localhost:8080/api/products
```

---

## Step 4 — Open the Frontend

Just open the file in any browser:

```
frontend/index.html
```

Double-click it, or drag it into Chrome/Firefox/Edge.

That's it! The app is running. 🎉

---

## API Endpoints (for testing with Postman or curl)

| Method | URL                              | Description          |
|--------|----------------------------------|----------------------|
| GET    | /api/products                    | List all products    |
| GET    | /api/products/{id}               | Get one product      |
| POST   | /api/products                    | Create product       |
| PUT    | /api/products/{id}               | Update product       |
| DELETE | /api/products/{id}               | Delete product       |
| GET    | /api/products/search?name=lap    | Search by name       |
| GET    | /api/products/category/Electronics | Filter by category |
| GET    | /api/products/low-stock          | Low stock items      |
| GET    | /api/products/categories         | All category names   |

### Example: Create a product with curl
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Item","price":999,"quantity":10,"category":"Test"}'
```

---

## Access from Other Computers (on same Wi-Fi)

1. Find your computer's local IP:
   - Windows: `ipconfig` → look for IPv4 (e.g. 192.168.1.5)
   - Mac/Linux: `ifconfig` → look for inet

2. On the other computer, open a browser and go to:
   ```
   http://192.168.1.5:8080/api/products
   ```

3. Update the `API` variable in `frontend/index.html`:
   ```javascript
   const API = 'http://192.168.1.5:8080/api/products';
   ```

---

## Project Structure

```
inventory-app/
├── frontend/
│   └── index.html              ← Open this in browser
│
├── backend/
│   ├── pom.xml                 ← Maven dependencies
│   └── src/main/
│       ├── java/com/inventory/
│       │   ├── InventoryApplication.java   ← Main entry point
│       │   ├── model/Product.java          ← Database entity
│       │   ├── repository/ProductRepository.java
│       │   ├── service/ProductService.java
│       │   └── controller/ProductController.java
│       └── resources/
│           └── application.properties      ← DB config
│
└── database/
    └── setup.sql               ← Run this first in MySQL
```
