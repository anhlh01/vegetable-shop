/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.controller;

import anhlh.cart.Cart;
import anhlh.cart.ProductForCart;
import static anhlh.controller.ProccessCheckOutController.LOGGER;
import anhlh.tblProducts.ProductDTO;
import anhlh.tblProducts.TblProductsDAO;
import anhlh.tblUserCheckOuts.UserCheckOut;
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
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String CHECKOUT = "checkout.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String VNPAY = "vnpay_index.jsp";
    private static final String SUCCESS = "CheckOutSuccessController";
    static final Logger LOGGER = Logger.getLogger(CheckOutController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = CHECKOUT;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String postcode = request.getParameter("postcode");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            UserCheckOut userCheckOut = new UserCheckOut(firstName, lastName, address, postcode, phone, email);

            session.setAttribute("CHECK_OUT_USER", userCheckOut);

            ArrayList<ProductForCart> list = new ArrayList<>();

            TblProductsDAO dao = new TblProductsDAO();
            ProductDTO product;

            if (cart != null) {
                for (ProductForCart productForCart : cart.getSet()) {
                    product = dao.searchProductByID(productForCart.getProductID());
                    if (product.getProductQuantity() < productForCart.getProductQuantity()) {
                        list.add(productForCart);
                        LOGGER.info("Add the product have been sold out to the list to show the message to the user");
                    }
                }
                if (!list.isEmpty()) {
                    String listSoldOut = "";
                    for (ProductForCart productForCart : list) {
                        listSoldOut += " "+productForCart.getProductName();
                        cart.remove(productForCart);
                    }
                    if(cart.getSet().isEmpty()){
                        cart.resetCart();
                        session.removeAttribute("CART");
                    }
                    request.setAttribute("PRODUCT_SOLD_OUT", "The product " + listSoldOut + "is sold out. Can't check out!");
                    url = CHECKOUT;
                    LOGGER.warn("Show message for user about the sold out product");
                } else {
                    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                    if (user == null) {
                        url = LOGIN;
                    } else {
                        if (user.getRoleID().equals("AD")) {
                            url = ADMIN;
                        }
                    }
                    String payMethod = request.getParameter("optradio");
                    if (payMethod.equals("VNPay")) {
                        url = VNPAY;
                        LOGGER.info("User choose the VNPay for Payment Method");
                    } else {
                        url = SUCCESS;
                        LOGGER.info("User choose the normal way for Payment Method");
                    }
                }
            } else {
                LOGGER.info("CART IS NULL. CAN'T NOT CHECK OUT");
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
