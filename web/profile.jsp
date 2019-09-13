<%@ page import="com.freshmel.model.Vender" %>
<%@ page import="com.freshmel.model.Customer" %>
<%@ page import="com.freshmel.model.User" %>
<%@ page import="com.freshmel.model.Address" %><%--
  Created by IntelliJ IDEA.
  User: Shawn
  Date: 9/9/19
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String type = (String)session.getAttribute("type");
    Vender vender = null;
    Customer customer = null;
    User user = null;
    if (type != null){
        if (type.equals("vender")){
            vender = (Vender)session.getAttribute("vender");
            user = vender;
        }else if(type.equals("customer")){
            customer = (Customer) session.getAttribute("customer");
            user = customer;
        }
    }
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
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body class="goto-here">

<jsp:include page="header.jsp"></jsp:include>

<!-- END nav -->

<div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="breadcrumbs"><span class="mr-2"><a href="index.jsp">Home</a></span> <span>My Profile</span></p>
                <h1 class="mb-0 bread">View Profile</h1>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="view-account">
        <section class="module">
            <div class="module-inner">
                <div class="side-bar">
                    <div class="user-info">
                        <img class="img-profile img-circle img-responsive center-block" src="/upload/photo/<%= user.getPhoto()%>" alt="">
                        <ul class="meta list list-unstyled">
                            <li class="name"><%= user.getFirstName()%> <%= user.getLasteName()%></li>
                            <li class="email"></i><%= user.getEmail()%></li>
                            <%--<li class="activity"><%= user.getLastLoginDate()%></li>--%>
                        </ul>
                    </div>
                    <nav class="side-menu">
                        <ul class="nav">
                            <li class="nav-tab active" onclick="openItem(event, 'profile')"><a href="#" ><span class="fa fa-user"></span> Profile</a></li>
                            <%
                                if (type.equals("customer")) {
                            %>
                            <li class="nav-tab" onclick="openItem(event, 'address')"><a href="#"><span class="fa fa-location-arrow"></span> Address</a></li>
                            <%
                                }
                            %>
                        </ul>
                    </nav>
                </div>

                <!-- profile update -->
                <div id="profile" class="content-panel">

                    <h2 class="title">Profile</h2>
                    <div class="container">
                        <form action="./updatePhoto" method="post" enctype="multipart/form-data" class="form-horizontal">
                            <div class="form-group avatar">
                                <figure class="figure col-md-2 col-sm-3 col-xs-12">
                                    <img class="img-rounded img-responsive" src="/upload/photo/<%= user.getPhoto()%>" alt="">
                                </figure>
                                <div class="form-inline col-md-10 col-sm-9 col-xs-12">
                                    <input name="file" type="file" class="file-uploader pull-left">
                                    <button type="submit" class="btn btn-secondary">Update Image</button>
                                </div>
                            </div>
                        </form>

                        <form action="/updateInfo" method="post" class="form-horizontal">
                            <fieldset class="fieldset">
                                <h3 class="fieldset-title">Personal Info</h3>

                                <div class="form-group">
                                    <label class="col-md-2 col-sm-3 col-xs-12 control-label">First Name</label>
                                    <div class="col-md-10 col-sm-9 col-xs-12">
                                        <input name="firstName" type="text" class="form-control" value="<%= user.getFirstName()%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 col-sm-3 col-xs-12 control-label">Last Name</label>
                                    <div class="col-md-10 col-sm-9 col-xs-12">
                                        <input name="lastName" type="text" class="form-control" value="<%= user.getLasteName()%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 col-sm-3 col-xs-12">Phone</label>
                                    <div class="col-md-10 col-sm-9 col-xs-12">
                                        <input name="phoneNumber" type="text" class="form-control" pattern="^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$" title="Please input a valid phone." value="<%= user.getPhoneNumber()%>" required>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="fieldset">
                                <h3 class="fieldset-title">Contact Info</h3>
                                <div class="form-group">
                                    <label class="col-md-2  col-sm-3 col-xs-12 control-label">Email</label>
                                    <div class="col-md-10 col-sm-9 col-xs-12">
                                        <input disabled="disabled" type="email" class="form-control" value="<%= user.getEmail()%>" readonly>
                                        <p class="help-block">The email is not changeable.</p>
                                    </div>
                                </div>
                            </fieldset>
                            <hr>
                            <div class="form-group">
                                <div class="col-md-10 col-sm-9 col-xs-12 col-md-push-2 col-sm-push-3 col-xs-push-0">
                                    <input class="btn btn-primary" type="submit" value="Update Profile">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- address update -->
                <%
                    if (type.equals("customer")){
                        Address address = customer.getAddresses();
                %>
                <div id="address" class="content-panel" style="display: none;">
                    <div class="container">
                        <h2 class="title">Delivery Address Details</h2>
                        <form action="/updateAddress" method="post" class="form-horizontal">
                            <fieldset class="fieldset">
                                <h3 class="fieldset-title">Address Info</h3>
                                <div class="row col-md-12">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label">First Name</label>
                                            <input name="firstName" type="text" class="form-control" value="<%=address.getFirstName()%>" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label">Last Name</label>
                                            <input name="lastName" type="text" class="form-control" value="<%=address.getLastName()%>" required>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="control-label">Phone</label>
                                            <input name="phone" type="text" class="form-control" placeholder="<%=address.getPhone()%>" pattern="^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$" title="Please input a valid phone." required>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="control-label">Street Address</label>
                                            <label class="control-label">Line1</label>
                                            <input name="line1" type="text" class="form-control" value="<%=address.getLine1()%>" required>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="control-label">Line2</label>
                                            <input name="line2" type="text" class="form-control" value="<%=address.getLine2()%>">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="control-label">Line3</label>
                                            <input name="line3" type="text" class="form-control" value="<%=address.getLine3()%>">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="towncity">Suburb</label>
                                            <input name="suburb" type="text" class="form-control" value="<%=address.getSuburb()%>" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="towncity">State / Territory</label>
                                            <input name="state" type="text" class="form-control" value="<%=address.getState()%>" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="postcodezip">Postcode / ZIP</label>
                                            <input name="postCode" type="text" class="form-control" value="<%=address.getPostCode()%>" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="country">Country</label>
                                            <input type="text" class="form-control" placeholder="" value="Australia" readonly>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <div class="form-group">
                                <div class="col-md-10 col-sm-9 col-xs-12 col-md-push-2 col-sm-push-3 col-xs-push-0">
                                    <input class="btn btn-primary" type="submit" value="Update Address">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <%
                    }
                %>

        </section>
    </div>
