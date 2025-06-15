# Online Shop UI

A Vue.js-based frontend application for managing an online shop's products. This application provides a user interface for viewing products and allows administrators to perform CRUD operations on products.

## Features

- **User Authentication**
  - JWT-based authentication
  - Role-based access control (Admin/User roles)
  - Secure logout functionality

- **Product Management**
  - View list of all products
  - Add new products (Admin only)
  - Edit existing products (Admin only)
  - Delete products (Admin only)
  - Input validation for product operations

## Tech Stack

- **Vue.js 3** - Progressive JavaScript Framework
- **Vue Router 4** - Official router for Vue.js
- **Axios** - HTTP client for API requests
- **JWT Decode** - JSON Web Token decoder
- **Vite** - Next Generation Frontend Tooling

## Prerequisites

- Node.js (Latest LTS version recommended)
- npm (comes with Node.js)
- Backend server running on `http://localhost:8081`

## Installation

Install dependencies:
```bash
npm install
```

## Development

To start the development server:

```bash
npm run dev
```

The application will be available at `http://localhost:5173` (or another port if 5173 is in use).

## Building for Production

To create a production build:

```bash
npm run build
```

To preview the production build:

```bash
npm run preview
```

## Project Structure

```
onlineshopUI/
├── public/
│   └── vite.svg
├── src/
│   ├── assets/
│   │   └── vue.svg
│   ├── components/
│   │   └── HelloWorld.vue
│   ├── views/
│   │   ├── Login.vue
│   │   └── ProductList.vue
│   ├── api.js
│   ├── App.vue
│   ├── main.js
│   ├── router.js
│   └── style.css
├── index.html
├── package.json
├── README.md
└── vite.config.js
```

## API Integration

The application integrates with a REST API running at `http://localhost:8081` with the following endpoints:

- `GET /products/all` - Fetch all products
- `POST /products/add` - Add a new product
- `PUT /products/update/:id` - Update an existing product
- `DELETE /products/delete/:id` - Delete a product

All API requests include JWT authentication tokens when available.

## Security Features

- JWT-based authentication
- Protected routes for admin functions
- Automatic token injection in API requests
- Input validation for all product operations
