
package accesobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AccesoBD extends javax.swing.JFrame {

    Connection conexion;
    Statement sentencia;
    
    public AccesoBD() {
        initComponents();
        PrepararBaseDatos();
    }

    void PrepararBaseDatos(){
        try
        {
            String controlador="com.mysql.jdbc.Driver";
            Class.forName(controlador).newInstance();
            
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex)
        {
            JOptionPane.showMessageDialog(null,"Error al cargar el controlador.");
            ex.printStackTrace();
        }
        
        String DBURL="jdbc:mysql://localhost/manempsa";
        String usuario="root";
        String password="";
        
        try
        {
            conexion=DriverManager.getConnection(DBURL,usuario,password);
        } catch (SQLException ex)
        {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {

             sentencia=conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex)
        {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }     
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtPanel = new javax.swing.JTextPane();
        btnTodos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtSueldo = new javax.swing.JTextField();
        btnIgual = new javax.swing.JButton();
        btnMenor = new javax.swing.JButton();
        btnMayor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(txtPanel);

        btnTodos.setText("Ver Todos Los Trabajadores ");
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });

        jLabel1.setText("Sueldo: ");

        btnIgual.setText("Igual");
        btnIgual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIgualActionPerformed(evt);
            }
        });

        btnMenor.setText("Menor");
        btnMenor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenorActionPerformed(evt);
            }
        });

        btnMayor.setText("Mayor");
        btnMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMayorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTodos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnIgual)
                        .addGap(18, 18, 18)
                        .addComponent(btnMenor, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMayor)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTodos)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIgual)
                    .addComponent(btnMayor)
                    .addComponent(btnMenor))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        String info="", cadnombre,cadapell,cadsueldo,cadfecha;
        
        try{
            ResultSet r=sentencia.executeQuery("select * from trabajadores order by sueldo");
            r.beforeFirst();
            while(r.next()){
                cadnombre=r.getString("nombre");
                cadapell=r.getString("apellido");
                cadsueldo=r.getString("sueldo").replace(",", ".");
                cadfecha=r.getString("fecha");
                cadfecha=cadfecha.substring(8, 10)+"/"+cadfecha.substring(5, 7)+"/"+cadfecha.substring(0, 4);
                info+=cadnombre+" "+cadapell+" -- "+cadsueldo+"€ "+cadfecha+"\n";
                
            }
            
            txtPanel.setText("");
            txtPanel.setText(info);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error:"+ex);
        }
    }//GEN-LAST:event_btnTodosActionPerformed

    private void btnIgualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIgualActionPerformed
        String info="", cadnombre,cadapell,cadsueldo,cadfecha;
        
        try{
            ResultSet r=sentencia.executeQuery("select * from trabajadores where sueldo = "+txtSueldo.getText());
            r.beforeFirst();
            while(r.next()){
                cadnombre=r.getString("nombre");
                cadapell=r.getString("apellido");
                cadsueldo=r.getString("sueldo").replace(",", ".");
                cadfecha=r.getString("fecha");
                cadfecha=cadfecha.substring(8, 10)+"/"+cadfecha.substring(5, 7)+"/"+cadfecha.substring(0, 4);
                info+=cadnombre+" "+cadapell+" -- "+cadsueldo+"€ "+cadfecha+"\n";
                
            }
            
            txtPanel.setText("");
            txtPanel.setText(info);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error:"+ex);
        }
    }//GEN-LAST:event_btnIgualActionPerformed

    private void btnMenorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenorActionPerformed
        String info="", cadnombre,cadapell,cadsueldo,cadfecha;
        
        try{
            ResultSet r=sentencia.executeQuery("select * from trabajadores where sueldo < "+txtSueldo.getText());
            r.beforeFirst();
            while(r.next()){
                cadnombre=r.getString("nombre");
                cadapell=r.getString("apellido");
                cadsueldo=r.getString("sueldo").replace(",", ".");
                cadfecha=r.getString("fecha");
                cadfecha=cadfecha.substring(8, 10)+"/"+cadfecha.substring(5, 7)+"/"+cadfecha.substring(0, 4);
                info+=cadnombre+" "+cadapell+" -- "+cadsueldo+"€ "+cadfecha+"\n";
                
            }
            
            txtPanel.setText("");
            txtPanel.setText(info);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error:"+ex);
        }
    }//GEN-LAST:event_btnMenorActionPerformed

    private void btnMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMayorActionPerformed
        String info="", cadnombre,cadapell,cadsueldo,cadfecha;
        
        try{
            ResultSet r=sentencia.executeQuery("select * from trabajadores where sueldo > "+txtSueldo.getText());
            r.beforeFirst();
            while(r.next()){
                cadnombre=r.getString("nombre");
                cadapell=r.getString("apellido");
                cadsueldo=r.getString("sueldo").replace(",", ".");
                cadfecha=r.getString("fecha");
                cadfecha=cadfecha.substring(8, 10)+"/"+cadfecha.substring(5, 7)+"/"+cadfecha.substring(0, 4);
                info+=cadnombre+" "+cadapell+" -- "+cadsueldo+"€ "+cadfecha+"\n";
                
            }
            
            txtPanel.setText("");
            txtPanel.setText(info);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error:"+ex);
        }
    }//GEN-LAST:event_btnMayorActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
        try{
            conexion.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al cerrar la base de datos:"+ e);
            
        }
    }                                  

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
            java.util.logging.Logger.getLogger(AccesoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccesoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccesoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccesoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccesoBD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIgual;
    private javax.swing.JButton btnMayor;
    private javax.swing.JButton btnMenor;
    private javax.swing.JButton btnTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane txtPanel;
    private javax.swing.JTextField txtSueldo;
    // End of variables declaration//GEN-END:variables
}
