# ExamServer

Spring Boot Exam Portal is a web application for managing exams and grades. It allows users to create and take exams, view their grades and exam history, and manage their account information.


## Installation

### Clone the repository:

	git clone (https://github.com/HarshalThitame/Exam-Portal-Server.git).
	
### Navigate to the project directory:

	cd Exam-Portal
	
### Build the project:

java -jar target/exam-portal-0.0.1-SNAPSHOT.jar

Open a web browser and go to (http://localhost:8080) to access the application.

#Spring Boot Exam Portal Controller
This Spring Boot project is an exam portal application that allows students to take online exams and teachers to create and manage exams. This particular file documents the controller layer of the application.

##Technologies Used
Java 11
Spring Boot 2.5.5
Maven
MySQL

##Controller Functionality
The controller layer of this application is responsible for handling HTTP requests and generating appropriate responses. The following are the functionalities implemented in the controller layer:

###Student controller

View exams available to the student
Start an exam
Submit an exam
View exam results

###Teacher controller

Create an exam
Edit an exam
Delete an exam
View a list of all exams

##Endpoints
The following are the endpoints implemented in the controller layer:

/api/student/exams - GET request to view all available exams to the student

/api/student/exams/{examId} - GET request to view details of a specific exam for the student

/api/student/exams/{examId}/start - POST request to start an exam

/api/student/exams/{examId}/submit - POST request to submit an exam

/api/student/exams/{examId}/results - GET request to view the results of an exam for the student

/api/teacher/exams - GET request to view a list of all exams for the teacher

/api/teacher/exams - POST request to create a new exam

/api/teacher/exams/{examId} - PUT request to update an exam

/api/teacher/exams/{examId} - DELETE request to delete an exam

##Dependencies
The following dependencies are used in this project:

Spring Boot Starter Web
Spring Boot Starter Data JPA
MySQL Driver
Spring Boot Starter Test

##How to Run
To run this application, make sure you have Java 11 and MySQL installed on your system. Follow the steps below:

Clone the repository
Create a new MySQL database named exam_portal
Run the SQL script src/main/resources/data.sql to create the necessary tables and sample data
Update the MySQL connection details in src/main/resources/application.properties if necessary
Open a terminal and navigate to the project directory
Run mvn spring-boot:run to start the application
Open a web browser and go to http://localhost:8080



## Usage

### Creating an Exam
To create an exam, log in as an administrator and go to the "Exams" page. Click the "Create Exam" button and fill in the exam details, including the exam name, duration, and questions.


### Taking an Exam
To take an exam, log in as a student and go to the "Exams" page. Find the exam you want to take and click the "Take Exam" button. Answer the questions and submit your answers before the exam time runs out.

### Viewing Grades and Exam History
To view your grades and exam history, log in as a student and go to the "Grades" page. You can view your grades for each exam and your overall GPA.

### Contributing
If you would like to contribute to this project, please fork the repository and submit a pull request. We welcome all contributions, including bug fixes, new features, and documentation improvements.

### License
This project is licensed under the MIT License. See the LICENSE file for details.
