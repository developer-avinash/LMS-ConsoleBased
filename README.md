# 📚 Library Management System

A **Console-Based Library Management System** built using **Java, Spring Boot, Spring Data JPA, Hibernate, and Oracle Database**.

The application allows users to manage book records through an interactive console menu. It supports CRUD operations, searching, filtering, custom exception handling, and persistent storage using Oracle Database.

---

## 🚀 Features

- ➕ Add Book
- 📋 View All Books
- 🔍 Search Book by ID
- ✏️ Update Book
- 🗑️ Delete Book
- 🔎 Search Books by Title
- 👤 Filter Books by Author
- 📂 Filter Books by Category
- ⚠️ Custom Exception Handling
- 🗄️ Oracle Database Integration
- 🧩 Layered Architecture
- 🖥️ Interactive Console Menu
- 📄 Hibernate SQL Logging

---

# 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core Programming Language |
| Spring Boot | Application Framework |
| Spring Data JPA | Database Repository Layer |
| Hibernate | ORM Framework |
| Oracle Database XE | Database |
| Maven | Dependency Management |
| CommandLineRunner | Console Application Execution |

---

# 🏗️ System Architecture

```text
LibraryManagementApplication
          │
          ▼
     ConsoleRunner
          │
          ▼
      BookService
          │
          ▼
    BookRepository
          │
          ▼
    Spring Data JPA
          │
          ▼
       Hibernate
          │
          ▼
   Oracle Database
```

The project follows a **Layered Architecture**, separating user interaction, business logic, database operations, and exception handling.

---

# 📁 Project Structure

```text
Library-Management-System
│
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── avi
│       │           └── librarymanagement
│       │
│       │               ├── LibraryManagementApplication.java
│       │
│       │               ├── model
│       │               │   └── Book.java
│       │
│       │               ├── repository
│       │               │   └── BookRepository.java
│       │
│       │               ├── service
│       │               │   └── BookService.java
│       │
│       │               ├── runner
│       │               │   └── ConsoleRunner.java
│       │
│       │               └── exception
│       │                   ├── BookNotFoundException.java
│       │                   └── DuplicateBookException.java
│       │
│       └── resources
│           └── application.properties
│
├── pom.xml
│
└── README.md
```

---

# 📖 Book Entity

The `Book` entity represents book information stored in the database.

## Fields

| Field | Description |
|---|---|
| ID | Unique Book ID |
| Title | Book Title |
| Author | Book Author |
| Category | Book Category |
| Price | Book Price |
| Quantity | Available Quantity |

Example:

```java
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    private int id;

    private String title;
    private String author;
    private String category;
    private double price;
    private int quantity;
}
```

---

# 🗄️ Database Configuration

The application uses **Oracle Database XE**.

File location:

```text
src/main/resources/application.properties
```

```properties
spring.application.name=Library-Management-System

# Oracle Database Configuration
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=system
spring.datasource.password=root
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# JPA / Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server Port
server.port=8086
```

> ⚠️ Update the database username and password according to your local Oracle configuration.

---

# 🧩 Repository Layer

The project uses `JpaRepository` for database operations.

```java
@Repository
public interface BookRepository
        extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorIgnoreCase(String author);

    List<Book> findByCategoryIgnoreCase(String category);
}
```

Spring Data JPA automatically provides methods such as:

```java
save()
findAll()
findById()
existsById()
deleteById()
```

---

# ⚙️ Service Layer

The `BookService` class contains the business logic of the application.

## Add Book

Before adding a new book, the application checks whether the Book ID already exists.

```java
public Book addBook(Book book) {

    if (bookRepository.existsById(book.getId())) {
        throw new DuplicateBookException(
                "Book already exists with ID: "
                        + book.getId()
        );
    }

    return bookRepository.save(book);
}
```

---

## Get Book By ID

```java
public Book getBookById(int id) {

    return bookRepository.findById(id)
            .orElseThrow(() ->
                    new BookNotFoundException(
                            "Book not found with ID: " + id
                    )
            );
}
```

---

## Update Book

```java
public Book updateBook(Book book) {

    if (!bookRepository.existsById(book.getId())) {
        throw new BookNotFoundException(
                "Book not found with ID: "
                        + book.getId()
        );
    }

    return bookRepository.save(book);
}
```

