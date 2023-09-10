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
public class ReviewerViewOtherRatingController {
    Reviewer r = new Reviewer();
    
    public ArrayList<ArrayList<String>> getReviewedList(String username){
        return r.getReviewedList(username);
    }
    
    public boolean otherReviewerIsEmpty(String paperID){
        return r.otherReviewerIsEmpty(paperID);
    }
    
    public ArrayList<ArrayList<String>> getOtherReviews(String username, String paperID){
        return r.getOtherReviews(username, paperID);
    }
    
    
}
