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
public class ReviewerViewBidsController {
    Reviewer r = new Reviewer();
    
    public ArrayList<ArrayList<String>> getCompleteBidsList(String username){
        return r.getCompleteBidsList(username);
    }
}
