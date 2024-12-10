# backend

The Vehicle Weight Detection System is an integrated software application designed to manage and monitor vehicle compliance with weight and speed restrictions across various routes. The system is implemented with a robust Java backend using IntelliJ and connects to a MySQL database for secure data storage.
To run this program you need first to have Database Management such MySQL or postgresql then you have to go to env file in the resource folder to set the basic configuration that include database or schema name then username and password for you own Database Management System.
After that change ddl line in resources/application.properties to spring.jpa.hibernate.ddl-auto=create-drop, then run the program.
The next step is to test the program to test it go to controllers repository where can add,delete, select and update routes, vehicles and tickets through the Endpoints.
To set the API I will refer you to VDS_API PDF that will be uploaded with the project.
