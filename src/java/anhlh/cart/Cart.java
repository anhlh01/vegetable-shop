/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.cart;

import anhlh.tblProducts.ProductDTO;
import anhlh.tblProducts.TblProductsDAO;
import java.sql.SQLException;
import java.util.TreeSet;

/**
 *
 * @author HP
 */
public class Cart {

    TreeSet<ProductForCart> set = new TreeSet<>();

    public TreeSet<ProductForCart> getSet() {
        return set;
    }

    public void setSet(TreeSet<ProductForCart> set) {
        this.set = set;
    }

    public void add(ProductForCart product) {
        for (ProductForCart productForCart : set) {
            if (productForCart.getProductID().equals(product.getProductID())) {
                productForCart.setProductQuantity(productForCart.getProductQuantity() + product.getProductQuantity());
            }
        }
        set.add(product);
    }

    public void remove(ProductForCart product) {
        for (ProductForCart productDTO : set) {
            if (productDTO.getProductID().equals(product.getProductID())) {
                set.remove(productDTO);
            }
        }
    }

    public void changeQuantity(String productID, int quantity) {
        for (ProductForCart productForCart : set) {
            if (productForCart.getProductID().equals(productID)) {
                productForCart.setProductQuantity(quantity);
            }
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (ProductForCart productForCart : set) {
            total += productForCart.getProductPrice() * productForCart.getProductQuantity();
        }
        return total;
    }
    
    public void resetCart(){
        set = new TreeSet<>();
    }

    public static void main(String[] args) throws SQLException {
        Cart a = new Cart();
        TreeSet<ProductForCart> set = a.getSet();
        TblProductsDAO dao = new TblProductsDAO();
        ProductDTO product1 = dao.searchProductByID("3");
        ProductDTO product2 = dao.searchProductByID("2");
        ProductDTO product3 = dao.searchProductByID("1");
    }
}
