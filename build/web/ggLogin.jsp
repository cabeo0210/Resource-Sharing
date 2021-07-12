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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assignment 1</title>
        <link href="css/indexCss.css" rel="stylesheet" type="text/css"/>
        <script>
             window.onload = function() {
                var url = document.location.href;
                var email = url.split('?')[1];
                var s = email.split('=')[1];
                document.getElementById("emailValue").innerHTML = s;
                document.getElementsByName("email")[0].value = s;
                console.log(document.getElementsByName("email")[0].value);
            }
        </script>
    </head>
    <body>


        <div class="wrapper fadeInDown">
            <div id="formContent">

                <div class="fadeIn first">
                    <img src="data:image/png;base64,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" 
                         id="icon" alt="User Icon" style="width: 100px; height: 100px"/>
                    <c:if test="${not empty ERROR}">
                        <p style="color: red">${ERROR}</p>   
                    </c:if>
                </div>
                <h4 id="emailValue"></h4>
                <form action="MainController" method="POST">
                    <input type="hidden" name="email"/>
                    <input type="submit" class="fadeIn fourth" value="Continue to login" name="btnAction">
                </form>
                <div id="formFooter">
                    <a href="index.jsp">Back to login page</a>
                </div>
            </div>
        </div>
    </body>
</html>
