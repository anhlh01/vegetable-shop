/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.tblUsers;

/**
 *
 * @author HP
 */
public class UserDTO {
    private String userID;
    private String userName;
    private String userAddress;
    private String userPhone;
    private String password;
    private String roleID;
    private String email;
    private String createDate;
    private boolean status;

    public UserDTO() {
    }
    
    public UserDTO(String userID, String password) {
        this.userID = userID;
        this.password = password;
        userName = "";
        userAddress = "";
        userPhone = "";
        roleID = "";
        email= "";
        status = true;
        createDate = "";
    }

    public UserDTO(String userID, String userName, String userAddress, String userPhone, String password, String roleID, String email, String createDate, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.password = password;
        this.roleID = roleID;
        this.email = email;
        this.createDate = createDate;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", userName=" + userName + ", userAddress=" + userAddress + ", userPhone=" + userPhone + ", password=" + password + ", roleID=" + roleID + ", email=" + email + ", createDate=" + createDate + ", status=" + status + '}';
    }
    
}
