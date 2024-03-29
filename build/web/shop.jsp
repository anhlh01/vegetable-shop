<%-- 
    Document   : shop
    Created on : Sep 28, 2021, 10:37:40 PM
    Author     : HP
--%>
<%@page import="java.util.TreeSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="anhlh.tblProducts.ProductDTO"%>
<%@page import="anhlh.tblCategory.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="anhlh.tblCategory.TblCategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Vegefoods - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="js/shop.js" defer></script>
    </head>
    <body class="goto-here">
        <jsp:include page = "Header.jsp"/>
        <!-- END nav -->

        <div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <div class="col-md-9 ftco-animate text-center">
                        <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span>Products</span></p>
                        <h1 class="mb-0 bread">Products</h1>
                    </div>
                </div>
            </div>
        </div>

        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-12 mb-5 text-center">
                        <ul class="product-category">
                            <li><a href="MainController?action=Load All Product" class="link">Tất Cả Sản Phẩm</a></li>
                                <c:if test="${requestScope.LIST_PRODUCT == null}">
                                    <c:redirect url="MainController?action=Load All Product"></c:redirect>
                                </c:if>
                                <c:forEach items="${requestScope.LIST_CATEGORY}" var="o">
                                <li><a class="link" href="MainController?action=Load a type&name=${o.categoryName}">${o.categoryName}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="row">



                    <c:forEach items="${requestScope.LIST_PRODUCT}" var="o">

                        <div class="col-md-6 col-lg-3 ftco-animate">
                            <div class="product">
                                <a href="MainController?action=Show Product&productID=${o.productID}&categoryID=${o.categoryID}" class="img-prod"><img class="img-fluid" src="${o.productImage}" alt="Colorlib Template">
                                    <!--                                <span class="status">30%</span>-->
                                    <div class="overlay"></div>
                                </a>
                                <div class="text py-3 pb-4 px-3 text-center">
                                    <h3><a href="MainController?action=Show Product&productID=${o.productID}&categoryID=${o.categoryID}">${o.productName}</a></h3>
                                    <div class="d-flex">
                                        <div class="pricing">
                                            <p class="price">
                                                <c:if test="${o.status == false}">
                                                    <%
                                                        String sold_out = (String) request.getAttribute("SOLD_OUT");
                                                    %>
                                                    <span class="mr-2 price-dc">SOLD OUT</span>
                                                </c:if>
                                                <span class="price-sale">${o.productPice}</span>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="bottom-area d-flex px-3">
                                        <div class="m-auto d-flex">
<!--                                            <a href="#" class="add-to-cart d-flex justify-content-center align-items-center text-center">
                                                <span><i class="ion-ios-menu"></i></span>
                                            </a>-->

                                            <form action="MainController" method="POST">
                                                <c:if test="${o.status == true}">
                                                    <button value="Add To Cart" name="action" class="button_addToCart buy-now d-flex justify-content-center align-items-center mx-1">
                                                        
                                                        <span> <i class="ion-ios-cart"></i></span>
                                                        
                                                    </button>
                                                    
                                                    <input id="submit" type="hidden" value="Add To Cart" name="action" />
                                                    <input type="hidden" name="productID" value="${o.productID}" />
                                                    <input type="hidden" name="quantity" value="1" /> 
                                                    <input type="hidden" name="page" value="shop" /> 
                                                </c:if>
                                            </form>


<!--                                            <a href="#" class="heart d-flex justify-content-center align-items-center ">
                                                <span><i class="ion-ios-heart"></i></span>
                                            </a>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 
                    </c:forEach>
                </div>
                <div class="row mt-5">
                    <div class="col text-center">
                        <div class="block-27">
                            <ul>
                                <li><a href="#">&lt;</a></li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">&gt;</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-section ftco-no-pt ftco-no-pb py-5 bg-light">
            <div class="container py-4">
                <div class="row d-flex justify-content-center py-5">
                    <div class="col-md-6">
                        <h2 style="font-size: 22px;" class="mb-0">Subcribe to our Newsletter</h2>
                        <span>Get e-mail updates about our latest shops and special offers</span>
                    </div>
                    <div class="col-md-6 d-flex align-items-center">
                        <form action="#" class="subscribe-form">
                            <div class="form-group d-flex">
                                <input type="text" class="form-control" placeholder="Enter email address">
                                <input type="submit" value="Subscribe" class="submit px-3">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="Footer.jsp"/>
        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/jquery.waypoints.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/jquery.animateNumber.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/scrollax.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="js/google-map.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
