# Basic Auth logic using Java Servlets, JSP, Hibernate and JPA
- Build the project using Maven in your prefered IDE.
- After deploying the war files in tomcat, you can access the application on port 8080 of your localhost.
- The `src/main/webapp/index.jsp` is the landing page and contains links to the protected routes.
- `src/main/webapp/protected.jsp` is accessible only after successful login.
- If user has not logged in already he will be redirected to `src/main/webapp/login.jsp`.
- login.jsp sends a POST request to `src/main/java/yashv/demo/basic_login/Login.java` which validates the user credentials and redirects to protected.jsp if the credentials are valid.
- The username is stored as a request session attribute and is used to verify if the user has logged in or not.
- The `src/main/java/yashv/demo/basic_login/Logout.java` servlet clears the session attribute and redirects to index.jsp.

**This in no way is a very secure design, however, it is a simple and beginner friendly template of how authentication system can be designed using core Java.**