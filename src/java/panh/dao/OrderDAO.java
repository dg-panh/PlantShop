/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import panh.dto.Order;
import panh.dto.OrderDetail;
import panh.utils.DBUtils;

/**
 *
 * @author Panh
 */
public class OrderDAO {
    private static final String GET_ORDER = "SELECT OrderID, OrdDate, shipdate, status, AccID FROM Orders \n" +
                                            "WHERE AccID = (SELECT AccID FROM Accounts WHERE email = ?)";
    private static final String GET_ORDER_DETAIL = "SELECT OD.DetailId, OD.OrderID, OD.FID, P.PName, P.price, P.imgPath, OD.quantity \n" +
                                                    "FROM OrderDetails OD INNER JOIN Plants P\n" +
                                                    "ON FID = PID\n" +
                                                    "WHERE OrderID = ?";
    
    public static ArrayList<Order> getOrders(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            conn = DBUtils.makeConnection();
            if(conn != null) {
                stm = conn.prepareStatement(GET_ORDER);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs != null && rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipdate = rs.getDate("shipdate");
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String orderDate, shipDate; 
                    if (ordDate == null) {
                        orderDate = "Processing";     
                    } else {
                        orderDate = dateFormat.format(ordDate);
                    }
                    if (shipdate == null) {
                        shipDate = "Processing";
                    } else {
                        shipDate = dateFormat.format(shipdate);
                    }
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccId");
                    listOrder.add(new Order(orderID, orderDate, shipDate, status, accID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return listOrder;
    } 
    
    public static ArrayList<OrderDetail> getOrderDetail(int orderID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<OrderDetail> listOrderDetail = new ArrayList<>();
        
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ORDER_DETAIL);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                while (rs != null && rs.next()) {
                    int detailID = rs.getInt("DetailId");
                    int plantID = rs.getInt("FID");
                    String plantName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    int quantity = rs.getInt("quantity");
                    listOrderDetail.add(new OrderDetail(detailID, orderID, plantID, plantName, price, imgPath, quantity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return listOrderDetail;
    }
    
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Order> listOrder = null;
        try {
            conn = DBUtils.makeConnection();
            if(conn != null) {
                stm = conn.prepareStatement(GET_ORDER);
                stm.setString(1, "abc@gmail.com");
                rs = stm.executeQuery();
                while (rs != null && rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipdate = rs.getDate("shipdate");
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String orderDate, shipDate; 
                    if (ordDate == null) {
                        orderDate = "Processing";     
                    } else {
                        orderDate = dateFormat.format(ordDate);
                    }
                    if (shipdate == null) {
                        shipDate = "Processing";
                    } else {
                        shipDate = dateFormat.format(shipdate);
                    }
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    Order od = new Order(orderID, orderDate, shipDate, status, accID);
                    System.out.println(od.toString());
                    Order test = new Order(1, "cvb", "ds", 1, 1);
                    System.out.println(test.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
    }
    
}
