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
public class ReviewerBidPaperController {
    Reviewer r = new Reviewer();
    
    public ArrayList<ArrayList<String>> getBiddablePapers(){
        return r.getBiddablePapers();
    }
    
    public ArrayList<String> getPapersId(){
        return r.getPapersId();
    }
    
    public boolean eligibleForBid(String username, String paperID){
        return r.eligibleForBid(username, paperID);
    }
    
    public void placeBid(String username, String paperID){
        r.placeBid(username, paperID);
    }
    
}
