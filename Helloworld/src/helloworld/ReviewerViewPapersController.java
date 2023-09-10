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
public class ReviewerViewPapersController {
    Reviewer r = new Reviewer();
    
    public ArrayList<ArrayList<String>> getSubmittedPapers(){
        return r.getSubmittedPapers();
    }
    
}
