/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author guihendro
 */
public class SystemAdmin extends User{
    // Constructors
    public SystemAdmin(){
        
    }
    
    public SystemAdmin(String username, String password){
        super(username, password, "System admin");
    }
    
    // Function to create account
    public void createAccount(String username, String password, String profile){
        try{ 
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("INSERT INTO users (username, password, profile) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, profile);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()){
                id = rs.getInt(1);
            }
            //set a default limit num to reviewer
            if(profile.equalsIgnoreCase("reviewer")){
                String sql = "INSERT INTO reviewers (reviewerID) VALUES (?)";
                PreparedStatement setDefaultLimit = connection.prepareStatement(sql);
                setDefaultLimit.setInt(1,id);
                setDefaultLimit.executeUpdate();
            }
            
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    // Function to check if username existed in database
    public boolean usernameExist(String username){
        int count = 0;
        boolean exist = false;
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select count(username) from users where username = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
                if (count == 1)
                {
                    exist = true;
                }
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return exist;
    }
    
    // Function to get a list of user profiles
    public ArrayList<String> getProfiles(){
        ArrayList<String> profileList = new ArrayList<String>();
        try{ 
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select profile from profiles;");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                profileList.add(rs.getString(1));
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return profileList;
    }
    
    // Function to create a new user profile
    public void createProfile(String profile){
        try{ 
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("INSERT INTO profiles (profile) VALUES (?)");
            st.setString(1, profile);
            st.executeUpdate();
            
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    // Function to check if user profile already existed in the database
    public boolean profileExist(String profile){
        boolean exist = false;
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select profile from profiles where profile = ?");
            st.setString(1, profile);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                exist = true;
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return exist;
    }
    
    // Function to get all user accounts
    public ArrayList<User> getAllUsers(){
        ArrayList<User> usersList = new ArrayList<User>();
        try{ 
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select * from users");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String queryUsername = rs.getString("username");
                String queryPassword = rs.getString("password");
                String queryProfile = rs.getString("profile");
                usersList.add(new User(queryUsername, queryPassword, queryProfile));
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return usersList;
    }
    
    // Function to get all user accounts with a specific user profile
    public ArrayList<User> getUsersByProfile(String profile){
        ArrayList<User> usersList = new ArrayList<User>();
        try{ 
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select * from users where profile='" + profile + "'");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String queryUsername = rs.getString("username");
                String queryPassword = rs.getString("password");
                String queryProfile = rs.getString("profile");
                usersList.add(new User(queryUsername, queryPassword, queryProfile));
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return usersList;
    }
    
    // Function to retrieve an user account by username
    public User searchUserByUsername(String username){
        User u = null;
        try{ 
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select * from users where username=?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                u = new User(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return u;
    }
    
    // Function to check if user account has the same profile as input profile
    public boolean checkSameProfile(String username, String profile){
        boolean same = false;
        String realProfile;
        try{ 
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select profile from users where username=?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                realProfile = rs.getString(1);
                if(realProfile.equals(profile)){
                    same = true;
                }
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return same;
    }
    
    // Function to change user profile of a specific user
    public void changeProfile(String username, String profile){
        try{ 
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("update users set profile = ? where username = ?");
            st.setString(1, profile);
            st.setString(2, username);
            st.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        } 
    }
    
}
