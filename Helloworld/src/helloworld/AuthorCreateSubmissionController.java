package helloworld;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class AuthorCreateSubmissionController {
    Author au = new Author();
    public void createSubmission(JFrame frame, JLabel error){
        au.createSubmission(frame,error);
    }


}