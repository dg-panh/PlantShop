<%-- 
    Document   : index
    Created on : 21 Feb, 2022, 1:00:27 AM
    Author     : Panh
--%>

<%@page import="panh.dao.PlantDAO"%>
<%@page import="panh.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="css/mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %> 
        </header>
        
        <section>
            <%
                String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("searchby");
                ArrayList<Plant> list;
                String[] status = {"Out of stock", "Available"};
                if (keyword == null && searchby == null) {
                    list = PlantDAO.getPlants("", "");
                } else {
                    list = PlantDAO.getPlants(keyword, searchby);
                }
                if (list != null && !list.isEmpty()) {
                    for (Plant p : list) {
                        %> 
                        <div class="product">
                            <p><img src="<%= p.getImgPath() %>" class="plantimg"/></p>
                            <p>Product ID: <%= p.getId() %></p>
                            <p>Product Name: <%= p.getName() %></p>
                            <p>Price: <%= p.getPrice() %></p>
                            <p>Status: <%= status[p.getStatus()] %></p>
                            <p>Category: <%= p.getCateName() %></p>
                            <p><a href="">Add to cart</a></p>
                        </div>
                        <%
                    }
                } else {
                    %> <p>Sorry, no product found for "<%= keyword %>"</p> <%
                }
                
            %>
        </section>
        
        <footer>
            <%@include file="footer.jsp" %> 
        </footer>
    </body>
</html>
