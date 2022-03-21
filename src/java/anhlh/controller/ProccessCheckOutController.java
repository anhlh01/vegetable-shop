/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.controller;

import anhlh.cart.Cart;
import anhlh.cart.ProductForCart;
import anhlh.tblProducts.ProductDTO;
import anhlh.tblProducts.TblProductsDAO;
import anhlh.tblUsers.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
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
public class ProccessCheckOutController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ProccessCheckOutController.class);
    private static final String ERROR = "error.jsp";
    private static final String CART = "cart.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String CHECKOUT = "checkout.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");

            Cart cart = (Cart) session.getAttribute("CART");

            TblProductsDAO dao = new TblProductsDAO();
            ProductDTO product;

            ArrayList<ProductForCart> list = new ArrayList<>();

            if (cart == null) {
                url = CART;
            } else {
                for (ProductForCart productForCart : cart.getSet()) {
                    product = dao.searchProductByID(productForCart.getProductID());
                    if (product.getProductQuantity() < productForCart.getProductQuantity()) {
                        list.add(productForCart);
                        LOGGER.info("Add the product have been sold out to the list to show the message to the user");
                    }
                }
                if (list.size()>0) {
                    String listSoldOut = "";
                    for (ProductForCart productForCart : list) {
                        listSoldOut += " "+productForCart.getProductName();
                        cart.remove(productForCart);
                    }
                    if(cart.getSet().isEmpty()){
                        cart.resetCart();
                        session.removeAttribute("CART");
                    }
                    request.setAttribute("PRODUCT_SOLD_OUT", "The product " + listSoldOut + " is sold out. Can't check out!");
                    url = CART;
                    LOGGER.warn("Show message for user about the sold out product");
                } else {
                    if (user == null) {
                        url = LOGIN;
                        session.setAttribute("CHECK_OUT", "checkout.jsp");
                        LOGGER.info("User must be login before check out");
                    } else {
                        url = CHECKOUT;
                    }
                }
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
