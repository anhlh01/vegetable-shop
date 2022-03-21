/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.cart;

/**
 *
 * @author HP
 */
public class ProductForCart implements Comparable<ProductForCart>{
    private String productID;
    private String productName;
    private double productPrice;
    private String categoryID;
    private boolean status;
    private int productQuantity;
    private String productImage;
    private int maxQuantity;

    public ProductForCart(String productID, String productName, double productPrice, String categoryID, boolean status, int productQuantity, String productImage, int maxQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryID = categoryID;
        this.status = status;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
        this.maxQuantity = maxQuantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    @Override
    public String toString() {
        return "ProductForCart{" + "productID=" + productID + ", productName=" + productName + ", productPrice=" + productPrice + ", categoryID=" + categoryID + ", status=" + status + ", productQuantity=" + productQuantity + ", productImage=" + productImage + ", maxQuantity=" + maxQuantity + '}';
    }

    

    
    @Override
    public int compareTo(ProductForCart t) {
        if (productID.compareTo(t.getProductID())>0) {
            return 10;
        }
        else if (productID.compareTo(t.getProductID())<0) {
            return -10;
        }
        else {
            return 0;
        }
    }
    
}
