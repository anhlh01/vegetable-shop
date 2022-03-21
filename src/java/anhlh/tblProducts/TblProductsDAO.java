/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.tblProducts;

import anhlh.cart.ProductForCart;
import anhlh.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class TblProductsDAO implements Serializable {

    public ArrayList<ProductDTO> loadListProduct() throws SQLException {
        ArrayList<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, productPrice, productQuantity, categoryID, productImage, productDescription, status, productCreateDate "
                        + " FROM tblProducts";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double productPrice = rs.getDouble("productPrice");
                    int productQuantity = rs.getInt("productQuantity");
                    String categoryID = rs.getString("categoryID");
                    String productImage = rs.getString("productImage");
                    String productDescription = rs.getString("productDescription");
                    String productCreateDate = rs.getString("productCreateDate");
                    boolean status = rs.getBoolean("status");
                    list.add(new ProductDTO(productID, productName, productPrice, productQuantity, categoryID, productImage, productDescription, productCreateDate, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public ArrayList<ProductDTO> searchProductByCategory(String categoryID) throws SQLException {
        ArrayList<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, productPrice, productQuantity, productImage, productDescription, status, productCreateDate "
                        + " FROM tblProducts "
                        + " WHERE categoryID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, categoryID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double productPrice = rs.getDouble("productPrice");
                    int productQuantity = rs.getInt("productQuantity");
                    String productImage = rs.getString("productImage");
                    String productDescription = rs.getString("productDescription");
                    String productCreateDate = rs.getString("productCreateDate");
                    boolean status = rs.getBoolean("status");
                    list.add(new ProductDTO(productID, productName, productPrice, productQuantity, categoryID, productImage, productDescription, productCreateDate, status));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public ProductDTO searchProductByID(String productID) throws SQLException {
        ProductDTO product = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT categoryID, productName, productPrice, productQuantity, productImage, productDescription, status, productCreateDate "
                        + " FROM tblProducts "
                        + " WHERE productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String productName = rs.getString("productName");
                    double productPrice = rs.getDouble("productPrice");
                    int productQuantity = rs.getInt("productQuantity");
                    String productImage = rs.getString("productImage");
                    String productDescription = rs.getString("productDescription");
                    String productCreateDate = rs.getString("productCreateDate");
                    String categoryID = rs.getString("categoryID");
                    boolean status = rs.getBoolean("status");
                    product = new ProductDTO(productID, productName, productPrice, productQuantity, categoryID, productImage, productDescription, productCreateDate, status);
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return product;
    }
    
    public ProductForCart searchProductByIDForCart(String productID, int productQuantity) throws SQLException {
        ProductForCart product = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT categoryID, productName, productPrice, productQuantity, productImage, productDescription, status, productCreateDate "
                        + " FROM tblProducts "
                        + " WHERE productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String productName = rs.getString("productName");
                    double productPrice = Double.parseDouble(rs.getString("productPrice"));
                    String productImage = rs.getString("productImage");
                    String productDescription = rs.getString("productDescription");
                    String productCreateDate = rs.getString("productCreateDate");
                    String categoryID = rs.getString("categoryID");
                    boolean status = rs.getBoolean("status");
                    int maxQuantity = rs.getInt("productQuantity");
                        product = new ProductForCart(productID, productName, productPrice, categoryID, status, productQuantity, productImage, maxQuantity);

                    
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return product;
    }

    public ProductForCart searchProductByIDForCart(String productID) throws SQLException {
        ProductForCart product = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT categoryID, productName, productPrice, productQuantity, productImage, productDescription, status, productCreateDate "
                        + " FROM tblProducts "
                        + " WHERE productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String productName = rs.getString("productName");
                    double productPrice = Double.parseDouble(rs.getString("productPrice"));
                    String productImage = rs.getString("productImage");
                    String productDescription = rs.getString("productDescription");
                    String productCreateDate = rs.getString("productCreateDate");
                    String categoryID = rs.getString("categoryID");
                    boolean status = rs.getBoolean("status");
                    int maxQuantity = rs.getInt("productQuantity");

                        product = new ProductForCart(productID, productName, productPrice, categoryID, status, 1, productImage, maxQuantity);
                
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return product;
    }

    public ArrayList<ProductDTO> searchProductByName(String productName) throws SQLException {
        ArrayList<ProductDTO> set = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT categoryID,productName , productID, productPrice, productQuantity, productImage, productDescription, status, productCreateDate "
                        + " FROM tblProducts "
                        + " WHERE productName like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + productName + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    productName = rs.getString("productName");
                    double productPrice = rs.getDouble("productPrice");
                    int productQuantity = rs.getInt("productQuantity");
                    String productImage = rs.getString("productImage");
                    String productID = rs.getString("productID");
                    String productDescription = rs.getString("productDescription");
                    String productCreateDate = rs.getString("productCreateDate");
                    String categoryID = rs.getString("categoryID");
                    boolean status = rs.getBoolean("status");
                    set.add(new ProductDTO(productID, productName, productPrice, productQuantity, categoryID, productImage, productDescription, productCreateDate, status));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return set;
    }

    public boolean minusProductQuantity(String productID, int productQuantity) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = " UPDATE tblProducts "
                        + " SET productQuantity = ? "
                        + " WHERE productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productQuantity);
                stm.setString(2, productID);
                rs = stm.executeUpdate();
                if(rs>0){
                    check = true;
                }
            }
        } catch (Exception e) {
        }finally{
            if(stm != null)stm.close();
            if(con != null)con.close();            
        }
        return check;
    }
    
    public boolean changeStatusOfProduct(String productID) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = " UPDATE tblProducts "
                        + " SET status = 0 "
                        + " WHERE productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeUpdate();
                if(rs>0){
                    check = true;
                }
            }
        } catch (Exception e) {
        }finally{
            if(stm != null)stm.close();
            if(con != null)con.close();            
        }
        return check;
    }
    
    public boolean addProduct(ProductDTO product) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = "INSERT INTO tblProducts (productID, productName, productPrice, productQuantity, categoryID, productImage, productDescription, status, productCreateDate) "
                        + " VALUES (?,?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, product.getProductID());
                stm.setString(2, product.getProductName());
                stm.setDouble(3, product.getProductPice());
                stm.setInt(4, product.getProductQuantity());
                stm.setString(5, product.getCategoryID());
                stm.setString(6, product.getProductImage());
                stm.setString(7, product.getProductDescription());
                stm.setBoolean(8, product.isStatus());
                stm.setString(9, product.getProductCreateDate());
                rs = stm.executeUpdate();
                if(rs>0){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(stm!=null)stm.close();
            if(con!=null)con.close();
        }
        return check;
    }
    
    public boolean editProduct(ProductDTO product) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = "UPDATE tblProducts "
                        + " SET productName=?, productPrice=?, productQuantity=?, categoryID=?, productImage=?, productDescription=?, status=? "
                        + " WHERE productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(8, product.getProductID());
                stm.setString(1, product.getProductName());
                stm.setDouble(2, product.getProductPice());
                stm.setInt(3, product.getProductQuantity());
                stm.setString(4, product.getCategoryID());
                stm.setString(5, product.getProductImage());
                stm.setString(6, product.getProductDescription());
                stm.setBoolean(7, product.isStatus());
                rs = stm.executeUpdate();
                if(rs>0){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(stm!=null)stm.close();
            if(con!=null)con.close();
        }
        return check;
    }
    
    public boolean deleteProduct(String id) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = "DELETE "
                        + " FROM tblProducts "
                        + " WHERE productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeUpdate();
                if(rs>0){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(stm!=null)stm.close();
            if(con!=null)con.close();
        }
        return check;
    }
    
    public static void main(String[] args) throws SQLException {
        TblProductsDAO dao = new TblProductsDAO();
//        System.out.println(dao.searchProductByID("10"));
        System.out.println(dao.deleteProduct("1"));
    }
}