</div>

<footer class="ftco-footer ftco-section">
    <div class="container">
        <div class="row">
            <div class="mouse">
                <a href="#" class="mouse-icon">
                    <div class="mouse-wheel"><span class="ion-ios-arrow-up"></span></div>
                </a>
            </div>
        </div>
        <div class="row mb-5">
            <div class="col-md">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2">FreshMel</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                        <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                        <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                        <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md">
                <div class="ftco-footer-widget mb-4 ml-md-5">
                    <h2 class="ftco-heading-2">Menu</h2>
                    <ul class="list-unstyled">
                        <li><a href="shop.jsp" class="py-2 d-block">Shop</a></li>
                        <li><a href="about.jsp" class="py-2 d-block">About</a></li>
                        <li><a href="contact.jsp" class="py-2 d-block">Contact Us</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2">Help</h2>
                    <div class="d-flex">
                        <ul class="list-unstyled mr-l-5 pr-l-3 mr-4">
                            <li><a href="#" class="py-2 d-block">Shipping Information</a></li>
                            <li><a href="#" class="py-2 d-block">Returns &amp; Exchange</a></li>
                            <li><a href="#" class="py-2 d-block">Terms &amp; Conditions</a></li>
                            <li><a href="#" class="py-2 d-block">Privacy Policy</a></li>
                        </ul>
                        <ul class="list-unstyled">
                            <li><a href="#" class="py-2 d-block">FAQs</a></li>
                            <li><a href="contact.jsp" class="py-2 d-block">Contact</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2">Have a Questions?</h2>
                    <div class="block-23 mb-3">
                        <ul>
                            <li><span class="icon icon-map-marker"></span><span class="text">227 Grattan Street, Carlton VIC, 3053, Australia</span></li>
                            <li><a href="#"><span class="icon icon-phone"></span><span class="text">+61 450 492 960</span></a></li>
                            <li><a href="#"><span class="icon icon-envelope"></span><span class="text">info@freshmel.com</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-center">

                <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    Copyright &copy;<script>document.write(new Date().getFullYear());</script> FreshMel | Powered by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                </p>
            </div>
        </div>
    </div>
</footer>



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

<script type="text/javascript">
    //open tab items
    function openItem(evt, itemName) {
        var i, panel, tabs;
        panel = document.getElementsByClassName("content-panel");
        for (i = 0; i < panel.length; i++) {
            panel[i].style.display = "none";
        }
        tabs = document.getElementsByClassName("nav-tab");
        for (i = 0; i < tabs.length; i++) {
            tabs[i].className = tabs[i].className.replace(" active", "");
        }
        document.getElementById(itemName).style.display = "block";
        evt.currentTarget.className += " active";
    }
</script>

</body>
</html>
