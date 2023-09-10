/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author guihendro
 */
public class Reviewer extends User{
    
    // Constructors
    public Reviewer(){
        
    }
    
    public Reviewer(String username, String password){
        super(username, password, "Reviewer");
    }
    
    // Function to retrieve all submitted papers
    public ArrayList<ArrayList<String>> getSubmittedPapers(){
        ArrayList<ArrayList<String>> papersList = new ArrayList<>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select users.username, authors.paperID, papers.name, papers.acceptance_status from authors inner join users on authors.userID = users.userID inner join papers on authors.paperID = papers.paperID;");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(rs.getString("username"));
                temp.add(rs.getString("paperID"));
                temp.add(rs.getString("name"));
                temp.add(rs.getString("acceptance_status"));
                papersList.add(temp);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return papersList;
    }   
    
    // Function to get a list of papers eligible for bid
    public ArrayList<ArrayList<String>> getBiddablePapers(){
        ArrayList<ArrayList<String>> papersList = new ArrayList<>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select users.username, authors.paperID, papers.name, papers.acceptance_status from authors inner join users on authors.userID = users.userID inner join papers on authors.paperID = papers.paperID where acceptance_status = 'PENDING';");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(rs.getString("username"));
                temp.add(rs.getString("paperID"));
                temp.add(rs.getString("name"));
                temp.add(rs.getString("acceptance_status"));
                papersList.add(temp);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return papersList;
    }
    
    // Function to get a list of available papers ID
    public ArrayList<String> getPapersId(){
        ArrayList<String> idList = new ArrayList<String>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select paperID from papers where acceptance_status = 'PENDING';");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                idList.add(rs.getString(1));
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return idList;
    }
    
    // Function to check if an user is eligible to bid a specific paper
    public boolean eligibleForBid(String username, String paperID){
        boolean eligible = true;
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("SELECT count(distinct r.paperID, r.reviewerID) from reviews r where r.reviewerID = (select u.userID from users u where u.username = ?)  AND r.paperID = ?;");
            st.setString(1, username);
            st.setString(2, paperID);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                if(count == 1){
                    eligible = false;
                }
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return eligible;
    }
    
