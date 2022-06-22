/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import panh.dto.Plant;
import panh.utils.DBUtils;

/**
 *
 * @author Panh
 */
public class PlantDAO {
    
    public static ArrayList<Plant> getPlants(String keyword, String searchby) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT PID, PName, price, imgPath, description, status, Plants.CateID AS CateID, CateName\n" +
                        "FROM Plants JOIN Categories ON Plants.CateID = Categories.CateID ";
        ArrayList<Plant> listPlant = new ArrayList<>();
        
        try {
            conn = DBUtils.makeConnection();
            if (conn != null && searchby != null) {
                if (searchby.equals("byname")) 
                    sql += "WHERE Plants.PName LIKE ?";
                else
                    sql += "WHERE CateName LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                rs = stm.executeQuery();
                while (rs != null && rs.next()) {
                    int pid = rs.getInt("PID");
                    String name = rs.getString("Pname");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    listPlant.add(new Plant(pid, name, price, imgPath, description, status, cateid, catename));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return listPlant;
    }
    
    public static void main(String[] args) throws SQLException {
        ArrayList<Plant> ls = PlantDAO.getPlants("cac", "bycate");
        for (Plant l : ls) {
            System.out.println(l);
        }
    }
}
