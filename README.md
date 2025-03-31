# E-commerce UI Automation Framework

## 📌 Overview
This project is a **UI automation framework** for an e-commerce platform using **Selenium, Java, Maven, and Jenkins**. It follows the **Page Object Model (POM)** to ensure maintainability, reusability, and scalability of test scripts.

## 🚀 Technologies Used
- **Java** - Programming language
- **Selenium WebDriver** - UI automation
- **Maven** - Build tool & dependency management
- **Jenkins** - CI/CD integration
- **TestNG** - Test framework
- **Page Object Model (POM)** - Framework design pattern


## 🛠️ Setup & Installation
### Prerequisites
- Install **Java (JDK 11 or later)**
- Install **Maven**
- Install **Jenkins** (optional for CI/CD)
- Install **ChromeDriver/GeckoDriver** for Selenium

### Clone the Repository
```sh
git clone https://github.com/sumitsaurabh2107/SleniumEcommFramework.git
cd ecommerce-ui-automation
```

## 🏃 Running the Tests
### Run Tests Locally
```sh
mvn test
```

### Run Tests in Jenkins
1. Configure **Jenkins Job** with a pipeline script or `Jenkinsfile`.
2. Use the following Maven command in Jenkins:
```sh
mvn clean test
```


## 🔄 CI/CD Pipeline (Jenkins)
- Automated test execution on **code commits & merges**.
- **Test results & logs stored in Jenkins for analysis**.
- Pipeline configuration managed via `Jenkinsfile`.

## 📌 Features Covered
✅ **User Login & Authentication**
✅ **Product Search & Filtering**
✅ **Add to Cart & Checkout Flow**
✅ **Order History & Profile Management**
✅ **Cross-Browser Testing Support**
✅ **Page Object Model (POM) for Scalable Test Design**

## 🤝 Contribution
Feel free to fork the repository, create a new branch, and submit a **Pull Request**.

---
🚀 **Happy Testing!** 🛒
