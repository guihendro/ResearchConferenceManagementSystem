package helloworld;

import javax.swing.table.DefaultTableModel;

public class ConferenceChairDisplayPaperInfoController {
    ConferenceChair cc = new ConferenceChair();
    //view list of bidders
    public void displayPaperInfo(int paperID, DefaultTableModel dtm_viewReviewers, DefaultTableModel dtm_bidders){
        cc.displayPaperInfo(paperID,dtm_viewReviewers,dtm_bidders);
    }
}
