/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panh.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Panh
 */
public class Validator {
    public static boolean checkEmail(String email) {
        // Compile regular expression
        final Pattern pattern = Pattern.compile("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(email);
        // Use results...
        return matcher.matches();
    }
    
    public static boolean checkFullName(String fullName) {
        final Pattern pattern = Pattern.compile("^[A-Za-z]([-']?[A-Za-z]+)*( [A-Za-z]([-']?[A-Za-z]+)*)+$", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(fullName);
        // Use results...
        return matcher.matches();
    }
    
    public static boolean checkPassword(String password) {
        return password.length() >= 8 && password.length() <= 30;
    }
    
    public static boolean checkPhone(String phone) {
        //phone in Vietnam
        final Pattern pattern = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8,9})\\b", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(phone);
        // Use results...
        return matcher.matches();
    }
    
    public static boolean checkQuantity(int quantity) {
        return quantity >= 0 && quantity <= 1000000;
    }
    
    public static boolean checkPrice(BigDecimal price) {
        return price.compareTo(new BigDecimal("100000000000")) <= 0 && price.compareTo(new BigDecimal(0)) >= 0;
    }
    
    public static boolean checkProductName(String productName) {
        if (productName.length() > 75) {
            return false;
        }
        final Pattern pattern = Pattern.compile("^[\\dA-Za-z]([-']?[\\dA-Za-z]+)*( [\\dA-Za-z]([-']?[\\dA-Za-z]+)*)*$", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(productName);
        // Use results...
        return matcher.matches();
    }
}
