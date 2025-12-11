import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

public class AddAppointments extends javax.swing.JFrame {
    private HashMap<String, String[]> appointmentData = new HashMap<>();
    private String callingFrame;
    
    public AddAppointments() {
        initComponents();
        loadAppointmentData();
        
        // Center the form on screen
        setLocationRelativeTo(null);
        
        // Add event listeners for the fields
        setupEventListeners();
        this.callingFrame = "Unknown";
    }
    
    public AddAppointments(String callingFrame) {
        this(); // Call the default constructor first
        this.callingFrame = callingFrame;
    }


    private void setupEventListeners() {
        // Add listener for Patient ID field to check if patient exists
        PatientIDTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkPatientExists();
            }
        });
        
        // Add listener for Doctor ID field to auto-fill doctor name
        DocIDTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkDoctorExists();
            }
        });
        
        // Add action listeners for the buttons
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAppointment();
            }
        });
        
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetForm();
            }
        });
        
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBack();
            }
        });
    }
    
    private void loadAppointmentData() {
        try {
            File file = new File("appointments.txt");
            if (!file.exists()) {
                return;
            }
            
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 11) {
                    // patientId, patientName, category, age, address, email, contact, docSpec, appointmentDate, docId, docName
                    appointmentData.put(parts[0], parts);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading appointment data: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void checkPatientExists() {
        String patientId = PatientIDTextField.getText().trim();
        if (patientId.isEmpty()) {
            return;
        }
        
        // Check if the patient ID exists in appointments
        if (appointmentData.containsKey(patientId)) {
            String[] details = appointmentData.get(patientId);
            PatientNameTextField.setText(details[1]);  // Patient name
            PCategoryComboBox.setSelectedItem(details[2]);  // Category
            AgeTextField.setText(details[3]);  // Age
            AddressTextField.setText(details[4]);  // Address
            EmailTextField.setText(details[5]);  // Email
            ContactNoTextField.setText(details[6]);  // Contact
            
            JOptionPane.showMessageDialog(this, "Patient record already exists!", 
                    "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // If not found, allow user to enter new patient details
            PatientNameTextField.setText("");
            PCategoryComboBox.setSelectedIndex(0);
            AgeTextField.setText("");
            AddressTextField.setText("");
            EmailTextField.setText("");
            ContactNoTextField.setText("");
        }
    }
    
    private void checkDoctorExists() {
        String doctorId = DocIDTextField.getText().trim();
        if (doctorId.isEmpty()) {
            return;
        }
        
        // Check all appointments for this doctor ID
        boolean doctorFound = false;
        String doctorName = "";
        String doctorSpec = "";
        
        for (String[] details : appointmentData.values()) {
            if (details.length >= 11 && details[9].equals(doctorId)) {
                doctorName = details[10];  // Doctor name
                doctorSpec = details[7];   // Doctor specification
                doctorFound = true;
                break;
            }
        }
        
        if (doctorFound) {
            DocNameTextField.setText(doctorName);
            
            // Check if doctor's specification matches the selected one
            String selectedSpec = DocSpecificationComboBox.getSelectedItem().toString();
            if (!doctorSpec.equals(selectedSpec)) {
                JOptionPane.showMessageDialog(this, 
                        "Warning: This doctor's specification is " + doctorSpec + 
                        " but you've selected " + selectedSpec, 
                        "Specification Mismatch", JOptionPane.WARNING_MESSAGE);
                DocSpecificationComboBox.setSelectedItem(doctorSpec);
            }
        } else {
            // New doctor, let user enter details
            DocNameTextField.setText("");
        }
    }
    
    private void saveAppointment() {
        // Validate all required fields
        if (!validateFields()) {
            return;
        }
        
        try {
            // Format the appointment date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String appointmentDate = "";
            if (jDateChooser1.getDate() != null) {
                appointmentDate = sdf.format(jDateChooser1.getDate());
            } else {
                JOptionPane.showMessageDialog(this, "Please select an appointment date.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String patientId = PatientIDTextField.getText().trim();
            
            // Check if patient already exists
            if (appointmentData.containsKey(patientId)) {
                int option = JOptionPane.showConfirmDialog(this, 
                        "Patient ID already exists. Do you want to create a new appointment for this patient?", 
                        "Patient Exists", JOptionPane.YES_NO_OPTION);
                
                if (option != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            
            // Create a line with all appointment details
            String appointmentLine = 
                    patientId + "," +
                    PatientNameTextField.getText().trim() + "," +
                    PCategoryComboBox.getSelectedItem().toString() + "," +
                    AgeTextField.getText().trim() + "," +
                    AddressTextField.getText().trim() + "," +
                    EmailTextField.getText().trim() + "," +
                    ContactNoTextField.getText().trim() + "," +
                    DocSpecificationComboBox.getSelectedItem().toString() + "," +
                    appointmentDate + "," +
                    DocIDTextField.getText().trim() + "," +
                    DocNameTextField.getText().trim();
            
            // Create appointments.txt if it doesn't exist
            File file = new File("appointments.txt");
            boolean fileExists = file.exists();
            
            // Save to appointments.txt
            FileWriter fw = new FileWriter("appointments.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            // Add a new line if appending to existing file
            if (fileExists) {
                bw.newLine();
            }
            
            bw.write(appointmentLine);
            bw.close();
            
            // Update the local data structure
            appointmentData.put(patientId, appointmentLine.split(","));
            
            JOptionPane.showMessageDialog(this, "Appointment saved successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Reset the form after saving
            resetForm();
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving appointment: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validateFields() {
        // Check Patient ID
        if (PatientIDTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient ID is required.", "Error", JOptionPane.ERROR_MESSAGE);
            PatientIDTextField.requestFocus();
            return false;
        }
        
        // Check Patient Name
        if (PatientNameTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient Name is required.", "Error", JOptionPane.ERROR_MESSAGE);
            PatientNameTextField.requestFocus();
            return false;
        }
        
        // Check Age
        if (AgeTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Age is required.", "Error", JOptionPane.ERROR_MESSAGE);
            AgeTextField.requestFocus();
            return false;
        }
        
        // Check Doctor ID
        if (DocIDTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Doctor ID is required.", "Error", JOptionPane.ERROR_MESSAGE);
            DocIDTextField.requestFocus();
            return false;
        }
        
        // Check Doctor Name
        if (DocNameTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Doctor Name is required.", "Error", JOptionPane.ERROR_MESSAGE);
            DocNameTextField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void resetForm() {
        // Clear all form fields
        PatientIDTextField.setText("");
        PatientNameTextField.setText("");
        AgeTextField.setText("");
        AddressTextField.setText("");
        EmailTextField.setText("");
        ContactNoTextField.setText("");
        DocIDTextField.setText("");
        DocNameTextField.setText("");
        jDateChooser1.setDate(null);
        PCategoryComboBox.setSelectedIndex(0);
        DocSpecificationComboBox.setSelectedIndex(0);
    }
    
    private void goBack() {
         // Use the stored calling frame information to determine where to go back to
        if ("CashierDashboard".equals(callingFrame)) {
            new CashierDashboard().setVisible(true);
        } else if ("ManagerDashboard".equals(callingFrame)) {
            new ManagerDashboard().setVisible(true);
        } else {
            // If the calling frame is unknown, just open the previous frame using navigation history
            // or default to opening the dashboard based on user role
            openPreviousFrame();
        }
        this.dispose(); // Close current form
    }
    
    private void openPreviousFrame() {
        // Try to determine the user role first
        String userRole = getUserRole();
        
        if ("Cashier".equals(userRole)) {
            new CashierDashboard().setVisible(true);
        } else if ("Manager".equals(userRole)) {
            new ManagerDashboard().setVisible(true);
        } else {
            // If we can't determine the role, just default to Manager dashboard
            new ManagerDashboard().setVisible(true);
        }
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
    }
    
    // Rest of the code remains the same...
    // The UI components, main method, etc.

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        PatientIDTextField = new javax.swing.JTextField();
        PatientNameTextField = new javax.swing.JTextField();
        AgeTextField = new javax.swing.JTextField();
        AddressTextField = new javax.swing.JTextField();
        EmailTextField = new javax.swing.JTextField();
        ContactNoTextField = new javax.swing.JTextField();
        DocSpecificationComboBox = new javax.swing.JComboBox<>();
        DocIDTextField = new javax.swing.JTextField();
        DocNameTextField = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        PCategoryComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel1.setText("Patient Name");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel2.setText("Age");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel3.setText("Address");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel5.setText("Contact No");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel6.setText("Doctor ID");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel7.setText("Patient ID");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel8.setText("Doctor Name");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, -1, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel9.setText("Doctor Specification");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel10.setText("Appointment Date");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        PatientIDTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(PatientIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 449, -1));

        PatientNameTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(PatientNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 449, -1));

        AgeTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        AgeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgeTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(AgeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 449, -1));

        AddressTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(AddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 449, -1));

        EmailTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(EmailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 449, -1));

        ContactNoTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(ContactNoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 449, -1));

        DocSpecificationComboBox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        DocSpecificationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General Medicine", "Cardiologist", "Pediatrician", "Desntist", "Surgen", "Psychiatrist" }));
        getContentPane().add(DocSpecificationComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 449, -1));

        DocIDTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(DocIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, 449, -1));

        DocNameTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(DocNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, 449, -1));

        jDateChooser1.setForeground(new java.awt.Color(204, 0, 0));
        jDateChooser1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 410, 449, -1));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("SAVE");
        jButton1.setActionCommand(" SAVE");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 540, -1, -1));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("RESET");
        jButton2.setActionCommand("CANCEL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 540, -1, -1));

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setText("BACK");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, -1, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel11.setText("Patient Category");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        PCategoryComboBox.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        PCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General", "Emergency", "Pediatric", "Maternity" }));
        getContentPane().add(PCategoryComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 449, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patient banner.jpg"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AgeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AgeTextFieldActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         resetForm();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(AddAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddAppointments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddAppointments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddressTextField;
    private javax.swing.JTextField AgeTextField;
    private javax.swing.JTextField ContactNoTextField;
    private javax.swing.JTextField DocIDTextField;
    private javax.swing.JTextField DocNameTextField;
    private javax.swing.JComboBox<String> DocSpecificationComboBox;
    private javax.swing.JTextField EmailTextField;
    private javax.swing.JComboBox<String> PCategoryComboBox;
    private javax.swing.JTextField PatientIDTextField;
    private javax.swing.JTextField PatientNameTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
