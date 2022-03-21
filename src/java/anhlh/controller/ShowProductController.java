/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.controller;

import anhlh.tblCategory.Category;
import anhlh.tblCategory.TblCategoryDAO;
import anhlh.tblProducts.ProductDTO;
import anhlh.tblProducts.TblProductsDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author HP
 */
public class ShowProductController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ShowProductController.class);
    private static final String SUCCESS = "shop.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<ProductDTO> list;
        TblProductsDAO dao = new TblProductsDAO();
        TblCategoryDAO cDao = new TblCategoryDAO();
        try {
            String name = request.getParameter("name");
            HashMap<String, String> map = cDao.loadCategory();
            String categoryID = map.get(name);
            list = dao.searchProductByCategory(categoryID);
            if (list != null) {
                request.setAttribute("LIST_PRODUCT", list);
                ArrayList<Category> list1 = cDao.getArray();
                request.setAttribute("LIST_CATEGORY", list1);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
