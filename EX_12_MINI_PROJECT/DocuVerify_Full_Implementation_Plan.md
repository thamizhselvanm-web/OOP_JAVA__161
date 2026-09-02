# DocuVerify — Secure Digital Document Verification System
## Full Implementation Plan, Requirements, Do's and Don'ts

**Project Type:** Java Mini Project  
**Application Type:** Desktop Application  
**Primary UI:** JavaFX  
**Database:** MySQL  
**Database Connectivity:** JDBC  
**Core Security Concept:** SHA-256 Document Hashing  

---

# 1. Project Overview

## 1.1 Project Name

**DocuVerify — Secure Digital Document Verification System**

## 1.2 Problem Statement

Digital documents such as certificates, mark sheets, invoices, and identity-related files can be copied or modified easily. In many situations, a person or organization needs a simple way to verify whether a submitted document is the same document that was originally registered.

DocuVerify provides a document registration and verification mechanism using cryptographic hashing.

When a document is registered:

1. The application reads the file.
2. A SHA-256 hash is generated from its binary content.
3. The hash and document metadata are stored in the database.

When a document is verified:

1. The verifier selects a file.
2. The application generates its SHA-256 hash.
3. The hash is searched in the database.
4. The system returns a verification result.

Possible results:

- **VERIFIED** — An exact matching registered document exists.
- **NOT REGISTERED** — No matching hash exists.
- **MODIFIED / MISMATCH** — Use this only when the system has an appropriate original reference to compare against.

> Important: A simple hash lookup alone can reliably prove an exact match or no exact match. It cannot always determine whether an unknown file is a modified version of a specific original document. Do not falsely claim that every "hash not found" result is definitely a modified document.

---

# 2. Project Objectives

The project should demonstrate practical usage of:

- Core Java
- Object-Oriented Programming
- JavaFX
- JDBC
- MySQL
- File Handling
- Collections
- Exception Handling
- SHA-256 hashing
- Date and Time API
- Basic concurrency for UI responsiveness
- MVC or layered application structure

The goal is to build a functional desktop application, not just a collection of separate Java programs.

---

# 3. Recommended Scope

## Minimum Viable Project (Must Have)

1. User registration and login
2. Admin and normal user roles
3. Document registration
4. File selection using JavaFX FileChooser
5. SHA-256 hash generation
6. Store document metadata and hash in MySQL
7. Document verification by hash comparison
8. Verification history
9. Basic admin dashboard
10. Proper validation and exception handling

## Optional Advanced Features

Implement only after the core project works:

- QR code generation
- JavaFX charts
- PDF verification receipt
- Document expiry date
- Background processing for large files
- Search and filters
- Password hashing
- Export verification history

Do not start with optional features.

---

# 4. System Users and Roles

## 4.1 Normal User

The normal user can:

- Register an account
- Log in
- Upload and register a document
- View registered documents
- Verify a document
- View their verification history
- Log out

## 4.2 Admin

The admin can:

- Log in
- View all registered documents
- View users
- View verification activity
- Search documents
- Monitor verification results
- View dashboard statistics

---

# 5. Functional Requirements

## FR-01: User Registration

The system shall allow a new user to register with:

- Full name
- Email
- Username
- Password
- Confirm password

Validation:

- Required fields cannot be empty.
- Email should follow a basic email format.
- Username should be unique.
- Password and confirm password must match.

## FR-02: User Login

The system shall authenticate users using their username/email and password.

After login:

- Admin → Admin Dashboard
- User → User Dashboard

## FR-03: Document Registration

The system shall allow a user to:

- Select a document from the local computer.
- Read the file.
- Generate SHA-256 hash.
- Extract file metadata.
- Store metadata and hash in the database.

Suggested supported file types:

- PDF
- DOCX
- TXT
- JPG/JPEG
- PNG

For the first version, you can support only PDF and DOCX if that simplifies testing.

## FR-04: Document Metadata Storage

