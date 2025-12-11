import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

public class UpdatePatient extends javax.swing.JFrame {

        // File paths for data
    private static final String APPOINTMENTS_FILE_PATH = "appointments.txt";
    private static final String DOCTORS_FILE_PATH = "doctors.txt";
    
    private HashMap<String, String[]> appointmentsData;
    private HashMap<String, String[]> doctorsData;
    
    // Previous frame reference
    private javax.swing.JFrame previousFrame;
    
    public UpdatePatient() {
        initComponents();
        loadAppointmentsData();
        loadDoctorsData();
        setupListeners();
        setLocationRelativeTo(null);
        
    }
     
    /**
     * Creates new form UpdatePatient with reference to previous frame
     */
    public UpdatePatient(javax.swing.JFrame previousFrame) {
        this();
        this.previousFrame = previousFrame;
    }
    
    /**
     * Load appointments data from text file
     */
    private void loadAppointmentsData() {
        appointmentsData = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_FILE_PATH))) {
            String line;
            // Skip header line
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 11) {
                    appointmentsData.put(data[0], data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading appointments data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Load doctors data from text file
     */
    private void loadDoctorsData() {
        doctorsData = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTORS_FILE_PATH))) {
            String line;
            // Skip header line
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    doctorsData.put(data[0], data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading doctors data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Setup action listeners for components
     */
    private void setupListeners() {
        // PatientID field focus lost listener to auto-fill other fields
        PatientIDTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String patientId = PatientIDTextField.getText();
                if (!patientId.isEmpty()) {
                    populateFieldsFromPatientId(patientId);
                }
            }
        });
        
        // DoctorID field focus lost listener to auto-fill doctor name
        DocIDTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String doctorId = DocIDTextField.getText();
                if (!doctorId.isEmpty()) {
                    updateDoctorDetails(doctorId);
                }
            }
        });
        
        // Update button action listener
        UpdatenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                updatePatientData();
            }
        });
        
        // Cancel button action listener
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                resetForm();
            }
        });
        
        // Back button action listener
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String userRole = getUserRole();
                if (userRole.equals("Cashier")) {
                    new CashierDashboard().setVisible(true);
                } else if (userRole.equals("Manager")) {
                    new ManagerDashboard().setVisible(true);
                } else {
                    // Default fallback
                    new ManagerDashboard().setVisible(true);
                }
                dispose(); // Close current form
            }
        });
    }
    
    /**
     * Get user role from current_user.txt
     */
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
    
    /**
     * Update doctor details based on doctor ID
     */
    private void updateDoctorDetails(String doctorId) {
        if (doctorsData.containsKey(doctorId)) {
            String[] doctorInfo = doctorsData.get(doctorId);
            DocNameTextField.setText(doctorInfo[1]);
        } else {
            JOptionPane.showMessageDialog(this, "Doctor ID not found in records.",
                    "Not Found", JOptionPane.WARNING_MESSAGE);
            DocNameTextField.setText("");
        }
    }
    
    /**
     * Populate form fields based on patient ID
     */
    private void populateFieldsFromPatientId(String patientId) {
        if (appointmentsData.containsKey(patientId)) {
            String[] data = appointmentsData.get(patientId);
            
            // Populate form fields
            PatientNameTextField.setText(data[1]);
            PCategoryComboBox.setSelectedItem(data[2]);
            AgeTextField.setText(data[3]);
            
            // Set appointment date
            try {
                Date appointmentDate = new SimpleDateFormat("yyyy-MM-dd").parse(data[8]);
                jDateChooser1.setDate(appointmentDate);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Error parsing date: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            DocIDTextField.setText(data[9]);
            DocNameTextField.setText(data[10]);
        } else {
            JOptionPane.showMessageDialog(this, "Patient ID not found in records.",
                    "Not Found", JOptionPane.WARNING_MESSAGE);
            resetForm();
        }
    }
    
    /**
     * Update patient data in memory and file
     */
    private void updatePatientData() {
        String patientId = PatientIDTextField.getText();
        
        if (patientId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Patient ID",
                    "Missing Data", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!appointmentsData.containsKey(patientId)) {
            JOptionPane.showMessageDialog(this, "Patient ID not found in records.",
                    "Not Found", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validate doctor ID
        String doctorId = DocIDTextField.getText();
        if (!doctorsData.containsKey(doctorId)) {
            JOptionPane.showMessageDialog(this, "Doctor ID not found in records.",
                    "Invalid Doctor", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Get current data
        String[] currentData = appointmentsData.get(patientId);
        String[] doctorInfo = doctorsData.get(doctorId);
        
        // Update with new values
        currentData[1] = PatientNameTextField.getText();
        currentData[2] = PCategoryComboBox.getSelectedItem().toString();
        currentData[3] = AgeTextField.getText();
        
        // Format appointment date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = "";
        if (jDateChooser1.getDate() != null) {
            formattedDate = dateFormat.format(jDateChooser1.getDate());
        }
        currentData[8] = formattedDate;
        
        // Update doctor information
        currentData[9] = doctorId;
        currentData[10] = doctorInfo[1];
        currentData[7] = doctorInfo[2]; // Update Doctor Specialization from doctors.txt
        
        // Update in memory
        appointmentsData.put(patientId, currentData);
        
        // Update file
        updateAppointmentsFile();
        
        JOptionPane.showMessageDialog(this, "Patient information updated successfully",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Update the appointments.txt file with current data
     */
    private void updateAppointmentsFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENTS_FILE_PATH))) {
            // Write header
            writer.write("PatientID,PatientName,Category,Age,Address,Email,Contact,DoctorSpecialization,AppointmentDate,DoctorID,DoctorName");
            writer.newLine();
            
            // Write data
            for (String[] data : appointmentsData.values()) {
                writer.write(String.join(",", data));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating appointments file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Reset the form fields
     */
    private void resetForm() {
        PatientIDTextField.setText("");
        PatientNameTextField.setText("");
        PCategoryComboBox.setSelectedIndex(0);
        AgeTextField.setText("");
        jDateChooser1.setDate(null);
        DocIDTextField.setText("");
        DocNameTextField.setText("");
    }
    
    /**
     * Go back to previous frame
     */
    private void goBack() {
        if (previousFrame != null) {
            previousFrame.setVisible(true);
        }
        this.dispose();
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PatientIDTextField = new javax.swing.JTextField();
        PatientNameTextField = new javax.swing.JTextField();
        AgeTextField = new javax.swing.JTextField();
        DocIDTextField = new javax.swing.JTextField();
        DocNameTextField = new javax.swing.JTextField();
        UpdatenButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        CancelButton = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        PCategoryComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        jLabel1.setText("Patient ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 38, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        jLabel2.setText("Patient Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        jLabel3.setText("Patient Category");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 159, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        jLabel4.setText("Age");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 218, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        jLabel5.setText("Appointment Date");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 273, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        jLabel6.setText("Doctor ID");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 329, -1, -1));

        PatientIDTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(PatientIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 46, 325, -1));

        PatientNameTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(PatientNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 108, 325, -1));

        AgeTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(AgeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 221, 325, -1));

        DocIDTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(DocIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 323, 325, -1));

        DocNameTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(DocNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 381, 325, -1));

        UpdatenButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        UpdatenButton.setText("UPDATE");
        UpdatenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatenButtonActionPerformed(evt);
            }
        });
        getContentPane().add(UpdatenButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 433, -1, -1));

        BackButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        BackButton.setText("BACK");
        getContentPane().add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(714, 433, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        jLabel7.setText("Doctor Name");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 387, -1, -1));

        CancelButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        CancelButton.setText("CANCEL");
        getContentPane().add(CancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(591, 433, -1, -1));

        jDateChooser1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 273, 325, -1));

        PCategoryComboBox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General", "Emergency", "Pediatric", "Maternity" }));
        getContentPane().add(PCategoryComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 156, 325, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/update.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 1010, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UpdatenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatenButtonActionPerformed
        // TODO add your handling code here:
        updatePatientData();
    }//GEN-LAST:event_UpdatenButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
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
            java.util.logging.Logger.getLogger(UpdatePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdatePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdatePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdatePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdatePatient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AgeTextField;
    private javax.swing.JButton BackButton;
    private javax.swing.JButton CancelButton;
    private javax.swing.JTextField DocIDTextField;
    private javax.swing.JTextField DocNameTextField;
    private javax.swing.JComboBox<String> PCategoryComboBox;
    private javax.swing.JTextField PatientIDTextField;
    private javax.swing.JTextField PatientNameTextField;
    private javax.swing.JButton UpdatenButton;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
