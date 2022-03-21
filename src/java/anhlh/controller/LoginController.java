/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.controller;

import anhlh.tblUsers.TblUsersDAO;
import anhlh.tblUsers.UserDTO;
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
public class LoginController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final String ERROR = "login.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "index.jsp";
    private static final String AD = "AD";
    private static final String US = "US";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");

            TblUsersDAO dao = new TblUsersDAO();
            UserDTO user = dao.checkLogin(userID, password);
            System.out.println(user);

            if (userID.isEmpty() || password.isEmpty()) {
                url = "login.jsp";
                LOGGER.warn("User not fill in userID of password");

            } else {
                if (user == null) {
                    request.setAttribute("ERROR", "Invalid User ID or Password!");
                    LOGGER.info("User not found");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", user);
                    String roleID = user.getRoleID();
                    boolean status = user.isStatus();
                    if (!status) {
                        url = ERROR;
                    } else {
                        if (AD.equals(roleID)) {
                            url = ADMIN;
                            LOGGER.info("Login success to the Admin page");
                        } else if (US.equals(roleID) && (String) session.getAttribute("CHECK_OUT") == null) {
                            url = USER;
                            LOGGER.info("Login success to the Index page");
                        } else if ((String) session.getAttribute("CHECK_OUT") != null) {
                            url = (String) session.getAttribute("CHECK_OUT");
                            session.removeAttribute("CHECK_OUT");
                        }
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
