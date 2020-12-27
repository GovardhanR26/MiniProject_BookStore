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
import java.awt.image.BufferedImage;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CategoryPanel extends javax.swing.JPanel {
    static Connection con;
    static PreparedStatement ps;
    public CategoryPanel() {
        initComponents();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conImg = DriverManager.getConnection("jdbc:derby://localhost:1527/MiniProject_BookStore","gova","gova");
            PreparedStatement stImg = conImg.prepareStatement("select * from gova.images");
            ResultSet rsetImg = stImg.executeQuery();
            while(rsetImg.next()) {
                switch(rsetImg.getString(1)) {
                    case "AcademicColor" : { BufferedImage im = ImageIO.read(rsetImg.getBinaryStream(2));
                                        academicLab.setIcon(new ImageIcon(im)); 
                                        break;
                                    }
                    case "NonFictionColor" : { BufferedImage im = ImageIO.read(rsetImg.getBinaryStream(2));
                                        nonFictLab.setIcon(new ImageIcon(im));
                                        break;
                                    }
                    case "CareerColor" : { BufferedImage im = ImageIO.read(rsetImg.getBinaryStream(2));
                                        careerLab.setIcon(new ImageIcon(im));
                                        break;
                                    }
                    case "FictionColor" : { BufferedImage im = ImageIO.read(rsetImg.getBinaryStream(2));
                                        fictLab.setIcon(new ImageIcon(im));
                                        break;
                                    }
                    case "LifeColor" : { BufferedImage im = ImageIO.read(rsetImg.getBinaryStream(2));
                                    lifeLab.setIcon(new ImageIcon(im));
                                    break;
                                   }
                    case "SelfColor" : { BufferedImage im = ImageIO.read(rsetImg.getBinaryStream(2));
                                    selfLab.setIcon(new ImageIcon(im));
                                    break;
                                   }
                }
                //academicImage.setVisible(true);
        }} catch(SQLException exc) {
            System.out.println("SQL Exception while setting images : "+exc);
        } catch(ClassNotFoundException exc2) {
            System.out.println("ClassNotFound Exception while setting images : "+exc2);
        } catch(Exception exc3) {
            System.out.println("Some exception while setting images : "+exc3);
        }
    }
    
    void getCategory(String category) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/MiniProject_BookStore","gova","gova");
            ps = con.prepareStatement("select * from gova.allbooks where category=?",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, category);
            ResultSet rset = ps.executeQuery();

            setLayout(new BorderLayout());
            DefaultTableModel defaultTableModelCategory = new DefaultTableModel();
            JTable categTable = new JTable(defaultTableModelCategory);
            categTable.setSize(1375, 700);
            categTable.setPreferredScrollableViewportSize(new Dimension(1370, 700));
            categTable.setFillsViewportHeight(true);
            categTable.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
            categTable.setRowHeight(50);
            JTableHeader tableHeader = categTable.getTableHeader();
            Font tableHeadFont = new Font("Book Antiqua",Font.BOLD, 26);
            tableHeader.setForeground(Color.blue);
            tableHeader.setBackground(Color.cyan);
            tableHeader.setFont(tableHeadFont);
            add(new JScrollPane(categTable), BorderLayout.NORTH);
            defaultTableModelCategory.addColumn("Title");
            defaultTableModelCategory.addColumn("Author");
            defaultTableModelCategory.addColumn("Cost");
            categTable.getColumn("Title").setPreferredWidth(550);
            
            
            int rowCount = rset.last() ? rset.getRow() : 0;
            System.out.println("Row count : "+rowCount);

            rset.beforeFirst();
            while(rset.next()) {
                String title = rset.getString(2);
                String author = rset.getString(3);
                Double price = rset.getDouble(4);
                Long cartISBN = rset.getLong(1);
                  defaultTableModelCategory.addRow(new Object[]{title,author,"Rs. "+price});               
            }
            categTable.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    try {
                        int row=categTable.rowAtPoint(e.getPoint());
                        int col=0;
                        BookView bv2 = new BookView(rset, con, defaultTableModelCategory.getValueAt(row, col).toString());
                        bv2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                            
                        bv2.setVisible(true);
                    } catch(ArrayIndexOutOfBoundsException exc) {
                        System.out.println("Clicked outside the table");
                    }
                }
            });
            SwingUtilities.updateComponentTreeUI(this);
            
        } catch(SQLException ex) {
            System.out.println(ex);
        } catch(ClassNotFoundException ex1) {
            System.out.println(ex1);
        } catch(Exception ex2) {
            System.out.println(ex2);
        }      
        
    }
    
    void searchResultsLabMouseClicked(java.awt.event.MouseEvent evt, ResultSet rset, Connection con, String s) {
        BookView bv2 = new BookView(rset, con, s);
        bv2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                            
        bv2.setVisible(true);
        //System.out.println("Must the book name clicked : "+s);
    }
