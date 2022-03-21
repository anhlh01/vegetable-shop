/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.tblOrders;

import anhlh.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class TblOrdersDAO implements Serializable{
    public boolean addOrder(String orderID, String userID,String orderDate,double orderTotal) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        boolean check = false;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = "INSERT INTO tblOrders (orderID, userID, orderDate, orderTotal) "
                        + " VALUES (?, ?, ?, ? ) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, userID);
                stm.setString(3, orderDate);
                stm.setDouble(4, orderTotal);
                rs = stm.executeUpdate();
                if(rs>0){
                    check = true;
                }
            }
        } catch (Exception e) {
        }finally{
            if (stm!=null)stm.close();
            if (con!=null)con.close();
        }
        return check;
    }
    public OrdersDTO findOrder(String orderID) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrdersDTO order = null;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = "SELECT orderID, userID, orderDate, orderTotal "
                        + " FROM tblOrders "
                        + " WHERE orderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                rs = stm.executeQuery();
                if(rs.next()){
                    String userID = rs.getString("orderID");
                    String orderDate = rs.getString("orderDate");
                    double orderTotal = rs.getDouble("orderTotal");
                    order = new OrdersDTO(orderID, userID, orderDate, orderTotal);
                }
            }
        } catch (Exception e) {
        }finally{
            if (stm!=null)stm.close();
            if (con!=null)con.close();
        }
        return order;
    }
    
    public boolean deleteOrder(String orderID) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        boolean check = false;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = "DELETE FROM tblOrders "
                        + " WHERE orderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                rs = stm.executeUpdate();
                if(rs>0){
                    check = true;
                }
            }
        } catch (Exception e) {
        }finally{
            if (stm!=null)stm.close();
            if (con!=null)con.close();
        }
        return check;
    }
    public static void main(String[] args) throws SQLException {
        TblOrdersDAO dao = new TblOrdersDAO();
        boolean a = dao.addOrder("1", "minhvd", "2020-1-1", 120);
        System.out.println(a);
        System.out.println(dao.findOrder("1"));
        dao.deleteOrder("1");
    }
}
