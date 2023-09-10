package helloworld;

import helloworld.User;

import java.util.ArrayList;

public class AuthorSearchSubmissionController {
    Author au = new Author();

    public ArrayList<Object[]> searchSubmission(String keyWord) {
        return au.searchSubmission(keyWord);

    }
}
