    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

/**
 *
 * @author guihendro
 */
public class SystemAdminSearchAccountController {
    
    SystemAdmin sa = new SystemAdmin();
    
    public User searchUserByUsername(String username){
        return sa.searchUserByUsername(username);
    }
    
}
