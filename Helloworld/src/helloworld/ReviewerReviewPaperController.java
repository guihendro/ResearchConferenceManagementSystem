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
public class ReviewerReviewPaperController {
    Reviewer r = new Reviewer();
    
    public ArrayList<ArrayList<String>> getReviewingList(String username){
        return r.getReviewingList(username);
    }
    
    public void submitReview(String username, String paperID, String rating, String comment){
        r.submitReview(username, paperID, rating, comment);
    }
    
}
