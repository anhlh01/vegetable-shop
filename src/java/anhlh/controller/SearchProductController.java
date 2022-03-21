/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.controller;

import anhlh.tblCategory.TblCategoryDAO;
import anhlh.tblProducts.ProductDTO;
import anhlh.tblProducts.TblProductsDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author HP
 */
public class SearchProductController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(SearchProductController.class);
    private static final String ERROR = "error.jsp";
    private static final String INDEX = "index.jsp";
    private static final String SHOP = "shop.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productName = request.getParameter("productName");
            LOGGER.info("User is searching about: " + productName);
            TblProductsDAO dao = new TblProductsDAO();
            if (productName.trim().isEmpty()) {
                url = INDEX;
                LOGGER.warn("If search is null of empty do nothing");
            } else {
                url = SHOP;
                ArrayList<ProductDTO> set = dao.searchProductByName(productName.trim());
                request.setAttribute("LIST_PRODUCT", set);
                request.setAttribute("PRODUCT_NAME", productName);
                TblCategoryDAO dao1 = new TblCategoryDAO();
                ArrayList<anhlh.tblCategory.Category> list = dao1.getArray();
                request.setAttribute("LIST_CATEGORY", list);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
