## RD_INFRO_TECHNOLOGY – Java Developer Internship Tasks

This repository contains the **Java Developer Internship tasks** completed as part of the RD INFRO TECHNOLOGY internship program.  
Each folder (for example `Task_1` to `Task_8`) contains the source code and resources for an individual assignment.

### Repository Structure

- **`Task_1/`** – Task 1 (Java basics / introduction).  
- **`Task_2/`** – Task 2 (OOP concepts / classes & objects).  
- **`Task_3/`** – Task 3 (collections / data structures).  
- **`Task_4/`** – Task 4 (file handling / I/O).  
- **`Task_5/`** – Task 5 (exception handling / debugging).  
- **`Task_6/`** – Task 6 (multithreading / concurrency).  
- **`Task_7/`** – Task 7 (Java with database / JDBC).  
- **`Task_8/`** – Task 8 (mini project / final assignment – computer stock manager with backend and frontend).  

> **Note**: The short descriptions above are placeholders. Update each line to match the actual problem statement of your tasks if they differ.

---

### Prerequisites

- **Java JDK**: Version 8 or higher (recommended: JDK 11 or JDK 17)  
- **Build / Run tools** (any one):
  - An IDE like **IntelliJ IDEA**, **Eclipse**, or **NetBeans**, or  
  - **Command line** with `javac` and `java` in the system PATH  

Additional tools used in some tasks:

- **Maven** (for the Spring Boot backend in `Task_8/Internship`)  
- **Node.js** and **npm** (for the React frontend in `Task_8/computer-stock-manager-frontend`)  

---

### How to Run the Java Tasks

#### Option 1: Using an IDE

1. **Clone** the repository:
   ```bash
   git clone https://github.com/<your-username>/RD_INFRO_TECHNOLOGY.git
   ```
2. **Open** the repository folder in your preferred Java IDE.
3. Navigate to the desired task folder (for example `Task_1`).
4. Open the main class file (for example `Main.java` or the file mentioned in that task).
5. Run the program from the IDE.

#### Option 2: Using Command Line

1. Open a terminal / PowerShell in the repository folder.
2. Change into a task folder, for example:
   ```bash
   cd Task_1
   ```
3. Compile the Java files:
   ```bash
   javac *.java
   ```
4. Run the main class (replace `Main` with your actual main class name):
   ```bash
   java Main
   ```

Repeat the same steps for other task folders (`Task_2`, `Task_3`, …, `Task_8`).

---

### Task 8 – Computer Stock Manager (Overview)

`Task_8` contains a small **Computer Stock Manager** application with:

- **Backend**: Spring Boot project in `Task_8/Both frontend and backend files/Internship`  
- **Frontend**: React application in `Task_8/Both frontend and backend files/computer-stock-manager-frontend`  

High level features:

- Manage computer devices (create, read, update, delete)  
- Store data in a database (configured via Spring Boot and `application.properties`)  
- REST API endpoints used by the React frontend  

To run the backend:

1. Go to the Spring Boot project:
   ```bash
   cd Task_8/Both frontend and backend files/Internship
   ```
2. Build and run with Maven:
   ```bash
   mvn spring-boot:run
   ```
   or run the `ComputerStockManagerApplication` main class from your IDE.

To run the frontend:

1. Go to the React app:
   ```bash
   cd Task_8/Both frontend and backend files/computer-stock-manager-frontend
   ```
2. Install dependencies and start the dev server:
   ```bash
   npm install
   npm run dev
   ```
3. Open the URL shown in the terminal (usually `http://localhost:5173/`) in your browser.

Make sure the backend is running so the frontend can communicate with the API.

---

### Learning Outcomes

Across these tasks, the main **Java and full‑stack concepts** covered include:

- Core Java syntax and basic programs  
- Object-Oriented Programming (classes, objects, inheritance, polymorphism, encapsulation)  
- Collections framework and data handling  
- File handling and input/output  
- Exception handling and debugging  
- Unit and integration testing (for Spring Boot)  
- Multithreading and concurrency (if used in your tasks)  
- Database connectivity and JPA/JDBC  
- Building RESTful web services with Spring Boot  
- Frontend development with React and API integration  

---

### Author

- **Name**: *Your Name*  
- **Role**: Java Developer Intern – RD INFRO TECHNOLOGY  
- **GitHub**: `https://github.com/<your-username>`

---

### License

This repository is created for **learning and internship evaluation** purposes.  
You may use the code for study and reference. For any other use, please contact the author.

