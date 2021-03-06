/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AfterSQLfix.BookStore_Final;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author Manimozhi Ramalingam
 */
public class ShowBill extends javax.swing.JFrame {

    /**
     * Creates new form showBill
     */
    public ShowBill(ResultSet rset, String order) {
        initComponents();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MiniProject_BookStore","gova","gova");
            PreparedStatement ps = con.prepareStatement("select * from gova.orderslist where orderid='"+order+"'",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rset2 = ps.executeQuery();
            rset2.next();
            String custName = rset2.getString(2);
            //System.out.println("Customer name : "+custName);
            String custAddress = rset2.getString(3);
            String custCity = rset2.getString(4);
            int custPin = rset2.getInt(5);
            long mobile = rset2.getLong(6);
            double totalCost = rset2.getDouble(7);
            
            nameStuff.setText(custName);
            addressStuff.setText(custAddress);
            cityStuff.setText(custCity);
            pinStuff.setText(""+custPin);
            mobileStuff.setText(""+mobile);
            grandTotalValue.setText("Rs. "+totalCost);
            orderIdStuff.setText(order);
            
            DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf4.format(now);
            dateStuff.setText(""+date);
            mainBillPane.setLayout(new BorderLayout());
            DefaultTableModel defaultTableModelBill = new DefaultTableModel();
            JTable billTable = new JTable(defaultTableModelBill);
            billTable.setSize(756,366);
            billTable.setPreferredScrollableViewportSize(new Dimension(756,366));
            billTable.setFillsViewportHeight(true);
            billTable.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
            billTable.setRowHeight(30);
            JTableHeader tableHeader = billTable.getTableHeader();
            Font tableHeadFont = new Font("Book Antiqua",Font.BOLD, 18);
            tableHeader.setForeground(Color.blue);
            tableHeader.setBackground(Color.cyan);
            tableHeader.setFont(tableHeadFont);
            mainBillPane.add(new JScrollPane(billTable), BorderLayout.NORTH);
            defaultTableModelBill.addColumn("Product #");
            defaultTableModelBill.addColumn("Title");
            defaultTableModelBill.addColumn("Author");
            defaultTableModelBill.addColumn("Cost");
            billTable.getColumn("Title").setPreferredWidth(270);
            billTable.getColumn("Product #").setPreferredWidth(50);
            
            rset.beforeFirst();
            while(rset.next()) {
                Long ISBN = rset.getLong(1);
                PreparedStatement ps2 = con.prepareStatement("select * from gova.allbooks where isbn="+ISBN+"",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rsetBook = ps2.executeQuery();
                rsetBook.next();
                String title = rsetBook.getString(2);
                //System.out.println("Book title : "+title);
                String author = rsetBook.getString(3);
                Double price = rsetBook.getDouble(4);
                defaultTableModelBill.addRow(new Object[]{ISBN,title,author,"Rs. "+price});
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch(SQLException ex) {
            System.out.println("SQLException in showBill : ");
            ex.printStackTrace();
        } catch(ClassCastException ex2) {
            System.out.println("ClassNotFound exception in showBill : "+ex2);
        } catch(Exception ex3) {
            System.out.println("Other exception in showBill : "+ex3);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        orderIdLab = new javax.swing.JLabel();
        BillToLab = new javax.swing.JLabel();
        invoiceLab = new javax.swing.JLabel();
        dateLab = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nameLab = new javax.swing.JLabel();
        addressLab = new javax.swing.JLabel();
        addressStuff = new javax.swing.JLabel();
        cityLab = new javax.swing.JLabel();
        cityStuff = new javax.swing.JLabel();
        pinLab = new javax.swing.JLabel();
        pinStuff = new javax.swing.JLabel();
        mobileLab = new javax.swing.JLabel();
        mobileStuff = new javax.swing.JLabel();
        nameStuff = new javax.swing.JLabel();
        dateStuff = new javax.swing.JLabel();
        orderIdStuff = new javax.swing.JLabel();
        grandTotal = new javax.swing.JLabel();
        grandTotalValue = new javax.swing.JLabel();
        mainBillPane = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thank you for shopping with us !");
        setBackground(new java.awt.Color(102, 153, 255));

        orderIdLab.setBackground(new java.awt.Color(153, 153, 255));
        orderIdLab.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        orderIdLab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        orderIdLab.setText("OrderID ");
        orderIdLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        orderIdLab.setOpaque(true);

        BillToLab.setBackground(new java.awt.Color(153, 153, 255));
        BillToLab.setFont(new java.awt.Font("Palatino Linotype", 1, 25)); // NOI18N
        BillToLab.setText("  Bill To :");
        BillToLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BillToLab.setOpaque(true);

        invoiceLab.setBackground(new java.awt.Color(0, 102, 153));
        invoiceLab.setFont(new java.awt.Font("Palatino Linotype", 0, 45)); // NOI18N
        invoiceLab.setForeground(new java.awt.Color(255, 255, 255));
        invoiceLab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invoiceLab.setText("INVOICE");
        invoiceLab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        invoiceLab.setOpaque(true);

        dateLab.setBackground(new java.awt.Color(153, 153, 255));
        dateLab.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        dateLab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateLab.setText("Date ");
        dateLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        dateLab.setOpaque(true);

        nameLab.setBackground(new java.awt.Color(153, 153, 255));
        nameLab.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        nameLab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLab.setText("Name ");
        nameLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nameLab.setOpaque(true);

        addressLab.setBackground(new java.awt.Color(153, 153, 255));
        addressLab.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        addressLab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addressLab.setText("Address ");
        addressLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        addressLab.setOpaque(true);

        addressStuff.setBackground(new java.awt.Color(255, 255, 255));
        addressStuff.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        addressStuff.setText("Address goes here");
        addressStuff.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        addressStuff.setOpaque(true);

        cityLab.setBackground(new java.awt.Color(153, 153, 255));
        cityLab.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        cityLab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cityLab.setText("City, State ");
        cityLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cityLab.setOpaque(true);

        cityStuff.setBackground(new java.awt.Color(255, 255, 255));
        cityStuff.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        cityStuff.setText("City, State goes here");
        cityStuff.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cityStuff.setOpaque(true);

        pinLab.setBackground(new java.awt.Color(153, 153, 255));
        pinLab.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        pinLab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pinLab.setText("Pincode");
        pinLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pinLab.setOpaque(true);

        pinStuff.setBackground(new java.awt.Color(255, 255, 255));
        pinStuff.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        pinStuff.setText("PIN goes here");
        pinStuff.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pinStuff.setOpaque(true);

        mobileLab.setBackground(new java.awt.Color(153, 153, 255));
        mobileLab.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        mobileLab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mobileLab.setText("Mobile ");
        mobileLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mobileLab.setOpaque(true);

        mobileStuff.setBackground(new java.awt.Color(255, 255, 255));
        mobileStuff.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        mobileStuff.setText("Mobile goes here");
        mobileStuff.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        mobileStuff.setOpaque(true);

        nameStuff.setBackground(new java.awt.Color(255, 255, 255));
        nameStuff.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        nameStuff.setText("Name goes here");
        nameStuff.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nameStuff.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mobileLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pinLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLab, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cityLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressStuff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cityStuff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pinStuff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mobileStuff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nameStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLab, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLab, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLab, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cityStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pinLab, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pinStuff))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mobileLab, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobileStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dateStuff.setBackground(new java.awt.Color(255, 255, 255));
        dateStuff.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        dateStuff.setText("Date goes here");
        dateStuff.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        dateStuff.setOpaque(true);

        orderIdStuff.setBackground(new java.awt.Color(255, 255, 255));
        orderIdStuff.setFont(new java.awt.Font("Palatino Linotype", 0, 20)); // NOI18N
        orderIdStuff.setText("OrderID goes here");
        orderIdStuff.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        orderIdStuff.setOpaque(true);

        grandTotal.setBackground(new java.awt.Color(102, 255, 102));
        grandTotal.setFont(new java.awt.Font("Palatino Linotype", 1, 25)); // NOI18N
        grandTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        grandTotal.setText("Grand Total");
        grandTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        grandTotal.setOpaque(true);

        grandTotalValue.setFont(new java.awt.Font("Palatino Linotype", 1, 25)); // NOI18N
        grandTotalValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        grandTotalValue.setText("Value");
        grandTotalValue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        mainBillPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout mainBillPaneLayout = new javax.swing.GroupLayout(mainBillPane);
        mainBillPane.setLayout(mainBillPaneLayout);
        mainBillPaneLayout.setHorizontalGroup(
            mainBillPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainBillPaneLayout.setVerticalGroup(
            mainBillPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(grandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(grandTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainBillPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BillToLab, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(orderIdLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dateStuff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(orderIdStuff, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
                            .addComponent(invoiceLab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BillToLab, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(invoiceLab, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateStuff, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(orderIdLab, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(orderIdStuff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(45, 45, 45)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainBillPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grandTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(showBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(showBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(showBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(showBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new showBill().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BillToLab;
    private javax.swing.JLabel addressLab;
    private javax.swing.JLabel addressStuff;
    private javax.swing.JLabel cityLab;
    private javax.swing.JLabel cityStuff;
    private javax.swing.JLabel dateLab;
    private javax.swing.JLabel dateStuff;
    private javax.swing.JLabel grandTotal;
    private javax.swing.JLabel grandTotalValue;
    private javax.swing.JLabel invoiceLab;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainBillPane;
    private javax.swing.JLabel mobileLab;
    private javax.swing.JLabel mobileStuff;
    private javax.swing.JLabel nameLab;
    private javax.swing.JLabel nameStuff;
    private javax.swing.JLabel orderIdLab;
    private javax.swing.JLabel orderIdStuff;
    private javax.swing.JLabel pinLab;
    private javax.swing.JLabel pinStuff;
    // End of variables declaration//GEN-END:variables
}
