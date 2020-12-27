/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AfterSQLfix.BookStore_Final;

import java.sql.*;
import javax.swing.*;

public class AddingToCart {
    static Connection con;
    
    public AddingToCart(long isbn) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/MiniProject_BookStore","gova","gova");
            PreparedStatement ps2 = con.prepareStatement("INSERT into GOVA.CART values("+isbn+")");
            ps2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Book successfully added to cart");
        } catch(SQLIntegrityConstraintViolationException exc) {
            JOptionPane.showMessageDialog(null, "Book already present in Cart", "Error", JOptionPane.ERROR_MESSAGE);
        } catch(ClassNotFoundException exc1) {
            System.out.println(exc1);
        } catch(SQLException exc2) {
            System.out.println(exc2);
        }
    }
    
}
