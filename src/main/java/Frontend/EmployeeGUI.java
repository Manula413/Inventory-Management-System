/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Frontend;

import Backend.EmployeeManager;
import Backend.Employee;
import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.*;
import javax.swing.border.Border;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Manula
 */
public class EmployeeGUI extends javax.swing.JPanel {

    private EmployeeManager employeeManager = new EmployeeManager();
    private ButtonGroup statusGroup = new ButtonGroup();
    private Border defaultBorder;
    private Border redBorder = BorderFactory.createLineBorder(Color.RED, 1);

    public EmployeeGUI() {
        initComponents();
        initializeButtonGroup();
        displayEmployees();
        defaultBorder = tfFirstName.getBorder();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dpDateOfBirth.setText(today.format(formatter));
        dpDateOfBirth.setForeground(Color.BLACK); // Set text color to black

        dpStartDate.setText(today.format(formatter));
        dpStartDate.setForeground(Color.BLACK); // Set text color to black

    }

    private void initializeButtonGroup() {
        statusGroup.add(rbStatusActive);
        statusGroup.add(rbStatusInactive);
    }

    private void clearForm() {
        tfFirstName.setText("");
        tfFirstName.setBorder(defaultBorder);

        tfLastName.setText("");
        tfLastName.setBorder(defaultBorder);

        tfEmail.setText("");
        tfEmail.setBorder(defaultBorder);

        cbRole.setSelectedIndex(0); // Reset role dropdown to default

        pfPassword.setText("");
        pfPassword.setBorder(defaultBorder);

        pfConfirmPassword.setText("");
        pfConfirmPassword.setBorder(defaultBorder);

        tfAddress.setText("");
        tfAddress.setBorder(defaultBorder);

        rbStatusActive.setSelected(false);

        rbStatusInactive.setSelected(false);
    }

    private void displayEmployees() {
        try {
            List<Employee> employees = employeeManager.getAllEmployees();
            DefaultTableModel model = createTableModel(employees);
            tblViewEmployees.setModel(model);
            SwingUtilities.invokeLater(() -> {
                fillEmptyRows(model);
                setCustomRenderer();
                setUpSorting(model);
            });
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error parsing employee data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private DefaultTableModel createTableModel(List<Employee> employees) {
        String[] columnNames = {"Name", "Email", "Role", "Start Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Employee employee : employees) {
            String name = employee.getFirstName() + " " + employee.getLastName();
            String email = employee.getEmail();
            String role = employee.getRole();
            String startDate = employee.getStartDate().toString();
            String status = employee.getStatus();
            Object[] row = {name, email, role, startDate, status};
            model.addRow(row);
        }

        return model;
    }

    private void fillEmptyRows(DefaultTableModel model) {
        JScrollPane scrollPane = (JScrollPane) tblViewEmployees.getParent().getParent();
        int tableHeight = scrollPane.getViewport().getHeight();
        int rowHeight = tblViewEmployees.getRowHeight();
        int visibleRowCount = tableHeight / rowHeight;
        int currentRowCount = model.getRowCount();
        int rowsToAdd = visibleRowCount - currentRowCount;

        if (rowsToAdd > 0) {
            for (int i = 0; i < rowsToAdd; i++) {
                model.addRow(new Object[model.getColumnCount()]);
            }
        }
    }

    private void setCustomRenderer() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBackground(Color.WHITE);
                return this;
            }
        };

        for (int i = 0; i < tblViewEmployees.getColumnCount(); i++) {
            tblViewEmployees.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void setUpSorting(DefaultTableModel model) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);

        sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                for (int i = 0; i < entry.getValueCount(); i++) {
                    if (entry.getStringValue(i) != null && !entry.getStringValue(i).trim().isEmpty()) {
                        return true;
                    }
                }
                return false;
            }
        });

        Comparator<String> roleComparator = (s1, s2) -> {
            if (s1 == null || s1.trim().isEmpty()) {
                return 1;
            }
            if (s2 == null || s2.trim().isEmpty()) {
                return -1;
            }

            List<String> rolePriority = Arrays.asList("Admin", "Manager", "Cashier");
            int index1 = rolePriority.indexOf(s1);
            int index2 = rolePriority.indexOf(s2);

            return Integer.compare(index1, index2);
        };

        sorter.setComparator(2, roleComparator);
        tblViewEmployees.setRowSorter(sorter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblViewEmployees = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblFirstName = new javax.swing.JLabel();
        lblLastName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblConfirmPassword = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblDateOfBirth = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        lblStart = new javax.swing.JLabel();
        tfFirstName = new javax.swing.JTextField();
        tfLastName = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        dpDateOfBirth = new com.github.lgooddatepicker.components.DatePicker();
        dpStartDate = new com.github.lgooddatepicker.components.DatePicker();
        rbStatusActive = new javax.swing.JRadioButton();
        rbStatusInactive = new javax.swing.JRadioButton();
        tfAddress = new javax.swing.JTextField();
        btncsave = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        pfPassword = new javax.swing.JPasswordField();
        pfConfirmPassword = new javax.swing.JPasswordField();
        cbRole = new javax.swing.JComboBox<>();

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblViewEmployees.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblViewEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Email", "Role", "Start Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblViewEmployees.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblViewEmployees.setGridColor(new java.awt.Color(255, 255, 255));
        tblViewEmployees.setRowHeight(25);
        tblViewEmployees.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(tblViewEmployees);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("View Employees", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblFirstName.setText("First Name :");
        lblFirstName.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        lblLastName.setText("Last Name :");
        lblLastName.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        lblEmail.setText("Email :");
        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        lblRole.setText("Role :");
        lblRole.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        lblPassword.setText("Password :");
        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        lblConfirmPassword.setText("Confirm Password :");
        lblConfirmPassword.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        lblAddress.setText("Address :");
        lblAddress.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        lblDateOfBirth.setText("Date Of Birth :");
        lblDateOfBirth.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        lblStartDate.setText("Start Date :");
        lblStartDate.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        lblStart.setText("Status : ");
        lblStart.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        tfFirstName.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        tfFirstName.setText(" ");

        tfLastName.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        tfLastName.setText(" ");

        tfEmail.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        tfEmail.setText(" ");

        dpDateOfBirth.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        dpStartDate.setText("June 8, 2024");
        dpStartDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        rbStatusActive.setText("Active");
        rbStatusActive.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        rbStatusInactive.setText("Inactive");
        rbStatusInactive.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        tfAddress.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        tfAddress.setText(" ");

        btncsave.setText("Save");
        btncsave.setBackground(new java.awt.Color(51, 51, 51));
        btncsave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btncsave.setForeground(new java.awt.Color(255, 255, 255));
        btncsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncsaveActionPerformed(evt);
            }
        });

        btnclear.setText("Clear");
        btnclear.setBackground(new java.awt.Color(51, 51, 51));
        btnclear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnclear.setForeground(new java.awt.Color(255, 255, 255));
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        pfPassword.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        pfConfirmPassword.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        cbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cashier", "Manager", "Admin" }));
        cbRole.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblEmail)
                        .addGap(128, 128, 128)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName)
                            .addComponent(lblLastName))
                        .addGap(89, 89, 89)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRole)
                            .addComponent(lblPassword)
                            .addComponent(lblConfirmPassword))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pfPassword)
                            .addComponent(pfConfirmPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbRole, 0, 120, Short.MAX_VALUE))))
                .addGap(177, 177, 177)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDateOfBirth)
                    .addComponent(lblStart)
                    .addComponent(lblStartDate)
                    .addComponent(lblAddress))
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbStatusActive)
                        .addGap(45, 45, 45)
                        .addComponent(rbStatusInactive))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btncsave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfAddress)
                    .addComponent(dpDateOfBirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dpStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFirstName)
                            .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblLastName)
                            .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEmail)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDateOfBirth))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddress))
                        .addGap(19, 19, 19)
                        .addComponent(dpDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRole)
                        .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblStartDate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dpStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPassword)
                        .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStart)
                        .addComponent(rbStatusActive)
                        .addComponent(rbStatusInactive)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfirmPassword)
                    .addComponent(pfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncsave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
        );

        jTabbedPane1.addTab("Add Employee", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents
    private static final Logger LOG = Logger.getLogger(Employee.class.getName());

    private void btncsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncsaveActionPerformed

        String firstName = tfFirstName.getText().trim();
        String lastName = tfLastName.getText().trim();
        String email = tfEmail.getText().trim();
        String role = cbRole.getSelectedItem().toString();
        String password = new String(pfPassword.getPassword());
        String confirmPassword = new String(pfConfirmPassword.getPassword());
        String address = tfAddress.getText().trim();
        String dateOfBirth = dpDateOfBirth.getText();
        String startDate = dpStartDate.getText();
        boolean isActive = rbStatusActive.isSelected();

        boolean valid = true;

        if (firstName.isEmpty() || !firstName.matches("[a-zA-Z\\s]+")) {
            tfFirstName.setBorder(redBorder);
            valid = false;
        } else {
            tfFirstName.setBorder(defaultBorder);
        }

        if (lastName.isEmpty() || !lastName.matches("[a-zA-Z\\s]+")) {
            tfLastName.setBorder(redBorder);
            valid = false;
        } else {
            tfLastName.setBorder(defaultBorder);
        }

        if (email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            tfEmail.setBorder(redBorder);
            valid = false;
        } else {
            tfEmail.setBorder(defaultBorder);
        }

        if (password.isEmpty() || password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*[0-9].*") || !password.matches(".*[!@#\\$%\\^&\\*].*")) {
            pfPassword.setBorder(redBorder);
            valid = false;
        } else {
            pfPassword.setBorder(defaultBorder);
        }

        if (confirmPassword.isEmpty() || !password.equals(confirmPassword)) {
            pfConfirmPassword.setBorder(redBorder);
            valid = false;
        } else {
            pfConfirmPassword.setBorder(defaultBorder);
        }

        if (address.isEmpty()) {
            tfAddress.setBorder(redBorder);
            valid = false;
        } else {
            tfAddress.setBorder(defaultBorder);
        }

        if (valid) {
            // Create an instance of EmployeeManager
            EmployeeManager employeeManager = new EmployeeManager();

            try {
                // Save the employee using the employeeManager
                employeeManager.saveEmployee(firstName, lastName, email, role, password, address, dateOfBirth, startDate, isActive);
                // Clear the form or display success message
            } catch (ParseException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btncsaveActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnclearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btncsave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbRole;
    private com.github.lgooddatepicker.components.DatePicker dpDateOfBirth;
    private com.github.lgooddatepicker.components.DatePicker dpStartDate;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblConfirmPassword;
    private javax.swing.JLabel lblDateOfBirth;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblStart;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JPasswordField pfConfirmPassword;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JRadioButton rbStatusActive;
    private javax.swing.JRadioButton rbStatusInactive;
    private javax.swing.JTable tblViewEmployees;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    // End of variables declaration//GEN-END:variables
}
