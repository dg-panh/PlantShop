/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panh.dto;

/**
 *
 * @author Panh
 */
public class Account {
    private int accID;
    private String email;
    private String password;
    private String fullname;
    private int status;
    private String phone;
    private int role;

    public Account(int accID, String email, String password, String fullname, int status, String phone, int role) {
        this.accID = accID;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.status = status;
        this.phone = phone;
        this.role = role;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "accID=" + accID + ", email=" + email + ", password=" + password + ", fullname=" + fullname + ", status=" + status + ", phone=" + phone + ", role=" + role + '}';
    }
    
    
}