---

## Delete Book

```java
public void deleteBook(int id) {

    if (!bookRepository.existsById(id)) {
        throw new BookNotFoundException(
                "Book not found with ID: " + id
        );
    }

    bookRepository.deleteById(id);
}
```

---

# 🔍 Search and Filter Operations

## Search by Title

```java
findByTitleContainingIgnoreCase(title)
```

This performs a case-insensitive search and can return books with matching titles.

---

## Filter by Author

```java
findByAuthorIgnoreCase(author)
```

This retrieves books written by the specified author.

---

## Filter by Category

```java
findByCategoryIgnoreCase(category)
```

This retrieves books belonging to the specified category.

---

# ⚠️ Exception Handling

The application uses custom exceptions for better error handling.

## DuplicateBookException

This exception is thrown when a book with the same ID already exists.

```java
public class DuplicateBookException
        extends RuntimeException {

    public DuplicateBookException(String message) {
        super(message);
    }
}
```

---

## BookNotFoundException

This exception is thrown when a requested book is not found.

```java
public class BookNotFoundException
        extends RuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
```

---

# 🖥️ Console Menu

When the application starts, the following menu is displayed:

```text
======================================
      LIBRARY MANAGEMENT SYSTEM
======================================

1. Add Book
2. View All Books
3. Search Book By ID
4. Update Book
5. Delete Book
6. Search Book By Title
7. Filter Books By Author
8. Filter Books By Category
9. Exit

======================================
Enter your choice:
```

---

# 🔄 Application Flow

```text
Start Application
        │
        ▼
Display Console Menu
        │
        ▼
User Selects Operation
        │
        ▼
ConsoleRunner
        │
        ▼
BookService
        │
        ▼
BookRepository
        │
        ▼
Spring Data JPA
        │
        ▼
Hibernate
        │
        ▼
Oracle Database
        │
        ▼
Display Result
```

---

# ▶️ How to Run the Project

## 1. Clone the Repository

```bash
git clone <your-github-repository-url>
```

## 2. Open the Project

Open the project using any Java IDE:

- Eclipse
- Spring Tool Suite
- IntelliJ IDEA

## 3. Configure Oracle Database

Make sure **Oracle Database XE** is running.

Update the following properties:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## 4. Build the Project

Using Maven:

```bash
mvn clean install
```

---

## 5. Run the Application

Run the main class:

```text
LibraryManagementApplication.java
```

Or use Maven:

```bash
mvn spring-boot:run
```

---

# 🧪 Testing the Application

Example book data:

```text
Book ID: 101
Title: Clean Code
Author: Robert Martin
Category: Programming
Price: 599
Quantity: 10
```

Expected result:

```text
Book added successfully!
```

You can verify the data in Oracle Database:

```sql
SELECT * FROM BOOKS;
```

---

# 📊 Testing Scenarios

| Test Case | Expected Result |
|---|---|
| Add Book | Book is saved successfully |
| Add Duplicate ID | DuplicateBookException |
| View All Books | All books are displayed |
| Search Valid ID | Book details displayed |
| Search Invalid ID | BookNotFoundException |
| Update Book | Book record updated |
| Delete Book | Book removed successfully |
| Search by Title | Matching books displayed |
| Filter by Author | Matching books displayed |
| Filter by Category | Matching books displayed |

---

# 📄 Hibernate SQL Logging

Hibernate SQL logging is enabled using:

```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

This allows generated SQL queries to be displayed in the application console.

Example:

```sql
insert into books
(author, category, price, quantity, title, id)
values (?, ?, ?, ?, ?, ?)
```

---

# 🚀 Future Enhancements

- Input validation
- Automatic Book ID generation
- Book issue functionality
- Book return functionality
- Library member management
- Available book tracking
- Authentication and authorization
- Role-based access
- Pagination and sorting
- REST API integration
- Web or Android application interface
- Export reports to CSV or PDF

---

# 👨‍💻 Author

**Avinash Kumar Pandey**

Java Developer | Spring Boot | Spring Data JPA | Hibernate | Oracle Database

---

# 📄 License

This project was developed for **educational and internship purposes**.
