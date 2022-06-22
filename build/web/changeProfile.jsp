<%-- 
    Document   : changeProfile
    Created on : 10 Mar, 2022, 1:58:19 AM
    Author     : Panh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Profile</title>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <p style="color: red;">You must login to change profile</p>
        <%
            } else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        
        <section>
            <form action="mainController" method="POST" class="form">
                <h1>Change Profile</h1>
                <table>
                    <tr><input type="hidden" name="txtemail" value="<%= session.getAttribute("email") %>"/></tr>
                    <tr>
                        <td>Full name</td>
                        <td><input type="text" name="txtfullname" required="" value="<%= name %>"></td>
                    </tr>
                    
                    <tr>
                        <td>Phone</td>
                        <td><input type="text" name="txtphone" required="" value=""></td>
                    </tr>
                    <tr>
                        <td>Current Password</td>
                        <td><input type="password" name="txtcurrentpassword" required=""></td>
                    </tr>
                    <tr>
                        <td>New Password</td>
                        <td><input type="password" name="txtnewpassword" required=""></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><font color="red"><%= (request.getAttribute("msg") != null) ? request.getAttribute("msg") : "" %></font></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Update Profile" name="action"></td>
                    </tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>    
        </footer>
        <%
            }
        %>
    </body>
</html>
