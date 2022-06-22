<%-- 
    Document   : error
    Created on : 12 Mar, 2022, 1:29:11 PM
    Author     : Panh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %> 
        </header>
        <p><font color="red"><%= request.getAttribute("error") %></font> </p>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
