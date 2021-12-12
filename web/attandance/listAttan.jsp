<%-- 
    Document   : listAttan
    Created on : Dec 12, 2021, 4:21:20 AM
    Author     : Do Phong PC
--%>

<%@page import="model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
          <%   ArrayList<Student>students= (ArrayList<Student>) request.getAttribute("students");                   
          %>
    </head>
    <body>
           <form method="POST" action="attan">          
        <%if(students.size()>0){ %>          
            <table border="1px">
            <tr>
                <td>Student</td>
                <td>Code</td>
                <td>Present</td>
            </tr>
            <c:forEach items="${requestScope.students}" var="s" >
                <tr>
                    <td>${s.stuName}<input type="hidden" value="${s.stuID}" name="id" /></td>
                    <td>${s.stuCode}<input type="hidden" name="code" /></td>
                    <td><input type="checkbox" name="present${s.stuID}" value="present" /></td>
                </tr>  
            </c:forEach>
            <input type="submit" value="Save"/>
        </table>
              <%}else{%>
        No result
        <%}%>
        </form>
    </body>
</html>
