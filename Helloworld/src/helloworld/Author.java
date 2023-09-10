/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author guihendro
 */
public class Author extends User {

    public Author() {
    }

    public Author(String username, String password, String profile) {
        super(username, password, profile);
    }

    public void createSubmission(JFrame frame, JLabel error) {
        try {
            File file = getFile(frame);
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO papers (name, file) values (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            InputStream inputStream = new FileInputStream(file);
            statement.setString(1, file.getName());
            statement.setBlob(2, inputStream);
            int row = statement.executeUpdate();
            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(User.getCurrUserID());
            //add more authors to a paper
            int check = JOptionPane.YES_NO_OPTION;
            int checkResult = JOptionPane.showConfirmDialog(frame, "Do you want to add more authors?", "More authors", check);
            while (checkResult == 0) {
//                System.out.println("Yes option");
                String path = JOptionPane.showInputDialog(frame, "Enter the other author's username(Only input one author at the same time)");
                String getIdSQL = "SELECT userID from users where username = ?";
                PreparedStatement stm = DatabaseConnection.getConnection().prepareStatement(getIdSQL);
                stm.setString(1, path);
                ResultSet getIdRs = stm.executeQuery();
                if (getIdRs.next()) {
                    int id = getIdRs.getInt("userID");
                    if (id == User.getCurrUserID())
                        JOptionPane.showMessageDialog(frame, "Don't input your username again!");
                    else
                        ids.add(id);
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong username, please enter again!");
                }
                checkResult = JOptionPane.showConfirmDialog(frame, "Do you want to add more authors?", "More authors", check);
            }


            if (row > 0) {
                error.setVisible(true);
                error.setText("Uploaded Successfully!");
                System.out.println("Successful.");
                ResultSet rs = statement.getGeneratedKeys();
                int paper_id = 0;
                if (rs.next()) {
                    paper_id = rs.getInt(1);
                }
//                        System.out.println(paper_id);
                String sql1 = "INSERT INTO authors (userID, paperID) values (?, ?)";
                for (Integer id : ids) {
                    PreparedStatement statement1 = conn.prepareStatement(sql1);
                    statement1.setInt(1, id);
                    statement1.setInt(2, paper_id);
                    statement1.executeUpdate();
                }

            }
        } catch (Exception ex) {
            error.setVisible(true);
            error.setText("Sorry please try again.");
//            ex.printStackTrace();
        }
    }

    public void editSubmission(JFrame frame, JTable table) {
        try {
            File file = getFile(frame);
            String sql = "UPDATE papers SET file = ? , name = ? WHERE paperID = ?";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
            InputStream inputStream = new FileInputStream(file);
            statement.setBlob(1, inputStream);
            statement.setString(2, file.getName());
            statement.setInt(3, Author.getPaperID(table));
            statement.executeUpdate();
            //fresh
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public File getFile(JFrame frame) throws Exception {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MS Office Documents", "docx"));
//                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(frame);
        return fileChooser.getSelectedFile();
    }

    public static boolean downloadFile(int paperID, JFrame frame) {
        String sql = "SELECT file,name from papers WHERE papers.paperID = ?";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose the destination folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        boolean folderSelected = chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION;
        if (!folderSelected)
            return false;

        String folderPath = chooser.getSelectedFile().getAbsolutePath();
        try {
            PreparedStatement downloadStatement = DatabaseConnection.getConnection().prepareStatement(sql);
            downloadStatement.setInt(1, paperID);
            ResultSet rs = downloadStatement.executeQuery();
            while (rs.next()) {
                // take the blob
                String paperName = rs.getString("name");
                Blob blob = rs.getBlob("file");
                String path = folderPath + File.separator + paperName;
                File file = new File(path);
                BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());
                FileOutputStream fos = new FileOutputStream(file);
                // you can set the size of the buffer
                byte[] buffer = new byte[2048];
                int r = 0;
                while ((r = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, r);
                }
                fos.flush();
                fos.close();
                is.close();
                blob.free();

            }


        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

    public void deleteSubmission(JTable table_view, DefaultTableModel dtm_view) {

        if (table_view.getSelectedRow()<0)
            //nothing selected
            return;
        String deleteSQL = "DELETE FROM papers WHERE paperID=?";
        try {
            PreparedStatement deleteStm = DatabaseConnection.getConnection().prepareStatement(deleteSQL);
            deleteStm.setInt(1, getPaperID(table_view));
            deleteStm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int select_row = table_view.getSelectedRow();
        dtm_view.removeRow(select_row);
    }

    public void getViewTableModel(DefaultTableModel dtm) {
        dtm.setRowCount(0);
        try {
            String getDataSQL = "SELECT papers.paperID, name, acceptance_status from papers JOIN authors on authors.paperID = papers.paperID where authors.userID = ?";
            PreparedStatement getDataStm = DatabaseConnection.getConnection().prepareStatement(getDataSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            getDataStm.setInt(1, User.getCurrUserID());
            ResultSet getDataRs = getDataStm.executeQuery();
//            getDataRs.beforeFirst();
            while (getDataRs.next()) {
                int ID = getDataRs.getInt("paperID");
                String name = getDataRs.getString("name");
                String status = getDataRs.getString("acceptance_status");
                Object[] data = {ID, name, status};
                dtm.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Object[]> viewSubmission(Object[] paper) {
        String getPaperDetail = "SELECT reviewerID, rating, review_status, comment from reviews where reviews.paperID = ?";
        ArrayList<Object[]> papers = new ArrayList<>();
        try {
            PreparedStatement getDetailStm = DatabaseConnection.getConnection().prepareStatement(getPaperDetail, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            int paperID = (int) paper[0];
            getDetailStm.setInt(1, paperID);
            ResultSet getDetailRs = getDetailStm.executeQuery();
//            dtm_details.setRowCount(0);
            while (getDetailRs.next()) {
                int ID = getDetailRs.getInt("reviewerID");
                String rating = getDetailRs.getString("rating");
                String comment = getDetailRs.getString("comment");
                String status = getDetailRs.getString("review_status");
                Object[] data = {ID, rating, comment, status};
                papers.add(data);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return papers;
    }

    public ArrayList<Object[]> searchSubmission(String keyWord) {
        ArrayList<Object[]> papers = new ArrayList<>();
        String getDataSQL = "SELECT papers.paperID, name, acceptance_status from papers JOIN authors on authors.paperID = papers.paperID where authors.userID = ? and papers.name LIKE ?";
        try {
            PreparedStatement search = DatabaseConnection.getConnection().prepareStatement(getDataSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            search.setInt(1, User.getCurrUserID());
            search.setString(2, "%" + keyWord + "%");
            ResultSet getDataRs = search.executeQuery();
            while (getDataRs.next()) {
                int ID = getDataRs.getInt("paperID");
                String name = getDataRs.getString("name");
                String status = getDataRs.getString("acceptance_status");
                Object[] data = {ID, name, status};
//                dtm_view.addRow(data);
                papers.add(data);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return papers;
    }

    public static int getPaperID(JTable table) {
        Object[] paper = getPaper(table);
        return (int) paper[0];
    }

    public static Object[] getPaper(JTable table) {
        Object[] paper = new Object[3];
        int select_row = table.getSelectedRow();
        int col = 0;
        for (int i = 0; i < 3; i++) {
//            paper(id,name,status)
            paper[i] = table.getValueAt(select_row, col);
            col++;
        }
        return paper;
    }



}
