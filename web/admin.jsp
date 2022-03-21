<%-- 
    Document   : admin
    Created on : Sep 30, 2021, 10:34:26 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="anhlh.tblCategory.TblCategoryDAO"%>
<%@page import="anhlh.tblCategory.Category"%>
<%@page import="anhlh.tblProducts.TblProductsDAO"%>
<%@page import="anhlh.tblProducts.ProductDTO"%>
<%@page import="anhlh.tblUsers.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Admin Product Page</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/style-admin.css">
        <link rel="stylesheet" href="css/navbar.css">

    </head>
    <body>

        <nav class="navbar navbar-expand-custom navbar-mainbg">
            <a class="navbar-brand navbar-logo" href="#">Navbar</a>
            <button
                class="navbar-toggler"
                type="button"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
                >
                <i class="fas fa-bars text-white"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <div class="hori-selector">
                        <div class="left"></div>
                        <div class="right"></div>
                    </div>
                    <li class="nav-item">
                        <a class="nav-link" href="admin.jsp"
                           >Product Management</a
                        >
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="UserManager.jsp"
                           >User Management</a
                        >
                    </li>

                </ul>
            </div>
        </nav>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Manage <b>Product</b></h2>
                            </div>
                            <div class="col-sm-6">
                                <a href="#logoutEmployeeModal" class="btn btn-primary" data-toggle="modal"><i class="fas fa-sign-out-alt"></i> <span>Logout</span></a>
                                <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Product ID</th>
                                <th>Product Name</th>
                                <th>Category Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <%
                            TblProductsDAO dao = new TblProductsDAO();
                            List<ProductDTO> productList = dao.loadListProduct();
                            TblCategoryDAO d = new TblCategoryDAO();

                            if (productList != null) {
                                for (ProductDTO product : productList) {
                        %>
                        <tbody>
                            <tr>
                                <td><%= product.getProductID()%></td>
                                <td><%= product.getProductName()%></td>
                                <td><%= d.getCategoryName(product.getCategoryID())%></td>
                                <td><%= product.getProductPice()%></td>
                                <td><%= product.getProductQuantity()%></td>
                                <td>
                                    <%
                                        if (product.isStatus()) {
                                    %>
                                    <span class="badge badge-success">Available</span>
                                    <%
                                    } else {
                                    %>
                                    <span class="badge badge-secondary">Not Available</span>
                                    <%
                                        }
                                    %>    
                                </td>
                                <td>
                                    <a href="#editEmployeeModal" class="edit edit-product" 
                                       data-id="<%= product.getProductID()%>" 
                                       data-name="<%= product.getProductName()%>"
                                       data-category="<%= product.getCategoryID()%>"
                                       data-price="<%= product.getProductPice()%>"
                                       data-discount="0.00"
                                       data-description="<%= product.getProductDescription()%>"
                                       data-image="<%= product.getProductImage()%>"
                                       data-quantity="<%= product.getProductQuantity()%>"
                                       data-available="<%= product.isStatus()%>"
                                       data-condition="<%= product.isStatus()%>"
                                       data-date="date"
                                       data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    <a href="#deleteEmployeeModal" data-id="<%= product.getProductID()%>" class="delete delete-product" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                            </tr>
                        </tbody>
                        <%
                                }
                            }
                        %>
                    </table>
                    
                </div>
            </div>        
        </div>
        <!-- Add Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="MainController" accept-charset="UTF-8">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Product ID</label>
                                <input name="productID" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Category Name</label>
                                <select name="category" method="POST" class="form-control" id="exampleFormControlSelect1">
                                    <%
                                        ArrayList<Category> categoryName = d.getArray();

                                        if (categoryName != null) {
                                            for (Category category : categoryName) {
                                    %>
                                    <option id="category-option" value="<%= category.getCategoryID()%>"><%= category.getCategoryName()%></option>
                                    <%
                                            }
                                        }

                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Product Name</label>
                                <input name="productName" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" step="0.01" min="0" type="number" class="form-control" required>
                            </div>
                      
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" type="text" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Product Image</label>
                                <input name="image" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" min="0" type="number" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Available Status</label>
                                <select name="availableStatus" class="form-control" id="exampleFormControlSelect1">
                                    <option value="AV">Available</option>
                                    <option value="NAV">Not Available</option>
                                </select>
                            </div>
                           
                            <div class="form-group">
                                <%                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    LocalDateTime now = LocalDateTime.now();
                                    String currentDate = dtf.format(now);

                                    if (currentDate != null) {
                                %>
                                <label>Date</label>
                                <input id="date" name="createDate" type="text" class="form-control" value="<%= currentDate%>" readonly>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" name="action" value="Add Product">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="editEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="MainController"  method="POST" accept-charset="UTF-8">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Employee</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Product ID</label>
                                <input id="product-id" name="productID" type="text" class="form-control" readonly="">
                            </div>
                            <div class="form-group">
                                <label>Category Name</label>
                                <select name="category" class="form-control" id="exampleFormControlSelect1">
                                    <%
                                        if (categoryName != null) {
                                            for (Category category : categoryName) {
                                    %>
                                    <option id="category-option" value="<%= category.getCategoryID()%>"><%= category.getCategoryName()%></option>
                                    <%
                                            }
                                        }

                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Product Name</label>
                                <input id="product-name" name="productName" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input id="product-price" step="0.01" name="price" min="0" type="number" class="form-control" required>
                            </div>
                          
                            <div class="form-group">
                                <label>Description</label>
                                <textarea id="product-description" name="description" rows="7" type="number" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Product Image</label>
                                <input id="product-image" name="image" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input id="product-quantity" name="quantity" min="0" type="number" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Available Status</label>
                                <select name="availableStatus" class="form-control" id="exampleFormControlSelect1">
                                    <option value="AV">Available</option>
                                    <option value="NAV">Not Available</option>
                                </select>
                            </div>
            
                            <div class="form-group">
                                <label>Date</label>
                                <input id="date" name="createDate" type="text" class="form-control" readonly>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" name="action" value="Edit Product">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Logout Modal HTML -->
        <div id="logoutEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="MainController">
                        <div class="modal-header">						
                            <h4 class="modal-title">Logout</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <p>Are you sure you want to logout?</p>
                            <p class="text-warning"><small>This action cannot be undone.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" name="action" value="Logout">

                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- End Logout Modal HTML -->
        <!-- Delete Modal HTML -->
        <div id="deleteEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="MainController" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Delete</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <p>Are you sure you want to delete this product?</p>
                            <p class="text-warning"><small>This action cannot be undone.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="hidden" name="delete-id" id="delete-id">
                            <input type="submit" class="btn btn-danger" name="action" value="Delete Product">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- End Logout Modal HTML -->
        <div id="addUserErrorModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">						
                            <h4 class="modal-title">Error</h4>
                        </div>
                        <div class="modal-body">					
                            <p>Error when adding new user</p>
                            <p class="text-warning">
                                <small>This action cannot be undone.</small>
                            </p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="modal hide fade" id="myModal">
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h3>Modal header</h3>
            </div>
            <div class="modal-body">
                <p>One fine body…</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn">Close</a>
                <a href="#" class="btn btn-primary">Save changes</a>
            </div>
        </div>


        <%
            String error_message = (String)request.getAttribute("ERROR_MESSAGE");
            
            if (error_message != null) {
                
        %>
        <!--popup-->  
        <div class="bts-popup" role="alert">
            <div class="bts-popup-container">
              
                <p><%= error_message %></p>
                <div class="bts-popup-button"></div>
                <a href="#0" class="bts-popup-close img-replace">
                    <i class="fas fa-times"></i>
                </a>
            </div>
        </div>
        <%
            }
        %>
<!--                end popup-->
        <script src="./js/app-admin.js"></script>
        <script
            src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"
        ></script>
        <script>

            function test() {
                var tabsNewAnim = $("#navbarSupportedContent");
                var selectorNewAnim = $("#navbarSupportedContent").find("li").length;
                var activeItemNewAnim = tabsNewAnim.find(".active");
                var activeWidthNewAnimHeight = activeItemNewAnim.innerHeight();
                var activeWidthNewAnimWidth = activeItemNewAnim.innerWidth();
                var itemPosNewAnimTop = activeItemNewAnim.position();
                var itemPosNewAnimLeft = activeItemNewAnim.position();
                $(".hori-selector").css({
                    top: itemPosNewAnimTop.top + "px",
                    left: itemPosNewAnimLeft.left + "px",
                    height: activeWidthNewAnimHeight + "px",
                    width: activeWidthNewAnimWidth + "px",
                });
                $("#navbarSupportedContent").on("click", "li", function (e) {
                    $("#navbarSupportedContent ul li").removeClass("active");
                    $(this).addClass("active");
                    var activeWidthNewAnimHeight = $(this).innerHeight();
                    var activeWidthNewAnimWidth = $(this).innerWidth();
                    var itemPosNewAnimTop = $(this).position();
                    var itemPosNewAnimLeft = $(this).position();
                    $(".hori-selector").css({
                        top: itemPosNewAnimTop.top + "px",
                        left: itemPosNewAnimLeft.left + "px",
                        height: activeWidthNewAnimHeight + "px",
                        width: activeWidthNewAnimWidth + "px",
                    });
                });
            }
            $(document).ready(function () {
                setTimeout(function () {
                    test();
                });
            });
            $(window).on("resize", function () {
                setTimeout(function () {
                    test();
                }, 500);
            });
            $(".navbar-toggler").click(function () {
                $(".navbar-collapse").slideToggle(300);
                setTimeout(function () {
                    test();
                });
            });

            // --------------add active class-on another-page move----------
            jQuery(document).ready(function ($) {
                // Get current path and find target link
                var path = window.location.pathname.split("/").pop();

                // Account for home page with empty path
                if (path == "") {
                    path = "index.jsp";
                }

                var target = $('#navbarSupportedContent ul li a[href="' + path + '"]');
                // Add active class to target link
                target.parent().addClass("active");
            });
        </script>
        <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>
    </body>
</html>


