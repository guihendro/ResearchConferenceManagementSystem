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
public class ReviewerUpdateRatingController {
    Reviewer r = new Reviewer();
    
    public ArrayList<ArrayList<String>> getPendingReviewedList(String username){
        return r.getPendingReviewedList(username);
    }
    
    public void updateRating(String username, String paperID, String rating){
        r.updateRating(username, paperID, rating);
    }
    
}
