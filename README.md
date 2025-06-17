# Document Management System - Spring Boot

This is a Document Management System (DMS) built with Java Spring Boot, designed to support user authentication, role-based access control, CRUD operations on documents, and an audit trail via report generation.

## ✨ Features

- **User roles**: Admin, Manager, and Author
- **JWT authentication**: Secure login with token-based authorization
- **Role-based permissions**:
  - Authors can create articles
  - Admins and Managers can create, update, and delete any document
- **Entities**:
  - `User` (with role association via a many-to-many relationship)
  - `Role` (only contains `id` and `name`)
  - `Document` (base entity, extended by `Article` and `Report`)
  - `Article` and `Report` inherit from `Document`
- **Search functionality**:
  - Search documents by title (contains substring)
  - Filter documents by issue date range (from–to)
  - Search results limited to documents:
    - Created by the currently logged-in user (if Author)
    - All documents (if Admin or Manager)
- **Automatic report generation**:
  - When a document is updated or deleted, a `Report` is automatically created
  - Report format: `"User X performed operation Y on document Z"`
  - Timestamp is set to current date/time
  - The report author is set to `NULL`

## Author

**Ilija Popadic**  
[GitHub: ipopadic-ip](https://github.com/ipopadic-ip)

## License

This project is licensed under the **Attribution License**.

You are free to use, modify, and share this code for personal purposes, **as long as proper credit is given**.  
That includes:

- Mentioning my full name **Ilija Popadic** visibly in your project or documentation.
- Providing a working link to my GitHub profile: [https://github.com/ipopadic-ip](https://github.com/ipopadic-ip)

Failure to provide visible credit is a violation of this license.

---
