/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.tblOrderDetails;

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
public class TblOrderDetailsDAO implements Serializable{
    public boolean addOrderDetail(String detailID, String orderID, String productID, double detailPrice, int detailQuantity) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = " INSERT INTO tblOrderDetails (detailID, orderID, productID, detailPrice, detailQuantity)"
                        + " VALUES (?, ?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, detailID);
                stm.setString(2, orderID);
                stm.setString(3, productID);
                stm.setDouble(4, detailPrice);
                stm.setInt(5, detailQuantity);
                rs = stm.executeUpdate();
                if(rs>0){
                    check = true;
                }
            }
        } catch (Exception e) {
        }finally{
            if(stm!=null)stm.close();
            if(con!=null)con.close();
        }
        return check;
    }
    
    public boolean findOrderId(String id) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = " SELECT orderID"
                        + " FROM tblOrderDetails"
                        + " WHERE productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if(rs.next()){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs!=null)stm.close();
            if(stm!=null)stm.close();
            if(con!=null)con.close();
        }
        return check;
    }
}
