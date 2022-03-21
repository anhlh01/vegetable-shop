/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.tblOrders;

/**
 *
 * @author HP
 */
public class OrdersDTO implements Comparable<OrdersDTO>{
    private String orderID;
    private String userID;
    private String orderDate;
    private double orderTotal;

    public OrdersDTO() {
    }

    public OrdersDTO(String orderID, String userID, String orderDate, double orderTotal) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "TblOrdersDTO{" + "orderID=" + orderID + ", userID=" + userID + ", orderDate=" + orderDate + ", orderTotal=" + orderTotal + '}';
    }

    @Override
    public int compareTo(OrdersDTO t) {
        if(orderID.compareTo(t.orderID)>0){
            return 10;
        }else if(orderID.compareTo(t.orderID)<0){
            return -10;
        }
        return 0;
    }
    
    
}
