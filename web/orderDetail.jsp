<%-- 
    Document   : orderDetail
    Created on : 8 Mar, 2022, 5:43:10 PM
    Author     : Panh
--%>

<%@page import="panh.dao.OrderDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="panh.dto.OrderDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link rel="stylesheet" href="css/mycss.css" type="text/css"/>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <p style="color: red;">You must login to view order detail</p>
        <%
            } else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back</h3>
            <h3><a href="mainController?action=Logout">Logout</h3>
            <a href="personalPage.jsp">View all orders</a>
        </section>
        <section>
            <!-- load order detail of the order at here -->
            <%
                String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> list = OrderDAO.getOrderDetail(orderID);
                    if (list != null && !list.isEmpty()) {
                        int money = 0;
                        for (OrderDetail orderDetail : list) { %>
                        <table class="order">
                            <tr>
                                <td>Order ID</td>
                                <td>Plant ID</td>
                                <td>Plant Name</td>
                                <td>Image</td>
                                <td>Price</td>
                                <td>Quantity</td>
                            </tr>
                            <tr>
                                <td><%= orderDetail.getOrderID() %></td>
                                <td><%= orderDetail.getPlantID() %></td>
                                <td><%= orderDetail.getPlantName()%></td>
                                <td><img src="<%= orderDetail.getImgPath() %>" class="plantimg"/></td>
                                <td><%= orderDetail.getPrice() %></td>
                                <td><%= orderDetail.getQuantity() %></td>
                                <% money = money + orderDetail.getPrice() * orderDetail.getQuantity(); %>
                            </tr>
                        </table>
                        <% }
                        %> <h3>Total money: <%= money %></h3> <%
                    }
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
