package helloworld;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AuthorDeleteSubmissionController {
    Author au = new Author();
    public void deleteSubmission(JTable table_view, DefaultTableModel dtm_view){
        au.deleteSubmission(table_view,dtm_view);
    }

}
