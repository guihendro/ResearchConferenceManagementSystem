package helloworld;

public class ConferenceChairAcceptRejectController {
    ConferenceChair cc = new ConferenceChair();
    public void rejectPaper(int id){
        cc.rejectPaper(id);
    }
    public void acceptPaper(int id){
        cc.acceptPaper(id);
    }
}
