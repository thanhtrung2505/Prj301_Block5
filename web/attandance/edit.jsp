<%-- 
    Document   : edit
    Created on : Dec 1, 2021, 2:54:55 PM
    Author     : Sap-lap
--%>

<%@page import="model.DetailAttendance"%>
<%@page import="model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%ArrayList<DetailAttendance> DetailClass = (ArrayList<DetailAttendance>) request.getAttribute("edit");%>
    </head>
    <body>
        <form method="POST" action="add">
            <table border="1px">
                 <% for (DetailAttendance x : DetailClass) {%>   
                <tr>
                    <td>Group</td>
                    <td><%=x.getGroupName()%></td>
                </tr>                
                <tr>
                    <td>Date</td>
                    <td> <input type="date" value="<%=x.getDate()%>" /></td>             
                </tr>
                <tr>
                    <td>Slot</td>
                    <td><%=x.getStuName()%></td>
                </tr
                <tr>
                    <td>Student</td>
                    <td><%=x.getSlot()%> </td>
                </tr>
                <tr>
                    <td>Code</td>
                    <td><%=x.getStuCode()%></td>
                </tr>
                <tr>
                    <td>Present</td> 
                <input type="checkbox" 
                       <%=x.isPresent() ? "checked=\"checked\"" : ""%>  /></td>
                </tr>
                <tr>
                    <td>Subject</td>
                    <td><%=x.getSubName()%></
                </tr>
                <tr>
                    <td>Room</td> 
                    <td><%=x.getClassName()%></td>  
                </tr> 
                <%}%>
            </table>          
        </form>
        <input type="submit" value="Return" onclick="window.location.href = 'http://localhost:43899/Prj301_Block5/list'"/>
    </body>
</html>
