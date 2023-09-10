package helloworld;

public class ConferenceChairDeleteController {
    ConferenceChair cc = new ConferenceChair();
    public void delete(String[] paper){
        cc.delete(paper);
    }
}
