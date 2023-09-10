package helloworld;

import java.util.ArrayList;

public class ConferenceChairViewHistoryController {
    ConferenceChair cc = new ConferenceChair();
    public ArrayList<String[]> viewApprovalHistory(){
        return cc.viewApprovalHistory();
    }
}
