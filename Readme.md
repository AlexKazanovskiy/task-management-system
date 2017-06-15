Use Maven for build solution (mvn clean install). Application uses built-in Apache Tomcat in Spring Boot. For using deployed application have to install MySQL and change connection with DB in application.properties.
 
For test try GET request:
```json
http://localhost:8080/api/developer
```
but after authorization that describes in SECURITY.md.

Another requests you can find [here](https://github.com/AlexKazanovskiy/task-management-system/tree/master/atms/src/main/java/com/atms/controller) 
