<%-- 
    Document   : detail
    Created on : Dec 13, 2021, 2:04:43 AM
    Author     : Do Phong PC
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.DetailAttandance"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%ArrayList<DetailAttandance> DetailClass = (ArrayList<DetailAttandance>) request.getAttribute("detailAt");%>
    </head>
    <body>
        <form method="POST" action="add">
            <table border="1px">
                <tr>
                    <td>Group</td>
                    <td>Date</td>
                    <td>Slot</td>
                    <td>Student</td>
                    <td>Code</td>
                    <td>Present</td> 
                    <td>Subject</td>
                    <td>Room</td> 

                </tr>
                <% for (DetailAttandance x : DetailClass) {%>     
                <tr>
                    <td><%=x.getGroupName()%></td>
                    <td> <input type="date" value="<%=x.getDate()%>" /></td>
                    <td><%=x.getSlot()%> </td>
                    <td><%=x.getStuName()%></td>
                    <td><%=x.getStuCode()%></td>
                    <td>
                        <input type="checkbox" 
                               <%=x.isPresent() ? "checked=\"checked\"" : ""%>  /></td>
                    <td><%=x.getSubName()%></td>
                    <td><%=x.getClassName()%></td>
                      <td>
                    <input type="button" value="Edit" onclick="window.location.href='edit?id=<%=x.getStuName()%>'"/> 
                    <input type="button" value="Delete" onclick="removeStudent(<%=x.getStuCode()%>);"/> 
                </td>
                </tr>
                <%}%>
            </table>
            <input type="submit" value="Return" onclick="window.location.href='http://localhost:43899/Prj301_Block5/attandance/add.jsp'"/>
        </form>
    </body>
</html>
