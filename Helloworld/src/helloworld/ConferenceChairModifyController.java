package helloworld;

public class ConferenceChairModifyController {
    ConferenceChair cc = new ConferenceChair();
    public void modify(int bidderID, int reviewerID, int paperID){
        cc.modify(bidderID,reviewerID,paperID);
    }
}
