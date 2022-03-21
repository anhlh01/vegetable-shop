/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author HP
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String SHOWPRODUCT = "ShowProductController";
    private static final String LOADPRODUCT = "LoadAllProductController";
    private static final String LOAD_A_PRODUCT = "LoadAProductController";
    private static final String ADD_TO_CART = "AddToCartController";
    private static final String DELETE_TO_CART = "DeleteFromCartController";
    private static final String PROCCESS_CHECKOUT = "ProccessCheckOutController";
    private static final String CHECKOUT = "CheckOutController";
    private static final String CHANGE_QUANTITY = "ChangeProductQuantityController";
    private static final String SEARCH = "SearchProductController";
    private static final String VN_PAY_CHECKOUT = "VNPayCheckOutController";
    private static final String SIGNUP = "SignUpController";
    private static final String ADD = "AddAProductController";
    private static final String EDIT = "EditProductController";
    private static final String DELETE = "DeleteProductController";
    private static final String EDIT_USER = "EditUserController";
    private static final String DELETE_USER = "DeleteUserController";
    static final Logger LOGGER = Logger.getLogger(MainController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = "index.jsp";
            } else {
                switch (action) {
                    case "Sign In":
                        url = LOGIN;
                        break;
                    case "Logout":
                        url = LOGOUT;
                        break;
                    case "Load a type":
                        url = SHOWPRODUCT;
                        break;
                    case "Load All Product":
                        url = LOADPRODUCT;
                        break;
                    case "Show Product":
                        url = LOAD_A_PRODUCT;
                        break;
                    case "Add To Cart":
                        url = ADD_TO_CART;
                        break;
                    case "Delete From Cart":
                        url = DELETE_TO_CART;
                        break;
                    case "Proccess Check Out":
                        url = PROCCESS_CHECKOUT;
                        break;
                    case "Check Out":
                        url = CHECKOUT;
                        break;
                    case "Change quantity":
                        url = CHANGE_QUANTITY;
                        break;
                    case "Search Product":
                        url = SEARCH;
                        break;
                    case "VNPay":
                        url = VN_PAY_CHECKOUT;
                        break;
                    case "Sign Up":
                        url = SIGNUP;
                        break;
                    case "Add Product":
                        url = ADD;
                        break;
                    case "Edit Product":
                        url = EDIT;
                        break;
                    case "Delete Product":
                        url = DELETE;
                        break;
                    case "Edit User":
                        url = EDIT_USER;
                        break;
                    case "Delete User":
                        url = DELETE_USER;
                        break;
                    default:
                        break;
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
