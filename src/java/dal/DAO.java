/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Article;

/**
 *
 * @author Hiep Nino
 */
public class DAO extends DBContext {

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy - h:mm");
    SimpleDateFormat hourFormat = new SimpleDateFormat("a");

    //get lastest article
    public Article getTop1Articles() {
        try {
            String query = "select top 1 * from Article\n"
                    + "order by timePost DESC";
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                String timePostFormat = dateFormat.format(rs.getTimestamp("timePost")) + hourFormat.format(rs.getTimestamp("timePost")).toLowerCase();
                Article a = new Article(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content_detail"),
                        rs.getString("image"),
                        rs.getString("author"),
                        timePostFormat,
                        rs.getString("shortDes"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
// get lastest 5 article

    public ArrayList<Article> getTop5Articles() {
        ArrayList<Article> listTop5 = new ArrayList<>();
        try {
            String query = "select top 5 * from Article "
                    + "where timePost not in (select max(timePost) from Article) "
                    + "order by timePost DESC";
            PreparedStatement smt = connection.prepareStatement(query);
            ResultSet rs = smt.executeQuery();

            while (rs.next()) {

                String timePostFormat = dateFormat.format(rs.getTimestamp("timePost")) + hourFormat.format(rs.getTimestamp("timePost"));
                Article a = new Article(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content_detail"),
                        rs.getString("image"),
                        rs.getString("author"),
                        timePostFormat,
                        rs.getString("shortDes"));
                listTop5.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTop5;
    }

    //get article by id
    public Article getArticleByID(int id) {
        try {
            String query = "select * from Article where id =?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String timePostFormat = dateFormat.format(rs.getTimestamp("timePost")) + hourFormat.format(rs.getTimestamp("timePost"));
                Article a = new Article(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content_detail"),
                        rs.getString("image"),
                        rs.getString("author"),
                        timePostFormat,
                        rs.getString("shortDes"));
                return a;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //count article find out
    public int countSearch(String txt) {
        try {
            String query = "select count(*) from Article where title like ?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, "%" + txt + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //search article (include txt search, index of currtent page, amount article per 1 page)
    public ArrayList<Article> searchArticles(String txt, int index, int amount) {
        ArrayList<Article> listArticle = new ArrayList<>();
        try {
            String query = "select * from Article \n"
                    + "where title like ?\n"
                    + "ORDER BY id OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, "%" + txt + "%");
            stm.setInt(2, (index - 1) * amount);
            stm.setInt(3, amount);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String timePostFormat = dateFormat.format(rs.getTimestamp("timePost")) + hourFormat.format(rs.getTimestamp("timePost"));

                //get all word of title
                String word = rs.getString(2);

                int i = word.toLowerCase().indexOf(txt.toLowerCase());
                String s1;
                if (i == 0) {
                    s1 = "";
                } else {
                    s1 = word.substring(0, i - 1);
                }

                String key = word.substring(i, i + txt.length());

                String newTitle = s1 + " <span style=\"background-color: yellow;\">" + key + "</span>";

                String s2 = word.substring(i + txt.length()); //đoạn sau
//        if (s2.contains(txt)) {
                int j = i;
                while (i != -1) {

                    j = i;
                    i = word.indexOf(txt, i + txt.length());
                    if (i != -1) {
                        key = word.substring(i, i + txt.length());

                        newTitle += word.substring(j + txt.length(), i) + " <span style=\"background-color: yellow;\">" + key + "</span>";
                    }
                }
                //       }
                newTitle = newTitle + word.substring(j + txt.length());

                Article a = new Article(rs.getInt("id"),
                        newTitle,
                        rs.getString("content_detail"),
                        rs.getString("image"),
                        rs.getString("author"),
                        timePostFormat,
                        rs.getString("shortDes"));
                listArticle.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listArticle;
    }

    public static void main(String[] args) {

        String word = "films Logan weapen logan logan X";
        String txt = "logan";
//        System.out.println(word.toLowerCase());
//        System.out.println(txt.toLowerCase());

        int i = word.toLowerCase().indexOf(txt.toLowerCase());
        String s1;
        if (i == 0) {
            s1 = "";
        } else {
            s1 = word.substring(0, i - 1);
        }

        String key = word.substring(i, i + txt.length());

        String newTitle = s1 + " <span style=\"background-color: yellow;\">" + key + "</span>";

        String s2 = word.substring(i + txt.length()); //đoạn sau
//        if (s2.contains(txt)) {
        int j = i;
        while (i != -1) {

            j = i;
            i = word.indexOf(txt, i + txt.length());
            if (i != -1) {
                key = word.substring(i, i + txt.length());

                newTitle += word.substring(j + txt.length(), i) + " <span style=\"background-color: yellow;\">" + key + "</span>";
            }
        }
        //       }
        newTitle = newTitle + word.substring(j + txt.length());
        System.out.println(newTitle);
    }
}