Store:

- Unique document ID
- Original file name
- File extension/type
- File size
- SHA-256 hash
- Owner/user ID
- Registration timestamp
- Status

## FR-05: Duplicate Registration Detection

Before registering a document:

1. Generate its SHA-256 hash.
2. Search the database.
3. If the hash already exists, do not create another identical document record unless duplicate registrations are intentionally supported.

Recommended result:

> "This exact document is already registered."

## FR-06: Document Verification

The verifier selects a file.

The system:

1. Generates SHA-256.
2. Searches the document hash in the database.
3. Returns the result.

Result:

### VERIFIED
Exact hash exists.

### NOT REGISTERED
No exact hash exists.

## FR-07: Verification History

Store:

- Verification ID
- User/verifier
- Submitted file name
- Generated hash
- Verification result
- Matching document ID, if any
- Timestamp

## FR-08: Dashboard

Display:

- Total registered documents
- Total verification attempts
- Verified documents
- Not registered attempts
- Recent activity

---

# 6. Non-Functional Requirements

## Performance

- Small documents should normally be processed quickly.
- The UI must not freeze during longer file operations.

## Usability

- Clear navigation
- Meaningful error messages
- Clear verification status
- Consistent button placement

## Reliability

- Database errors must be handled.
- Invalid files must not crash the application.
- Duplicate records should be prevented where appropriate.

## Security

- Never store passwords in plain text in a production-quality design.
- Do not expose database credentials in source code repositories.
- Validate file size and type.

---

# 7. Technology Stack

| Layer | Technology |
|---|---|
| Programming Language | Java |
| UI | JavaFX |
| Database | MySQL |
| Database Connectivity | JDBC |
| Hashing | Java MessageDigest |
| Build Tool | Maven recommended |
| IDE | IntelliJ IDEA / Eclipse / NetBeans |
| Architecture | MVC / Layered Architecture |

---

# 8. Recommended Architecture

Use a layered structure:

```text
JavaFX Views
      ↓
Controllers
      ↓
Services
      ↓
DAO Layer
      ↓
JDBC
      ↓
MySQL
```

## Responsibilities

### View
FXML and CSS only.

### Controller
Handles UI events and navigation.

### Service
Contains business logic.

### DAO
Handles SQL operations.

### Model
Represents application data.

### Utility
Contains reusable helper methods.

---

# 9. Project Folder Structure

```text
DocuVerify/
│
├── pom.xml
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/docuverify/
│   │   │       ├── Main.java
│   │   │       │
│   │   │       ├── model/
│   │   │       │   ├── User.java
│   │   │       │   ├── Document.java
│   │   │       │   └── Verification.java
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── LoginController.java
│   │   │       │   ├── RegisterController.java
│   │   │       │   ├── DashboardController.java
│   │   │       │   ├── UploadController.java
│   │   │       │   └── VerificationController.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── AuthenticationService.java
│   │   │       │   ├── DocumentService.java
│   │   │       │   └── VerificationService.java
│   │   │       │
│   │   │       ├── dao/
│   │   │       │   ├── UserDAO.java
│   │   │       │   ├── DocumentDAO.java
│   │   │       │   └── VerificationDAO.java
│   │   │       │
│   │   │       ├── database/
│   │   │       │   └── DBConnection.java
│   │   │       │
│   │   │       └── util/
│   │   │           ├── HashUtil.java
│   │   │           ├── ValidationUtil.java
│   │   │           └── AlertUtil.java
│   │   │
│   │   └── resources/
│   │       ├── fxml/
│   │       │   ├── login.fxml
│   │       │   ├── register.fxml
│   │       │   ├── dashboard.fxml
│   │       │   ├── upload.fxml
│   │       │   └── verify.fxml
│   │       │
│   │       ├── css/
│   │       │   └── styles.css
│   │       │
│   │       └── images/
│   │
└── README.md
```

---

# 10. Core Data Models

