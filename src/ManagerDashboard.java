import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


public class ManagerDashboard extends javax.swing.JFrame {
    
    private List<String[]> appointmentData = new ArrayList<>();
    private List<String[]> doctorData = new ArrayList<>();
    private String currentView = "";

    public ManagerDashboard() {
        initComponents();
        loadAppointmentData();
        loadDoctorData(); 
        
        // Add action listener to AppointmentsButton if not already added
        if (AppointmentsButton.getActionListeners().length == 0) {
            AppointmentsButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    AppointmentsButtonActionPerformed(evt);
                }
            });
        }
        
        // Add action listener to PatientsButton if not already added
        if (PatientsButton.getActionListeners().length == 0) {
            PatientsButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    PatientsButtonActionPerformed(evt);
                }
            });
        }
       // Add action listener to ConsultantsButton if not already added
        if (ConsultantsButton.getActionListeners().length == 0) {
            ConsultantsButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ConsultantsButtonActionPerformed(evt);
                }
            });
        }
        
        // Add action listener to SearchDocButton if not already added
        if (SearchDocButton.getActionListeners().length == 0) {
            SearchDocButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    SearchDocButtonActionPerformed(evt);
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
        String[] columnNames = {"Patient ID", "Patient Name", "Category", "Age", "Contact", "Doctor ID", "Update", "Delete"};
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
                data[i][6] = "Update";
                data[i][7] = "Delete";
            }
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTable1.setModel(model);
        
        addButtonsToTable();
    }
    
    private void displayPatientData() {
        currentView = "patients";
        String[] columnNames = {"Patient ID", "Patient Name", "Age", "Address", "Email", "Contact", "Update", "Delete"};
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
                data[i][6] = "Update";
                data[i][7] = "Delete";
            }
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTable1.setModel(model);
        
        addButtonsToTable();
    }
    
    private void displayDoctorData() {
        currentView = "doctors";
        String[] columnNames = {"Doctor ID", "Doctor Name", "Specialization", "Contact", "Email", "Experience", "Update", "Delete"};
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
                data[i][6] = "Update";
                data[i][7] = "Delete";
            }
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTable1.setModel(model);
        
        addButtonsToTable();
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
        
        // Display the search results
        String[] columnNames = {"Doctor ID", "Doctor Name", "Specialization", "Contact", "Email", "Experience", "Update", "Delete"};
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
                data[i][6] = "Update";
                data[i][7] = "Delete";
            }
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTable1.setModel(model);
        
        addButtonsToTable();
    }
    
    private void addButtonsToTable() {
        // Update column (Column 6)
        TableColumn updateColumn = jTable1.getColumnModel().getColumn(6);
        updateColumn.setCellRenderer(new ButtonColumnRenderer("Update"));
        
        // Delete column (Column 7)
        TableColumn deleteColumn = jTable1.getColumnModel().getColumn(7);
        deleteColumn.setCellRenderer(new ButtonColumnRenderer("Delete"));
        
        // Remove any existing listeners to avoid duplicates
        for (java.awt.event.MouseListener listener : jTable1.getMouseListeners()) {
            if (listener instanceof java.awt.event.MouseAdapter) {
                jTable1.removeMouseListener(listener);
            }
        }
        
        // Add mouse listener to handle button clicks
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.rowAtPoint(evt.getPoint());
                int col = jTable1.columnAtPoint(evt.getPoint());
                
                if (row >= 0 && col >= 0) {
                    // Check if clicked on Update or Delete button
                    if (col == 6) { // Update column
                        handleUpdate(row);
                    } else if (col == 7) { // Delete column
                        handleDelete(row);
                    }
                }
            }
        });
    }
    
    // Simple button renderer (contained within this class)
    private class ButtonColumnRenderer implements TableCellRenderer {
        private JButton button;
        
        public ButtonColumnRenderer(String buttonText) {
            button = new JButton(buttonText);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return button;
        }
    }
    
    private void handleUpdate(int row) {
      if (row < 0) {
        return;
    }
    
    String id = jTable1.getValueAt(row, 0).toString();
    
    if (currentView.equals("appointments")) {
        if (row >= appointmentData.size()) {
            return;
        }
        // Close current frame and open UpdatePatient form
        this.dispose();
        new UpdatePatient().setVisible(true);
    } else if (currentView.equals("patients")) {
        if (row >= appointmentData.size()) {
            return;
        }
        // Close current frame and open UpdatePatient form
        this.dispose();
        new UpdatePatient().setVisible(true);
    } else if (currentView.equals("doctors")) {
        // When in search view, we're not directly accessing doctorData
        // so we need to find the doctor by ID instead
        String doctorId = jTable1.getValueAt(row, 0).toString();
        
        // Close current frame and open UpdateDoctor form
        this.dispose();
        new UpdateDoctor().setVisible(true);
    }
    }
    
    private void handleDelete(int row) {
        if (row < 0) {
            return;
        }
        
        if (currentView.equals("appointments") || currentView.equals("patients")) {
            if (row >= appointmentData.size()) {
                return;
            }
            
            String patientId = jTable1.getValueAt(row, 0).toString();
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete " + (currentView.equals("appointments") ? "appointment" : "patient") + 
                    " with ID " + patientId + "?", 
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                // Find and remove from the data list
                int dataIndex = -1;
                for (int i = 0; i < appointmentData.size(); i++) {
                    if (appointmentData.get(i)[0].equals(patientId)) {
                        dataIndex = i;
                        break;
                    }
                }
                
                if (dataIndex >= 0) {
                    appointmentData.remove(dataIndex);
                    
                    // Remove from the table
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.removeRow(row);
                    
                    // Save the updated data back to the file
                    saveAppointmentData();
                    
                    JOptionPane.showMessageDialog(this, 
                            (currentView.equals("appointments") ? "Appointment" : "Patient") + " deleted successfully.");
                }
            }
        } else if (currentView.equals("doctors")) {
            String doctorId = jTable1.getValueAt(row, 0).toString();
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete doctor with ID " + doctorId + "?", 
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                // Find and remove from the data list
                int dataIndex = -1;
                for (int i = 0; i < doctorData.size(); i++) {
                    if (doctorData.get(i)[0].equals(doctorId)) {
                        dataIndex = i;
                        break;
                    }
                }
                
                if (dataIndex >= 0) {
                    doctorData.remove(dataIndex);
                    
                    // Remove from the table
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.removeRow(row);
                    
                    // Save the updated data back to the file
                    saveDoctorData();
                    
                    JOptionPane.showMessageDialog(this, "Doctor deleted successfully.");
                }
            }
        }
    }
    
    private void saveAppointmentData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("appointments.txt"))) {
            // Write header
            bw.write("PatientID,PatientName,Category,Age,Address,Email,Contact,DoctorSpecialization,AppointmentDate,DoctorID,DoctorName");
            bw.newLine();
            
            // Write data
            for (String[] row : appointmentData) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving appointment data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveDoctorData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("doctors.txt"))) {
            // Write header
            bw.write("DoctorID,DoctorName,DoctorSpecialization,DoctorTelNo,DocEmail,Experience");
            bw.newLine();
            
            // Write data
            for (String[] row : doctorData) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving doctor data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void AppointmentsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        loadAppointmentData();
        displayAppointmentData();
    }
    
    private void PatientsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        loadAppointmentData();
        displayPatientData();
    }
    
    private void ConsultantsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        loadDoctorData();
        displayDoctorData();
    }


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        HomeButton = new javax.swing.JButton();
        AppointmentsButton = new javax.swing.JButton();
        PatientsButton = new javax.swing.JButton();
        ConsultantsButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        AddAppointmentButton = new javax.swing.JButton();
        AddDoctorButton = new javax.swing.JButton();
        SearchTextField = new javax.swing.JTextField();
        SearchDocButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/manager icon.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        HomeButton.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        HomeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home-icon.png"))); // NOI18N
        HomeButton.setText(" HOME");
        HomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeButtonActionPerformed(evt);
            }
        });
        jPanel2.add(HomeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 310, 53));

        AppointmentsButton.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        AppointmentsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appointment icon.png"))); // NOI18N
        AppointmentsButton.setText("APPOINTMENTS");
        jPanel2.add(AppointmentsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 310, 52));

        PatientsButton.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        PatientsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paient details icon.png"))); // NOI18N
        PatientsButton.setText(" PATIENTS DETAILS");
        jPanel2.add(PatientsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 310, 53));

        ConsultantsButton.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        ConsultantsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DOC DETAILS Icon.png"))); // NOI18N
        ConsultantsButton.setText(" CONSULTANTS");
        jPanel2.add(ConsultantsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 310, 50));

        jButton5.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new-user icon.png"))); // NOI18N
        jButton5.setText(" NEW ACCOUNT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 310, 50));

        jButton6.setFont(new java.awt.Font("Yu Gothic", 1, 20)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sign-out-icon.png"))); // NOI18N
        jButton6.setText(" LOGOUT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 310, 51));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 600));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 48)); // NOI18N
        jLabel1.setText("MANAGER DASHBOARD");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(490, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 1050, 80));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 1050, 400));

        AddAppointmentButton.setFont(new java.awt.Font("Yu Gothic Medium", 1, 15)); // NOI18N
        AddAppointmentButton.setText("ADD APPOINTMENT");
        AddAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAppointmentButtonActionPerformed(evt);
            }
        });
        jPanel1.add(AddAppointmentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 120, -1, -1));

        AddDoctorButton.setFont(new java.awt.Font("Yu Gothic Medium", 1, 15)); // NOI18N
        AddDoctorButton.setText("ADD DOCTOR");
        AddDoctorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDoctorButtonActionPerformed(evt);
            }
        });
        jPanel1.add(AddDoctorButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 120, -1, -1));

        SearchTextField.setFont(new java.awt.Font("Yu Gothic Medium", 0, 15)); // NOI18N
        jPanel1.add(SearchTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 290, -1));

        SearchDocButton.setFont(new java.awt.Font("Yu Gothic Medium", 1, 15)); // NOI18N
        SearchDocButton.setText("SEARCH DOCTOR");
        SearchDocButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchDocButtonActionPerformed(evt);
            }
        });
        jPanel1.add(SearchDocButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, -1, -1));

        jButton1.setFont(new java.awt.Font("Yu Gothic Medium", 1, 15)); // NOI18N
        jButton1.setText("ADD PATIENT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 120, -1, -1));

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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        // Close the current frame
    this.dispose();
    
    // Open the frame
    new MultiLogin().setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void HomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeButtonActionPerformed
        // TODO add your handling code here:
        // Close the current frame
    this.dispose();
    
    // Open the frame
    new Login().setVisible(true);
    }//GEN-LAST:event_HomeButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    
    // Open theframe
    new SignInFrame().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void AddAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAppointmentButtonActionPerformed
        // TODO add your handling code here:
         this.dispose();
    
        // Open the Login frame
         new AddAppointments("ManagerDashboard").setVisible(true);
    }//GEN-LAST:event_AddAppointmentButtonActionPerformed

    private void AddDoctorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddDoctorButtonActionPerformed
        // TODO add your handling code here:
        new AddDoctor(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_AddDoctorButtonActionPerformed

    private void SearchDocButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchDocButtonActionPerformed
        // TODO add your handling code here:
        loadDoctorData();
        
        // Get search term and perform search
        String searchTerm = SearchTextField.getText();
        searchDoctors(searchTerm);
    }//GEN-LAST:event_SearchDocButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new AddAppointments("CashierDashboard").setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAppointmentButton;
    private javax.swing.JButton AddDoctorButton;
    private javax.swing.JButton AppointmentsButton;
    private javax.swing.JButton ConsultantsButton;
    private javax.swing.JButton HomeButton;
    private javax.swing.JButton PatientsButton;
    private javax.swing.JButton SearchDocButton;
    private javax.swing.JTextField SearchTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
