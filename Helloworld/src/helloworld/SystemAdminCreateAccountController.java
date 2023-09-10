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
public class SystemAdminCreateAccountController {
    SystemAdmin sa = new SystemAdmin();
    public void createAccount(String username, String password, String profile){
        sa.createAccount(username, password, profile);
    }
    
    public boolean usernameExist(String username){
        return sa.usernameExist(username);
    }
    
    public ArrayList<String> getProfiles(){
        return sa.getProfiles();
    }
    
}
