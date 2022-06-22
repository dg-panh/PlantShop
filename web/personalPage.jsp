<%-- 
    Document   : personalPage
    Created on : 21 Feb, 2022, 5:17:08 PM
    Author     : Panh
--%>

<%@page import="panh.dao.OrderDAO"%>
<%@page import="panh.dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal Page</title>
        <link rel="stylesheet" href="css/mycss.css" type="text/css"/>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <p style="color: red;">You must login to view personal page</p>
        <%
            } else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back</h3>
            <h3><a href="mainController?action=Logout">Logout</h3>
        </section>
        <section>
            <!-- load all orders of the user at here -->
            <%
                String email = (String) session.getAttribute("email");
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "Processing", "Completed", "Canceled"};
                if (list != null && !list.isEmpty()) {
                    for (Order order : list) { %>
                    <table class="order">
                        <tr>
                            <td>Order ID</td>
                            <td>Order Date</td>
                            <td>Ship Date</td>
                            <td>Order's status</td>
                            <td>Action</td>
                        </tr>
                        <tr>
                            <td><%= order.getOrderID() %></td>
                            <td><%= order.getOrderDate() %></td>
                            <td><%= order.getShipDate() %></td>
                            <td><%= status[order.getStatus()] %>
                                <br/><% if(order.getStatus() == 1) %><a href="#">Cancel order</a>
                            </td>
                            <td><a href="orderDetail.jsp?orderid=<%= order.getOrderID() %>">Detail</td>
                        </tr>
                    </table>
                    <% }
                } else {
            %> 
                    <p>You don't have any order</p>
        </section>
        <footer>
            <%@include file="footer.jsp" %>    
        </footer>
        <%
                }
            }
        %>
    </body>
</html>
