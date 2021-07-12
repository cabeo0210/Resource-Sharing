<%-- 
    Document   : index
    Created on : May 10, 2021, 3:53:54 PM
    Author     : haudq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new account</title>
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
        <link href="css/create.css" rel="stylesheet" type="text/css"/>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script>

        </script>
    </head>
    <body>
        <div class="container register">
            <div class="row">
                <div class="col-md-3 register-left">
                    <img src="https://images.vexels.com/media/users/3/136818/isolated/preview/69b086c3753289c9acad2f9b0c53de79-colorful-share-icon-by-vexels.png" style="height: 100px; width: 100px;" alt=""/>
                    <h3>Sharing resource</h3>
                    <a href="index.jsp"><input type="submit" name="" value="Back to login"/><br/></a>
                </div>
                <div class="col-md-9 register-right">
                    <form action="MainController" method="POST">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <h3 class="register-heading">Create new account </br>
                                    <span style="color: red; font-size: small">${ERROR}</span>
                                </h3>
                                <div class="row register-form">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <!--<input type="text" pattern="^[A-Za-z0-9]+@[A-Za-z0-9]+\.[A-Za-z]{2,3}+$" class="form-control" name="userid" placeholder="UserId *" title="UserId can not empty and must be a email(aaa123@gmail.com)"  required="true"/>-->
                                            <input type="email" class="form-control" name="userid" placeholder="UserId *" required/>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control" id="password" name="password" placeholder="Password *" required=""/>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control" id="confirmPass" name="confirmPass" placeholder="Confirm Password *" oninput="check(this)"  required="" />
                                            <span id="errorConfirm"></span>
                                            <script language='javascript' type='text/javascript'>
                                                function check(input) {
                                                    if (input.value !== document.getElementById('password').value) {
                                                        input.setCustomValidity('Password Must be Matching.');
                                                    } else {
                                                        input.setCustomValidity('');
                                                    }
                                                }
                                            </script>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="address" placeholder="Your address *" required=""/>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="name" placeholder="Name *" required=""/>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" pattern="[0-9]{10}" title="The phone must be 10 digits" name="phone" class="form-control" placeholder="Your Phone *" required="" />
                                        </div>
                                        <input type="hidden" name="role" value="employee"/>
                                        <input type="hidden" name="status" value="active"/>
                                        <input type="submit" class="btnRegister" name="btnAction" value="Create"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
