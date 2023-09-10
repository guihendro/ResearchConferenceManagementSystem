/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author guihendro
 */
public class User {
    // Initialize attributes
    private String username;
    private static String currUsername;
    private static int currUserID;
    
    private String password;
    private String profile;
    Connection connection = DatabaseConnection.getConnection();
    
    // Constructors
    public User(){
        
    }
    
    public User(String username, String password, String profile){
        this.username = username;
        this.password = password;
        this.profile = profile;
    }
    
    // Function to validate login - Check if username and password exists in database
    public boolean validateLogin(String username, String password){
        
        boolean validLogin = false;
        
        try{ 

            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement("Select userID, username, password from users where username=? and password=?");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                setCurrUserID(rs.getInt("userID"));
                validLogin = true;
                setCurrUsername(username);
            }
            
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return validLogin;
    }
    
    // Function to check profile - obtain the user profile of a specific user
    public String checkProfile(String username){
        String profile = "";
        try{ 

            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement("Select profile from users where username=?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                profile = rs.getString(1);
            }
            
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return profile;
    }
    
    // Getter and setters
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getProfile(){
        return profile;
    }
    
    public static String getCurrUsername(){
        return currUsername;
    }
    
    public static int getCurrUserID(){
        return currUserID;
    }
    
    public static void setCurrUsername(String username){
        User.currUsername = username;
    }
    
    public static void setCurrUserID(int userID){
       User.currUserID = userID;
    }

    public void Logout(JFrame frame, JButton btnLogout){
        int quit = JOptionPane.showConfirmDialog(btnLogout, "Are you sure you want to log out?");
        if (quit == JOptionPane.YES_OPTION) {
            frame.dispose();
            LoginGUI obj = new LoginGUI();
        }
    }

}
