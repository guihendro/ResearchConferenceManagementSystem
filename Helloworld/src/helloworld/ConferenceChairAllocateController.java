package helloworld;

public class ConferenceChairAllocateController {
    ConferenceChair cc = new ConferenceChair();
    //allocate papers for a selected reviewer
    public void allocatePaper(String[] paper, String[] reviewer){
        cc.allocatePaper(paper,reviewer);
    }
//allocate bidders to a selected paper
    public void allocateBidders(int paperID,int bidderID){
        cc.allocateBidders(paperID,bidderID);
    }
}
