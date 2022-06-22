/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import panh.dao.AccountDAO;
import panh.dto.Account;
import panh.exception.RegisterExc;
import panh.utils.Validator;

/**
 *
 * @author Panh
 */
public class accountServlet extends HttpServlet {

    private final String errorViewURL = "error.jsp";
    private final String loginViewURL = "login.jsp";
    private final String registerViewURL = "registration.jsp";
    private final String homeViewURL = "index.jsp";
    private final String changeProfileURL = "changeProfile.jsp";
    private static String PERSONAL_PAGE = "personalPage.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            switch (action) {
                case "Login":
                    login(request, response);
                    break;
                case "Register":
                    register(request, response);
                    break;
                case "Logout":
                    logout(request, response);
                    break;
                case "Update Profile":
                    updateProfile(request, response);
                    break;
                default:
                    response.sendRedirect(homeViewURL);
            }
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //Require user to logout first
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            request.setAttribute("error", "Must logout before login new account");
            request.getRequestDispatcher(errorViewURL).forward(request, response);
            return;
        }
        //Get login in4
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account acc = AccountDAO.getAccount(email, password);

        //Cant find user in database 
        if (acc == null) {
            request.setAttribute("error", "Cannot find account, please try again");
            request.getRequestDispatcher(loginViewURL).forward(request, response);
            return;
        }

        //Login success, set user infor in session
        if (acc.getRole() == 1) {
            //admin homepage
        } else {
            session = request.getSession(true);
            session.setAttribute("name", acc.getFullname());
            session.setAttribute("email", acc.getEmail());
            response.sendRedirect(PERSONAL_PAGE);
        }

    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<RegisterExc> errorList;

        if (request.getAttribute("errorList") == null) {
            errorList = new ArrayList<>();
            request.setAttribute("errorList", errorList);
        } else {
            errorList = (ArrayList<RegisterExc>) request.getAttribute("errorList");
        }

        String email = request.getParameter("txtemail");
        String fullname = request.getParameter("txtfullname");
        String password = request.getParameter("txtpassword");
        String phone = request.getParameter("txtphone");
        int status = 1;
        int role = 0;
        //Validate code
        boolean valid = true;
        if (!Validator.checkEmail(email)) {
            errorList.add(new RegisterExc("Please insert valid email."));
            valid = false;
        }
        if (!Validator.checkFullName(fullname)) {
            errorList.add(new RegisterExc("Please insert valid full name."));
            valid = false;
        }
        if (!Validator.checkPassword(password)) {
            errorList.add(new RegisterExc("Password must be between 8 and 30 characters."));
            valid = false;
        }
        if (!Validator.checkPhone(phone)) {
            errorList.add(new RegisterExc("Please insert valid full name."));
            valid = false;
        }
        if (!valid) {
            request.setAttribute("errorList", errorList);
            request.getRequestDispatcher(registerViewURL).forward(request, response);
            return;
        }

        //check email in DB
        Account acc = AccountDAO.searchAccount(email);
        if (acc != null) {
            errorList.add(new RegisterExc("Email is used, please choose another email"));
            request.setAttribute("errorList", errorList);
            request.getRequestDispatcher(registerViewURL).forward(request, response);
            return;
        }

        //create account in DB
        if (!AccountDAO.insertAccount(email, password, fullname, phone, status, role)) {
            errorList.add(new RegisterExc("Cannot create new user in database, please try again"));
            request.setAttribute("errorList", errorList);
            request.getRequestDispatcher(registerViewURL).forward(request, response);
            return;
        }

        //create successfully
        request.setAttribute("registorMsg", "Success!");
        request.getRequestDispatcher(registerViewURL).forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(homeViewURL);
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<RegisterExc> errorList;

        if (request.getAttribute("errorList") == null) {
            errorList = new ArrayList<>();
            request.setAttribute("errorList", errorList);
        } else {
            errorList = (ArrayList<RegisterExc>) request.getAttribute("errorList");
        }
        
        String email = request.getParameter("txtemail");
        String fullname = request.getParameter("txtfullname");
        String phone = request.getParameter("txtphone");
        String currentPass = request.getParameter("txtcurrentpassword");
        String newPass = request.getParameter("txtnewpassword");
        
        //Validate code
        boolean valid = true;
        if (!Validator.checkFullName(fullname)) {
            errorList.add(new RegisterExc("Please insert valid full name."));
            valid = false;
        }
        if (!Validator.checkPassword(newPass)) {
            errorList.add(new RegisterExc("Password must be between 8 and 30 characters."));
            valid = false;
        }
        if (!Validator.checkPhone(phone)) {
            errorList.add(new RegisterExc("Please insert valid full name."));
            valid = false;
        }
        if (!valid) {
            request.setAttribute("errorList", errorList);
            request.getRequestDispatcher(changeProfileURL).forward(request, response);
            return;
        }
        
        Account acc = AccountDAO.getAccount(email, currentPass);
        if (acc == null) {
            errorList.add(new RegisterExc("Current password is wrong."));
            request.setAttribute("errorList", errorList);
            request.getRequestDispatcher(changeProfileURL).forward(request, response);
            return;
        }
        
        //update in database
        if (!AccountDAO.updateAccount(email, newPass, fullname, phone)) {
            errorList.add(new RegisterExc("Cannot update profile in database, please try again"));
            request.setAttribute("errorList", errorList);
            request.getRequestDispatcher(changeProfileURL).forward(request, response);
            return;
        }

        //update successfully
        request.setAttribute("changeProfileMsg", "Success!");
        request.getRequestDispatcher(changeProfileURL).forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(accountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(accountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
