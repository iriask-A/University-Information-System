# Information System of a Research-Oriented University

## Project Overview
This is a console-based Object-Oriented Programming (OOP) project developed in Java. The system is designed to manage the complex operations of a research-oriented university, including academic registration, performance tracking, and extensive research management.

## 👥 Team Members
1. **Inkar Askarbekova** - Team Leader
2. **Kassym Gulnaz**
3. **Daniyarbekov Kairat**
4. **Kassymbay Nazym**
5. **Nuradin Alinur**

## 🛠 Project Components
The project is divided into three primary phases:
* **Phase A: Diagrams** – Architecture design using Use Case and UML Class Diagrams (StarUML).
* **Phase B: Models** – Implementation of classes, interfaces, enumerations, and custom exceptions.
* **Phase C: Demo** – A console-based test class to demonstrate system functionality.

## 🚀 Key Requirements & Functionality

### 1. Research Management (Priority)
* **Researcher Roles:** The system supports Researchers who can be Teachers, Students (4th year), or dedicated Employees.
* **H-Index Validation:** 4th-year students are assigned a supervisor; if the supervisor's h-index is < 3, a custom exception is thrown.
* **Research Papers:** Tracks metadata including name, authors, journal, citations, and DOI.
* **Analytics:** Supports printing top-cited researchers of the year and sorting papers by citations or publication date.

### 2. Academic Core
* **Course Registration:** Students can register for courses (max 21 credits).
* **Grading System:** Teachers manage marks consisting of 1st attestation, 2nd attestation, and the final exam.
* **User Roles:** Distinct functionalities for Admins, Managers (OR/Department), Teachers, and Students.

### 3. Technical Implementation
* **Design Patterns:** Integration of at least 4 design patterns (e.g., Decorator, Singleton, or Factory).
* **Data Persistence:** Proper usage of Serialization for data storage.
* **Standard API:** Heavy use of `Comparable`, `Comparator`, `Collections`, and `Enumerations`.

## 📅 Deadlines & Deliverables
* **Week 15:** Submission of Use Case and Class Diagrams.
* **Final Exam:** Submission of the complete Project (Zip), Report (PDF), and Presentation (3-4 slides).

## 🏗 System Architecture
The system utilizes a hierarchy of classes including:
* **Users:** `User`, `Admin`, `Student`, `Teacher`, `Manager`, `Employee`.
* **Academic:** `Course`, `Mark`, `Lesson`.
* **Research:** `Researcher`, `ResearchPaper`, `ResearchProject`.
