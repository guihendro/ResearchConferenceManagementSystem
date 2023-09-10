package helloworld;

import javax.swing.*;

public class AuthorEditSubmissionController {
    Author au = new Author();
    public void editSubmission(JFrame frame, JTable table){
        au.editSubmission(frame,table);
    }
}
