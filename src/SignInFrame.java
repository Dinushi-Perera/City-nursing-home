import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignInFrame extends javax.swing.JFrame {

    public SignInFrame() {
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
        userTypeComboBox = new javax.swing.JComboBox<>();
        EmailTextField = new javax.swing.JTextField();
        PasswordField = new javax.swing.JPasswordField();
        ConformPasswordField = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        RegisterButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Create New Account");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 19)); // NOI18N
        jLabel2.setText("User Type");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 1, 19)); // NOI18N
        jLabel3.setText("Email");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 1, 19)); // NOI18N
        jLabel4.setText("Password");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        jLabel5.setFont(new java.awt.Font("Franklin Gothic Book", 1, 19)); // NOI18N
        jLabel5.setText("Conform Password");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, -1, -1));

        userTypeComboBox.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        userTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cashier", "Manager", "Doctor" }));
        jPanel1.add(userTypeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 237, -1));

        EmailTextField.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        jPanel1.add(EmailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 237, -1));

        PasswordField.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        jPanel1.add(PasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 237, -1));

        ConformPasswordField.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        jPanel1.add(ConformPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 237, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sign in background.jpg"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, -1, 460));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.jpg"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        RegisterButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        RegisterButton.setText("Register Now");
        RegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterButtonActionPerformed(evt);
            }
        });
        jPanel1.add(RegisterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterButtonActionPerformed
        // TODO add your handling code here:
        // Get user input
    String userType = userTypeComboBox.getSelectedItem().toString();
    String email = EmailTextField.getText();
    String password = new String(PasswordField.getPassword());
    String confirmPassword = new String(ConformPasswordField.getPassword());
    
    // Validate input
    if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill all fields", "Registration Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Check if passwords match
    if (!password.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match", "Registration Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Check if email is valid format
    if (!email.contains("@") || !email.contains(".")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address", "Registration Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Check if user already exists
    if (userExists(userType, email)) {
        JOptionPane.showMessageDialog(this, "A user with this email already exists", "Registration Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Register the user
    if (registerUser(userType, email, password)) {
        JOptionPane.showMessageDialog(this, "Registration successful! You can now login.");
        openLoginFrame();
    } else {
        JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Registration Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void LoginlabelMouseClicked(java.awt.event.MouseEvent evt) {
    openLoginFrame();
}

private void openLoginFrame() {
    // Close this frame and open the login frame
    this.dispose();
    new MultiLogin().setVisible(true);
}

/**
 * Checks if a user with the given email and type already exists
 */
private boolean userExists(String userType, String email) {
    try {
        File file = new File("users.txt");
        
        // If file doesn't exist, create it and return false (no users exist)
        if (!file.exists()) {
            return false;  // No need to create the file here, it will be created in registerUser
        }
        
        Scanner scanner = new Scanner(file);
        
        // Skip header line if it exists
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) continue;  // Skip empty lines
            
            String[] parts = line.split(",");
            
            if (parts.length >= 2) {
                String storedUserType = parts[0].trim();
                String storedEmail = parts[1].trim();
                
                if (storedUserType.equals(userType) && storedEmail.equals(email)) {
                    scanner.close();
                    return true;
                }
            }
        }
        scanner.close();
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error checking user existence: " + e.getMessage(), 
                                     "File Error", JOptionPane.ERROR_MESSAGE);
    }
    return false;
}


private boolean registerUser(String userType, String email, String password) {
    try {
        File file = new File("users.txt");
        boolean fileExists = file.exists();
        
        // Create file with header if it doesn't exist
        if (!fileExists) {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("userType,email,password");
            bw.newLine();
            bw.close();
        }
        
        // Append the new user
        FileWriter fw = new FileWriter(file, true); // true for append mode
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(userType + "," + email + "," + password);
        bw.newLine();
        bw.close();
        
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error registering user: " + e.getMessage(), 
                                     "File Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    }//GEN-LAST:event_RegisterButtonActionPerformed

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
            java.util.logging.Logger.getLogger(SignInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignInFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ConformPasswordField;
    private javax.swing.JTextField EmailTextField;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton RegisterButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> userTypeComboBox;
    // End of variables declaration//GEN-END:variables
}