## User

Suggested fields:

```text
id
name
email
username
passwordHash
role
createdAt
```

## Document

Suggested fields:

```text
id
userId
documentName
fileType
fileSize
documentHash
registeredAt
status
```

## Verification

Suggested fields:

```text
id
verifierId
submittedFileName
submittedHash
matchedDocumentId
result
verifiedAt
```

---

# 11. Database Design

## 11.1 Users Table

```sql
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 11.2 Documents Table

```sql
CREATE TABLE documents (
    document_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(50),
    file_size BIGINT,
    document_hash CHAR(64) NOT NULL UNIQUE,
    status VARCHAR(30) DEFAULT 'REGISTERED',
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);
```

## 11.3 Verification History Table

```sql
CREATE TABLE verification_history (
    verification_id INT PRIMARY KEY AUTO_INCREMENT,
    verifier_id INT,
    submitted_file_name VARCHAR(255) NOT NULL,
    submitted_hash CHAR(64) NOT NULL,
    matched_document_id INT NULL,
    result VARCHAR(30) NOT NULL,
    verified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (verifier_id)
        REFERENCES users(user_id)
        ON DELETE SET NULL,

    FOREIGN KEY (matched_document_id)
        REFERENCES documents(document_id)
        ON DELETE SET NULL
);
```

---

# 12. JavaFX Screen Requirements

## Screen 1: Login

Components:

- Application logo/title
- Username/email field
- Password field
- Login button
- Register navigation button

Actions:

```text
Login → Validate → Authenticate → Open Dashboard
```

## Screen 2: Registration

Components:

- Full name
- Email
- Username
- Password
- Confirm password
- Register button

## Screen 3: Dashboard

Recommended cards:

```text
Total Documents
Verified
Not Registered
Total Verification Attempts
```

Also include:

- Recent verification activity
- Navigation sidebar

## Screen 4: Document Registration

Components:

- Choose File button
- Selected file information
- Register Document button
- Progress indicator

Display:

```text
File Name
File Type
File Size
Generated SHA-256
Registration Status
```

## Screen 5: Document Verification

Components:

- Choose file
- Verify button
- Result panel

Result examples:

```text
✓ VERIFIED

Exact matching document found.
```

or

```text
? NOT REGISTERED

No exact matching registered document was found.
```

## Screen 6: History

Use a JavaFX TableView.

Columns:

- File Name
- Result
- Date
- Matching Document ID

---

# 13. Hash Generation Implementation

Use Java's `MessageDigest` with SHA-256.

High-level process:

```text
Selected File
     ↓
Open File Stream
     ↓
Read Bytes in Chunks
     ↓
Update SHA-256 Digest
     ↓
Convert Bytes to Hex String
     ↓
64-character Hash
```

Recommended approach:

- Use buffered/chunk-based reading.
- Do not load a very large file completely into memory.
- Use try-with-resources to close streams.

---

# 14. Verification Logic

## Exact Match Verification

```text
Input File
    ↓
Generate SHA-256 Hash
    ↓
Search documents.document_hash
    ↓
Hash Found?
   /        \
 Yes        No
  ↓          ↓
