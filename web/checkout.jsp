<%@ page import="com.freshmel.model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.freshmel.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Customer customer = (Customer) session.getAttribute("customer");
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
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.jsp">Home</a></span> <span>Checkout</span></p>
            <h1 class="mb-0 bread">Coming soon, Next Feature...</h1>
          </div>
        </div>
      </div>
    </div>


    <section class="ftco-section">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-xl-7 ftco-animate">
						<form action="#" class="billing-form">
							<h3 class="mb-4 billing-heading">Billing Details</h3>
	          	<div class="row align-items-end">
	          		<div class="col-md-6">
	                <div class="form-group">
	                	<label>Firt Name</label>
	                  <input type="text" class="form-control" placeholder="" value="<%=customer.getFirstName()%>" required readonly>
	                </div>
	              </div>
	              <div class="col-md-6">
	                <div class="form-group">
	                	<label>Last Name</label>
	                  <input type="text" class="form-control" placeholder="" value="<%=customer.getLastName()%>" required readonly>
	                </div>
                </div>
                <div class="w-100"></div>
		            <div class="col-md-12">
		            	<div class="form-group">
		            		<label>State</label>
		            		<div class="select-wrap">
								<input type="text" class="form-control" placeholder="" value="<%=customer.getAddresses().getState()%>" required readonly>
		                </div>
		            	</div>
		            </div>
		            <div class="w-100"></div>
		            <div class="col-md-4">
		            	<div class="form-group">
	                	<label>Street line 1</label>
	                  <input type="text" class="form-control" value="<%=customer.getAddresses().getLine1()%>" required readonly>
	                </div>
		            </div>
					<div class="col-md-4">
						<div class="form-group">
							<label>Street line 2</label>
							<input type="text" class="form-control" value="<%=customer.getAddresses().getLine2()%>" required readonly>
						</div>
					</div>
		            <div class="col-md-4">
		            	<div class="form-group">
							<label>Street line 3</label>
							<input type="text" class="form-control" value="<%=customer.getAddresses().getLine3()%>" required readonly>
	                </div>
		            </div>
		            <div class="w-100"></div>
		            <div class="col-md-6">
		            	<div class="form-group">
	                	<label>Suburb</label>
	                  <input type="text" class="form-control" placeholder="" value="<%=customer.getAddresses().getSuburb()%>" required readonly>
	                </div>
		            </div>
		            <div class="col-md-6">
		            	<div class="form-group">
		            		<label>Postcode / ZIP *</label>
	                  <input type="text" class="form-control" placeholder="" value="<%=customer.getAddresses().getPostCode()%>" required readonly>
	                </div>
		            </div>
		            <div class="w-100"></div>
		            <div class="col-md-6">
	                <div class="form-group">
	                	<label>Phone</label>
	                  <input type="text" class="form-control" placeholder="" value="<%=customer.getPhoneNumber()%>" required readonly>
	                </div>
	              </div>
	              <div class="col-md-6">
	                <div class="form-group">
	                	<label>Email Address</label>
	                  <input type="text" class="form-control" placeholder="" pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$" title="Please input a valid email." value="<%=customer.getEmail()%>" required readonly>
	                </div>
                </div>
                <div class="w-100"></div>
                <div class="col-md-12">
                	<%--<div class="form-group mt-4">--%>
										<%--<div class="radio">--%>
										  <%--<label class="mr-3"><input type="radio" name="optradio"> Create an Account? </label>--%>
										  <%--<label><input type="radio" name="optradio"> Ship to different address</label>--%>
										<%--</div>--%>
									<%--</div>--%>
                </div>
	            </div>
	          </form><!-- END -->
					</div>
					<div class="col-xl-5">
	          <div class="row mt-5 pt-3">
	          	<div class="col-md-12 d-flex mb-5">
	          		<div class="cart-detail cart-total p-3 p-md-4">
	          			<h3 class="billing-heading mb-4">Cart Total</h3>
						<%
							List<Cart> carts = customer.getCarts();
							Float totalPrice = (float)0;
							System.out.println(carts.size());
							for(int i=0;i<carts.size();i++){
							    Cart cart = carts.get(i);
								Float singleTotalPrice = cart.getQuantity()*cart.getProduct().getPrice();
								totalPrice += singleTotalPrice;
						%>
						<p class="d-flex">
							<span><%=cart.getProduct().getName()%> * <%=cart.getQuantity()%></span>
							<span><%=singleTotalPrice%></span>
						</p>
						<%
							}
						%>
		    					<p class="d-flex">
		    						<span>Delivery</span>
		    						<span>$0.00</span>
		    					</p>
		    					<p class="d-flex">
		    						<span>Discount</span>
		    						<span>$0.00</span>
		    					</p>
		    					<hr>
		    					<p class="d-flex total-price">
		    						<span>Total</span>
		    						<span><%=totalPrice%></span>
		    					</p>
								</div>
	          	</div>
	          	<div class="col-md-12">
	          		<div class="cart-detail p-3 p-md-4">
	          			<h3 class="billing-heading mb-4">Payment Method</h3>
									<div class="form-group">
										<div class="col-md-12">
											<div class="radio">
											   <label><input type="radio" name="optradio" class="mr-2"> Direct Bank Tranfer</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<div class="radio">
											   <label><input type="radio" name="optradio" class="mr-2"> Alipay</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<div class="radio">
											   <label><input type="radio" name="optradio" class="mr-2"> Paypal</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<div class="checkbox">
											   <label><input type="checkbox" value="" class="mr-2"> I have read and accept the terms and conditions</label>
											</div>
										</div>
									</div>
									<p><a href="/addOrder"class="btn btn-primary py-3 px-4">Place an order</a></p>
								</div>
	          	</div>
	          </div>
          </div> <!-- .col-md-8 -->
        </div>
      </div>
    </section> <!-- .section -->

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
		$(document).ready(function(){

		var quantitiy=0;
		   $('.quantity-right-plus').click(function(e){
		        
		        // Stop acting like a button
		        e.preventDefault();
		        // Get the field name
		        var quantity = parseInt($('#quantity').val());
		        
		        // If is not undefined
		            
		            $('#quantity').val(quantity + 1);

		          
		            // Increment
		        
		    });

		     $('.quantity-left-minus').click(function(e){
		        // Stop acting like a button
		        e.preventDefault();
		        // Get the field name
		        var quantity = parseInt($('#quantity').val());
		        
		        // If is not undefined
		      
		            // Increment
		            if(quantity>0){
		            $('#quantity').val(quantity - 1);
		            }
		    });
		    
		});
	</script>
    
  </body>
</html>