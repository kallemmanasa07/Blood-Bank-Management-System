# 🩸 Blood Bank Management System

<div align="center">

### A Web-Based Blood Bank Management System Developed Using Java, JSP, Jakarta Servlets, JDBC, MySQL, Maven, Bootstrap 5, and Apache Tomcat

A secure and user-friendly application that helps blood banks efficiently manage donors, patients, blood inventory, blood donations, and blood requests.

</div>

---

# 📖 Overview

The **Blood Bank Management System** is a web-based application designed to automate and simplify blood bank operations. It provides an efficient platform for managing donor information, patient records, blood stock availability, blood donations, and blood requests.

The system reduces manual record keeping, improves data accuracy, and enables quick access to blood availability information through an intuitive web interface.

The project follows the **MVC (Model-View-Controller)** architecture, making the application modular, maintainable, and scalable.

---

# 🎯 Objectives

- Automate blood bank operations.
- Maintain donor and patient records.
- Manage blood stock efficiently.
- Process blood donation and request information.
- Reduce manual paperwork.
- Provide secure user authentication.
- Improve overall blood bank management.

---

# ✨ Features

## 👤 User Module

- User Registration
- User Login
- Secure Authentication
- Logout

## 🩸 Donor Module

- Add New Donor
- Update Donor Details
- Delete Donor
- View All Donors
- Search Donor

## 🏥 Patient Module

- Add Patient
- Update Patient Details
- Delete Patient
- View Patient Records

## ❤️ Blood Donation Module

- Record Blood Donations
- Update Donation Details
- View Donation History

## 🧪 Blood Stock Module

- Add Blood Units
- Update Blood Stock
- View Available Blood Groups
- Monitor Blood Inventory

## 📋 Blood Request Module

- Add Blood Request
- Update Request Status
- View Blood Requests

## 📊 Dashboard

- Blood Availability
- Total Donors
- Total Patients
- Blood Inventory Summary

---

# 🛠️ Technology Stack

## Frontend

- HTML5
- CSS3
- Bootstrap 5
- JavaScript
- JSP

## Backend

- Java
- Jakarta Servlets
- JDBC

## Database

- MySQL

## Build Tool

- Maven

## Web Server

- Apache Tomcat 10.1

## Architecture

MVC (Model–View–Controller)

---

# 📂 Project Structure

```text
Blood-Bank-Management-System
│
├── src
│   └── main
│       ├── java
│       │   ├── controller
│       │   ├── dao
│       │   ├── model
│       │   └── util
│       │
│       └── webapp
│           ├── css
│           ├── js
│           ├── images
│           ├── WEB-INF
│           ├── index.jsp
│           ├── login.jsp
│           ├── register.jsp
│           ├── dashboard.jsp
│           └── ...
│
├── bloodbank.sql
├── pom.xml
├── README.md
└── .gitignore
```

---

# 💾 Database

**Database Name**

```
bloodbank
```

Database File

```
bloodbank.sql
```

---

# ⚙️ Installation

## Clone Repository

```bash
git clone https://github.com/kallemmanasa07/Blood-Bank-Management-System.git
```

---

## Import into Eclipse

- File
- Import
- Existing Maven Project
- Finish

---

## Configure MySQL

Create database

```sql
CREATE DATABASE bloodbank;
```

Import

```
bloodbank.sql
```

---

## Configure Database Connection

Open

```
src/main/java/util/DBConnection.java
```

Update

```java
private static final String URL="jdbc:mysql://localhost:3306/bloodbank";
private static final String USER="root";
private static final String PASSWORD="your_password";
```

---

## Run Project

- Configure Apache Tomcat 10.1
- Add project to server
- Start Server

Open

```
http://localhost:8081/BloodBankManagementSystem/
```

---

# 📸 Screenshots

```
screenshots/

home.png

login.png

register.png

dashboard.png

donor.png

patient.png

bloodstock.png

donation.png

request.png
```

Example

```markdown
## Home Page

![Home](screenshots/home.png)

## Dashboard

![Dashboard](screenshots/dashboard.png)
```

---

# 🎥 Demo

```
demo/

BloodBankDemo.mp4
```

or

```
YouTube Demo Link
```

---

# 📌 Advantages

- Easy to use
- Fast data retrieval
- Secure login system
- Reduces manual work
- Organized database management
- Improved blood inventory tracking
- Scalable architecture
- Responsive interface

---

# 🔮 Future Enhancements

- Role-Based Access Control
- Email Notifications
- SMS Notifications
- Blood Donation Scheduling
- Advanced Search
- PDF Report Generation
- Excel Export
- REST API
- Cloud Deployment
- Mobile Application

---

# 📚 Learning Outcomes

During this project, I gained practical experience in:

- Java Web Development
- Jakarta Servlets
- JSP
- JDBC
- MySQL Database Design
- MVC Architecture
- Maven Project Management
- Apache Tomcat Deployment
- Git & GitHub Version Control
- CRUD Operations
- Session Management
- Responsive UI Design

---

# 👩‍💻 Author

## Kallem Manasa Reddy

B.Tech – Computer Science Engineering

GitHub

https://github.com/kallemmanasa07

LinkedIn

https://www.linkedin.com/in/kallem-manasa

---

# 🤝 Contributing

Contributions are welcome.

1. Fork the repository

2. Create a new branch

3. Commit your changes

4. Push to GitHub

5. Open a Pull Request

---

# 📄 License

This project is developed for educational and learning purposes.

---

# ⭐ Support

If you like this project,

⭐ Star this repository

🍴 Fork the repository

📢 Share it with others

---

# 🙏 Acknowledgements

- Java Documentation
- Jakarta EE Documentation
- Apache Tomcat
- MySQL Documentation
- Bootstrap Documentation
- Maven Documentation

---

<div align="center">

## ⭐ Thank You for Visiting ⭐

**If you found this project helpful, don't forget to give it a ⭐ on GitHub!**

</div>
