<%@ page import="com.freshmel.model.Vender" %>
<%@ page import="com.freshmel.model.Customer" %>
<%@ page import="com.freshmel.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Shawn
  Date: 9/9/19
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String type = (String)session.getAttribute("type");
    String currPage = request.getRequestURL().toString();
    currPage = currPage.substring(currPage.lastIndexOf("/")+1);

    Vender vender = null;
    Customer customer = null;
    User user = null;
    if (type != null){
        if (type.equals("vender")){
            vender = (Vender)session.getAttribute("vender");
            user = (User) vender;
        }else if(type.equals("customer")){
            customer = (Customer) session.getAttribute("customer");
            user = (User) customer;
        }
    }


%>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">FreshMel</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <% if (currPage.equals("") || currPage.equals("index.jsp")) { %>
                <li class="nav-item active"><a href="index.jsp" class="nav-link">Home</a></li>
                <% } else {%>
                <li class="nav-item"><a href="index.jsp" class="nav-link">Home</a></li>
                <% } %>
                <%
                    if (type == "vender"){
                        if (currPage.equals("shop.jsp")) {
                %>
                <li class="nav-item active"><a href="/shop" class="nav-link">My Products</a></li>
                <%
                        } else {
                %>
                <li class="nav-item"><a href="product.jsp" class="nav-link">My Products</a></li>
                <%
                        }
                    } else {
                        if (currPage.equals("shop.jsp")) {
                %>
                <li class="nav-item active"><a href="shop.jsp" class="nav-link">Shop</a></li>
                <%
                        } else {
                %>
                <li class="nav-item"><a href="/shop" class="nav-link">Shop</a></li>
                <%
                        }
                    }
                %>

                <%
                    if (type != null){
                        if (currPage.equals("order.jsp")) {
                %>
                <li class="nav-item active"><a href="order.jsp" class="nav-link">Order</a></li>
                <%
                        } else {
                %>
                <li class="nav-item"><a href="order.jsp" class="nav-link">Order</a></li>
                <%
                        }
                    }
                %>
                <%
                    if (currPage.equals("about.jsp")) {
                %>
                <li class="nav-item active"><a href="about.jsp" class="nav-link">About</a></li>
                <%
                    } else {
                %>
                <li class="nav-item"><a href="about.jsp" class="nav-link">About</a></li>
                <%
                    }
                    if (currPage.equals("contact.jsp")) {
                %>
                <li class="nav-item active"><a href="contact.jsp" class="nav-link">Contact</a></li>
                <%
                    } else {
                %>
                <li class="nav-item"><a href="contact.jsp" class="nav-link">Contact</a></li>
                <%
                    }
                %>
                <%
                    if (type == "customer"){
                %>
                <li class="nav-item cta cta-colored"><a href="/cart" class="nav-link"><span class="icon-shopping_cart"></span>[<%=customer.getCarts().size()%>]</a></li>
                <%
                    }
                %>

                <%
                    if (type != null){
                %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img class="navbar-avatar" src="/upload/photo/<%=user.getPhoto()%>" alt="Avatar"></a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                        <a class="dropdown-item" href="/profile.jsp">My Profile</a>
                        <a class="dropdown-item" href="/logout">Logout</a>
                    </div>
                <%
                    } else {
                %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img class="navbar-avatar" src="https://www.w3schools.com/howto/img_avatar.png" alt="Avatar"></a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#loginModal">Login</a>
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#regModal">Register</a>
                    </div>
                <%
                    }
                %>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- END nav -->

<!-- Login Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="loginModalLongTitle">Login</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12 order-md-last">
                    <form action="./login" method="post" class="bg-white p-5 contact-form">
                        <div class="form-group">
                            <label class="control-label">Your User Type</label>
                            <select name="type" class="form-control">
                                <option value="customer">Customer</option>
                                <option value="vender">Vendor</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Email</label>
                            <input name="email" type="text" class="form-control" pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$" title="Please input a valid email." required>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Password</label>
                            <input name="password" type="password" class="form-control" required>
                        </div>
                        <div class="text-right">
					<span class="txt1">
						Forgot
					</span>

                            <a href="#" class="txt2" data-dismiss="modal" data-toggle="modal" data-target="#resetModal">
                                Username / Password?
                            </a>
                        </div>

                </div>
            </div>
            <div class="modal-footer">
                <div class="text-left">
					<span class="txt1 p-b-9">
						Don’t have an account?
					</span><br>

                    <a href="#" class="txt3" data-dismiss="modal" data-toggle="modal" data-target="#regModal">
                        Sign up now
                    </a>
                </div>
                <div>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>

<!-- Register Modal -->
<div class="modal fade" id="regModal" tabindex="-1" role="dialog" aria-labelledby="regModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="regModalLongTitle">Sign Up</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12 order-md-last">
                    <form action="/register" method="post" class="bg-white p-5 contact-form">
                        <div class="form-group">
                            <label class="control-label">Your User Type</label>
                            <select name="type" class="form-control">
                                <option value="customer">Customer</option>
                                <option value="vender">Vendor</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Your Email</label>
                            <input name="email" type="text" class="form-control" placeholder="" pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$" title="Please input a valid email." required>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Your Password</label>
                            <input name="password" type="password" class="form-control" id="psw1-reg" placeholder="" required>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Retype Your Password</label>
                            <input name="password2" type="password" class="form-control" id="psw2-reg" placeholder="" required>
                        </div>
                        <div class="text-center">
					<span class="txt1">
						An email will be sent to you after registration.
					</span>
                        </div>

                </div>
            </div>
            <div class="modal-footer">
                <div>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="checkReg">Sign Up</button>
                </div>
            </div>
            </form>
<%--            check password --%>
            <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
            <script>
                document.getElementById("checkReg").onclick = function () {
                    var psw1 = document.getElementById("psw1-reg").value;
                    var psw2 = document.getElementById("psw2-reg").value;
                    if (psw1 != psw2) {
                        swal("Info", "Please ensure the 2 password are the same!").then((value) => {});
                        document.getElementById("psw1-reg").value = "";
                        document.getElementById("psw2-reg").value = "";
                        return false;
                    }
                }
            </script>
        </div>
    </div>
</div>

<!-- Reset Modal -->
<div class="modal fade" id="resetModal" tabindex="-1" role="dialog" aria-labelledby="resetModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="resetModalLongTitle">Reset Your Password</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12 order-md-last">
                    <form action="#" class="bg-white p-5 contact-form">
                        <div class="form-group">
                            <label class="control-label">Your Email</label>
                            <input type="text" class="form-control" placeholder="" pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$" title="Please input a valid email." required>
                        </div>
                        <div class="text-center">
					<span class="txt1">
						An email will be sent to you as a guidance.
					</span>
                        </div>

                </div>
            </div>
            <div class="modal-footer">
                <div>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Confirm</button>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>