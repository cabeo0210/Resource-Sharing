<%-- 
    Document   : search.jsp
    Created on : May 11, 2021, 9:40:42 AM
    Author     : haudq
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
        <title>Search page</title>
        <link href="css/searchCss.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar navbar-light bg-light p-3">
            <div class="d-flex col-12 col-md-3 col-lg-2 mb-2 mb-lg-0 flex-wrap flex-md-nowrap justify-content-between">
                <a class="navbar-brand" href="#">
                    <img src="https://images.vexels.com/media/users/3/136818/isolated/preview/69b086c3753289c9acad2f9b0c53de79-colorful-share-icon-by-vexels.png" style="height: 50px; width: 45px;"/>Sharing Resource
                </a>
                <button class="navbar-toggler d-md-none collapsed mb-3" type="button" data-toggle="collapse" data-target="#sidebar" aria-controls="sidebar" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
            <div class="col-12 col-md-4 col-lg-6">
                <form class="form-inline" action="MainController" method="POST">
                    <div class="input-group mb-3">
                        <input type="text" name="name" value="${NAME_SEARCH}" class="form-control" placeholder="Enter equipment's name" aria-label="Recipient's username" aria-describedby="basic-addon2">
                        <input type="date" name="rentDate" value="${RENT_DATE}" class="form-control" aria-label="Recipient's username" aria-describedby="basic-addon2">
                        <c:if test="${ACTION.trim() != 'requestHistory'}" var="testAction">
                            <select class="form-select" aria-label="Default select example" name="status" value="${STATUS}">
                                <option selected value="">Status</option>
                                <option value="New">New</option>
                                <option value="Reject">Rejected</option>
                                <option value="Accepted">Accepted</option>
                            </select>
                        </c:if> 
                        <div class="input-group-append">
                            <button class="btn btn-outline-primary" type="submit" name="btnAction" value="searchRequest">Search</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-12 col-md-5 col-lg-4 d-flex align-items-center justify-content-md-end mt-3 mt-md-0">
                <form action="MainController" method="POST">
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-expanded="false">
                            ${INFO.name}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <li><a class="dropdown-item" href="#"><button name="btnAction" class="btn btn-outline-info w-50 border-0" type="submit" value="Logout">Logout</button></a></li>
                        </ul>
                    </div>
                </form>

            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                    <form action="MainController" method="POST">
                        <div class="position-sticky">
                            <ul class="nav flex-column">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">
                                        <button name="btnAction" class="btn btn-outline-info w-100 border-0" type="submit" value="Dashboard">Dashboard</button>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="#">
                                        <button name="btnAction" class="btn btn-outline-info w-100 border-0" type="submit" value="requestHistory">Request history</button>
                                    </a>
                                </li>
                                <c:if test="${not empty INFO.getRole()}">
                                    <c:if test="${INFO.getRole() == 'manager'}">
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">
                                                <button name="btnAction" class="btn btn-outline-info w-100 border-0" type="submit" value="requestController">Request List</button>
                                            </a>
                                        </li>
                                    </c:if>
                                </c:if>
                            </ul>
                        </div>
                    </form>
                </nav>
                <main class="col-md-9 ml-sm-auto col-lg-10 px-md-4 py-4">
                    <h1 class="h2">Dashboard</h1>
                    <p>Welcome <span style="font-weight: bold;">${INFO.name}</span> to sharing resource application</p>
                    <span style="color: red">${ERROR}</span>
                    <span style="color: green">${NOTIFY}</span>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                        </ol>
                    </nav>
                    <div class="container">
                        <div class="row col-md-12 col-md-offset-2 custyle">
                            <c:if test="${not empty REQUESTS }" var="testNull">
                                <table class="table table-striped custab">
                                    <thead>
                                    <a href="#" class="btn btn-primary btn-xs pull-right">Request to rent resource</a>
                                    <tr>
                                        <th>N.O</th>
                                        <th>Resource Name</th>
                                        <th>User name</th>
                                        <th>Request to rent date</th>
                                        <th>Status</th>
                                        <th colspan="2">Operator</th>
                                    </tr>
                                    </thead>
                                    <c:forEach items="${REQUESTS}" var="request" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${request.getResourceName()}</td>
                                            <td>${request.getUserName()}</td>
                                            <td>${request.getRentDate()}</td>
                                            <td>${request.getStatus()}</td>
                                        <form action="RequestController" method="POST">
                                            <input type="hidden" name="rentId" value="${request.getId()}"/>
                                            <c:if test="${request.getStatus().trim() == 'new' && ACTION.trim() != 'requestHistory'}" var="checkStatus">
                                                <td><button type="submit" name="btnAction" value="approve" class="btn btn-success">Approve</button></td>
                                                <td><button type="submit" name="btnAction" value="reject" class="btn btn-danger">Reject</button></td>
                                            </c:if>
                                            <c:if test="${!checkStatus && ACTION.trim() != 'requestHistory'}">
                                                <td><button type="submit" name="btnAction" value="approve" disabled="" class="btn btn-success">Approve</button></td>
                                                <td><button type="submit" name="btnAction" value="reject" disabled="" class="btn btn-danger">Reject</button></td>
                                            </c:if>
                                            <c:if test="${ACTION.trim() == 'requestHistory' && request.getStatus().trim() != 'inactive'}" var="testDelete">
                                                <td><button type="submit" name="btnAction" value="delete" class="btn btn-danger">Delete</button></td>
                                            </c:if>
                                            <c:if test="${!testDelete && ACTION.trim() == 'requestHistory'}">
                                                <td><button type="submit" name="btnAction" value="delete" disabled="" class="btn btn-danger">Delete</button></td>
                                            </c:if>
                                        </form>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                            <c:if test="${!testNull}">
                                <table class="table table-striped custab">
                                    <thead>
                                    <a href="#" class="btn btn-primary btn-xs pull-right">Request to rent resource</a>
                                    <tr>
                                        <th>The data empty!</th>
                                    </tr>
                                    </thead>
                                </table>
                            </c:if>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <footer class="bg-light text-center text-lg-start fixed-bottom col-md-9 ml-sm-auto col-lg-10 px-md-4 py-4">
            <c:if test="${testNull}">
                <form action="MainController" method="POST">
                    <button type="submit" name="btnAction" class="btn btn-outline-primary" value="preRequest">Previous</button>
                    <button type="submit" name="btnAction"class="btn btn-outline-primary" value="nextRequest">Next</button>
                    <input type="hidden" name="name"  value="${NAME_SEARCH}">
                    <input type="hidden" name="rentDate"   value="${RENT_DATE}">
                    <input type="hidden" name="status"   value="${STATUS}">
                    <input type="hidden" name="offset" offset value="${offsetPage}"/>
                </form>
            </c:if>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
    </body>
</html>
