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
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            ArrayList<Group> groups = (ArrayList<Group>) request.getAttribute("groups");
            ArrayList<Class1> classe1 = (ArrayList<Class1>) request.getAttribute("slots");
            ArrayList<Class1> classe2 = (ArrayList<Class1>) request.getAttribute("dates");
            ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
            
            String groupid = (String) request.getAttribute("groupid");           
            String dateid = (String)request.getAttribute("dateid");          
            String slotid =  (String)request.getAttribute("slotid");       
        %>
    </head>
    <body>
        <form method="POST" action="list">           
            Group: 
            <select name="groupid">                
                <% for (Group d : groups) {%>                              
                <option value="<%=d.getGroupid()%>"><%=d.getGroupName()%></option>
                <%}%>
            </select>
            Date: 
            <select name="dateid">

                <% for (Class1 d : classe2) {%>             
                <option value="<%=d.getDate()%>"><%=d.getDate()%></option>
                <%}%>
            </select>
            Slot: 
            <select name="slotid">

                <% for (Class1 d : classe1) {%>               
                <option value="<%=d.getSlot()%>"><%=d.getSlot()%></option>
                <%}%>
            </select>
            <br/>         
            <input type="submit" value="Search"/>
        </form> 

        <form method="POST" action="attan">          
            <%if (students.size() > 0) { %>  
            <br/>
            Attendance  Group:<%=groupid%> Date:<%=dateid%> Slot:<%=slotid%> <br/>     
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
            </table>
            <input type="submit" value="Save"/>
            <%} else {%>      
            <%}%>
        </form>
    </body>
</html>