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
public class ReviewerUpdateBidController {
    Reviewer r = new Reviewer();
    public ArrayList<ArrayList<String>> getBidsList(String username){
        return r.getBidsList(username);
    }
    
    public boolean eligibleForUpdate(String username, String newPaperID){
        return r.eligibleForUpdate(username, newPaperID);
    }
    
    public void updateBid(String username, String oldPaperID, String newPaperID){
        r.updateBid(username, oldPaperID, newPaperID);
    }
}
