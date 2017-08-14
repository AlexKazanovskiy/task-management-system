# Task management system
This is RESTful services for task management system.
### Technologies 
* Spring MVC 
* Spring Boot
* Spring Data JPA
* Spring Security
* MySQL

### Deployment 
Use Maven for build solution (mvn clean install). Application uses embedded Apache Tomcat. For using deployed application have to install MySQL and change connection with DB in application.properties.
 
For test try GET request:
```json
http://localhost:8080/api/developer
```
but after authorization that describes in SECURITY.md.
