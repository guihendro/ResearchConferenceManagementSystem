package helloworld;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AuthorViewSubmissionController {
    Author au = new Author();
    //View rating(paper details)
    public ArrayList<Object[]> viewSubmission(Object[] paper){
        return au.viewSubmission(paper);
    }
//view submission
    public void getViewTableModel(DefaultTableModel dtm) {
        au.getViewTableModel(dtm);
    }
}
