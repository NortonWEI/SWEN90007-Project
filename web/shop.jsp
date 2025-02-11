<%@ page import="com.freshmel.service.ProductService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.freshmel.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> products =  (List<Product>)request.getAttribute("products");
    String query = (String) request.getAttribute("query");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>FreshMel - Your Carry-on Supermarket</title>
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
</head>
<body class="goto-here">
<jsp:include page="header.jsp"></jsp:include>

<div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="breadcrumbs"><span class="mr-2"><a href="index.jsp">Home</a></span> <span>Products</span></p>
                <h1 class="mb-0 bread">Products</h1>
            </div>
        </div>
    </div>
</div>

<section class="ftco-section">
    <div class="container">
        <div class="sidebar-box" align="center">
            <form action="/shop" method="get" class="search-form">
                <div class="form-group col-md-6">
                    <%
                        if (query != null) {
                    %>
                    <input name="query" type="text" class="form-control" placeholder="Search..." id="searchBar" value="<%=query%>" required>
                    <%
                        } else {
                    %>
                    <input name="query" type="text" class="form-control" placeholder="Search..." id="searchBar" required>
                    <%
                        }
                    %>
                    <button type="submit" class="btn icon ion-ios-search">
                    </button>
                </div>
            </form>
            <br>
            <form action="/shop" method="get" class="search-form" id="rank-form">
                <div class="form-group col-md-6">
                    <a href="javascript:void(0)" id="rank_price" class="btn btn-primary py-3 px-5">Rank by Price</a>
                    <a href="javascript:void(0)" id="rank_inv" class="btn btn-primary py-3 px-5">Rank by Inventory</a>
                    <input id="rank-input" type="hidden" name="rank" value="">
                </div>
            </form>
                <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
                <script>
                    document.getElementById("rank_price").onclick = function () {

                        var form = document.getElementById('rank-form');
                        var input = document.getElementById('rank-input');

                        input.value = 'price';
                        form.submit();
                    }

                    document.getElementById("rank_inv").onclick = function () {
                        var form = document.getElementById('rank-form');
                        var input = document.getElementById('rank-input');

                        input.value = 'inv';
                        form.submit();
                    }

                </script>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-10 mb-5 text-center">
                <ul class="product-category">
                    <li><a href="./shop" id="All" name="tab">All</a></li>
                    <li><a href="./shop?type=Vegetable" id="Vegetable" name="tab">Vegetable</a></li>
                    <li><a href="./shop?type=Fruit" id="Fruit" name="tab">Fruit</a></li>
                    <li><a href="./shop?type=Seafood" id="Seafood" name="tab">Seafood</a></li>
                    <li><a href="./shop?type=Dairy" id="Dairy" name="tab">Dairy</a></li>
                    <li><a href="./shop?type=Poultry" id="Poultry" name="tab">Poultry</a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <%
                for (int i=0;i<products.size();i++){
                    Product product = products.get(i);
            %>
            <div class="col-md-6 col-lg-3 ftco-animate">
                <div class="product">
                    <a href="/singleProduct?id=<%=product.getId()%>" class="img-prod"><img class="img-fluid" src="./upload/photo/<%=product.getPhoto()%>" alt="Colorlib Template">
                        <div class="overlay"></div>
                    </a>
                    <div class="text py-3 pb-4 px-3 text-center">
                        <h3><a href="/singleProduct?id=<%=product.getId()%>"><%=product.getName()%></a></h3>
                        <div class="d-flex">
                            <div class="pricing">
                                <p class="price">
                                    <%--<span class="mr-2 price-dc">$120.00</span>--%>
                                    <span class="price-sale">$<%=product.getPrice()%></span>
                                </p>
                            </div>
                        </div>
                        <div class="bottom-area d-flex px-3">
                            <div class="m-auto d-flex">
                                <%--<a href="#" class="add-to-cart d-flex justify-content-center align-items-center text-center">--%>
                                <%--<span><i class="ion-ios-menu"></i></span>--%>
                                <%--</a>--%>
                                <%--									<a href="#" class="buy-now d-flex justify-content-center align-items-center mx-1">--%>
                                <%--										<span><i class="ion-ios-cart"></i></span>--%>
                                <%--									</a>--%>
                                <%--<a href="#" class="heart d-flex justify-content-center align-items-center ">--%>
                                <%--<span><i class="ion-ios-heart"></i></span>--%>
                                <%--</a>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
        <%--<div class="row mt-5">--%>
        <%--<div class="col text-center">--%>
        <%--<div class="block-27">--%>
        <%--<ul>--%>
        <%--<li><a href="#">&lt;</a></li>--%>
        <%--<li class="active"><span>1</span></li>--%>
        <%--<li><a href="#">2</a></li>--%>
        <%--<li><a href="#">3</a></li>--%>
        <%--<li><a href="#">4</a></li>--%>
        <%--<li><a href="#">5</a></li>--%>
        <%--<li><a href="#">&gt;</a></li>--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
    </div>
</section>

<%--<section class="ftco-section ftco-no-pt ftco-no-pb py-5 bg-light">--%>
<%--<div class="container py-4">--%>
<%--<div class="row d-flex justify-content-center py-5">--%>
<%--<div class="col-md-6">--%>
<%--<h2 style="font-size: 22px;" class="mb-0">Subcribe to our Newsletter</h2>--%>
<%--<span>Get e-mail updates about our latest shops and special offers</span>--%>
<%--</div>--%>
<%--<div class="col-md-6 d-flex align-items-center">--%>
<%--<form action="#" class="subscribe-form">--%>
<%--<div class="form-group d-flex">--%>
<%--<input type="text" class="form-control" placeholder="Enter email address">--%>
<%--<input type="submit" value="Subscribe" class="submit px-3">--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</section>--%>
<jsp:include page="footer.jsp"></jsp:include>

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

<script>
    $(function () {
        var anchor = window.location.href;
        var tab;

        if (anchor.includes("type=")) {
            anchor = anchor.substring(anchor.indexOf("=")+1);
        } else {
            anchor = "All";
        }

        tab = document.getElementById(anchor);
        tab.className += "active";
    })
</script>

</body>
</html>