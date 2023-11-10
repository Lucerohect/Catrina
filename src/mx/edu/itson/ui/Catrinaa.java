/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mx.edu.itson.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import mx.edu.itson.ui.Complemento.AccountStatement;
import mx.edu.itson.ui.ui.Transaction;

/**
 *
 * @author USER
 */
public class Catrinaa extends javax.swing.JFrame {

    /**
     * Creates new form Catrinaa
     */
    public Catrinaa() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Catrinaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Catrinaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Catrinaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Catrinaa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       



        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Catrina - Estado de Cuenta");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

       
            JPanel panel = new JPanel();
            JLabel labelMonth = new JLabel("Seleccione el mes:");
            JComboBox<String> comboBoxMonth = new JComboBox<>(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
            JLabel labelFile = new JLabel("Seleccione el archivo a cargar:");
            JButton buttonBrowse = new JButton("Examinar");
            JTable table = new JTable();
            JScrollPane scrollPane = new JScrollPane(table);

    
            panel.add(labelMonth);
            panel.add(comboBoxMonth);
            panel.add(labelFile);
            panel.add(buttonBrowse);

           
            frame.getContentPane().add(panel, "North");
            frame.getContentPane().add(scrollPane, "Center");

        
            AccountStatement accountStatement = loadAccountStatement("data/account_statement.json");

         
            displayDataInTable(accountStatement, table);

        
            frame.setVisible(true);
        });
    }

    private static AccountStatement loadAccountStatement(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        AccountStatement accountStatement = null;

        JsonNode rootNode = objectMapper.readTree(new File(filePath));
        
        // Obtener información general (código omitido para brevedad)

        return accountStatement;
    }

    private static void displayDataInTable(AccountStatement accountStatement, JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("DATE");
        model.addColumn("DESCRIPTION");
        model.addColumn("DEPOSITS");
        model.addColumn("WITHDRAWALS");
        model.addColumn("SUBTOTAL");

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

        for (Transaction transaction : accountStatement.getTransactions()) {
            String date = transaction.getDate();
            String description = transaction.getDescription();
            String deposits = (transaction.getType() == 1) ? currencyFormatter.format(transaction.getAmount()) : "";
            String withdrawals = (transaction.getType() == 2) ? currencyFormatter.format(transaction.getAmount()) : "";
            String subtotal = currencyFormatter.format(transaction.getSubtotal());

            model.addRow(new Object[]{date, description, deposits, withdrawals, subtotal});
        }

        table.setModel(model);
    }
}

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Catrinaa().setVisible(true);
            
        });
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

