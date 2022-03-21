/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.tblCategory;

import anhlh.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author HP
 */
public class TblCategoryDAO implements Serializable {
    

    public ArrayList<Category> getArray() throws SQLException {
        ArrayList<Category> array = new ArrayList<>();       
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT categoryID, categoryName, categoryImage "
                        + " FROM tblCategory ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String categoryImage = rs.getString("categoryImage");
                    String categoryName =rs.getString("categoryName"); 
                    String categoryID =rs.getString("categoryID"); 
                    Category a = new Category(categoryID, categoryName, categoryImage);
                    array.add(a);
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
        return array;
    }
    public HashMap<String, String> loadCategory() throws SQLException {
        HashMap<String, String> list = new HashMap<>();
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT categoryID, categoryName, categoryImage "
                        + " FROM tblCategory ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String categoryImage = rs.getString("categoryImage");
                    String categoryName =rs.getString("categoryName"); 
                    String categoryID =rs.getString("categoryID"); 
                    list.put(categoryName, categoryID);
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
    
    public String getCategoryName(String id) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = "SELECT categoryName "
                        + "FROM tblCategory "
                        + "WHERE categoryID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if(rs.next()){
                    return rs.getString("categoryName");
                }
            }
        } catch (Exception e) {
        }finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(con!=null)con.close();
        }
        return "sai roi";
    }

    public static void main(String[] args) throws SQLException {
        TblCategoryDAO dao = new TblCategoryDAO();
//        HashMap<String, String> list=dao.loadCategory();
//        System.out.println(list.get("Các Loại Hạt"));
        System.out.println(dao.getCategoryName("1"));
    }
}
