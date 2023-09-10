/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package helloworld;

/**
 *
 * @author guihendro
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class SystemAdminGUI extends javax.swing.JFrame {
    
    private JFrame frame;
    private String[] columnNames = {"Username", "Password", "Profile"};
    private String username = User.getCurrUsername();
    
    /**
     * Creates new form SystemAdminGUI
     */
    public SystemAdminGUI() {
        displaySystemAdminGUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private void displaySystemAdminGUI(){
        // Set up frame
        frame = new JFrame("System Admin");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 600, 500);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        frame.setVisible(true);
        
        /* 
            Home page panel below
        */
        
        JPanel homePage = new JPanel();
        frame.getContentPane().add(homePage);
        homePage.setLayout(null);
        homePage.setVisible(true);
        
        JLabel lblTitle = new JLabel("Welcome " + username);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblTitle.setBounds(50, 50, 300, 31);
        homePage.add(lblTitle);
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(375, 50, 150, 31);
        homePage.add(btnLogout);
        
        JButton btnCreateUserAccount = new JButton("Create account");
        btnCreateUserAccount.setBounds(125, 150, 150, 40);
        homePage.add(btnCreateUserAccount);
        
        JButton btnCreateUserProfile = new JButton("Create user profile");
        btnCreateUserProfile.setBounds(125, 200, 150, 40);
        homePage.add(btnCreateUserProfile);
        
        JButton btnViewUserAccounts = new JButton("View user accounts");
        btnViewUserAccounts.setBounds(125, 250, 150, 40);
        homePage.add(btnViewUserAccounts);
        
        JButton btnViewUserProfiles = new JButton("View user profiles");
        btnViewUserProfiles.setBounds(125, 300, 150, 40);
        homePage.add(btnViewUserProfiles);
        
        JButton btnSearchUserProfile = new JButton("Search user profile");
        btnSearchUserProfile.setBounds(325, 150, 150, 40);
        homePage.add(btnSearchUserProfile);
        
        JButton btnSearchUserAccount = new JButton("Search user account");
        btnSearchUserAccount.setBounds(325, 200, 150, 40);
        homePage.add(btnSearchUserAccount);
        
        JButton btnChangeUserProfile = new JButton("Change user profile");
        btnChangeUserProfile.setBounds(325, 250, 150, 40);
        homePage.add(btnChangeUserProfile);
        
        btnLogout.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                LogoutController controller = new LogoutController();
                controller.Logout(frame,btnLogout);
            }
        });
        
        
        /*
            Create account panel below
        */
        
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(null);
        frame.getContentPane().add(createAccountPanel);
        
        JLabel lblCreateUserTitle = new JLabel("Create a new user account");
        lblCreateUserTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblCreateUserTitle.setBounds(165, 50, 300, 31);
        createAccountPanel.add(lblCreateUserTitle);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsername.setBounds(155, 150, 150, 31);
        createAccountPanel.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(155, 200, 150, 31);
        createAccountPanel.add(lblPassword);

        JLabel lblProfile = new JLabel("Profile");
        lblProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProfile.setBounds(155, 250, 150, 31);
        createAccountPanel.add(lblProfile);
        
        JTextField txtNewUsername = new JTextField();
        txtNewUsername.setBounds(235, 155, 150, 20);
        createAccountPanel.add(txtNewUsername);
        txtNewUsername.setColumns(15);

        JTextField txtNewPassword = new JTextField();
        txtNewPassword.setBounds(235, 205, 150, 22);
        createAccountPanel.add(txtNewPassword);
        txtNewUsername.setColumns(15);
        
        JButton btnCreateAccountBack = new JButton("Back");
        btnCreateAccountBack.setBounds(50, 20, 70, 35);
        createAccountPanel.add(btnCreateAccountBack);
        
        JComboBox<Object> createAccountProfileBox = new JComboBox<Object>();
        createAccountProfileBox.setToolTipText("");
        createAccountProfileBox.setBounds(235, 255, 113, 22);
        createAccountPanel.add(createAccountProfileBox);
        
        btnCreateUserAccount.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                homePage.setVisible(false);
                createAccountProfileBox.removeAllItems();
                
                SystemAdminCreateAccountController controller = new SystemAdminCreateAccountController();
                ArrayList<String> profileList = controller.getProfiles();
                for(String p : profileList){
                    createAccountProfileBox.addItem(p);
                } 
                
                createAccountPanel.setVisible(true);
                txtNewUsername.setText("");
                txtNewPassword.setText("");
                createAccountProfileBox.setSelectedItem(0);
            }
        });
        
	btnCreateAccountBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAccountPanel.setVisible(false);
                homePage.setVisible(true);
            }
        });
        
        JButton btnConfirmCreateUserAccount = new JButton("Confirm");
        btnConfirmCreateUserAccount.setBounds(235, 300, 146, 46);
        btnConfirmCreateUserAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemAdminCreateAccountController controller = new SystemAdminCreateAccountController();
                String username = txtNewUsername.getText();
                String password = txtNewPassword.getText();
                String profile = createAccountProfileBox.getSelectedItem().toString();
                
                if(controller.usernameExist(username)){
                    JOptionPane.showMessageDialog(btnConfirmCreateUserAccount, "Username already existed!", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    controller.createAccount(username, password, profile);
                    JOptionPane.showMessageDialog(btnConfirmCreateUserAccount, "Account successfully created!");
                    createAccountPanel.setVisible(false);
                    homePage.setVisible(true);
                }
            }
        });
        createAccountPanel.add(btnConfirmCreateUserAccount);
        
        /*
            Create profile panel below
        */
        
        JPanel createProfilePanel = new JPanel();
        createProfilePanel.setLayout(null);
        frame.getContentPane().add(createProfilePanel);
        
        JLabel lblCreateProfileTitle = new JLabel("Create a new user profile");
        lblCreateProfileTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblCreateProfileTitle.setBounds(165, 50, 300, 31);
        createProfilePanel.add(lblCreateProfileTitle);
        
        JLabel lblNewProfile = new JLabel("New profile");
        lblNewProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewProfile.setBounds(155, 150, 150, 31);
        createProfilePanel.add(lblNewProfile);
        
        JTextField txtNewProfile = new JTextField();
        txtNewProfile.setBounds(235, 155, 150, 20);
        createProfilePanel.add(txtNewProfile);
        txtNewProfile.setColumns(15);
        
        JButton btnCreateProfileBack = new JButton("Back");
        btnCreateProfileBack.setBounds(50, 20, 70, 35);
        createProfilePanel.add(btnCreateProfileBack);
        
        JButton btnConfirmCreateUserProfile = new JButton("Confirm");
        btnConfirmCreateUserProfile.setBounds(235, 200, 146, 46);
        createProfilePanel.add(btnConfirmCreateUserProfile);
        
        btnCreateUserProfile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                homePage.setVisible(false);
                txtNewProfile.setText("");
                createProfilePanel.setVisible(true);
            }
        });
        
        btnCreateProfileBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createProfilePanel.setVisible(false);
                homePage.setVisible(true);
            }
        });
     
        btnConfirmCreateUserProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemAdminCreateProfileController controller = new SystemAdminCreateProfileController();
                String newProfile = txtNewProfile.getText();
                
                if(controller.profileExist(newProfile)){
                    JOptionPane.showMessageDialog(btnConfirmCreateUserProfile, "Profile already existed!", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    controller.createProfile(newProfile);
                    JOptionPane.showMessageDialog(btnConfirmCreateUserProfile, "Profile successfully created!");
                    createProfilePanel.setVisible(false);
                    homePage.setVisible(true);
                }
            }
        });
        
        /*
            Search user profile panel below
        */
        
        JPanel searchProfilePanel = new JPanel();
        searchProfilePanel.setLayout(null);
        frame.getContentPane().add(searchProfilePanel);
        
        JLabel lblSearchProfileTitle = new JLabel("Search an user profile");
        lblSearchProfileTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblSearchProfileTitle.setBounds(165, 50, 300, 31);
        searchProfilePanel.add(lblSearchProfileTitle);
        
        JLabel lblSelectProfile = new JLabel("Select profile");
        lblSelectProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSelectProfile.setBounds(50, 90, 150, 31);
        searchProfilePanel.add(lblSelectProfile);
        
        JButton btnConfirmSearchProfile = new JButton("Search");
        btnConfirmSearchProfile.setBounds(250, 93, 146, 25);
        searchProfilePanel.add(btnConfirmSearchProfile);
        
        JComboBox<Object> searchProfileProfileBox = new JComboBox<Object>();
        searchProfileProfileBox.setToolTipText("");
        searchProfileProfileBox.setBounds(140, 95, 113, 22);
        searchProfilePanel.add(searchProfileProfileBox);
        
        JButton btnSearchProfileBack = new JButton("Back");
        btnSearchProfileBack.setBounds(50, 20, 70, 35);
        searchProfilePanel.add(btnSearchProfileBack);
        
        DefaultTableModel searchUserProfileModel = new DefaultTableModel();
        searchUserProfileModel.setColumnIdentifiers(columnNames);
        
        JTable searchProfileTable = new JTable();
        searchProfileTable.setModel(searchUserProfileModel);
        searchProfileTable.setDefaultEditor(Object.class, null);
        searchProfileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        searchProfileTable.setFillsViewportHeight(true);
        
        JScrollPane searchProfileScroll = new JScrollPane(searchProfileTable);
        searchProfileScroll.setBounds(50, 150, 500, 250);
        searchProfileScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        searchProfileScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        searchProfilePanel.add(searchProfileScroll);
        
        btnSearchUserProfile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                homePage.setVisible(false);
                searchUserProfileModel.setRowCount(0);
                searchProfileProfileBox.removeAllItems();
                
                SystemAdminSearchProfileController controller = new SystemAdminSearchProfileController();
                ArrayList<String> profileList = controller.getProfiles();
                for(String p : profileList){
                    searchProfileProfileBox.addItem(p);
                } 
                searchProfileProfileBox.setSelectedIndex(0);
                
                ArrayList<User> usersList = controller.getAllUsers();
                for(User u : usersList){
                    searchUserProfileModel.addRow(new Object[]{u.getUsername(),u.getPassword(), u.getProfile()});
                }
                searchProfilePanel.setVisible(true);
                
            }
        });
        
        btnSearchProfileBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchProfilePanel.setVisible(false);
                homePage.setVisible(true);
            }
        });
        
        btnConfirmSearchProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchUserProfileModel.setRowCount(0);
                
                SystemAdminSearchProfileController controller = new SystemAdminSearchProfileController();
                String profile = searchProfileProfileBox.getSelectedItem().toString();
                
                ArrayList<User> usersList = controller.getUsersByProfile(profile);
                for(User u : usersList){
                    searchUserProfileModel.addRow(new Object[]{u.getUsername(),u.getPassword(), u.getProfile()});
                }
            }
        });
        
        /*
            Search user account panel below
        */
        
        JPanel searchUserAccountPanel = new JPanel();
        searchUserAccountPanel.setLayout(null);
        frame.getContentPane().add(searchUserAccountPanel);
        
        JLabel lblSearchUserAccountTitle = new JLabel("Search an user account");
        lblSearchUserAccountTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblSearchUserAccountTitle.setBounds(165, 50, 300, 31);
        searchUserAccountPanel.add(lblSearchUserAccountTitle);
        
        JLabel lblSearchUsername = new JLabel("Enter username");
        lblSearchUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSearchUsername.setBounds(155, 120, 150, 31);
        searchUserAccountPanel.add(lblSearchUsername);
        
        JTextField txtSearchUsername = new JTextField();
        txtSearchUsername.setBounds(280, 125, 150, 20);
        searchUserAccountPanel.add(txtSearchUsername);
        txtSearchUsername.setColumns(15);
        
        JLabel lblUsernameResult = new JLabel("Username");
        lblUsernameResult.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsernameResult.setBounds(165, 200, 150, 31);
        searchUserAccountPanel.add(lblUsernameResult);
        
        JTextField txtUsernameResult = new JTextField();
        txtUsernameResult.setBounds(245, 205, 150, 20);
        searchUserAccountPanel.add(txtUsernameResult);
        txtUsernameResult.setColumns(15);
        txtUsernameResult.setEditable(false);
        
        JLabel lblPasswordResult = new JLabel("Password");
        lblPasswordResult.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPasswordResult.setBounds(165, 250, 150, 31);
        searchUserAccountPanel.add(lblPasswordResult);
        
        JTextField txtPasswordResult = new JTextField();
        txtPasswordResult.setBounds(245, 255, 150, 20);
        searchUserAccountPanel.add(txtPasswordResult);
        txtPasswordResult.setColumns(15);
        txtPasswordResult.setEditable(false);
        
        JLabel lblProfileResult = new JLabel("Profile");
        lblProfileResult.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProfileResult.setBounds(165, 300, 150, 31);
        searchUserAccountPanel.add(lblProfileResult);
        
        JTextField txtProfileResult = new JTextField();
        txtProfileResult.setBounds(245, 305, 150, 20);
        searchUserAccountPanel.add(txtProfileResult);
        txtProfileResult.setColumns(15);
        txtProfileResult.setEditable(false);
        
        JButton btnExecuteSearchUserAccount = new JButton("Search");
        btnExecuteSearchUserAccount.setBounds(360, 153, 70, 25);
        searchUserAccountPanel.add(btnExecuteSearchUserAccount);
        
        JButton btnSearchUserAccountBack = new JButton("Back");
        btnSearchUserAccountBack.setBounds(50, 20, 70, 35);
        searchUserAccountPanel.add(btnSearchUserAccountBack);
        
        btnSearchUserAccount.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                homePage.setVisible(false);
                txtSearchUsername.setText("");
                txtUsernameResult.setText("");
                txtPasswordResult.setText("");
                txtProfileResult.setText("");
                searchUserAccountPanel.setVisible(true);
            }
        });
        
        btnSearchUserAccountBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchUserAccountPanel.setVisible(false);
                homePage.setVisible(true);
            }
        });
        
        btnExecuteSearchUserAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemAdminSearchAccountController controller = new SystemAdminSearchAccountController();
                User u = controller.searchUserByUsername(txtSearchUsername.getText());
                
                txtUsernameResult.setText(u.getUsername());
                txtPasswordResult.setText(u.getPassword());
                txtProfileResult.setText(u.getProfile());
            }
        });
        
        /*
            View user accounts panel below
        */
        
        JPanel viewUserAccountsPanel = new JPanel();
        viewUserAccountsPanel.setLayout(null);
        frame.getContentPane().add(viewUserAccountsPanel);
        
        JLabel lblViewUserAccountsTitle = new JLabel("View user accounts");
        lblViewUserAccountsTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblViewUserAccountsTitle.setBounds(195, 50, 300, 31);
        viewUserAccountsPanel.add(lblViewUserAccountsTitle);
        
        JButton btnViewUserAccountsBack = new JButton("Back");
        btnViewUserAccountsBack.setBounds(50, 20, 70, 35);
        viewUserAccountsPanel.add(btnViewUserAccountsBack);
        
        DefaultTableModel viewUserAccountsModel = new DefaultTableModel();
        viewUserAccountsModel.setColumnIdentifiers(columnNames);
        
        JTable viewUserAccountsTable = new JTable();
        viewUserAccountsTable.setModel(viewUserAccountsModel);
        viewUserAccountsTable.setDefaultEditor(Object.class, null);
        viewUserAccountsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        viewUserAccountsTable.setFillsViewportHeight(true);
        
        JScrollPane viewUserAccountsScroll = new JScrollPane(viewUserAccountsTable);
        viewUserAccountsScroll.setBounds(50, 150, 500, 250);
        viewUserAccountsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        viewUserAccountsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        viewUserAccountsPanel.add(viewUserAccountsScroll);
        
        btnViewUserAccounts.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                homePage.setVisible(false);
                viewUserAccountsModel.setRowCount(0);
                
                SystemAdminViewAccountsController controller = new SystemAdminViewAccountsController();
                ArrayList<User> usersList = controller.getAllUsers();
                for(User u : usersList){
                    viewUserAccountsModel.addRow(new Object[]{u.getUsername(),u.getPassword(), u.getProfile()});
                }
                viewUserAccountsPanel.setVisible(true);
                
            }
        });
        
        btnViewUserAccountsBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewUserAccountsPanel.setVisible(false);
                homePage.setVisible(true);
            }
        });
        
        /*
            View user profiles panel below
        */
        
        JPanel viewUserProfilesPanel = new JPanel();
        viewUserProfilesPanel.setLayout(null);
        frame.getContentPane().add(viewUserProfilesPanel);
        
        JLabel lblViewUserProfilesTitle = new JLabel("View user profiles");
        lblViewUserProfilesTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblViewUserProfilesTitle.setBounds(205, 50, 300, 31);
        viewUserProfilesPanel.add(lblViewUserProfilesTitle);
        
        JButton btnViewUserProfilesBack = new JButton("Back");
        btnViewUserProfilesBack.setBounds(50, 20, 70, 35);
        viewUserProfilesPanel.add(btnViewUserProfilesBack);
        
        DefaultTableModel viewUserProfilesModel = new DefaultTableModel();
        viewUserProfilesModel.setColumnIdentifiers(new String[] {"Profile"});
        
        JTable viewUserProfilesTable = new JTable();
        viewUserProfilesTable.setModel(viewUserProfilesModel);
        viewUserProfilesTable.setDefaultEditor(Object.class, null);
        viewUserProfilesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        viewUserProfilesTable.setFillsViewportHeight(true);
        
        JScrollPane viewUserProfilesScroll = new JScrollPane(viewUserProfilesTable);
        viewUserProfilesScroll.setBounds(50, 150, 500, 250);
        viewUserProfilesScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        viewUserProfilesScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        viewUserProfilesPanel.add(viewUserProfilesScroll);
        
        btnViewUserProfiles.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                homePage.setVisible(false);
                viewUserProfilesModel.setRowCount(0);
                
                SystemAdminViewProfilesController controller = new SystemAdminViewProfilesController();
                ArrayList<String> profilesList = controller.getProfiles();
                for(String p : profilesList){
                    viewUserProfilesModel.addRow(new String[]{p});
                }
                viewUserProfilesPanel.setVisible(true);
            }
        });
        
        btnViewUserProfilesBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewUserProfilesPanel.setVisible(false);
                homePage.setVisible(true);
            }
        });
        
        /*
            Change user profile panel below
        */
        
        JPanel changeUserProfilePanel = new JPanel();
        changeUserProfilePanel.setLayout(null);
        frame.getContentPane().add(changeUserProfilePanel);
        
        JLabel lblChangeUserProfileTitle = new JLabel("Change user profile");
        lblChangeUserProfileTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblChangeUserProfileTitle.setBounds(165, 50, 300, 31);
        changeUserProfilePanel.add(lblChangeUserProfileTitle);

        JLabel lblChangeUsername = new JLabel("Username");
        lblChangeUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblChangeUsername.setBounds(155, 150, 150, 31);
        changeUserProfilePanel.add(lblChangeUsername);
        
        JTextField txtChangeUsername = new JTextField();
        txtChangeUsername.setBounds(235, 155, 150, 20);
        changeUserProfilePanel.add(txtChangeUsername);
        txtChangeUsername.setColumns(15);
        
        JButton btnChangeUserProfileBack = new JButton("Back");
        btnChangeUserProfileBack.setBounds(50, 20, 70, 35);
        changeUserProfilePanel.add(btnChangeUserProfileBack);
        
        JLabel lblChangeProfile = new JLabel("New profile");
        lblChangeProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblChangeProfile.setBounds(155, 200, 150, 31);
        changeUserProfilePanel.add(lblChangeProfile);
        
        JComboBox<Object> changeUserProfileProfileBox = new JComboBox<Object>();
        changeUserProfileProfileBox.setToolTipText("");
        changeUserProfileProfileBox.setBounds(235, 205, 113, 22);
        changeUserProfilePanel.add(changeUserProfileProfileBox);
        
        btnChangeUserProfile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                homePage.setVisible(false);
                changeUserProfileProfileBox.removeAllItems();
                
                SystemAdminChangeProfileController controller = new SystemAdminChangeProfileController();
                ArrayList<String> profileList = controller.getProfiles();
                for(String p : profileList){
                    changeUserProfileProfileBox.addItem(p);
                }
                
                txtChangeUsername.setText("");
                changeUserProfileProfileBox.setSelectedItem(0);
                changeUserProfilePanel.setVisible(true);
            }
        });
        
	btnChangeUserProfileBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeUserProfilePanel.setVisible(false);
                homePage.setVisible(true);
            }
        });
        
        JButton btnConfirmChangeUserProfile = new JButton("Confirm");
        btnConfirmChangeUserProfile.setBounds(235, 250, 146, 46);
        btnConfirmChangeUserProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemAdminChangeProfileController controller = new SystemAdminChangeProfileController();
                String username = txtChangeUsername.getText();
                String profile = changeUserProfileProfileBox.getSelectedItem().toString();
                
                if(!controller.usernameExist(username)){
                    JOptionPane.showMessageDialog(btnConfirmChangeUserProfile, "User does not exists!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if(controller.checkSameProfile(username, profile)){
                    JOptionPane.showMessageDialog(btnConfirmChangeUserProfile, "This user already has the selected profile!", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    controller.changeProfile(username, profile);
                    JOptionPane.showMessageDialog(btnConfirmChangeUserProfile, "Profile changed successfully!");
                    changeUserProfilePanel.setVisible(false);
                    homePage.setVisible(true);
                }
            }
        });
        changeUserProfilePanel.add(btnConfirmChangeUserProfile);
        
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}