VERIFIED   NOT REGISTERED
```

## Important Accuracy Rule

Do not use the following logic:

```text
Hash not found → MODIFIED
```

That is technically inaccurate because the file may simply never have been registered.

If you later implement a "compare against a selected original document" feature, then:

```text
Original Hash vs Submitted Hash
```

can produce:

- MATCH
- MODIFIED / MISMATCH

---

# 15. Implementation Phases

## Phase 1 — Environment Setup

### Tasks

- Install JDK.
- Install MySQL.
- Configure JavaFX.
- Create Maven project.
- Add MySQL JDBC dependency.
- Confirm JavaFX application launches.

### Completion Check

A basic JavaFX window should open successfully.

---

## Phase 2 — Database Setup

### Tasks

- Create database.
- Create tables.
- Insert one test admin account.
- Implement DBConnection.
- Test JDBC connectivity.

### Completion Check

A simple Java query should successfully retrieve data from MySQL.

---

## Phase 3 — Authentication

### Tasks

- Create User model.
- Create UserDAO.
- Build registration screen.
- Build login screen.
- Validate user credentials.
- Implement role-based navigation.

### Completion Check

Users can register and log in.

---

## Phase 4 — Dashboard

### Tasks

- Build JavaFX layout.
- Create sidebar.
- Add statistics cards.
- Fetch statistics from database.
- Add navigation.

### Completion Check

Dashboard values update based on database data.

---

## Phase 5 — Document Registration

### Tasks

- Implement FileChooser.
- Validate selected file.
- Read file metadata.
- Generate SHA-256.
- Check duplicate hash.
- Save document metadata.

### Completion Check

A document can be registered and its hash appears in the database.

---

## Phase 6 — Verification

### Tasks

- Select verification file.
- Generate SHA-256.
- Search database.
- Display result.
- Save verification history.

### Completion Check

An identical registered file returns VERIFIED.

A different file returns NOT REGISTERED.

---

## Phase 7 — History and Search

### Tasks

- Add TableView.
- Load verification records.
- Add search by file name.
- Add filtering by result.

---

## Phase 8 — Testing and Error Handling

Test:

- Empty login fields
- Wrong password
- Duplicate username
- Duplicate document
- Unsupported file
- Missing file
- Database unavailable
- Identical file verification
- Different file verification

---

## Phase 9 — UI Improvement

After functionality is complete:

- Improve CSS
- Add icons
- Improve spacing
- Add loading indicators
- Improve result cards

Do not spend most of the project time on design before the logic works.

---

# 16. Java Concepts Demonstrated

| Concept | Project Usage |
|---|---|
| Classes and Objects | User, Document, Verification |
| Encapsulation | Private fields and getters/setters |
| Inheritance | Optional role hierarchy |
| Interfaces | Optional service contracts |
| Collections | Lists and tables |
| Exception Handling | File and database errors |
| File Handling | Reading documents |
| JDBC | MySQL communication |
| JavaFX | Desktop interface |
| Event Handling | Button and UI actions |
| Date-Time API | Registration timestamps |
| Hashing | SHA-256 |
| Concurrency | Background file processing |

---

# 17. Do's

## Architecture

- DO separate UI, business logic, and database code.
- DO use DAO classes for database operations.
- DO use service classes for business logic.
- DO keep controllers focused on UI events.

## Database

- DO use PreparedStatement.
- DO close database resources properly.
- DO use unique constraints for important values.
- DO validate data before insertion.

## File Processing

- DO use a file size limit appropriate for your project.
- DO use try-with-resources.
- DO read large files in chunks.
- DO validate the selected file before processing.

## UI

- DO show progress when processing a file.
- DO display clear verification results.
- DO disable buttons when required.
- DO provide useful error messages.

## Security

- DO hash user passwords rather than storing plain text.
- DO keep database credentials outside public repositories.
- DO treat file names and metadata as untrusted input.

## Development

- DO implement one module at a time.
- DO test after every module.
- DO commit working versions to Git.
- DO prepare sample documents for demonstration.

---

# 18. Don'ts

## Do Not Overclaim the Security

- DON'T say SHA-256 alone proves who created a document.
- DON'T say a hash not found definitely means the document was modified.
- DON'T call a plain hash a "digital signature".
- DON'T claim blockchain-level immutability unless blockchain is actually implemented.

## Do Not Mix Everything in One Class

Avoid:

```text
Main.java
    - UI
    - SQL
    - hashing
    - validation
    - business logic
