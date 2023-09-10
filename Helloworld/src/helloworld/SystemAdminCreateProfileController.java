/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

/**
 *
 * @author guihendro
 */
public class SystemAdminCreateProfileController {
    
    SystemAdmin sa = new SystemAdmin();
    
    public void createProfile(String profile){
        sa.createProfile(profile);
    }
    
    public boolean profileExist(String profile){
        return sa.profileExist(profile);
    }
    
}
