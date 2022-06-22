<%-- 
    Document   : login
    Created on : 21 Feb, 2022, 1:03:30 AM
    Author     : Panh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %> 
        </header>

        <section>
            <!-- //Require user to logout first -->
            <%
                session = request.getSession(false);
                if (session != null && session.getAttribute("email") != null) {
                    %><p><font color="red">Must logout before login new account</font> </p><%
                } else {
                    %>
                    <form action="mainController" method="POST" class="form">
                        <table>
                            <tr>
                                <td>Email</td>
                                <td><input type="text" name="email" required=""></td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td><input type="password" name="password" required=""></td>
                            </tr>
                            <tr>
                                <td colspan="2"><input type="submit" name="action" value="Login"></td>
                            </tr>
                        </table>
                    </form>
                    <%
                }
            %>
            
        </section>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
