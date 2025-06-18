# Education Platform

A comprehensive Spring Boot-based educational platform that connects students with engineers for learning and project collaboration.

## 🌟 Features

### Student Features

- Student Dashboard with course progress tracking
- File management system for assignments and projects
- Real-time chat with engineers
- Payment processing for courses and services
- Notification system for updates and messages

### Engineer Features

- Engineer Dashboard with student management
- Schedule management system
- Earnings tracking and payment history
- Project request handling
- Real-time chat with students

### Admin Features

- User management (approve/suspend users)
- Course management
- Payment monitoring
- System analytics and reporting

## 🛠️ Technologies

- **Backend Framework:** Spring Boot 3.2.3
- **Security:** Spring Security with JWT Authentication
- **Database:** PostgreSQL
- **File Storage:** AWS S3
- **Payment Processing:** Paymob Integration
- **Real-time Communication:** WebSocket
- **API Documentation:** Swagger/OpenAPI
- **Testing:** JUnit 5, Mockito

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL
- AWS Account (for S3)
- Paymob Account (for payments)

### Installation

1. Clone the repository

```bash
git clone https://github.com/omartarekmoh/Educational-Platform
```

2. Configure the application

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edit `application.properties` with your configuration

3. Build the project

```bash
mvn clean install
```

4. Run the application

```bash
mvn spring-boot:run
```

## 📁 Project Structure

```
src/main/java/com/education/platform/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── dto/            # Data Transfer Objects
├── entity/         # JPA entities
├── exception/      # Custom exceptions
├── model/          # Business models
├── repository/     # JPA repositories
├── security/       # Security related classes
└── service/        # Business logic
    └── implementation/  # Service implementations
```

## 🔒 Security

- JWT-based authentication
- Role-based access control (STUDENT, ENGINEER, ADMIN)
- Password encryption
- Secure file storage
- Payment data protection

## 📝 API Documentation

API documentation is available at `/swagger-ui.html` when running the application.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- Omar Tarek

## 🙏 Acknowledgments

- Spring Boot Team
- AWS
- Paymob
- All contributors
