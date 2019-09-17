<%@ page import="java.util.List" %>
<%@ page import="com.freshmel.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Product product = (Product) request.getAttribute("product");
	String type = (String)session.getAttribute("type");

	Vender vender = null;
	Customer customer = null;
	User user = null;
	List<Cart> carts = null;
	List<Product> products = null;
	if (type != null){
		if (type.equals("vender")){
			vender = (Vender)session.getAttribute("vender");
			user = (User) vender;
		}else if(type.equals("customer")){
			customer = (Customer) session.getAttribute("customer");
			user = (User) customer;
			carts = customer.getCarts();
		}
	}
//
//	for (Cart cart : carts) {
//		products.add(cart.getProduct());
//	}
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
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.jsp">Home</a></span> <span class="mr-2"><a href="index.html">Product</a></span> <span>Product Single</span></p>
            <h1 class="mb-0 bread">Product Single</h1>
          </div>
        </div>
      </div>
    </div>

    <section class="ftco-section">
    	<div class="container">
    		<div class="row">
    			<div class="col-lg-6 mb-5 ftco-animate">
    				<a href="./upload/photo/<%=product.getPhoto()%>" class="image-popup"><img src="./upload/photo/<%=product.getPhoto()%>" class="img-fluid" alt="<%=product.getName()%>"></a>
    			</div>
    			<div class="col-lg-6 product-details pl-md-5 ftco-animate">
    				<h3><%=product.getName()%></h3>
    				<div class="rating d-flex">
							<%--<p class="text-left mr-4">--%>
								<%--<a href="#" class="mr-2">5.0</a>--%>
								<%--<a href="#"><span class="ion-ios-star-outline"></span></a>--%>
								<%--<a href="#"><span class="ion-ios-star-outline"></span></a>--%>
								<%--<a href="#"><span class="ion-ios-star-outline"></span></a>--%>
								<%--<a href="#"><span class="ion-ios-star-outline"></span></a>--%>
								<%--<a href="#"><span class="ion-ios-star-outline"></span></a>--%>
							<%--</p>--%>
							<%--<p class="text-left mr-4">--%>
								<%--<a href="#" class="mr-2" style="color: #000;">100 <span style="color: #bbb;">Rating</span></a>--%>
							<%--</p>--%>
							<p class="text-left">
								<a href="#" class="mr-2" style="color: #000;"><%=product.getInventory()%> <span style="color: #bbb;">inventory</span></a>
							</p>
						</div>
    				<p class="price"><span>$<%=product.getPrice()%></span></p>
    				<p><%=product.getDescription()%></p>
						<div class="row mt-4">
							<div class="col-md-6">
								<div class="form-group d-flex">
		              <div class="select-wrap">
	                  <div class="icon"><span class="ion-ios-arrow-down"></span></div>
	                  <%--<select name="" id="" class="form-control">--%>
	                  	<%--<option value="">Small</option>--%>
	                    <%--<option value="">Medium</option>--%>
	                    <%--<option value="">Large</option>--%>
	                    <%--<option value="">Extra Large</option>--%>
	                  <%--</select>--%>
	                </div>
		            </div>
							</div>
							<div class="w-100"></div>
							<div class="input-group col-md-6 d-flex mb-3">
	             	<span class="input-group-btn mr-2">
	                	<button type="button" class="quantity-left-minus btn"  data-type="minus" data-field="">
	                   <i class="ion-ios-remove"></i>
	                	</button>
	            		</span>
	             	<input type="text" id="quantity" name="quantity" class="form-control input-number" value="1" min="1" max="100">
	             	<span class="input-group-btn ml-2">
	                	<button type="button" class="quantity-right-plus btn" data-type="plus" data-field="">
	                     <i class="ion-ios-add"></i>
	                 </button>
	             	</span>
	          	</div>
	          	<div class="w-100"></div>
	          	<%--<div class="col-md-12">--%>
	          		<%--<p style="color: #000;">600 kg available</p>--%>
	          	<%--</div>--%>
          	</div>
          	<p><a href="#" id="add_cart" class="btn btn-black py-3 px-5">Add to Cart</a></p>
    			</div>
    		</div>
    	</div>
    </section>

    <%--<section class="ftco-section">--%>
    	<%--<div class="container">--%>
				<%--<div class="row justify-content-center mb-3 pb-3">--%>
          <%--<div class="col-md-12 heading-section text-center ftco-animate">--%>
          	<%--<span class="subheading">Products</span>--%>
            <%--<h2 class="mb-4">Related Products</h2>--%>
            <%--<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia</p>--%>
          <%--</div>--%>
        <%--</div>   		--%>
    	<%--</div>--%>
    	<%--<div class="container">--%>
    		<%--<div class="row">--%>
    			<%--<div class="col-md-6 col-lg-3 ftco-animate">--%>
    				<%--<div class="product">--%>
    					<%--<a href="#" class="img-prod"><img class="img-fluid" src="images/product-1.jpg" alt="Colorlib Template">--%>
    						<%--<span class="status">30%</span>--%>
    						<%--<div class="overlay"></div>--%>
    					<%--</a>--%>
    					<%--<div class="text py-3 pb-4 px-3 text-center">--%>
    						<%--<h3><a href="#">Bell Pepper</a></h3>--%>
    						<%--<div class="d-flex">--%>
    							<%--<div class="pricing">--%>
		    						<%--<p class="price"><span class="mr-2 price-dc">$120.00</span><span class="price-sale">$80.00</span></p>--%>
		    					<%--</div>--%>
	    					<%--</div>--%>
	    					<%--<div class="bottom-area d-flex px-3">--%>
	    						<%--<div class="m-auto d-flex">--%>
	    							<%--<a href="#" class="add-to-cart d-flex justify-content-center align-items-center text-center">--%>
	    								<%--<span><i class="ion-ios-menu"></i></span>--%>
	    							<%--</a>--%>
	    							<%--<a href="#" class="buy-now d-flex justify-content-center align-items-center mx-1">--%>
	    								<%--<span><i class="ion-ios-cart"></i></span>--%>
	    							<%--</a>--%>
	    							<%--<a href="#" class="heart d-flex justify-content-center align-items-center ">--%>
	    								<%--<span><i class="ion-ios-heart"></i></span>--%>
	    							<%--</a>--%>
    							<%--</div>--%>
    						<%--</div>--%>
    					<%--</div>--%>
    				<%--</div>--%>
    			<%--</div>--%>
    			<%--<div class="col-md-6 col-lg-3 ftco-animate">--%>
    				<%--<div class="product">--%>
    					<%--<a href="#" class="img-prod"><img class="img-fluid" src="images/product-2.jpg" alt="Colorlib Template">--%>
    						<%--<div class="overlay"></div>--%>
    					<%--</a>--%>
    					<%--<div class="text py-3 pb-4 px-3 text-center">--%>
    						<%--<h3><a href="#">Strawberry</a></h3>--%>
    						<%--<div class="d-flex">--%>
    							<%--<div class="pricing">--%>
		    						<%--<p class="price"><span>$120.00</span></p>--%>
		    					<%--</div>--%>
	    					<%--</div>--%>
    						<%--<div class="bottom-area d-flex px-3">--%>
	    						<%--<div class="m-auto d-flex">--%>
	    							<%--<a href="#" class="add-to-cart d-flex justify-content-center align-items-center text-center">--%>
	    								<%--<span><i class="ion-ios-menu"></i></span>--%>
	    							<%--</a>--%>
	    							<%--<a href="#" class="buy-now d-flex justify-content-center align-items-center mx-1">--%>
	    								<%--<span><i class="ion-ios-cart"></i></span>--%>
	    							<%--</a>--%>
	    							<%--<a href="#" class="heart d-flex justify-content-center align-items-center ">--%>
	    								<%--<span><i class="ion-ios-heart"></i></span>--%>
	    							<%--</a>--%>
    							<%--</div>--%>
    						<%--</div>--%>
    					<%--</div>--%>
    				<%--</div>--%>
    			<%--</div>--%>
    			<%--<div class="col-md-6 col-lg-3 ftco-animate">--%>
    				<%--<div class="product">--%>
    					<%--<a href="#" class="img-prod"><img class="img-fluid" src="images/product-3.jpg" alt="Colorlib Template">--%>
	    					<%--<div class="overlay"></div>--%>
	    				<%--</a>--%>
    					<%--<div class="text py-3 pb-4 px-3 text-center">--%>
    						<%--<h3><a href="#">Green Beans</a></h3>--%>
    						<%--<div class="d-flex">--%>
    							<%--<div class="pricing">--%>
		    						<%--<p class="price"><span>$120.00</span></p>--%>
		    					<%--</div>--%>
	    					<%--</div>--%>
    						<%--<div class="bottom-area d-flex px-3">--%>
	    						<%--<div class="m-auto d-flex">--%>
	    							<%--<a href="#" class="add-to-cart d-flex justify-content-center align-items-center text-center">--%>
	    								<%--<span><i class="ion-ios-menu"></i></span>--%>
	    							<%--</a>--%>
	    							<%--<a href="#" class="buy-now d-flex justify-content-center align-items-center mx-1">--%>
	    								<%--<span><i class="ion-ios-cart"></i></span>--%>
	    							<%--</a>--%>
	    							<%--<a href="#" class="heart d-flex justify-content-center align-items-center ">--%>
	    								<%--<span><i class="ion-ios-heart"></i></span>--%>
	    							<%--</a>--%>
    							<%--</div>--%>
    						<%--</div>--%>
    					<%--</div>--%>
    				<%--</div>--%>
    			<%--</div>--%>
    			<%--<div class="col-md-6 col-lg-3 ftco-animate">--%>
    				<%--<div class="product">--%>
    					<%--<a href="#" class="img-prod"><img class="img-fluid" src="images/product-4.jpg" alt="Colorlib Template">--%>
    						<%--<div class="overlay"></div>--%>
    					<%--</a>--%>
    					<%--<div class="text py-3 pb-4 px-3 text-center">--%>
    						<%--<h3><a href="#">Purple Cabbage</a></h3>--%>
    						<%--<div class="d-flex">--%>
    							<%--<div class="pricing">--%>
		    						<%--<p class="price"><span>$120.00</span></p>--%>
		    					<%--</div>--%>
	    					<%--</div>--%>
    						<%--<div class="bottom-area d-flex px-3">--%>
	    						<%--<div class="m-auto d-flex">--%>
	    							<%--<a href="#" class="add-to-cart d-flex justify-content-center align-items-center text-center">--%>
	    								<%--<span><i class="ion-ios-menu"></i></span>--%>
	    							<%--</a>--%>
	    							<%--<a href="#" class="buy-now d-flex justify-content-center align-items-center mx-1">--%>
	    								<%--<span><i class="ion-ios-cart"></i></span>--%>
	    							<%--</a>--%>
	    							<%--<a href="#" class="heart d-flex justify-content-center align-items-center ">--%>
	    								<%--<span><i class="ion-ios-heart"></i></span>--%>
	    							<%--</a>--%>
    							<%--</div>--%>
    						<%--</div>--%>
    					<%--</div>--%>
    				<%--</div>--%>
    			<%--</div>--%>
    		<%--</div>--%>
    	<%--</div>--%>
    <%--</section>--%>

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

		document.getElementById('add_cart').onclick = function () {
		    var quantity = document.getElementById("quantity").value;
		    // var cookies = document.cookie.split(';');
<%--			<%--%>
<%--			Customer.ADD_CART = 1;--%>
<%--			%>--%>

            window.location.href="/addCart?productId=<%=product.getId()%>&quantity="+quantity

			<%--var products = <%=products%>;--%>

			<%--if (!products.includes(<%=product%>)) {--%>
			<%--	quantity++;--%>
			<%--}--%>

			// window.location.href = "/shop";
		}
	</script>
    
  </body>
</html>