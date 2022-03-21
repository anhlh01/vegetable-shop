/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.controller;

import anhlh.tblProducts.ProductDTO;
import anhlh.tblProducts.TblProductsDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class EditProductController extends HttpServlet {

    private static final String SUCCESS = "admin.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String productID = request.getParameter("productID");
            String category = request.getParameter("category");
            String productName = request.getParameter("productName");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            String createDate = request.getParameter("createDate");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String availableStatus = request.getParameter("availableStatus");
            boolean status;
            if(availableStatus.equals("AV")&&quantity>0){
                status = true;
            }else{
                status = false;
            }
            ProductDTO product = new ProductDTO(productID, productName, price, quantity, category, image, description, createDate, status);
            TblProductsDAO dao = new TblProductsDAO();
            dao.editProduct(product);
           
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
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
