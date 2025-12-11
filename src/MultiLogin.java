import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class MultiLogin extends javax.swing.JFrame {

    public MultiLogin() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        EmailTextField = new javax.swing.JTextField();
        PasswordField = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login new.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 630, -1));

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 36)); // NOI18N
        jLabel2.setText("Welcome ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel3.setText("City Nursing Home");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel4.setText("User Type");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, -1, -1));

        jLabel5.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel5.setText("Email");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel6.setText("Password");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cashier", "Manager", "Doctor" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 230, -1));

        EmailTextField.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        EmailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(EmailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 230, -1));

        PasswordField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(PasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 230, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.jpg"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        LoginButton.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        LoginButton.setText("LOGIN");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        jPanel1.add(LoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 140, -1));

        BackButton.setBackground(new java.awt.Color(255, 255, 255));
        BackButton.setForeground(new java.awt.Color(255, 255, 255));
        BackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exit.png"))); // NOI18N
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 50, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EmailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailTextFieldActionPerformed

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        // TODO add your handling code here:
    String selectedUserType = jComboBox1.getSelectedItem().toString();
    String email = EmailTextField.getText();
    String password = new String(PasswordField.getPassword());
    
    // Check for empty fields
    if(email.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter both email and password", 
                                     "Login Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Authenticate and redirect to the right dashboard
    if (authenticateUser(selectedUserType, email, password)) {
        // Display success message based on user type
        switch (selectedUserType) {
            case "Cashier":
                JOptionPane.showMessageDialog(this, "Cashier login Successfully", 
                                             "Login Success", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Manager":
                JOptionPane.showMessageDialog(this, "Manager login Successfully", 
                                             "Login Success", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Doctor":
                JOptionPane.showMessageDialog(this, "Doctor login Successfully", 
                                             "Login Success", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown user type!", 
                                             "System Error", JOptionPane.ERROR_MESSAGE);
                return;
        }
        
        // Open the appropriate dashboard
        openDashboard(selectedUserType);
    } else {
        JOptionPane.showMessageDialog(this, "Invalid credentials!", 
                                     "Login Error", JOptionPane.ERROR_MESSAGE);
    }
}                                           

/**
 * Checks if the provided credentials match a record in the users.txt file
 */
private boolean authenticateUser(String userType, String email, String password) {
    try {
        File file = new File("users.txt");
        Scanner scanner = new Scanner(file);
        
        // Skip header line if it exists
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            
            if (parts.length == 3) {
                String storedUserType = parts[0];
                String storedEmail = parts[1];
                String storedPassword = parts[2];
                
                if (storedUserType.equals(userType) && 
                    storedEmail.equals(email) && 
                    storedPassword.equals(password)) {
                    scanner.close();
                    return true;
                }
            }
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(this, "User database file not found! Please create a users.txt file.", 
                                     "System Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    return false;
}

/**
 * Opens the appropriate dashboard based on the user type
 */
private void openDashboard(String userType) {
    this.dispose(); // Close the login window
    
    switch (userType) {
        case "Cashier":
            CashierDashboard cashierDash = new CashierDashboard();
            cashierDash.setVisible(true);
            break;
        case "Manager":
            ManagerDashboard managerDash = new ManagerDashboard();
            managerDash.setVisible(true);
            break;
        case "Doctor":
            DoctorDashboard doctorDash = new DoctorDashboard();
            doctorDash.setVisible(true);
            break;
        
        default:
            JOptionPane.showMessageDialog(this, "Unknown user type!", 
            "System Error", JOptionPane.ERROR_MESSAGE);
            break;
    }

    }//GEN-LAST:event_LoginButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
            this.dispose();
    
         // Open the Login frame
             new Login().setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MultiLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MultiLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MultiLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MultiLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MultiLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JTextField EmailTextField;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
