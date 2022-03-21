/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.controller;

import anhlh.googleLogin.GooglePojo;
import anhlh.googleLogin.GoogleUtils;
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
public class LoginGoogleController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final Logger LOGGER = Logger.getLogger(LoginGoogleController.class);
    private static final String LOGIN = "login.jsp";
    private static final String INDEX = "index.jsp";

    public LoginGoogleController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String url = null;
        try {
            if (code == null || code.isEmpty()) {
                url = LOGIN;
                LOGGER.warn("Code is null or code is empty. Can not login the user");
            } else {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                String id = googlePojo.getId();
                String email = googlePojo.getEmail();
                UserDTO user = new UserDTO(id, email, "", "", "", "GG", email, "", true);
                TblUsersDAO dao = new TblUsersDAO();
                UserDTO old_user = dao.searchUser1(id);
                if (old_user == null) {
                    dao.addUser(googlePojo);
                }
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", user);
                if (session.getAttribute("CHECK_OUT") != null) {
                    url = (String) session.getAttribute("CHECK_OUT");
                } else {
                    if (old_user != null) {
                        if (old_user.isStatus()) {
                            url = INDEX;
                        } else {
                            url = LOGIN;
                        }
                    }else{
                        url = INDEX;
                    }
                }
                LOGGER.info("Login user success");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            response.sendRedirect(url);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
