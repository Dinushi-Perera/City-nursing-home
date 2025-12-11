
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        SignInButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 48)); // NOI18N
        jLabel1.setText("We Care About ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 27, -1, 78));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 48)); // NOI18N
        jLabel2.setText("Your Health");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 112, -1, -1));

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel3.setText("Welcome to City Nursing Home's integrated management  ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 211, -1, -1));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel4.setText("portal where compassionate care meets digital efficiency.");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 239, -1, -1));

        jLabel5.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel5.setText("Our vision is to eliminate administrative barriers so you can");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 267, -1, -1));

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel6.setText("focus on what truly matters patient wellbeing.");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 295, -1, -1));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel7.setText("This platform,");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 295, -1, -1));

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel9.setText("we connect all aspects of care delivery into one seamless experience,");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 323, -1, -1));

        jLabel10.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel10.setText("revolutionizing healthcare one click.");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 351, -1, -1));

        LoginButton.setBackground(new java.awt.Color(153, 153, 153));
        LoginButton.setFont(new java.awt.Font("Lucida Sans", 1, 20)); // NOI18N
        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        getContentPane().add(LoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 210, -1));

        SignInButton.setBackground(new java.awt.Color(153, 153, 153));
        SignInButton.setFont(new java.awt.Font("Lucida Sans", 0, 20)); // NOI18N
        SignInButton.setText("Logout");
        SignInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignInButtonActionPerformed(evt);
            }
        });
        getContentPane().add(SignInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 230, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home new.jpg"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        // TODO add your handling code here:
        // Close the current frame
    this.dispose();
    
    // Open the Login frame
    new MultiLogin().setVisible(true);
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void SignInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignInButtonActionPerformed
         System.exit(0);
    }//GEN-LAST:event_SignInButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginButton;
    private javax.swing.JButton SignInButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
