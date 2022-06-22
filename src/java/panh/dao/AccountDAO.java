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
import java.util.logging.Level;
import java.util.logging.Logger;
import panh.dto.Account;
import panh.utils.DBUtils;

/**
 *
 * @author Panh
 */
public class AccountDAO {
    private static final String GET_ACCOUNT = "SELECT accID, email, password, fullname, phone, status, role " +
                                                "FROM Accounts " +
                                                "WHERE email = ? AND password = ?";
    private static final String GET_LIST_ACCOUNT = "SELECT accID, email, password, fullname, phone, status, role " +
                                                    "FROM Accounts";
    private static final String UPDATE_ACCOUNT_STATUS = "UPDATE Accounts SET status = ? WHERE email = ?";
    private static final String UPDATE_ACCOUNT = "UPDATE Accounts SET fullname = ?, password = ?, phone = ? " +
                                                    "WHERE email = ?";
    private static final String INSERT_ACCOUNT = "INSERT INTO Accounts(email, password, fullname, phone, status, role)\n" +
                                                    "VALUES(?, ?, ?, ?, ?, ?)";
    
    public static Account getAccount(String email, String password) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ACCOUNT);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    int AccId = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccId, Email, Password, Fullname, Status, Phone, Role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return acc;
    }
    
    public static ArrayList<Account> getAccounts() throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Account> listAccount = new ArrayList<>();
        
        try {
            conn = DBUtils.makeConnection();
            if(conn != null) {
                stm = conn.prepareStatement(GET_LIST_ACCOUNT);
                rs = stm.executeQuery();
                while(rs != null && rs.next()) {
                    int AccId = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    listAccount.add(new Account(AccId, Email, Password, Fullname, Status, Phone, Role));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return listAccount;
    }
    
    public static boolean updateAccountStatus(String email, int status) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        int rowAffected;
        boolean result = false;
        
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_ACCOUNT_STATUS);
                stm.setInt(1, status);
                stm.setString(2, email);
                rowAffected = stm.executeUpdate();
                if (rowAffected == 1) result = true; //=1 vi email la k the duplicate
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return result;
    }
    
    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        int rowAffected;
        boolean result = false;
        
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_ACCOUNT);
                stm.setString(1, newFullname);
                stm.setString(2, newPassword);
                stm.setString(3, newPhone);
                stm.setString(4, email);
                rowAffected = stm.executeUpdate();
                if (rowAffected == 1) result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return result;
    }
    
    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newSatus,int newRole) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        int rowAffected;
        boolean result = false;
        ArrayList<Account> listAcount = AccountDAO.getAccounts();
        
        Account ac = searchAccount(newEmail);
        if (ac == null) {
            try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_ACCOUNT);
                stm.setString(1, newEmail);
                stm.setString(2, newPassword);
                stm.setString(3, newFullname);
                stm.setString(4, newPhone);
                stm.setInt(5, newSatus);
                stm.setInt(6, newRole);
                rowAffected = stm.executeUpdate();
                if (rowAffected == 1) result = true;
            }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (stm != null) stm.close();
                if (conn != null) conn.close();
            }
        }
        return result;
    }
    
    public static Account searchAccount(String email) throws SQLException {
        ArrayList<Account> listAccount = AccountDAO.getAccounts();
        for (Account account : listAccount) {
            if (account.getEmail().equalsIgnoreCase(email))
                return account;
        }
        return null;
    }
    
    public static void main(String[] args) throws SQLException {
        ArrayList<Account> listAcc;
        listAcc = getAccounts();
        for (Account account : listAcc) {
            System.out.println(account);  
        }
        
        boolean updateAccStt = AccountDAO.updateAccountStatus("abc@gmail.com", 1);
        System.out.println(updateAccStt);
        
        boolean updateAcc = AccountDAO.updateAccount("abc@gmail.com", "1", "Den Vau", "");
        System.out.println(updateAcc);
        
        boolean insertAcc = AccountDAO.insertAccount("phimrathay@gmail.com", "1", "Snowdrop", "", 0, 0);
        System.out.println(insertAcc);
    }
    
    
}