```

Instead separate responsibilities.

## Database

- DON'T concatenate user input directly into SQL.
- DON'T store duplicate document hashes unnecessarily.
- DON'T expose database passwords in GitHub.

## UI

- DON'T build every screen before testing functionality.
- DON'T use excessive animations.
- DON'T make the UI more important than the working verification logic.

## Scope

- DON'T add AI, blockchain, OCR, cloud storage, QR, and PDF generation all at once.
- DON'T attempt real legal document verification.
- DON'T attempt to support every possible file format in version 1.

---

# 19. Recommended Development Order

Follow this order exactly:

```text
1. Java + JavaFX Setup
2. MySQL Database Setup
3. JDBC Connection
4. User Model and Authentication
5. JavaFX Navigation
6. Dashboard
7. File Selection
8. SHA-256 Hashing
9. Document Registration
10. Duplicate Detection
11. Document Verification
12. Verification History
13. Testing
14. UI Polish
15. Optional Features
```

---

# 20. Suggested Minimum Features for Final Submission

For a strong mini-project submission, complete these features:

- User registration
- Login
- Role-based access
- JavaFX dashboard
- File selection
- SHA-256 hash generation
- Document registration
- Duplicate detection
- Document verification
- Verification history
- MySQL database
- JDBC integration
- Error handling

This is sufficient to demonstrate a complete Java application.

---

# 21. Optional Version 2 Features

After the main project is stable, add:

## QR Verification

Generate a unique document identifier and QR code.

## Verification Receipt

Create a receipt containing:

```text
Verification ID
File Name
Document Hash
Result
Timestamp
```

## Charts

Use JavaFX charts to show:

- Verification attempts by day
- Verified vs not registered
- Total registered documents

## Background Processing

Use a JavaFX Task for long-running hash generation so the UI remains responsive.

---

# 22. Testing Checklist

## Authentication

- [ ] Register valid user
- [ ] Reject duplicate username
- [ ] Reject duplicate email
- [ ] Reject invalid password confirmation
- [ ] Login with valid credentials
- [ ] Reject invalid credentials

## Documents

- [ ] Select valid document
- [ ] Reject unsupported type
- [ ] Generate correct SHA-256
- [ ] Store metadata
- [ ] Detect duplicate hash

## Verification

- [ ] Verify exact registered file
- [ ] Test unregistered file
- [ ] Save verification history
- [ ] Display correct result

## Database

- [ ] Handle connection failure
- [ ] Prevent SQL injection with PreparedStatement
- [ ] Close ResultSet/Statement/Connection resources

---

# 23. Final Demonstration Flow

Use this flow during your project demonstration:

```text
1. Open DocuVerify

2. Register or log in

3. Select a sample document

4. Register the document

5. Show generated SHA-256 hash

6. Show the document record in the system

7. Open verification module

8. Upload the exact same document

9. Show VERIFIED result

10. Upload a different document

11. Show NOT REGISTERED result

12. Open verification history

13. Show both verification records
```

This demonstrates the complete end-to-end workflow clearly.

---

# 24. Final Project Definition

## DocuVerify

**A JavaFX-based secure digital document registration and verification application that uses SHA-256 hashing to identify exact document matches and maintain a verification audit history.**

### Core Workflow

```text
REGISTER DOCUMENT

Select File
    ↓
Validate File
    ↓
Generate SHA-256
    ↓
Check Duplicate
    ↓
Store Hash + Metadata
    ↓
Document Registered
```

```text
VERIFY DOCUMENT

Select File
    ↓
Generate SHA-256
    ↓
Search Registered Hash
    ↓
Exact Match?
   /          \
 Yes          No
  ↓            ↓
VERIFIED   NOT REGISTERED
    ↓
Store Verification History
```

---

# 25. Final Recommendation

Keep the first version focused on **exact document integrity verification**.

The strongest combination for this mini project is:

> **JavaFX + Core Java + OOP + JDBC + MySQL + File Handling + SHA-256 + Exception Handling**

Build the core workflow first. Make every module work independently. Then integrate them and improve the UI.

**Working functionality is more important than adding too many advanced features.**
