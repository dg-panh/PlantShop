/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panh.dto;

import java.sql.SQLException;
import panh.dao.AccountDAO;

/**
 *
 * @author Panh
 */
public class TestConnection {
    public static void main(String[] args) throws SQLException {
        //test login
        Account acc = AccountDAO.getAccount("abc@gmail.com", "1");
        if (acc != null) {
            if (acc.getRole() == 1) System.out.println("I'm an admin");
            else System.out.println("I'm a user");
        } else System.out.println("Login fail");
    }
}
