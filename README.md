# Alice Shop - Online Shopping Application

This is a modern e-commerce application built with Spring Boot backend and Vue.js frontend. The project consists of two main components: a REST API backend (`onlineshop`) and a Vue.js frontend (`onlineshopUI`).

## Project Structure

```
├── onlineshop/           # Spring Boot Backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/    # Java source files
│   │   │   └── resources/ # Application properties and static resources
│   │   └── test/        # Test files
│   └── pom.xml          # Maven dependencies
├── onlineshopUI/        # Vue.js Frontend
│   ├── src/
│   │   ├── components/  # Vue components
│   │   ├── views/       # Vue views/pages
│   │   └── assets/      # Static assets
│   └── package.json     # NPM dependencies
└── aliceshop.sql        # Database schema
```

## Technology Stack

### Backend (onlineshop)
- **Framework**: Spring Boot 3.5.0
- **Java Version**: 17
- **Dependencies**:
  - Spring Data JPA
  - Spring Security
  - Spring Web
  - JWT Authentication
  - Jakarta Faces (JSF)
  - MySQL Database

### Frontend (onlineshopUI)
- **Framework**: Vue.js 3
- **Build Tool**: Vite
- **Dependencies**:
  - Vue Router
  - Axios (for HTTP requests)
  - JWT Decode

## Features
- User authentication with JWT
- Product management
- Mobile-friendly interface
- Secure API endpoints
- Database persistence with MySQL

## Getting Started

### Backend Setup
1. Make sure you have Java 17 installed
2. Configure MySQL database (create a database named 'aliceshop')
3. Update database credentials in `application.properties` if needed
4. Run the Spring Boot application:
   ```bash
   cd onlineshop
   mvn spring-boot:run
   ```
   The backend will start on port 8081

### Frontend Setup
1. Make sure you have Node.js installed
2. Install dependencies:
   ```bash
   cd onlineshopUI
   npm install
   ```
3. Run the development server:
   ```bash
   npm run dev
   ```
   The frontend will be available at `http://localhost:5173`

## API Endpoints

The backend provides several RESTful endpoints:
- Authentication endpoints
- Product management endpoints
- Mobile-specific endpoints

## Development

### Backend Development
- The application uses Spring Boot's standard project structure
- JPA entities are in the `entity` package
- Controllers are in the `controller` package
- Business logic is in the `service` package
- Database access is handled by repositories in the `repository` package

### Frontend Development
- Vue components are in the `components` directory
- Page views are in the `views` directory
- API calls are centralized in `api.js`
- Routing is configured in `router.js`

## Database

The application uses MySQL as its primary database. The schema is provided in `aliceshop.sql`. The application is configured to automatically update the database schema using Hibernate's `ddl-auto=update` setting.

## Security

- JWT-based authentication
- Password encryption using Spring Security
- Protected API endpoints
- CORS configuration for frontend access

## Building for Production

### Backend
```bash
cd onlineshop
mvn clean package
```
The JAR file will be generated in the `target` directory.

### Frontend
```bash
cd onlineshopUI
npm run build
```
The build output will be in the `dist` directory.