    // Function to place a bid on a specific paper by an user
    public void placeBid(String username, String paperID){
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("Insert into reviews (paperID, reviewerID, review_status) values (?,(select userID from users where username = ?), 'Bidding');");
            st.setString(1, paperID);
            st.setString(2, username);
            st.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    // Function to get a list of bids by a specific user
    public ArrayList<ArrayList<String>> getCompleteBidsList(String username){
        ArrayList<ArrayList<String>> bidsList = new ArrayList<>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select reviews.paperID, papers.name, reviews.review_status from reviews inner join papers on reviews.paperID = papers.paperID where reviews.reviewerID = (select userID from users where username = ?);");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(rs.getString("paperID"));
                temp.add(rs.getString("name"));
                temp.add(rs.getString("review_status"));
                bidsList.add(temp);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return bidsList;
    }
    
    // Function to get a list of bids in "Bidding" status of a specific user
    public ArrayList<ArrayList<String>> getBidsList(String username){
        ArrayList<ArrayList<String>> bidsList = new ArrayList<>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select reviews.paperID, papers.name, reviews.review_status from reviews inner join papers on reviews.paperID = papers.paperID where reviews.reviewerID = (select userID from users where username = ?) AND reviews.review_status = 'Bidding';");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(rs.getString("paperID"));
                temp.add(rs.getString("name"));
                temp.add(rs.getString("review_status"));
                bidsList.add(temp);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return bidsList;
    }
    
    // Function to get all paper ID of bids under the "Bidding" status
    public ArrayList<String> getBiddingPaperID(String username){
        ArrayList<String> idList = new ArrayList<String>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select paperID from reviews where reviewerID = (select userID from users where username = ?) AND review_status = 'Bidding'  ;");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                idList.add(rs.getString(1));
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return idList;
    }
    
    // Function to delete a bid of a specific user
    public void deleteBid(String username, String paperID){
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("Delete from reviews where reviewerID = (select userID from users where username = ?) and paperID = ?;");
            st.setString(1, username);
            st.setString(2, paperID);
            st.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    // Function to search a specific bid
    public ArrayList<String> searchBid(String username, String paperID){
        ArrayList<String> idList = new ArrayList<String>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select reviews.paperID, papers.name, reviews.review_status from reviews inner join papers on reviews.paperID = papers.paperID where reviews.reviewerID = (select userID from users where username = ?) and reviews.paperID = ?;");
            st.setString(1, username);
            st.setString(2, paperID);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                idList.add(rs.getString(1));
                idList.add(rs.getString(2));
                idList.add(rs.getString(3));
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return idList;
    }
    
    // Function to check if an update is appropriate (e.g., old paper ID and new paper ID is not the same)
    public boolean eligibleForUpdate(String username, String newPaperID){
        boolean eligible = true;
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select count(paperID) from reviews where paperID = ? AND reviewerID = (select userID from users where username = ?)");
            st.setString(1, newPaperID);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                if(count == 1){
                    eligible = false;
                }
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return eligible;
    }
    
    // Function to perform an update on a bid
    public void updateBid(String username, String oldPaperID, String newPaperID){
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("update reviews set paperID = ? where paperID = ? and reviewerID = (select userID from users where username = ?) and review_status = 'Bidding';");
            st.setString(1, newPaperID);
            st.setString(2, oldPaperID);
            st.setString(3, username);
            st.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    // Function to get the review limit of a specific user
    public String getCurrentLimit(String username){
        String limit = "";
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select reviewers.limit from reviewers where reviewerID = (select userID from users where username = ?)");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                limit = rs.getString(1);
            } else{
                limit = "Unset";
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return limit;
    }
    
    // Function to change the review limit of an user
    public void setLimit(String username, String limit){
        try{
            String currLimit = getCurrentLimit(username);
            if(currLimit.equals("Unset")){
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("Insert into reviewers(reviewerID, reviewers.limit) value ((select userID from users where username = ?), ?);");
                st.setString(1, username);
                st.setString(2, limit);
                st.executeUpdate();
            } else{
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("UPDATE reviewers set reviewers.limit = ? where reviewerID = (select userID from users where username = ?);");
                st.setString(1, limit);
                st.setString(2, username);
                st.executeUpdate();
            }
            
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    // Function to get a list of bids under "Reviewing" status of a specific user
    public ArrayList<ArrayList<String>> getReviewingList(String username){
        ArrayList<ArrayList<String>> reviewingList = new ArrayList<>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select reviews.paperID, papers.name, reviews.review_status from reviews inner join papers on reviews.paperID = papers.paperID where reviews.reviewerID = (select userID from users where username = ?) AND reviews.review_status = 'REVIEWING';");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(rs.getString("paperID"));
                temp.add(rs.getString("name"));
                temp.add(rs.getString("review_status"));
                reviewingList.add(temp);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return reviewingList;
    }
    
    // Function to submit a review
    public void submitReview(String username, String paperID, String rating, String comment){
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("update reviews set rating = ?, comment = ?, review_status = 'REVIEWED' where paperID = ? and reviewerID = (select userID from users where username = ?);");
            st.setString(1, rating);
            st.setString(2, comment);
            st.setString(3, paperID);
            st.setString(4, username);
            st.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    // Function to get a list of bids under "Reviewed" status
    public ArrayList<ArrayList<String>> getReviewedList(String username){
        ArrayList<ArrayList<String>> reviewedList = new ArrayList<>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select reviews.paperID, papers.name, reviews.review_status from reviews inner join papers on reviews.paperID = papers.paperID where reviews.reviewerID = (select userID from users where username = ?) AND reviews.review_status = 'REVIEWED';");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(rs.getString("paperID"));
                temp.add(rs.getString("name"));
                temp.add(rs.getString("review_status"));
                reviewedList.add(temp);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return reviewedList;
    }
    
    // Function to check if a paper is reviewed by multiple reviewers
    public boolean otherReviewerIsEmpty(String paperID){
        boolean eligible = false;
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select count(*) from reviews where paperID = ? AND review_status = 'REVIEWED';");
            st.setString(1, paperID);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                if(count == 1){
                    eligible = true;
                }
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return eligible;
    }
    
    // Function to get all the reviews performed by other reviewers on a specific paper
    public ArrayList<ArrayList<String>> getOtherReviews(String username, String paperID){
        ArrayList<ArrayList<String>> otherReviewsList = new ArrayList<>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select users.username, reviews.rating, reviews.comment from reviews inner join users on reviews.reviewerID = users.userID where reviews.reviewerID NOT IN (select userID from users where username = ?) AND reviews.paperID = ? AND reviews.review_status = 'REVIEWED';");
            st.setString(1, username);
            st.setString(2, paperID);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(rs.getString("username"));
                temp.add(rs.getString("rating"));
                temp.add(rs.getString("comment"));
                otherReviewsList.add(temp);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return otherReviewsList;
    }
    
    // Function to get a list of reviewed papers that haven't been approved by conference chair
    public ArrayList<ArrayList<String>> getPendingReviewedList(String username){
        ArrayList<ArrayList<String>> pendingReviewedList = new ArrayList<>();
        
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select reviews.paperID, papers.name, reviews.rating from reviews inner join papers on reviews.paperID = papers.paperID where reviews.reviewerID = (select userID from users where username = ?) AND reviews.review_status = 'REVIEWED' AND papers.acceptance_status = 'PENDING';");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(rs.getString("paperID"));
                temp.add(rs.getString("name"));
                temp.add(rs.getString("rating"));
                pendingReviewedList.add(temp);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return pendingReviewedList;
    }
    
    // Function to update the rating of a paper
    public void updateRating(String username, String paperID, String rating){
        try{
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("update reviews set rating = ? where paperID = ? and reviewerID = (select userID from users where username = ?);");
            st.setString(1, rating);
            st.setString(2, paperID);
            st.setString(3, username);
            st.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
}
