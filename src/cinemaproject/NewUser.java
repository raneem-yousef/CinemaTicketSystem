/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cinemaproject;
import java.sql.*;
import javax.swing.JOptionPane;

public class NewUser extends javax.swing.JFrame {
        Connection conn = null;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewUser.class.getName());

    /**
     * Creates new form NewUser
     */
    public NewUser() {
        initComponents();
        setTitle("New User");
    setLocationRelativeTo(null);
    txtCustomerID.setEditable(false);
     btnCreate.addActionListener(this::createAccount);
    btnCancel.addActionListener(e -> dispose());

    getContentPane().setBackground(UITheme.BG_DARK);
    UITheme.styleHeading(jLabel1);
    UITheme.styleFieldLabel(jLabel2);
    UITheme.styleFieldLabel(jLabel3);
    UITheme.styleFieldLabel(jLabel4);
    UITheme.styleFieldLabel(jLabel5);
    UITheme.styleFieldLabel(jLabel6);
    UITheme.styleFieldLabel(jLabel7);
    UITheme.stylePrimaryButton(btnCreate);
    UITheme.styleLinkButton(btnCancel);
    
    try {
        conn = DriverManager.getConnection(
            "jdbc:derby://localhost:1527/RegDB",
            "db",
            "123"
        );
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
            null,
            ex.getMessage(),
            "DB Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    loadNextCustomerID();
}
    
private void loadNextCustomerID() {
    try {
        String sql = "SELECT COALESCE(MAX(CUSTOMER_ID), 0) + 1 FROM CUSTOMERS";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            txtCustomerID.setText(String.valueOf(rs.getInt(1)));
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
            null,
            ex.getMessage(),
            "DB Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
private void createAccount(java.awt.event.ActionEvent evt) {

    String idText = txtCustomerID.getText();
    String name = txtName.getText();
    String phone = txtPhone.getText();
    String email = txtEmail.getText();
    String username = txtUsername.getText();
    String password = txtPassword.getText();

    if (idText.isEmpty() || name.isEmpty() || phone.isEmpty()
            || email.isEmpty() || username.isEmpty()
            || password.isEmpty()) {

        JOptionPane.showMessageDialog(
            null,
            "Please fill in all fields.",
            "Validation",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    int customerID;

    try {
        customerID = Integer.parseInt(idText);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(
            null,
            "Customer ID must be a number.",
            "Validation",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
        JOptionPane.showMessageDialog(
            null,
            "Please enter a valid email.",
            "Validation",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    try {

        String checkUser =
            "SELECT USERNAME FROM \"DB\".USERS WHERE USERNAME=?";

        PreparedStatement ps = conn.prepareStatement(checkUser);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(
                null,
                "This username already exists.",
                "Validation",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String checkCustomer =
            "SELECT CUSTOMER_ID FROM CUSTOMERS WHERE CUSTOMER_ID=?";

        ps = conn.prepareStatement(checkCustomer);
        ps.setInt(1, customerID);

        rs = ps.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(
                null,
                "This Customer ID already exists.",
                "Validation",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String userIDSQL =
            "SELECT COALESCE(MAX(USER_ID), 0) + 1 FROM \"DB\".USERS";

        ps = conn.prepareStatement(userIDSQL);
        rs = ps.executeQuery();

        int userID = 1;

        if (rs.next()) {
            userID = rs.getInt(1);
        }

        String insertCustomer =
            "INSERT INTO CUSTOMERS " +
            "(CUSTOMER_ID, NAME, PHONE, EMAIL) " +
            "VALUES (?,?,?,?)";

        ps = conn.prepareStatement(insertCustomer);

        ps.setInt(1, customerID);
        ps.setString(2, name);
        ps.setString(3, phone);
        ps.setString(4, email);

        ps.executeUpdate();

        String insertUser =
            "INSERT INTO \"DB\".USERS " +
            "(USER_ID, USERNAME, PASSWORD, ROLE, CUSTOMER_ID) " +
            "VALUES (?,?,?,?,?)";

        ps = conn.prepareStatement(insertUser);

        ps.setInt(1, userID);
        ps.setString(2, username);
        ps.setString(3, password);
        ps.setString(4, "CUSTOMER");
        ps.setInt(5, customerID);

        ps.executeUpdate();

        JOptionPane.showMessageDialog(
            null,
            "Account created successfully.\n"
            + "Your Customer ID is: " + customerID
            + "\nYou can now login.",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );

        dispose();

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(
            null,
            "Could not create account:\n" + ex.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        btnCancel = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        txtCustomerID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Create New Customer Account");

        jLabel2.setText("Customer ID :");

        jLabel3.setText("Name :");

        jLabel4.setText("Phone:");

        jLabel5.setText("Email :");

        jLabel6.setText("Username :");

        jLabel7.setText("Password :");

        btnCancel.setText("Cancel");

        btnCreate.setText("Create Account");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCustomerID)
                            .addComponent(txtName)
                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(txtEmail)
                            .addComponent(txtUsername)
                            .addComponent(txtPassword))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 221, Short.MAX_VALUE)
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addGap(256, 256, 256))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnCreate))
                .addContainerGap(269, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        UITheme.installLookAndFeel();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new NewUser().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtCustomerID;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
