# 🍽️ Restaurant Management System

A desktop-based Java Swing application for restaurant management with three user roles: **Admin**, **Chef**, and **Waiter**. This system helps restaurants manage their daily operations, from placing orders to preparing food and managing tables.

Built with **Java**, **MySQL**, and runs locally using **MAMP**.

---

## 👥 User Roles

- 👑 **Admin**: Manages menu items, users, and oversees operations.
- 👨‍🍳 **Chef**: Views incoming orders and updates cooking status.
- 🧑‍💼 **Waiter**: Takes orders and manages table assignments.

---

## 📦 Features

- 🔐 Login system for role-based access
- 📋 Add/update/delete menu items
- 🍽️ Place and manage orders
- 🧾 View pending/completed orders
- 📊 Organized dashboard for each role

---

## 🛠️ Tech Stack

| Layer    | Technology                        |
| -------- | --------------------------------- |
| Language | Java                              |
| Database | MySQL                             |
| Runtime  | MAMP (Apache & MySQL)             |
| UI       | Java Swing (or JavaFX if used)    |
| DAO      | JDBC (Java Database Connectivity) |

---

## 📁 Project Structure

restaurant-management/
├── /src/dao/DatabaseConnection.java
├── /database/restaurant_db.sql # 🗄️ SQL dump file
├── /screenshots/ # UI screenshots
└── README.md

---

## ⚙️ Setup Instructions

### 1. 🔽 Download MAMP

Download and install [MAMP](https://www.mamp.info/en/) for your OS.

> MAMP includes Apache + MySQL to run your backend database locally.

---

### 2. 🚀 Start MAMP

- Launch **MAMP**.
- Start **Apache** and **MySQL** servers.
- Open **phpMyAdmin** via `http://localhost/phpmyadmin`.

---

### 3. 🗃️ Import the Database

1. Create a new database (e.g. `restaurant_db`).
2. Go to the **Import** tab.
3. Select the file: `database/restaurant_db.sql`.
4. Click **Go**.

You now have all necessary tables and test data ready.

---

### 4. 🛠️ Configure Ports (Important)

By default, MAMP uses:

- MySQL Port: `8889`
- Apache Port: `8888`

If your MAMP uses **non-default ports**, update the JDBC URL in:

`/src/dao/DatabaseConnection.java`

⚠️ If your MAMP is using default MySQL port 3306, no changes are needed.

🖼️ Screenshots

### Admin Dashboard

![Admin Dashboard](/images/adminDashboard.png)

### Chef Order View

![Chef Order View](/images/chefOrderView.png)

### Waiter Order Panel

![Waiter Order Panel](/images/waiterOrderPanel.png)

🧪 Testing & Running the App
Open the project in your IDE (e.g., IntelliJ, Eclipse).

Ensure the MAMP server is running.

Run your Main class.

Login using one of the sample credentials from the database (check SQL file).

📄 License
This project is licensed under the MIT License.

✉️ Contact
For any issues or feature requests, feel free to open an issue on the GitHub repository.

🙌 Acknowledgments

Thanks to open-source tools and the Java + MySQL developer community.
