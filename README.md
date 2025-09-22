# CareerBuddy Backend ⚙️

_CSE-3216 — Software Design Pattern Lab (University of Dhaka)_

This is the backend service of **CareerBuddy**, a career management system designed to help students and job seekers find opportunities, manage applications, and build resumes.  
It is built with **Java (Spring Boot + Maven)** and uses **PostgreSQL** as the database.

---
### Frontend
[CareerBuddy Frontend](https://github.com/reckless-meherun/CareerBuddy_FrontEnd-CSE-3216-SDP_Project.git)

## Features (Backend Responsibilities)

- REST APIs for user management (registration, login, profiles)
- Job posting, searching, and filtering
- Application management (apply, track, review)
- Resume builder & state management
- Notifications & subscriptions (Observer + Strategy patterns)
- Secure authentication (Middleware + Decorator pattern)
- Reporting and statistics endpoints

---

## Requirements

- **Java 17+**
- **Maven 3.8+**
- **PostgreSQL 14+**
- **IntelliJ IDEA** (recommended for running)

---

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/sheldor944/CareerBuddy_BackEnd-CSE-3216-SDP-Project.git
   cd CareerBuddy_BackEnd-CSE-3216-SDP-Project
   ```
2. **Install PostgreSQL**
   CREATE DATABASE jobPortal;
3. **Configure database connection**
   Edit src/main/resources/application.properties and update:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/jobPortal
    spring.datasource.username=your_username
    spring.datasource.password=your_password
   ```
4. **Build and run the project**
   Open in IntelliJ IDEA and run the main application class.

## Project Structure
```
CareerBuddy_BackEnd/
├─ src/
│  ├─ main/
│  │  ├─ java/          # Java source code
│  │  └─ resources/     # application.properties, configs
│  └─ test/             # Unit and integration tests
├─ pom.xml              # Maven configuration
└─ README.md
```
## Running the Application
- The backend runs by default at:
  http://localhost:8080
- Example base API URL:
  ```
  http://localhost:8080/api
  ```
## Next Steps
For now, the backend uses a local PostgreSQL database. After the base structure is finalized, the project will migrate to a cloud-hosted database for production deployment.
