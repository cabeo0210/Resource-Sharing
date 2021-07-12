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
        <title>Confirm</title>
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
        <link href="css/create.css" rel="stylesheet" type="text/css"/>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
                                <h3 class="register-heading">Confirm your account <br/>
                                    <span style="font-size: small">We sent a code to your email. Please input code here to confirm your account!</span>
                                </h3>
                                <div class="row register-form">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <c:if test="${not empty ERROR}">
                                                <p style="color: red">${ERROR}</p>   
                                            </c:if>
                                            <input type="text" class="form-control" name="codeEmail" placeholder="Enter your code *" required=""/>
                                            <input type="submit" class="btnRegister" name="btnAction" value="Continue" title="Continue to login"/>
                                        </div>
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
