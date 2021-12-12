<%-- 
    Document   : edit
    Created on : Dec 1, 2021, 2:54:55 PM
    Author     : Sap-lap
--%>

<%@page import="model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Department"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            ArrayList<Department> depts = (ArrayList<Department>) request.getAttribute("depts");
            Student s = (Student)request.getAttribute("s");
        %>
    </head>
    <body>
        <form method="POST" action="edit">
            Id: <%=s.getId()%>
            <input type="hidden" name="id" value="<%=s.getId()%>" /> <br/>
            Name: <input type="text" name="name" value="<%=s.getName()%>"  /> <br/>
            Gender: 
            <input type="radio" name="gender" 
                   <%=s.isGender()?"checked=\"checked\"":"" %>
                   value="male" />Male
            <input type="radio" name="gender" 
                   <%=!s.isGender()?"checked=\"checked\"":"" %>
                   value="female" />Female 
            <br/>
            Dob: <input type="date" name="dob" value="<%=s.getDob()%>" />(yyyy-MM-dd)<br/>
            Department: 
            <select name="did">
                <% for (Department d : depts) {
                %>
                <option 
                    <%= d.getId() == s.getDept().getId() ?"selected=\"selected\"":"" %>
                    value="<%=d.getId() %>"><%=d.getName() %></option>
                <%}%>
            </select>
            <br/>
            <input type="submit" value="Save"/>
        </form> 
    </body>
</html>
