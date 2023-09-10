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
public class ReviewerDeleteBidController {
    Reviewer r = new Reviewer();
    
    public ArrayList<String> getBiddingPaperID(String username){
        return r.getBiddingPaperID(username);
    }
    
    public ArrayList<ArrayList<String>> getBidsList(String username){
        return r.getBidsList(username);
    }
    
    public void deleteBid(String username, String paperID){
        r.deleteBid(username, paperID);
    }
    
}
