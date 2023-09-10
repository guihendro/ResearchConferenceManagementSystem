/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

import java.util.ArrayList;

/**
 *
 * @author guihendro
 */
public class SystemAdminSearchProfileController {
    
    SystemAdmin sa = new SystemAdmin();
    
    public ArrayList<User> getUsersByProfile(String profile){
        return sa.getUsersByProfile(profile);
    }
    
    public ArrayList<User> getAllUsers(){
        return sa.getAllUsers();
    }
    
    public ArrayList<String> getProfiles(){
        return sa.getProfiles();
    }
}
