# **E-Commerce Website with Spring Boot and Thymeleaf**

# Table of Contents

1. Introduction
2. Features
3. Technologies Used
4. Setup Instructions
5. Contributing
6. FAQs

### Introduction

This is an e-commerce website built with Spring Boot and Thymeleaf.
It features a user-friendly interface for browsing and purchasing products,
managing categories, and handling orders.
The project demonstrates the integration of various technologies to create a robust and scalable web application.

### **_Features_**

* User authentication and authorization
* Product management (CRUD operations)
* Category management (CRUD operations)
* Order management
* Shopping cart functionality
* Responsive design with Bootstrap
* Integration with a database for persistent storage

### **_Technologies used_**

* **Spring Boot**: Backend framework
* **Thymeleaf**: Templating engine
* **Spring Security**: Security framework
* **Hibernate/JPA**: ORM framework
* **MySQL**: Database
* **Bootstrap**: Frontend framework
* **Maven**: Build and dependency management tool

### _**Setup and installation**_

##### **Prerequisites**

* Java 11 or higher
* Maven 3.6.0 or higher
* MySQL 8.0 or higher

##### **Installation**

1. **Clone the repository:**

```bash
   git clone https://github.com/your-username/ecommerce-website.git
   cd ecommerce-website
   ```
2. **Configure the database**:

* Run sql file from Database folder to create a working database
* Change application.properties file in both Admin and Customer to your mysql workbench setup

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_springboot
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```

3. **Run Projects**

* Run AdminApplication to run admin site on port 8089
* Run CustomerApplication to run user site on port 8020
* Register your account on both site with your personal information

#### **_Contributing_**

Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure that your code
follows the project's coding standards and includes appropriate tests.

#### **_FAQS_**

* For any further support please feel free to contact me through any means