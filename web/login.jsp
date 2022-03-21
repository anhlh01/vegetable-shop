<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2021, 10:54:03 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="anhlh.tblUsers.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/login.css">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="js/login.js" defer></script>
    </head>
    <body>

        <div class="login-wrap">
            <div class="login-html">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>

                <div class="login-form">
                    <form action="MainController" method="POST">
                        <div class="sign-in-htm">
                            <div class="group">
                                <label for="user" class="label">Username</label>
                                <input id="user" type="text" class="input" name="userID">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Password</label>
                                <input id="pass" type="password" class="input" data-type="password" name="password">
                            </div>

                            <div class="group">
                                <input type="submit" name="action" class="button" value="Sign In">
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <label for="tab-2">Need an account? Sign up now!</a><br>
                                <a class="login-with-google-btn" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/Shop/login-google&response_type=code&client_id=138099327090-mmumublg74hscr8sm3ouvi667ak8gqo8.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
                            </div>
                        </div>
                    </form>
                    <form action="MainController" method="POST" id="target">
                        <div class="sign-up-htm">
                            <div class="group">
                                <label for="user" class="label">Username <span style="color: red; font-size: large;">*</span></label>
                                <input id="userName" type="text" class="input" name="userIDSignUp">
                                <p class="userName" style="color: white"></p>             
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Password <span style="color: red; font-size: large;">*</span></label>
                                <input id="password" type="password" class="input" data-type="password" name="passwordSignUp">
                                <p class="password" style="color: white"></p>
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Repeat Password <span style="color: red; font-size: large;">*</span></label>
                                <input id="confirm" type="password" class="input" data-type="password" name="confirm">
                                <p class="confirm" style="color: white"></p>
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Email Address</label>
                                <input id="email" name="email" type="text" class="input">
                                <p class="email" style="color: white"></p>
                            </div>
                            <div class="group">
                                <input id="submit" type="submit" name="action" class="button" value="Sign Up" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <label for="tab-1">Already Member?</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <!-- Modal -->
<!--        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Shop Vegetable</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="modal-content"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>-->

        <script type="text/javascript">
//            $("#email").keyup(function (e) {
//                if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(e.target.value))
//                {
//                    $(".email").html("");
//                    return (true);
//                } else {
//                    $(".email").html("The email is wrong format!");
//                    return (false);
//                }
//            });
//            $(document).ready(function () {
//                var x_timer;
//                $("#userName").keyup(function (e) {
//                    clearTimeout(x_timer);
//                    const user_name = $("#userName").val();
//                    x_timer = setTimeout(function () {
//                        check_username_ajax(user_name);
//                    }, 0);
//                });
//                function check_username_ajax(userName) {
//                    const userNameMessage = document.querySelector(".userName");
//                    if (userName === "" || userName === null) {
//                        userNameMessage.innerHTML = "The user name can not be empty!";
//                    } else if (userName.length < 2 || userName.length > 50) {
//                        userNameMessage.innerHTML = "The length of user name is between 2 to 50!";
//                    } else {
//                        $.ajax({
//                            type: 'POST',
//                            url: '/Shop/CheckDuplicateUserController',
//                            data: "userName=" + userName,
//                            dataType: 'text',
//                            success: function (data)
//                            {
//                                $(".userName").html(data);
//                            }
//                        });
//                    }
//                }
//                $("#target").submit(function (e) {
//                    console.log(e.target);
//                    const userName = $('#userName').val();
//                    const password = $('#password').val();
//                    const confirm = $('#confirm').val();
//                    const submit = $('#submit');
//                    const checkName = $('.userName').text().length;
//                    const checkPassword = $('.password').text().length;
//                    const checkConfirm = $('.confirm').text().length;
//                    console.log(userName, password, confirm, $('.userName').html(), $('.password').html(), $('.confirm').html());
//                    if (userName == null || userName == "" || password == null || password == "" || confirm == null || confirm == "") {
//                        $("#modal-content").html("Sign up user failed!");
//                        $('#myInput').trigger('focus');
//                        e.preventDefault();
//                    } else if (checkName > 5 || checkPassword > 5 || checkConfirm > 5) {
//                        $("#modal-content").html("Sign up user failed!");
//                        $('#myInput').trigger('focus');
//                        e.preventDefault();
//                    } else {
//                        $("#modal-content").html("Sign up user successfully!");
//                        $('#myInput').trigger('focus');e.preventDefault();
//                        e.preventDefault();
//
//                    }
//                });
//
//            });


        </script>
    </body>
</html>
