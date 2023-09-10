/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

/**
 *
 * @author guihendro
 */
public class ReviewerSetLimitController {
    Reviewer r = new Reviewer();
    
    public String getCurrentLimit(String username){
        return r.getCurrentLimit(username);
    }
    
    public void setLimit(String username, String limit){
        r.setLimit(username, limit);
    }
}
