/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.tblOrderDetails;

/**
 *
 * @author HP
 */
public class OrderDetailsDTO {
    private String detailID;
    private String orderID;
    private String productID;
    private double detailPrice;
    private int detailQuantity;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String detailID, String orderID, String productID, double detailPrice, int detailQuantity) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.productID = productID;
        this.detailPrice = detailPrice;
        this.detailQuantity = detailQuantity;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(double detailPrice) {
        this.detailPrice = detailPrice;
    }

    public int getDetailQuantity() {
        return detailQuantity;
    }

    public void setDetailQuantity(int detailQuantity) {
        this.detailQuantity = detailQuantity;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" + "detailID=" + detailID + ", orderID=" + orderID + ", productID=" + productID + ", detailPrice=" + detailPrice + ", detailQuantity=" + detailQuantity + '}';
    }
    
}
