/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.tblUserCheckOuts;

import anhlh.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class TblUserCheckOutsDAO implements Serializable {

    public boolean addUserCheckOut(UserCheckOut user, String orderID) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblUserCheckOuts (orderID, firstName, lastName, address, city, postcode, phone, email) "
                        + "  VALUES (?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, user.getFirstName());
                stm.setString(3, user.getLastName());
                stm.setString(4, user.getAddress());
                stm.setString(5, user.getCity());
                stm.setString(6, user.getPostcode());
                stm.setString(7, user.getPhone());
                stm.setString(8, user.getEmail());
                rs = stm.executeUpdate();
                if(rs>0){
                    check = true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
}
