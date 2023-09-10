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
public class SystemAdminChangeProfileController {
    
    SystemAdmin sa = new SystemAdmin();
    
    public ArrayList<String> getProfiles(){
        return sa.getProfiles();
    }
            
    public boolean checkSameProfile(String username, String profile){
        return sa.checkSameProfile(username, profile);
    }
    
    public void changeProfile(String username, String profile){
        sa.changeProfile(username, profile);
    }
    
    public boolean usernameExist(String username){
        return sa.usernameExist(username);
    }
}
