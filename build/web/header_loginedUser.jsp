<%-- 
    Document   : header_loginedUser
    Created on : 21 Feb, 2022, 5:16:50 PM
    Author     : Panh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Login Component Page</title>
        <link rel="stylesheet" href="css/mycss.css" type="text/css"/>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="changeProfile.jsp">Change profile</a></li>
                <li><a href="">Completed orders</a></li>
                <li><a href="">Canceled orders</a></li>
                <li><a href="">Processing orders</a></li>
                <li>From <input type="date" name="from"> to <input type="date" name="to">
                    <input type="submit" value="Search">
                </li>
            </ul>
        </nav>
    </body>
</html>
