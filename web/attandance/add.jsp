<%-- 
    Document   : add
    Created on : Dec 9, 2021, 4:39:20 AM
    Author     : Do Phong PC
--%>

<%@page import="model.Student"%>
<%@page import="model.Class1"%>
<%@page import="model.Group"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            ArrayList<Group> groups = (ArrayList<Group>) request.getAttribute("groups");
            ArrayList<Class1>classe1= (ArrayList<Class1>) request.getAttribute("slots");
            ArrayList<Class1>classe2= (ArrayList<Class1>) request.getAttribute("dates");                      
        %>
    </head>
    <body>
        <form method="POST" action="list">           
            Group: 
            <select name="groupid">
                <% for (Group d : groups) {
                %>
                <option value="<%=d.getGroupid()%>"><%=d.getGroupName()%></option>
                <%}%>
            </select>
            Date: 
            <select name="dateid">
                <% for (Class1 d : classe2) {
                %>
                <option value="<%=d.getDate()%>"><%=d.getDate()%></option>
                <%}%>
            </select>
            Slot: 
            <select name="slotid">
                <% for (Class1 d :classe1) {
                %>
                <option value="<%=d.getSlot()%>"><%=d.getSlot()%></option>
                <%}%>
            </select>
            <br/>         
            <input type="submit" value="Search"/>
        </form> 
    </body>
</html>