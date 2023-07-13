# Book store API V 1.0:
This is an api(application programming interface) to manage the ordering of books through a web application. It is made up of endpoints to create, read, update and delete details in the database. The database name is bookapi_db. The list below are the api endpoints with their coresponding screenshots. The api documentation was done with the postman which is an application for testing api endpoints.
The tech stack used was the Java version 11 programming language, Spring Framework version 2.6.12 for web, MySql workbench for the database, Java mail sender for sending emails, Jason Web token for generating secure tokens, Spring security for security all the endpoints. Follow the steps below to access the functionalities of the api.
1. Create the bookapi_db in the database of mysql workbench
2. define your security credentials in the application.properties of the app
- spring.datasource.url = jdbc:mysql://localhost:3309/bookapi_db
- spring.datasource.username = root
- spring.datasource.password = 

4. use the run button of your intelliJ idea if thus the ide you are using and likewise if the ide is different
To perform various functionalisties kinldy follow through accordingly:
5. copy this link(http://localhost:8080/bookstore-api/user/add-new). To access the api on the network head to command prompt type ip config and enter. The with the ip provided beside the IPv4 address eg. 192.168.1.100:8080. Now replace the localhost:8080 with the ip provided. 

## CODE IN ACTION ENGLISH
![SIGNUP endpoint] (http://localhost:8080/bookstore-api/user/add-new)
