import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class AddDoctor extends javax.swing.JFrame {
    
    // Add this class variable to store the previous frame
    private javax.swing.JFrame previousFrame;

    // Modified constructor to accept the previous frame
    public AddDoctor(javax.swing.JFrame previous) {
        initComponents();
        this.previousFrame = previous;
        
        // Add focus listener to DocIDTextField to check for existing doctor ID
        DocIDTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkDoctorExists();
            }
        });
    }
    
    // Default constructor for compatibility
    public AddDoctor() {
        this(null); // Call the parameterized constructor with null
    }
    
    // Method to check if the doctor ID already exists and populate fields if it does
    private void checkDoctorExists() {
        String doctorID = DocIDTextField.getText().trim();
        if (doctorID.isEmpty()) {
            return;
        }
        
        try {
            File file = new File("doctors.txt");
            if (!file.exists()) {
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            // Skip header line
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[0].equals(doctorID)) {
                    // Doctor found - populate fields
                    DocNameTextField.setText(parts[1]);
                    
                    // Set the specialization in combo box
                    String specialization = parts[2];
                    for (int i = 0; i < DocSpecificationComboBox.getItemCount(); i++) {
                        if (DocSpecificationComboBox.getItemAt(i).equals(specialization)) {
                            DocSpecificationComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                    
                    DocTelNoTextField.setText(parts[3]);
                    DocEmailTextField.setText(parts[4]);
                    DocExperienceTextField.setText(parts[5]);
                    
                    JOptionPane.showMessageDialog(this, "Doctor ID already exists!");
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error reading doctor data: " + e.getMessage());
        }
    }

    // Method to validate doctor input
    private boolean validateDoctorInput() {
        if (DocIDTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Doctor ID");
            return false;
        }
        
        if (DocNameTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Doctor Name");
            return false;
        }
        
        if (DocTelNoTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Phone Number");
            return false;
        }
        
        if (DocEmailTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Email");
            return false;
        }
        
        if (DocExperienceTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Years of Experience");
            return false;
        }
        
        // Validate if years of experience is a number
        try {
            Integer.parseInt(DocExperienceTextField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Years of Experience must be a number");
            return false;
        }
        
        return true;
    }

    // Method to reset the form
    private void resetForm() {
        DocIDTextField.setText("");
        DocNameTextField.setText("");
        DocSpecificationComboBox.setSelectedIndex(0);
        DocTelNoTextField.setText("");
        DocEmailTextField.setText("");
        DocExperienceTextField.setText("");
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
        setLocation(new java.awt.Point(200, 150));
        setUndecorated(true);
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
        SaveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        SaveButton.setText("Save");
        SaveButton.setToolTipText("");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });
        getContentPane().add(SaveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, -1, -1));

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
        CloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close.png"))); // NOI18N
        CloseButton.setText("Close");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, -1, -1));

        jLabel7.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 26)); // NOI18N
        jLabel7.setText("ADD NEW CONSULTANT");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        DocSpecificationComboBox.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        DocSpecificationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General Medicine", "Cardiologist", "Pediatrician", "Desntist", "Surgen", "Psychiatrist" }));
        getContentPane().add(DocSpecificationComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 250, -1));

        BackButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        BackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exit.png"))); // NOI18N
        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DocIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocIDTextFieldActionPerformed
        // TODO add your handling code here:
        checkDoctorExists();    
        
    }//GEN-LAST:event_DocIDTextFieldActionPerformed

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
            boolean exists = file.exists();
            boolean doctorExists = false;
            
            // Check if doctor already exists and prepare updated content
            StringBuilder content = new StringBuilder();
            if (exists) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                
                // Add header line
                line = reader.readLine();
                if (line != null) {
                    content.append(line).append("\n");
                }
                
                // Check each doctor line
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
                        doctorExists = true;
                    } else {
                        // Keep existing line
                        content.append(line).append("\n");
                    }
                }
                reader.close();
            } else {
                // Create new file with header
                content.append("DoctorID,DoctorName,DoctorSpecialization,DoctorTelNo,DocEmail,Experience\n");
            }
            
            // Add new doctor if not already exists
            if (!doctorExists) {
                content.append(doctorID).append(",")
                       .append(doctorName).append(",")
                       .append(specialization).append(",")
                       .append(phoneNumber).append(",")
                       .append(email).append(",")
                       .append(experience).append("\n");
            }
            
            // Write to file
            FileWriter writer = new FileWriter(file);
            writer.write(content.toString());
            writer.close();
            
            JOptionPane.showMessageDialog(this, "Doctor information saved successfully!");
            resetForm();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving doctor information: " + e.getMessage());
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    
    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        // TODO add your handling code here:
        resetForm();
        setVisible(false);
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
       // Make the previous frame visible if it exists
        if (previousFrame != null) {
            previousFrame.setVisible(true);
        }
        // Close and dispose of this frame
        setVisible(false);
        dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

     
   
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
            java.util.logging.Logger.getLogger(AddDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDoctor().setVisible(true);
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