//    void searchResultsLabMouseEntered(java.awt.event.MouseEvent evt, JLabel jlab) {
//        jlab.setForeground(Color.LIGHT_GRAY);
//    }
//    void searchResultsLabMouseExited(java.awt.event.MouseEvent evt, JLabel jlab) {
//        jlab.setForeground(Color.black);
//    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fictLab = new javax.swing.JLabel();
        nonFictLab = new javax.swing.JLabel();
        lifeLab = new javax.swing.JLabel();
        careerLab = new javax.swing.JLabel();
        academicLab = new javax.swing.JLabel();
        selfLab = new javax.swing.JLabel();

        setBackground(new java.awt.Color(69, 177, 232));
        setPreferredSize(new java.awt.Dimension(1381, 764));

        fictLab.setBackground(new java.awt.Color(69, 177, 232));
        fictLab.setFont(new java.awt.Font("Book Antiqua", 0, 24)); // NOI18N
        fictLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fictLab.setText("   Fiction ");
        fictLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        fictLab.setOpaque(true);
        fictLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fictLabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fictLabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fictLabMouseExited(evt);
            }
        });

        nonFictLab.setBackground(new java.awt.Color(69, 177, 232));
        nonFictLab.setFont(new java.awt.Font("Book Antiqua", 0, 24)); // NOI18N
        nonFictLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nonFictLab.setText("   Non - Fiction");
        nonFictLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nonFictLab.setOpaque(true);
        nonFictLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nonFictLabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nonFictLabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nonFictLabMouseExited(evt);
            }
        });

        lifeLab.setBackground(new java.awt.Color(69, 177, 232));
        lifeLab.setFont(new java.awt.Font("Book Antiqua", 0, 24)); // NOI18N
        lifeLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lifeLab.setText("   Lifestyle");
        lifeLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        lifeLab.setOpaque(true);
        lifeLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lifeLabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lifeLabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lifeLabMouseExited(evt);
            }
        });

        careerLab.setBackground(new java.awt.Color(69, 177, 232));
        careerLab.setFont(new java.awt.Font("Book Antiqua", 0, 24)); // NOI18N
        careerLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        careerLab.setText("   Business and Career");
        careerLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        careerLab.setOpaque(true);
        careerLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                careerLabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                careerLabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                careerLabMouseExited(evt);
            }
        });

        academicLab.setBackground(new java.awt.Color(69, 177, 232));
        academicLab.setFont(new java.awt.Font("Book Antiqua", 0, 24)); // NOI18N
        academicLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        academicLab.setText("   Academic and Education");
        academicLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        academicLab.setOpaque(true);
        academicLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                academicLabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                academicLabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                academicLabMouseExited(evt);
            }
        });

        selfLab.setBackground(new java.awt.Color(69, 177, 232));
        selfLab.setFont(new java.awt.Font("Book Antiqua", 0, 24)); // NOI18N
        selfLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        selfLab.setText("   Self Help");
        selfLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        selfLab.setOpaque(true);
        selfLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selfLabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selfLabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selfLabMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fictLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(academicLab, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
                        .addGap(283, 283, 283)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lifeLab, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(careerLab, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nonFictLab, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(283, 283, 283)
                        .addComponent(selfLab, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(careerLab, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(academicLab, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fictLab, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lifeLab, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selfLab, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nonFictLab, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void fictLabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fictLabMouseEntered
        // TODO add your handling code here:
        fictLab.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_fictLabMouseEntered

    private void fictLabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fictLabMouseExited
        // TODO add your handling code here:
        fictLab.setForeground(Color.black);
    }//GEN-LAST:event_fictLabMouseExited

    private void fictLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fictLabMouseClicked
        // TODO add your handling code here:
        removeAll();
        getCategory("Fiction");
    }//GEN-LAST:event_fictLabMouseClicked

    private void nonFictLabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nonFictLabMouseEntered
        // TODO add your handling code here:
        nonFictLab.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_nonFictLabMouseEntered

    private void nonFictLabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nonFictLabMouseExited
        // TODO add your handling code here:
        nonFictLab.setForeground(Color.black);
    }//GEN-LAST:event_nonFictLabMouseExited

    private void academicLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_academicLabMouseClicked
        // TODO add your handling code here:
        removeAll();
        getCategory("Academic");
    }//GEN-LAST:event_academicLabMouseClicked

    private void academicLabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_academicLabMouseEntered
        // TODO add your handling code here:
        academicLab.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_academicLabMouseEntered

    private void academicLabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_academicLabMouseExited
        // TODO add your handling code here:
        academicLab.setForeground(Color.black);
    }//GEN-LAST:event_academicLabMouseExited

    private void nonFictLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nonFictLabMouseClicked
        // TODO add your handling code here:
        removeAll();
        getCategory("NonFict");
    }//GEN-LAST:event_nonFictLabMouseClicked

    private void careerLabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_careerLabMouseEntered
        // TODO add your handling code here:
        careerLab.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_careerLabMouseEntered

    private void careerLabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_careerLabMouseExited
        // TODO add your handling code here:
        careerLab.setForeground(Color.black);
    }//GEN-LAST:event_careerLabMouseExited

    private void careerLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_careerLabMouseClicked
        // TODO add your handling code here:
        removeAll();
        getCategory("Business");
    }//GEN-LAST:event_careerLabMouseClicked

    private void lifeLabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lifeLabMouseEntered
        // TODO add your handling code here:
        lifeLab.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_lifeLabMouseEntered

    private void lifeLabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lifeLabMouseExited
        // TODO add your handling code here:
        lifeLab.setForeground(Color.black);
    }//GEN-LAST:event_lifeLabMouseExited

    private void lifeLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lifeLabMouseClicked
        // TODO add your handling code here:
        removeAll();
        getCategory("LifeStyle");
    }//GEN-LAST:event_lifeLabMouseClicked

    private void selfLabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selfLabMouseEntered
        // TODO add your handling code here:
        selfLab.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_selfLabMouseEntered

    private void selfLabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selfLabMouseExited
        // TODO add your handling code here:
        selfLab.setForeground(Color.black);
    }//GEN-LAST:event_selfLabMouseExited

    private void selfLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selfLabMouseClicked
        // TODO add your handling code here:
        removeAll();
        getCategory("Self");
    }//GEN-LAST:event_selfLabMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel academicLab;
    private javax.swing.JLabel careerLab;
    private javax.swing.JLabel fictLab;
    private javax.swing.JLabel lifeLab;
    private javax.swing.JLabel nonFictLab;
    private javax.swing.JLabel selfLab;
    // End of variables declaration//GEN-END:variables
}
