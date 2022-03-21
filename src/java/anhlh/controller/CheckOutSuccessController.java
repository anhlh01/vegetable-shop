/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.controller;

import anhlh.cart.Cart;
import anhlh.cart.ProductForCart;
import anhlh.tblUserCheckOuts.UserCheckOut;
import anhlh.tblOrderDetails.TblOrderDetailsDAO;
import anhlh.tblOrders.TblOrdersDAO;
import anhlh.tblProducts.ProductDTO;
import anhlh.tblProducts.TblProductsDAO;
import anhlh.tblUserCheckOuts.TblUserCheckOutsDAO;
import anhlh.tblUsers.UserDTO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;
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
public class CheckOutSuccessController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(CheckOutSuccessController.class);
    private static final String ERROR = "error.jsp";
    private static final String CHECKOUT = "checkout.jsp";
    private static final String SENDMAIL = "SendEmailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");

            TblProductsDAO dao = new TblProductsDAO();
            ProductDTO product;

            ArrayList<String> list = new ArrayList<>();

            for (ProductForCart productForCart : cart.getSet()) {
                product = dao.searchProductByID(productForCart.getProductID());
                if (product.getProductQuantity() < productForCart.getProductQuantity()) {
                    list.add(productForCart.getProductName());
                    LOGGER.info("Add the product have been sold out to the list to show the message to the user");
                }
            }
            if (!list.isEmpty()) {
                request.setAttribute("PRODUCT_SOLD_OUT", "The product " + list + "is sold out. Can't check out!");
                url = CHECKOUT;
                LOGGER.warn("Show message for user about the sold out product");
            } else {

                UserCheckOut userCheckOut = (UserCheckOut) session.getAttribute("CHECK_OUT_USER");

                TreeSet<ProductForCart> set = cart.getSet();

                UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");

                url = SENDMAIL;

                TblOrdersDAO daoOrder = new TblOrdersDAO();
                Random random = new Random();
                int orderID = random.nextInt(1000000000);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String date = dtf.format(now);

                boolean check = daoOrder.addOrder(orderID + "", user.getUserID(), date, cart.getTotalPrice());

                request.setAttribute("ORDER_ID", orderID);
                request.setAttribute("TOTAL", cart.getTotalPrice());

                TblUserCheckOutsDAO daoUserCheckOut = new TblUserCheckOutsDAO();

                daoUserCheckOut.addUserCheckOut(userCheckOut, orderID + "");
                if (check) {
                    TblOrderDetailsDAO detailDao = new TblOrderDetailsDAO();
                    TblProductsDAO daoProduct = new TblProductsDAO();
                    int newProductQuantity;
                    for (ProductForCart productForCart : set) {
                        detailDao.addOrderDetail("" + random.nextInt(1000000000), date, productForCart.getProductID(), productForCart.getProductPrice(), productForCart.getProductQuantity());
                        newProductQuantity = productForCart.getMaxQuantity() - productForCart.getProductQuantity();
                        if (newProductQuantity == 0) {
                            daoProduct.changeStatusOfProduct(productForCart.getProductID());
                            daoProduct.minusProductQuantity(productForCart.getProductID(), 0);
                        } else {
                            daoProduct.minusProductQuantity(productForCart.getProductID(), newProductQuantity);
                        }
                    }
                    cart.resetCart();
                    session.removeAttribute("CART");

                }
                LOGGER.info("Check out success, create new order, order detail, user infor check out to the database");
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
