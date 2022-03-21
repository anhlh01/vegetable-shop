/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.controller;

import anhlh.cart.Cart;
import anhlh.cart.ProductForCart;
import anhlh.tblProducts.TblProductsDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author HP
 */
public class AddToCartController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(AddToCartController.class);
    static final String ERROR = "index.jsp";
    static final String INDEX = "index.jsp";
    static final String SHOP = "shop.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if(cart==null){
                cart = new Cart();
                session.setAttribute("CART", cart);
            }
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String productID = request.getParameter("productID");
            String page = request.getParameter("page");
            if("index".equals(page)){
                url = INDEX;
            }else{
                url = SHOP;
            }
            TblProductsDAO dao = new TblProductsDAO();
            ProductForCart product = dao.searchProductByIDForCart(productID, quantity);
            if (product.isStatus()) {
                cart.add(product);
                LOGGER.info("Add product to cart success");
            } else{
                LOGGER.info("Add product to cart fail. Because product have been sold out");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            response.sendRedirect(url);
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
