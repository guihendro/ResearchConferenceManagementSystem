/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

/**
 *
 * @author guihendro
 */
public class LoginController {
    private User u = new User();
    
    public boolean login(String username, String password){
        return u.validateLogin(username, password);
    }
    
    public String checkProfile(String username){
        return u.checkProfile(username);
    }
}
