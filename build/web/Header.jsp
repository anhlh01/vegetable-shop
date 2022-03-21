<%-- 
    Document   : Hearder
    Created on : Sep 28, 2021, 10:21:39 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.TreeSet"%>
<%@page import="anhlh.cart.ProductForCart"%>
<%@page import="anhlh.cart.Cart"%>
<%@page import="anhlh.tblUsers.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="py-1 bg-primary">
    <div class="container">
        <div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
            <div class="col-lg-12 d-block">
                <div class="row d-flex">
                    <div class="col-md pr-4 d-flex topper align-items-center">
                        <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
                        <span class="text">+ 1235 2355 98</span>
                    </div>
                    <div class="col-md pr-4 d-flex topper align-items-center">
                        <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
                        <span class="text">youremail@email.com</span>
                    </div>
                    <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
                        <c:if test="${sessionScope.LOGIN_USER != null}" >
                        <span class="text">Welcome, ${sessionScope.LOGIN_USER.userName}</span>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Vegefoods</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <%
            String search = (String) request.getAttribute("PRODUCT_NAME");
            if (search == null) {
                search = "";
            }
        %>
        <form action="MainController">
            <div class="search">
                <input class="search-text" type="text" name="productName" placeholder="search" value="<%= search %>">
                <button name="action" value="Search Product" type="submit" class="search-button"><i class="fa fa-search" style="font-size:15px; color: #28a745; background-color: white;"></i></button>
            </div>
        </form>


        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a href="index.jsp" class="nav-link">Home</a></li>
                <li class="nav-item"><a href="shop.jsp" class="nav-link">Shop</a></li>
<!--                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                        <a class="dropdown-item" href="shop.jsp">Shop</a>
                        <a class="dropdown-item" href="wishlist.jsp">Wishlist</a>
                                                <a class="dropdown-item" href="product-single.jsp">Single Product</a>
                        <a class="dropdown-item" href="cart.jsp">Cart</a>
                        <a class="dropdown-item" href="checkout.jsp">Checkout</a>
                    </div>
                </li>-->
                <li class="nav-item"><a href="about.jsp" class="nav-link">About</a></li>
<!--                <li class="nav-item"><a href="blog.jsp" class="nav-link">Blog</a></li>-->
<!--                <li class="nav-item"><a href="contact.jsp" class="nav-link">Contact</a></li>-->

                <%
                    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                    if (user == null) {
                %>
                <li class="nav-item"><a href="login.jsp" class="nav-link">Login</a></li>
                    <%
                    } else {
                    %>
<!--                <li class="nav-item"><div class="nav-link">Welcome, <%= user.getUserName()%></div></li>-->
                <li class="nav-item"><a href="MainController?action=Logout" class="nav-link" id="logout">Logout</a></li>

                <%
                    }
                %>

                <%
                    Cart cart = (Cart) session.getAttribute("CART");
                    if (cart == null) {
                %>
                <li class="nav-item cta cta-colored"><a href="cart.jsp" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li> 
                    <%
                    } else {
                        TreeSet<ProductForCart> list = cart.getSet();
                        if (list != null) {
                    %>
                <li class="nav-item cta cta-colored"><a href="cart.jsp" class="nav-link"><span class="icon-shopping_cart"></span>[<%= list.size()%>]</a></li>
                    <%
                    } else {
                    %>
                <li class="nav-item cta cta-colored"><a href="cart.jsp" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li> 
                    <%
                                }
                            }
                        
                    %>
            </ul>
        </div>
    </div>
</nav>
