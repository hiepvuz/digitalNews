/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Article;

/**
 *
 * @author Hiep Nino
 */
@WebServlet(name = "SearchPageServlet", urlPatterns = {"/search"})
public class SearchPageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=UTF-8");
        DAO db = new DAO();
        //get text need to search from jsp
        String txtSearch = request.getParameter("txtSearch");

        
        //first, index must be 1
        int index = 1;
        
         //get index choosen
        String indexString = request.getParameter("index");
        
        if (indexString != null) {
            index = Integer.parseInt(request.getParameter("index"));
        }

        //set amount article per page
        int amountPerPage = 2;

        int count = db.countSearch(txtSearch);

        //calculate amount page need to show find out
        int totalPage = (count % amountPerPage == 0) ? count / amountPerPage : count / amountPerPage + 1;

        List<Integer> lsPage = new ArrayList<>();
        for (int i = 1; i <= totalPage; ++i) {
            lsPage.add(i);
        }

        Article top1 = db.getTop1Articles();
        ArrayList<Article> top5 = db.getTop5Articles();

        //find article match text
        ArrayList<Article> listArticle = new DAO().searchArticles(txtSearch, index, amountPerPage);

        request.setAttribute("index", index);
        request.setAttribute("lsPage", lsPage);
        request.setAttribute("listArticle", listArticle);
        request.setAttribute("top1", top1);
        request.setAttribute("top5", top5);
        request.setAttribute("txtSearch", txtSearch);
        
        request.getRequestDispatcher("SearchPage.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
