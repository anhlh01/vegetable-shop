/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.tblUsers;

import anhlh.googleLogin.GooglePojo;
import anhlh.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class TblUsersDAO implements Serializable {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = " SELECT userID, userName, userAddress, userPhone, password, roleID, email, createDate, status  "
                        + " FROM tblUsers "
                        + " WHERE userID = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("userName");
                    String userAddress = rs.getString("userAddress");
                    String userPhone = rs.getString("userPhone");
                    String roleID = rs.getString("roleID");
                    String email = rs.getString("email");
                    String createDate = rs.getString("createDate");
                    boolean status = rs.getBoolean("status");
                    user = new UserDTO(userID, userName, userAddress, userPhone, password, roleID, email, createDate, status);
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
        return user;
    }

    public boolean searchUser(String userID) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            String sql = " SELECT userID"
                    + " FROM tblUsers "
                    + " WHERE userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, userID);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
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
        return check;
    }
    
    public UserDTO searchUser1(String userID) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            String sql = " SELECT userID, status"
                    + " FROM tblUsers "
                    + " WHERE userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, userID);
            rs = stm.executeQuery();
            if (rs.next()) {
                user = new UserDTO();
                user.setStatus(rs.getBoolean("status"));
                user.setUserID(userID);
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
        return user;
    }

    public ArrayList<String> readAllUser() throws SQLException {
        ArrayList<String> users = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT userID FROM tblUsers";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    users.add(rs.getString("userID"));
                    
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
        return users;
    }
    
    public ArrayList<UserDTO> loadAllUser() throws SQLException {
        ArrayList<UserDTO> users = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, userName, userAddress, userPhone, password, roleID, email, createDate, status "
                        + " FROM tblUsers";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    users.add(new UserDTO(rs.getString("userID"), rs.getString("userName"), rs.getString("userAddress"), rs.getString("userPhone"), rs.getString("password"), rs.getString("roleID"), rs.getString("email"), rs.getString("createDate"), rs.getBoolean("status")));
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
        return users;
    }

    public boolean addUser(GooglePojo user) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblUsers (userID, userName, userAddress, userPhone, password, roleID, email, createDate, status ) "
                        + " VALUES(?,?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getId());
                stm.setString(2, user.getEmail());
                stm.setString(3, "");
                stm.setString(4, "");
                stm.setString(5, "");
                stm.setString(6, "GG");
                stm.setString(7, user.getEmail());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate now = LocalDate.now();
                String date = dtf.format(now);
                stm.setString(8, date);
                stm.setInt(9, 1);
                rs = stm.executeUpdate();
                if (rs > 0) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean addUserBySignUp(UserDTO user) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblUsers (userID, userName, userAddress, userPhone, password, roleID, email, createDate, status ) "
                        + " VALUES(?,?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getUserID());
                stm.setString(3, user.getUserAddress());
                stm.setString(4, user.getUserPhone());
                stm.setString(5, user.getPassword());
                stm.setString(6, user.getRoleID());
                stm.setString(7, user.getEmail());
                stm.setString(8, user.getCreateDate());
                stm.setInt(9, 1);
                rs = stm.executeUpdate();
                if (rs > 0) {
                    check = true;
                }
            }
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
    
    public boolean editUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = " UPDATE tblUsers"
                        + " SET userName = ?, userAddress = ?, userPhone= ?, password= ?, roleID= ?, email= ?, createDate= ?, status= ?  "
                        + " WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getUserName());
                stm.setString(2, user.getUserAddress());
                stm.setString(3, user.getUserPhone());
                stm.setString(4, user.getPassword());
                stm.setString(5, user.getRoleID());
                stm.setString(6, user.getEmail());
                stm.setString(7, user.getCreateDate());
                stm.setBoolean(8, user.isStatus());
                stm.setString(9, user.getUserID());
                rs = stm.executeUpdate();
                if(rs>0){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    
    public boolean disableUser(String id) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            con = DBUtil.makeConnection();
            if(con!=null){
                String sql = "UPDATE tblUsers "
                        + " SET status = ? "
                        + " WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setString(2, id);
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

    public static void main(String[] args) throws SQLException {
        TblUsersDAO a = new TblUsersDAO();
        System.out.println(a.loadAllUser());
    }
}
