/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author guihendro
 */
public class ConferenceChair extends User {
    public ConferenceChair() {

    }

    public ConferenceChair(String username, String password) {
        super(username, password, "Conference chair");
    }

    //bidders, ratings,
    public void displayPaperInfo(int paperID, DefaultTableModel dtm_viewReviewers, DefaultTableModel dtm_bidders){
        try {
            String showReviewersSQL = "SELECT reviewerID, users.username , review_status, rating from reviews \n" +
                    "JOIN users on users.userID = reviewerID\n" +
                    "where reviews.paperID = ? and reviews.review_status != \"BIDDING\"";
            PreparedStatement showReviewerStm = DatabaseConnection.getConnection().prepareStatement(showReviewersSQL);
            showReviewerStm.setInt(1, paperID);
            ResultSet reviewersRS = showReviewerStm.executeQuery();
            while (reviewersRS.next()) {
                String id = String.valueOf(reviewersRS.getInt("reviewerID"));
                String username = reviewersRS.getString("username");
                String rating = reviewersRS.getString("rating");
                String status = reviewersRS.getString("review_status");
                String[] result = {id, username, rating, status};
                dtm_viewReviewers.addRow(result);
            }
            String showBidderSQL2 = "SELECT reviews.reviewerID,users.username, reviewers.limit\n" +
                    "from reviews\n" +
                    "Join reviewers on reviews.reviewerID = reviewers.reviewerID\n" +
                    "join users on users.userID = reviews.reviewerID\n" +
                    "where paperID = ? and review_status = \"BIDDING\"";

            PreparedStatement showBidderStm2 = DatabaseConnection.getConnection().prepareStatement(showBidderSQL2);
            showBidderStm2.setInt(1, paperID);
            ResultSet showBiddersRs2 = showBidderStm2.executeQuery();

            ArrayList<String[]> results = new ArrayList<>();
            while (showBiddersRs2.next()) {
                String id = String.valueOf(showBiddersRs2.getInt("reviewerID"));
                String name = showBiddersRs2.getString("username");
                String limit = String.valueOf(showBiddersRs2.getInt("limit"));
                String[] result = {id, name, "", limit};
                results.add(result);
            }
            StringBuilder value = new StringBuilder(results.isEmpty() ? "(null)" : "(");
            for (int i = 0; i < results.size(); i++) {
                String[] temp = results.get(i);
                String id = temp[0];
                value.append(id);
                if (i == results.size() - 1)
                    value.append(")");
                else
                    value.append(",");
            }

            String showBiddersSQL1 = "\n" +
                    "SELECT reviews.reviewerID, COUNT(case when review_status = \"REVIEWING\" then 1 else null END) AS total\n" +
                    "from reviews \n" +
                    "where reviews.reviewerID in " + value + "\n" +
                    "Group by reviews.reviewerID;";
            PreparedStatement showBiddersStm1 = DatabaseConnection.getConnection().prepareStatement(showBiddersSQL1);
            ResultSet showBiddersRs1 = showBiddersStm1.executeQuery();
            ArrayList<Integer> ids = new ArrayList();
            while (showBiddersRs1.next()) {
                int total = showBiddersRs1.getInt("total");
                ids.add(total);
            }
            int i = 0;
            for (String[] result : results) {
                result[2] = String.valueOf(ids.get(i));
                dtm_bidders.addRow(results.get(i));
                i++;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<String[]> viewApprovalHistory(){
        ArrayList<String[]> history = new ArrayList<>();
        try {
            String showHistorySQL = "SELECT * FROM papers where acceptance_status != \"PENDING\"";
            PreparedStatement showHistoryStm = DatabaseConnection.getConnection().prepareStatement(showHistorySQL);
            ResultSet historyRs = showHistoryStm.executeQuery();

            while (historyRs.next()) {
                String id = String.valueOf(historyRs.getInt("paperID"));
                String name = historyRs.getString("name");
                String status = historyRs.getString("acceptance_status");
                String[] result = {id, name, status};
                history.add(result);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return history;
    }

    public void rejectPaper(int id){
        Connection con = DatabaseConnection.getConnection();
        try {
            String updateSql = "UPDATE papers SET acceptance_status = \"REJECTED\" where paperID = ?";
            String updateSql2 = "UPDATE reviews SET review_status = \"REVIEWED\" where paperID = ?";
            con.setAutoCommit(false);
            PreparedStatement stm = con.prepareStatement(updateSql);
            PreparedStatement stm2 = con.prepareStatement(updateSql2);
            stm.setInt(1, id);
            stm2.setInt(1, id);
            stm.executeUpdate();
            stm2.executeUpdate();
            con.commit();
            //clear other bidders for this paper
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void acceptPaper(int id){
        Connection con = DatabaseConnection.getConnection();
        try {
            String updateSql = "UPDATE papers SET acceptance_status = \"ACCEPTED\" where paperID = ?";
            String deletSql = "UPDATE reviews SET review_status = \"REVIEWED\" where paperID = ?";
            con.setAutoCommit(false);
            PreparedStatement stm = con.prepareStatement(updateSql);
            PreparedStatement stm2 = con.prepareStatement(deletSql);
            stm.setInt(1, id);
            stm2.setInt(1, id);
            stm.executeUpdate();
            stm2.executeUpdate();
            con.commit();

            //clear other bidders for this paper
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    public void modify(int bidderID, int reviewerID, int paperID) {
        Connection con = DatabaseConnection.getConnection();
        try {
            String modifySQL = "UPDATE reviews SET review_status = \"REVIEWING\" where reviewerID = ? and paperID = ?;";
            String modifySQL2 = "DELETE FROM reviews where reviewerID = ? and paperID = ?";
            con.setAutoCommit(false);
            PreparedStatement modifyDtm = con.prepareStatement(modifySQL);
            PreparedStatement modifyDtm2 = con.prepareStatement(modifySQL2);
            modifyDtm.setInt(1, bidderID);
            modifyDtm.setInt(2, paperID);
            modifyDtm2.setInt(1, reviewerID);
            modifyDtm2.setInt(2, paperID);
            modifyDtm.executeUpdate();
            modifyDtm2.executeUpdate();
            con.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void delete(String[] paper) {
        try {
            String sql = "DELETE FROM reviews WHERE paperID = ?";
            PreparedStatement stm = DatabaseConnection.getConnection().prepareStatement(sql);
            int paperID = Integer.parseInt(paper[0]);
            stm.setInt(1, paperID);
            stm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void allocateBidders(int paperID, int bidderID){
        try {
            String sql = "UPDATE reviews SET review_status = \"REVIEWING\" where paperID = ? and reviewerID = ?";
            PreparedStatement stm = DatabaseConnection.getConnection().prepareStatement(sql);
            stm.setInt(1, paperID);
            stm.setInt(2, bidderID);
            stm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void allocatePaper(String[] paper, String[] reviewer) {
        try {
            String sql = "UPDATE reviews SET review_status = \"REVIEWING\" WHERE paperID = ? and reviewerID = ?";
            PreparedStatement stm = DatabaseConnection.getConnection().prepareStatement(sql);
            int paperID = Integer.parseInt(paper[0]);
            stm.setInt(1, paperID);
            stm.setInt(2, Integer.parseInt(reviewer[0]));
            stm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void autoAllocate() {
        ArrayList<int[]> paperResults = new ArrayList<>();
//                HashMap<Integer, ArrayList<Integer>> paperResults = new HashMap<>();
        //[id, name, total, max]
        ArrayList<String[]> reviewersInfoArr = getReviewerInfo();
        HashMap<Integer, ArrayList<Integer>> reviewersInfo = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> bidderInfo = new HashMap<>();

        for (String[] strings : reviewersInfoArr) {

            int key = Integer.parseInt(strings[0]);
            ArrayList<Integer> value = new ArrayList<>();
//                    value.add(Integer.valueOf(strings[1]));
            value.add(Integer.valueOf(strings[2]));
            value.add(Integer.valueOf(strings[3]));
            reviewersInfo.put(key, value);
        }
        //paper status
        String getPaperSQL = "SELECT paperID, COUNT(reviewerID)AS total from reviews where review_status = \"BIDDING\" group by paperID;";
        String getBidList = "SELECT reviewerID,paperID from reviews where review_status = \"BIDDING\"";
        try {
            PreparedStatement getResultsStm = DatabaseConnection.getConnection().prepareStatement(getPaperSQL);
            PreparedStatement getBidListStm = DatabaseConnection.getConnection().prepareStatement(getBidList);
            ResultSet paperResult = getResultsStm.executeQuery();
            ResultSet getBidListRs = getBidListStm.executeQuery();
            while (paperResult.next()) {
                int paperID = paperResult.getInt("paperID");
                int total = paperResult.getInt("total");
                paperResults.add(new int[]{paperID, total});
            }

            while (getBidListRs.next()) {
                int reviewerID = getBidListRs.getInt("reviewerID");
                int paperID = getBidListRs.getInt("paperID");
                //if null, create a new one
                bidderInfo.computeIfAbsent(reviewerID, k -> new ArrayList<Integer>());
                bidderInfo.get(reviewerID).add(paperID);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //paperResults : [paperID, no of current bidders]
        //HashMap<Integer,ArrayList<String>> reviewersInfo : key: userID value: [current reviewing papers, max,bid num]
        //HashMap<Integer,ArrayList<Integer>> biddersInfo : key: user id, value: the list of paper they bid
        //when to stop allocate?
        //1. no more bidders 2. no more papers
        Map<Integer, ArrayList<Integer>> sortedBidder = sortMap(bidderInfo);
        while (paperResults.size() > 0 && bidderInfo.size() > 0) {

            ArrayList<Integer> sortPaper = new ArrayList<>();
            int size = paperResults.size();

            for (int i = 0; i < size; i++) {
                int[] temp = paperResults.get(i);
                if (temp[1] == 0) {
                    paperResults.remove(i);
                    i--;
                    size--;
                } else
                    sortPaper.add(temp[1]);
            }
            if (sortPaper.size() == 0)
                break;
            int[] minBidderPaper = paperResults.get(sortPaper.indexOf(getMin(sortPaper)));
            int minBidderPaperId = minBidderPaper[0];
            Set<Integer> tempMap = new HashSet<>(sortedBidder.keySet());

            for (Integer key : tempMap) {
                int min = reviewersInfo.get(key).get(0);
                int max = reviewersInfo.get(key).get(1);
                if (min >= max) {
                    sortedBidder.remove(key);
                    for (Integer value : bidderInfo.get(key)) {
                        for (int i = 0; i < size; i++) {
                            int[] temp = paperResults.get(i);
                            if (temp[0] == value) {
                                int[] result = paperResults.get(i);
                                result[1]--;
                                paperResults.set(i, result);
                            }
                        }
                    }
                    bidderInfo.remove(key);
                    break;
                }
                if (sortedBidder.get(key).size() == 0) {
                    sortedBidder.remove(key);
                    bidderInfo.remove(key);
                } else {
                    if (sortedBidder.get(key).contains(minBidderPaperId)) {
                        //allocate paper SQL
                        sortedBidder.get(key).remove((Integer) minBidderPaperId);
                        bidderInfo.get(key).remove((Integer) minBidderPaperId);
//                                bidderInfo.remove(sortedBidder.get(key).indexOf(minBidderPaperId));
                        for (int i = 0; i < paperResults.size(); i++) {
                            int[] result = paperResults.get(i);
                            if (result[0] == minBidderPaperId) {
                                result[1]--;
                                paperResults.set(i, result);
                                String sql = "UPDATE reviews SET review_status = \"REVIEWING\" where paperID = ? and reviewerID = ?";
                                try {
                                    PreparedStatement stm = DatabaseConnection.getConnection().prepareStatement(sql);
                                    stm.setInt(1, result[0]);
                                    stm.setInt(2, key);
                                    stm.executeUpdate();
//                                    System.out.println(stm);
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                                min++;
                                reviewersInfo.get(key).set(0, min);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }

    }

    public static ArrayList<String[]> getReviewerInfo() {
        ArrayList<String[]> results = new ArrayList<>();
        try {
            //if a paper already got accepted or rejected, it won't show at here
            String getDataSQL = "SELECT reviews.reviewerID,users.username, COUNT(case when review_status = \"REVIEWING\" then 1 else null END) AS total, reviewers.limit \n" +
                    "from reviews JOIN reviewers on reviews.reviewerID = reviewers.reviewerID \n" +
                    "join users on reviews.reviewerID = users.userID\n" +
                    "JOIN papers on papers.paperID = reviews.paperID\n" +
                    "where acceptance_status = \"PENDING\" \n" +
                    "Group by reviews.reviewerID;";
            PreparedStatement getDataStm = DatabaseConnection.getConnection().prepareStatement(getDataSQL);
            ResultSet getDataRs = getDataStm.executeQuery();

//            getDataRs.beforeFirst();
            while (getDataRs.next()) {
                int ID = getDataRs.getInt("reviewerID");
                String name = getDataRs.getString("username");
                int limit = getDataRs.getInt("limit");
                String current = getDataRs.getString("total");
                String[] data = {String.valueOf(ID), name, current, String.valueOf(limit)};
                results.add(data);
//                dtm_reviewer.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static <K, V extends Collection> Map<K, V> sortMap(Map<K, V> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


    private int getMin(ArrayList<Integer> arr) {
        int temp = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < temp)
                temp = arr.get(i);
        }
        return temp;
    }


}
