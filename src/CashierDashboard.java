import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class CashierDashboard extends javax.swing.JFrame {

    private List<String[]> appointmentData = new ArrayList<>();
    private List<String[]> doctorData = new ArrayList<>();
    private String currentView = "";
    
    public CashierDashboard() {
        initComponents();
        loadAppointmentData();
        loadDoctorData();
        //Center the form
        this.setLocationRelativeTo(null);
        
        // Add action listener to Appointments Button if not already added
        if (jButton2.getActionListeners().length == 0) {
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    AppointmentsButtonActionPerformed(evt);
                }
            });
    }

     // Add action listener to Patients Button if not already added
        if (jButton4.getActionListeners().length == 0) {
            jButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    PatientsButtonActionPerformed(evt);
                }
            });
        }
        
        // Add action listener to Consultants Button if not already added
        if (ConsultantsButton.getActionListeners().length == 0) {
            ConsultantsButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ConsultantsButtonActionPerformed(evt);
                }
            });
        }
        
        // Add action listener to Search Doctor Button if not already added
        if (SearchDocButton.getActionListeners().length == 0) {
            SearchDocButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    SearchDocButtonActionPerformed(evt);
                }
            });
        }
        
        // Add action listeners for the add buttons
        if (AddAppointmentButton.getActionListeners().length == 0) {
            AddAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    AddAppointmentButtonActionPerformed(evt);
                }
            });
        }
        
    }
    
    private void loadAppointmentData() {
        appointmentData.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }
                
                String[] rowData = line.split(",");
                appointmentData.add(rowData);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading appointment data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadDoctorData() {
        doctorData.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("doctors.txt"))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }
                
                String[] rowData = line.split(",");
                doctorData.add(rowData);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading doctor data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayAppointmentData() {
        currentView = "appointments";
        String[] columnNames = {"Patient ID", "Patient Name", "Category", "Age", "Contact", "Doctor ID"};
        Object[][] data = new Object[appointmentData.size()][columnNames.length];
        
        for (int i = 0; i < appointmentData.size(); i++) {
            String[] row = appointmentData.get(i);
            if (row.length >= 11) { // Ensure we have enough data
                data[i][0] = row[0]; // Patient ID
                data[i][1] = row[1]; // Patient Name
                data[i][2] = row[2]; // Category
                data[i][3] = row[3]; // Age
                data[i][4] = row[6]; // Contact
                data[i][5] = row[9]; // Doctor ID
            }
        }
        
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, columnNames);
        jTable1.setModel(model);
    }
    
    private void displayPatientData() {
        currentView = "patients";
        String[] columnNames = {"Patient ID", "Patient Name", "Age", "Address", "Email", "Contact"};
        Object[][] data = new Object[appointmentData.size()][columnNames.length];
        
        for (int i = 0; i < appointmentData.size(); i++) {
            String[] row = appointmentData.get(i);
            if (row.length >= 7) { // Ensure we have enough data
                data[i][0] = row[0]; // Patient ID
                data[i][1] = row[1]; // Patient Name
                data[i][2] = row[3]; // Age
                data[i][3] = row[4]; // Address
                data[i][4] = row[5]; // Email
                data[i][5] = row[6]; // Contact
            }
        }
        
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, columnNames);
        jTable1.setModel(model);
    }
    
    private void displayDoctorData() {
        currentView = "doctors";
        String[] columnNames = {"Doctor ID", "Doctor Name", "Specialization", "Contact", "Email", "Experience"};
        Object[][] data = new Object[doctorData.size()][columnNames.length];
        
        for (int i = 0; i < doctorData.size(); i++) {
            String[] row = doctorData.get(i);
            if (row.length >= 6) { // Ensure we have enough data
                data[i][0] = row[0]; // Doctor ID
                data[i][1] = row[1]; // Doctor Name
                data[i][2] = row[2]; // Specialization
                data[i][3] = row[3]; // Contact
                data[i][4] = row[4]; // Email
                data[i][5] = row[5]; // Experience
            }
        }
        
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, columnNames);
        jTable1.setModel(model);
    }
    
    private void searchDoctors(String searchTerm) {
        // Always make sure doctor data is loaded before searching
        loadDoctorData();
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            displayDoctorData(); // If search term is empty, display all doctors
            return;
        }
        
        searchTerm = searchTerm.toLowerCase().trim();
        currentView = "doctors";
        
        // Create a list to store the search results
        List<String[]> searchResults = new ArrayList<>();
        
        // Search for doctors with matching name or specialization
        for (String[] doctor : doctorData) {
            if (doctor.length >= 6) { // Make sure we have all required data
                String doctorId = doctor[0].toLowerCase();
                String doctorName = doctor[1].toLowerCase();
                String specialization = doctor[2].toLowerCase();
                
                if (doctorId.contains(searchTerm) || 
                    doctorName.contains(searchTerm) || 
                    specialization.contains(searchTerm)) {
                    searchResults.add(doctor);
                }
            }
        }
        // Check if search results are empty
    if (searchResults.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
                                     "No results found for: " + searchTerm, 
                                     "Search Results", 
                                     JOptionPane.INFORMATION_MESSAGE);
        // You can either display all doctors or keep the current view
        displayDoctorData(); // Optional: show all doctors when no results found
        return;
    }
        // Display the search results
        String[] columnNames = {"Doctor ID", "Doctor Name", "Specialization", "Contact", "Email", "Experience"};
        Object[][] data = new Object[searchResults.size()][columnNames.length];
        
        for (int i = 0; i < searchResults.size(); i++) {
            String[] row = searchResults.get(i);
            if (row.length >= 6) {
                data[i][0] = row[0]; // Doctor ID
                data[i][1] = row[1]; // Doctor Name
                data[i][2] = row[2]; // Specialization
                data[i][3] = row[3]; // Contact
                data[i][4] = row[4]; // Email
                data[i][5] = row[5]; // Experience
            }
        }
        
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, columnNames);
        jTable1.setModel(model);
    }

    private void AppointmentsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        loadAppointmentData();
        displayAppointmentData();
    }
    
    private void PatientsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        loadAppointmentData();
        displayPatientData();
    }
    
    
    
    private void SearchDocButtonActionPerformed(java.awt.event.ActionEvent evt) {
        loadDoctorData();
        
        // Get search term and perform search
        String searchTerm = SearchTextField.getText();
        searchDoctors(searchTerm);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        LogoutButton = new javax.swing.JButton();
        ConsultantsButton = new javax.swing.JButton();
        HomeButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        AddAppointmentButton = new javax.swing.JButton();
        SearchDocButton = new javax.swing.JButton();
        SearchTextField = new javax.swing.JTextField();
        AddPatientButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cashier.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 13, -1, 248));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appointment icon.png"))); // NOI18N
        jButton2.setText(" APPOINTMENTS");
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 310, 50));

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paient details icon.png"))); // NOI18N
        jButton4.setText(" PATIENT DETAILS");
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 310, 50));

        LogoutButton.setBackground(new java.awt.Color(204, 204, 204));
        LogoutButton.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        LogoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sign-out-icon.png"))); // NOI18N
        LogoutButton.setText("LOGOUT");
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });
        jPanel2.add(LogoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 310, 50));

        ConsultantsButton.setBackground(new java.awt.Color(204, 204, 204));
        ConsultantsButton.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        ConsultantsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DOC DETAILS Icon.png"))); // NOI18N
        ConsultantsButton.setText("CONSULTANTS");
        ConsultantsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultantsButtonActionPerformed(evt);
            }
        });
        jPanel2.add(ConsultantsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 310, 51));

        HomeButton.setBackground(new java.awt.Color(204, 204, 204));
        HomeButton.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        HomeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home-icon.png"))); // NOI18N
        HomeButton.setText("HOME");
        HomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeButtonActionPerformed(evt);
            }
        });
        jPanel2.add(HomeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 310, 51));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 620));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 48)); // NOI18N
        jLabel2.setText("CASHIER DASHBOARD");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(490, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 1050, 80));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 1050, -1));

        AddAppointmentButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        AddAppointmentButton.setText("ADD APPOINTMENT");
        AddAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAppointmentButtonActionPerformed(evt);
            }
        });
        jPanel1.add(AddAppointmentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 120, -1, -1));

        SearchDocButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        SearchDocButton.setText("SEARCH DOCTOR");
        jPanel1.add(SearchDocButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, -1, -1));

        SearchTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(SearchTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 300, -1));

        AddPatientButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        AddPatientButton.setText("ADD PATIENT");
        AddPatientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPatientButtonActionPerformed(evt);
            }
        });
        jPanel1.add(AddPatientButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 120, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConsultantsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultantsButtonActionPerformed
        // TODO add your handling code here:
        loadDoctorData();
        displayDoctorData();
    }//GEN-LAST:event_ConsultantsButtonActionPerformed

    private void HomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeButtonActionPerformed
    for (java.awt.Frame frame : java.awt.Frame.getFrames()) {
        if (frame instanceof CashierDashboard) {
            frame.dispose();
        }
    }
    
    // Open the Login frame
    new Login().setVisible(true);
    }//GEN-LAST:event_HomeButtonActionPerformed

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        for (java.awt.Frame frame : java.awt.Frame.getFrames()) {
        if (frame instanceof CashierDashboard) {
            frame.dispose();
        }
    }
    
    // Open the MultiLogin frame
    new MultiLogin().setVisible(true);
    }//GEN-LAST:event_LogoutButtonActionPerformed

    private void AddAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAppointmentButtonActionPerformed
        // TODO add your handling code here:
        new AddAppointments("CashierDashboard").setVisible(true);
    }//GEN-LAST:event_AddAppointmentButtonActionPerformed

    private void AddPatientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPatientButtonActionPerformed
        // TODO add your handling code here:
        new AddAppointments("CashierDashboard").setVisible(true);
    }//GEN-LAST:event_AddPatientButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CashierDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashierDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashierDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashierDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CashierDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAppointmentButton;
    private javax.swing.JButton AddPatientButton;
    private javax.swing.JButton ConsultantsButton;
    private javax.swing.JButton HomeButton;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JButton SearchDocButton;
    private javax.swing.JTextField SearchTextField;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
