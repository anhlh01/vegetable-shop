/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.tblProducts;

/**
 *
 * @author HP
 */
public class ProductDTO implements Comparable<ProductDTO>{
    private String productID;
    private String productName;
    private double productPice;
    private int productQuantity;
    private String categoryID;
    private String productImage;
    private String productDescription;
    private String productCreateDate;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, double productPice, int productQuantity, String categoryID, String productImage, String productDescription, String productCreateDate, boolean status) {
        this.productID = productID;
        this.productName = productName;
        this.productPice = productPice;
        this.productQuantity = productQuantity;
        this.categoryID = categoryID;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productCreateDate = productCreateDate;
        this.status = status;
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

    public double getProductPice() {
        return productPice;
    }

    public void setProductPice(double productPice) {
        this.productPice = productPice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCreateDate() {
        return productCreateDate;
    }

    public void setProductCreateDate(String productCreateDate) {
        this.productCreateDate = productCreateDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", productName=" + productName + ", productPice=" + productPice + ", productQuantity=" + productQuantity + ", categoryID=" + categoryID + ", productImage=" + productImage + ", productDescription=" + productDescription + ", productCreateDate=" + productCreateDate + ", status=" + status + '}';
    }

    @Override
    public int compareTo(ProductDTO t) {
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
