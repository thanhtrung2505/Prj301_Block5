<%-- 
    Document   : add
    Created on : Dec 9, 2021, 4:39:20 AM
    Author     : Do Phong PC
--%>

<%@page import="model.Department"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            ArrayList<Department> depts = (ArrayList<Department>) request.getAttribute("depts");
        %>
    </head>
    <body>
        <form method="POST" action="add">
            Id:<input type="text" name="id"/> <br/>
            Name: <input type="text" name="name" /> <br/>
            Gender: <input type="radio" name="gender" value="male" />Male
            <input type="radio" name="gender" value="female" />Female <br/>
            Dob: <input type="date" name="dob"/>(yyyy-MM-dd)<br/>
            Department: 
            <select name="did">
                <% for (Department d : depts) {
                %>
                <option value="<%=d.getId() %>"><%=d.getName() %></option>
                <%}%>
            </select>
            <br/>
            <input type="submit" value="Save"/>
        </form> 
    </body>
</html>