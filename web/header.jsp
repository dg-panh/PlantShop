<%-- 
    Document   : header
    Created on : 21 Feb, 2022, 12:52:48 AM
    Author     : Panh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Component</title>
        <link rel="stylesheet" href="css/mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href=""><img src="images/logo.jpg"></a> </li>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="registration.jsp">Register</a></li>
                    <li><a href="login.jsp" >Login</a></li>
                    <li><form action="mainController" method="POST" class="form">
                            <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch") == null) ? "" : request.getParameter("txtsearch") %>">
                            <select name="searchby">
                                <option value="byname">By name</option>
                                <option value="bycate">By category</option>
                            </select>
                            <input type="submit" value="Search" name="action" >
                        </form>
                    </li>
                </ul>
            </nav>
        </header>
    </body>
</html>
