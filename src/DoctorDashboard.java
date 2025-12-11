import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;


public class DoctorDashboard extends javax.swing.JFrame {

   
    public DoctorDashboard() {
        initComponents();
        setLocationRelativeTo(null); // Center the window
        loadAppointmentsData();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        HomeButton = new javax.swing.JButton();
        AppointmentsButton = new javax.swing.JButton();
        PatientsButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/doctor icon.png"))); // NOI18N

        HomeButton.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        HomeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home-icon.png"))); // NOI18N
        HomeButton.setText(" HOME");
        HomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeButtonActionPerformed(evt);
            }
        });

        AppointmentsButton.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        AppointmentsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appointment icon.png"))); // NOI18N
        AppointmentsButton.setText(" APPOINTMENTS");
        AppointmentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AppointmentsButtonActionPerformed(evt);
            }
        });

        PatientsButton.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        PatientsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paient details icon.png"))); // NOI18N
        PatientsButton.setText(" PATIENTS DETAILS");
        PatientsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatientsButtonActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sign-out-icon.png"))); // NOI18N
        jButton5.setText(" LOGOUT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addContainerGap(43, Short.MAX_VALUE))
            .addComponent(AppointmentsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PatientsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(HomeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(AppointmentsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PatientsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(HomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 540));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 48)); // NOI18N
        jLabel2.setText("DOCTOR DASHBOARD");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addContainerGap(516, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 1050, 80));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 111, 1050, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeButtonActionPerformed
        
    this.dispose();
    
    new Login().setVisible(true);
    }//GEN-LAST:event_HomeButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    this.dispose();
    new MultiLogin().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void AppointmentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AppointmentsButtonActionPerformed
        // TODO add your handling code here:
        loadAppointmentsData();
    }//GEN-LAST:event_AppointmentsButtonActionPerformed

    private void PatientsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientsButtonActionPerformed
        // TODO add your handling code here:
        loadPatientsData();
    }//GEN-LAST:event_PatientsButtonActionPerformed

    // Method to load appointments data into the table
    private void loadAppointmentsData() {
        // Define column names for appointments
        String[] columnNames = {"PatientID", "PatientName", "Category", "Age", "Contact", "DoctorID"};
        
        // Create a new table model with the column names
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        try {
            // Open the appointments.txt file
            BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"));
            String line;
            
            // Skip the header line if it exists
            line = reader.readLine();
            
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                
                // Make sure we have enough data
                if (data.length >= 10) {
                    // Create a row with the required fields (PatientID, PatientName, Category, Age, Contact, DoctorID)
                    Object[] row = {
                        data[0].trim(),   // PatientID
                        data[1].trim(),   // PatientName
                        data[2].trim(),   // Category
                        data[3].trim(),   // Age
                        data[6].trim(),   // Contact
                        data[9].trim()    // DoctorID
                    };
                    
                    // Add the row to the model
                    model.addRow(row);
                }
            }
            
            // Close the reader
            reader.close();
            
            // Set the new model for the table
            jTable1.setModel(model);
            
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error reading appointments file: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Method to load patients data into the table
    private void loadPatientsData() {
        // Define column names for patients
        String[] columnNames = {"PatientID", "PatientName", "Age", "Address", "Email", "Contact"};
        
        // Create a new table model with the column names
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        try {
            // Open the appointments.txt file
            BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"));
            String line;
            
            // Skip the header line if it exists
            line = reader.readLine();
            
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                
                // Make sure we have enough data
                if (data.length >= 7) {
                    // Create a row with the required fields (PatientID, PatientName, Age, Address, Email, Contact)
                    Object[] row = {
                        data[0].trim(),   // PatientID
                        data[1].trim(),   // PatientName
                        data[3].trim(),   // Age
                        data[4].trim(),   // Address
                        data[5].trim(),   // Email
                        data[6].trim()    // Contact
                    };
                    
                    // Add the row to the model
                    model.addRow(row);
                }
            }
            
            // Close the reader
            reader.close();
            
            // Set the new model for the table
            jTable1.setModel(model);
            
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error reading patients file: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(DoctorDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoctorDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoctorDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoctorDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AppointmentsButton;
    private javax.swing.JButton HomeButton;
    private javax.swing.JButton PatientsButton;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
