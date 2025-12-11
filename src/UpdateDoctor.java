import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class UpdateDoctor extends javax.swing.JFrame {

    public UpdateDoctor() {
        initComponents();
        this.setLocationRelativeTo(null); // Center on screen
        SaveButton.setEnabled(false); 
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        DocIDTextField = new javax.swing.JTextField();
        SaveButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        DocNameTextField = new javax.swing.JTextField();
        DocTelNoTextField = new javax.swing.JTextField();
        DocEmailTextField = new javax.swing.JTextField();
        DocExperienceTextField = new javax.swing.JTextField();
        CloseButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        DocSpecificationComboBox = new javax.swing.JComboBox<>();
        BackButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Doctor ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        DocIDTextField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        DocIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocIDTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(DocIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 250, -1));

        SaveButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        SaveButton.setText("UPDATE");
        SaveButton.setToolTipText("");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });
        getContentPane().add(SaveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Speciality");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Phone Number");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Email");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, 20));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Year of Experience");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, -1, -1));

        DocNameTextField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        getContentPane().add(DocNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 250, -1));

        DocTelNoTextField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        getContentPane().add(DocTelNoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 250, -1));

        DocEmailTextField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        getContentPane().add(DocEmailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 250, -1));

        DocExperienceTextField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        getContentPane().add(DocExperienceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 250, -1));

        CloseButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        CloseButton.setText("CLOSE");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, -1, -1));

        jLabel7.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 26)); // NOI18N
        jLabel7.setText("UPDATE NEW CONSULTANT");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        DocSpecificationComboBox.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        DocSpecificationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General Medicine", "Cardiologist", "Pediatrician", "Desntist", "Surgen", "Psychiatrist" }));
        getContentPane().add(DocSpecificationComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 250, -1));

        BackButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        BackButton.setText("BACK");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 440, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DocIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocIDTextFieldActionPerformed
        // TODO add your handling code here:
        checkDoctorExists();

    }//GEN-LAST:event_DocIDTextFieldActionPerformed

    private void checkDoctorExists() {
        String doctorID = DocIDTextField.getText().trim();
        
        if (doctorID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Doctor ID", "Error", JOptionPane.ERROR_MESSAGE);
            resetFormFields();
            SaveButton.setEnabled(false);
            return;
        }
        
        try {
            File file = new File("doctors.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(this, "Doctor database file not found", "Error", JOptionPane.ERROR_MESSAGE);
                SaveButton.setEnabled(false);
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean doctorFound = false;
            
            // Skip header line
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[0].equals(doctorID)) {
                    // Doctor found, populate fields
                    DocNameTextField.setText(parts[1]);
                    
                    // Select the correct specialization in combo box
                    String specialization = parts[2];
                    for (int i = 0; i < DocSpecificationComboBox.getItemCount(); i++) {
                        if (DocSpecificationComboBox.getItemAt(i).equalsIgnoreCase(specialization)) {
                            DocSpecificationComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                    
                    DocTelNoTextField.setText(parts[3]);
                    DocEmailTextField.setText(parts[4]);
                    DocExperienceTextField.setText(parts[5]);
                    
                    doctorFound = true;
                    SaveButton.setEnabled(true);
                    break;
                }
            }
            
            reader.close();
            
            if (!doctorFound) {
                JOptionPane.showMessageDialog(this, "Doctor ID " + doctorID + " not found in the database", 
                        "Unavailable Doctor ID", JOptionPane.WARNING_MESSAGE);
                resetFormFields();
                SaveButton.setEnabled(false);
            }
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading doctor data: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void resetFormFields() {
        DocNameTextField.setText("");
        DocSpecificationComboBox.setSelectedIndex(0);
        DocTelNoTextField.setText("");
        DocEmailTextField.setText("");
        DocExperienceTextField.setText("");
    }
    
    
    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
       if (!validateDoctorInput()) {
            return;
        }

        String doctorID = DocIDTextField.getText().trim();
        String doctorName = DocNameTextField.getText().trim();
        String specialization = DocSpecificationComboBox.getSelectedItem().toString();
        String phoneNumber = DocTelNoTextField.getText().trim();
        String email = DocEmailTextField.getText().trim();
        String experience = DocExperienceTextField.getText().trim();

        try {
            File file = new File("doctors.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(this, "Doctor database file not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Read all lines from the file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            boolean doctorUpdated = false;
            
            // Add header line
            line = reader.readLine();
            if (line != null) {
                content.append(line).append("\n");
            }
            
            // Process each doctor line
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(doctorID)) {
                    // Update existing doctor
                    content.append(doctorID).append(",")
                          .append(doctorName).append(",")
                          .append(specialization).append(",")
                          .append(phoneNumber).append(",")
                          .append(email).append(",")
                          .append(experience).append("\n");
                    doctorUpdated = true;
                } else {
                    // Keep existing line
                    content.append(line).append("\n");
                }
            }
            reader.close();
            
            // Write updated content back to file
            FileWriter writer = new FileWriter(file);
            writer.write(content.toString());
            writer.close();
            
            if (doctorUpdated) {
                JOptionPane.showMessageDialog(this, "Doctor information updated successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error: Doctor ID not found in database", 
                        "Update Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating doctor information: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                          

    private boolean validateDoctorInput() {
        // Check Doctor ID
        if (DocIDTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Doctor ID cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            DocIDTextField.requestFocus();
            return false;
        }
        
        // Check Doctor Name
        if (DocNameTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Doctor Name cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            DocNameTextField.requestFocus();
            return false;
        }
        
        // Check Phone Number
        String phoneNumber = DocTelNoTextField.getText().trim();
        if (phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone Number cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            DocTelNoTextField.requestFocus();
            return false;
        }
        
        if (!phoneNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Phone Number should contain only digits", "Validation Error", JOptionPane.ERROR_MESSAGE);
            DocTelNoTextField.requestFocus();
            return false;
        }
        
        // Check Email
        String email = DocEmailTextField.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            DocEmailTextField.requestFocus();
            return false;
        }
        
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address", "Validation Error", JOptionPane.ERROR_MESSAGE);
            DocEmailTextField.requestFocus();
            return false;
        }
        
        // Check Experience
        String experience = DocExperienceTextField.getText().trim();
        if (experience.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Years of Experience cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
            DocExperienceTextField.requestFocus();
            return false;
        }
        
        try {
            int experienceYears = Integer.parseInt(experience);
            if (experienceYears < 0) {
                JOptionPane.showMessageDialog(this, "Years of Experience cannot be negative", "Validation Error", JOptionPane.ERROR_MESSAGE);
                DocExperienceTextField.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Years of Experience must be a valid number", "Validation Error", JOptionPane.ERROR_MESSAGE);
            DocExperienceTextField.requestFocus();
            return false;
        }
        
        return true;
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        // TODO add your handling code here:
        resetForm();
        setVisible(false);
        dispose();
    }//GEN-LAST:event_CloseButtonActionPerformed

      private void resetForm() {
        DocIDTextField.setText("");
        resetFormFields();
        SaveButton.setEnabled(false);
    }
      
    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
        String userRole = getUserRole();
        if (userRole.equals("Cashier")) {
            new CashierDashboard().setVisible(true);
        } else if (userRole.equals("Manager")) {
            new ManagerDashboard().setVisible(true);
        } else {
            // Default fallback
            new ManagerDashboard().setVisible(true);
        }
        this.dispose(); // Close current form
    }                                          

    private String getUserRole() {
        // This function should get the current user's role from your system
        try {
            File file = new File("current_user.txt");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        scanner.close();
                        return parts[1]; // Assuming format is "username,role"
                    }
                }
                scanner.close();
            }
        } catch (Exception e) {
            // If there's an error, default to Manager
            return "Manager";
        }

        // Default to Manager if can't determine
        return "Manager";
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
            java.util.logging.Logger.getLogger(UpdateDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateDoctor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton CloseButton;
    private javax.swing.JTextField DocEmailTextField;
    private javax.swing.JTextField DocExperienceTextField;
    private javax.swing.JTextField DocIDTextField;
    private javax.swing.JTextField DocNameTextField;
    private javax.swing.JComboBox<String> DocSpecificationComboBox;
    private javax.swing.JTextField DocTelNoTextField;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
