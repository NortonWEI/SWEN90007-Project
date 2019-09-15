<%@ page import="java.util.List" %>
<%@ page import="com.freshmel.model.Product" %>
<%@ page import="com.freshmel.model.Vender" %>
<%@ page import="com.freshmel.dataMapper.ProductMapper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Vender vender = (Vender) session.getAttribute("vender");
	ProductMapper productMapper = new ProductMapper();
	vender.setProducts(productMapper.findByVenderID(vender.getId()));
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

    <div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
      <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
          <div class="col-md-9 ftco-animate text-center">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.jsp">Home</a></span> <span>My Product</span></p>
            <h1 class="mb-0 bread">Product List</h1>
          </div>
        </div>
      </div>
    </div>

    <section class="ftco-section ftco-cart">
			<div class="container">
				<div class="row">
    			<div class="col-md-12 ftco-animate">
    				<div class="cart-list">
	    				<table class="table">
						    <thead class="thead-primary">
						      <tr class="text-center">
						        <th>&nbsp;</th>
						        <th> </th>
						        <th>Product List</th>
						        <th>Price</th>
						        <th>Inventory</th>
						        <th>State</th>
						      </tr>
						    </thead>
						    <tbody>
							<tr class="text-left">
								<td class="product-remove">
									<span class="form-group">All</span>
								</td>
							</tr>
							<%
								List<Product> products = vender.getProducts();
								for (int i=0;i<products.size();i++){
								    Product product = products.get(i);
								    String state = null;
								    if (product.getState() == 1){
								        state = "On Sale";
									}else if (product.getState() == 2){
										state = "Unavailable";
									}
							%>
							<tr class="text-center">
								<td class="product-remove">
									<a href="/deleteProduct?id=<%=product.getId()%>">
										<span class="fa fa-trash"></span>
									</a>
									<a href="#" data-toggle="modal" data-target="#prodModal<%=i%>">
										<span class="fa fa-edit"></span>
									</a>
								</td>

								<td class="image-prod"><div class="img" style="background-image:url(./upload/photo/<%=product.getPhoto()%>);"></div></td>

								<td class="product-name">
									<h3><%=product.getName()%></h3>
									<p><%=product.getDescription()%></p>
								</td>

								<td class="price">$<%=product.getPrice()%></td>

								<td class="inventory"><%=product.getInventory()%></td>

								<td class="state"><%=state%></td>
							</tr><!-- END TR-->
							<%
								}
							%>
						    </tbody>
						  </table>
					  </div>
    			</div>
    		</div>
				<div class="col-md-12 col-sm-9 col-xs-12 col-md-push-2 col-sm-push-3 col-xs-push-0" align="center">
					<input class="btn btn-primary" type="submit" value="Add Product" data-toggle="modal" data-target="#prodModal">
				</div>
			</div>
		</section>

		<!-- Add Product Modal -->
		<div class="modal fade" id="prodModal" tabindex="-1" role="dialog" aria-labelledby="prodModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="prodModalLongTitle">Add new product</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="col-md-12 order-md-last">
							<form action="/addProduct" method="post" class="bg-white p-5 product-form">
								<div class="form-group avatar" align="center">
									<figure class="figure">
										<img class="img-rounded" id='show_img' src="https://www.w3schools.com/howto/img_avatar.png" alt="">
									</figure>
									<div class="form-group">
										<input type="file" id='pic_img' class="file-uploader pull-left">
										<input type="hidden" id="photo_id" name="photo" value="">
									</div>
									<div class="form-group">
										<button type="button" id="upload_btn" onclick='upload("upload_btn","pic_img","show_img","photo_id")' class="btn btn-secondary">Update Image</button>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label">Name</label>
									<input type="text" name="name" class="form-control" required>
								</div>
								<div class="form-group">
									<label class="control-label">Price</label>
									<input type="text" name="price" class="form-control" pattern="^\d+(\.\d{1,2})?$" title="Please input a valid price!" required>
								</div>
								<div class="form-group">
									<label class="control-label">Inventory</label>
									<input type="text" name="inventory" class="form-control" pattern="^[1-9]\d*$" title="Please input a valid inventory!" required>
								</div>
								<div class="form-group">
									<label class="control-label">Description</label>
									<textarea type="text" name="description" class="form-control" required></textarea>
								</div>
								<div class="form-group">
									<label class="form-group">Type</label>
									<select name="type" id="" class="form-control">
										<option value="Fruit">Fruit</option>
										<option value="Vegetable">Vegetable</option>
										<option value="Poultry">Poultry</option>
										<option value="Seafood">Seafood</option>
										<option value="Dairy">Dairy</option>
									</select>
								</div>
								<div class="form-group">
									<label class="form-group">State</label>
									<select name="state" class="form-control">
										<option value="1">On Sale</option>
										<option value="2">Unavailable</option>
									</select>
								</div>
						</div>
					</div>
					<div class="modal-footer" align="end">
						<div>
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Add</button>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>

  		<%
			for (int i=0;i<products.size();i++) {
				Product product = products.get(i);
				String state = null;
				if (product.getState() == 1) {
					state = "On Sale";
				} else if (product.getState() == 2) {
					state = "Unavailable";
				}
		%>
  		<div class="modal fade" id="prodModal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="prodModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
		  <div class="modal-content">
			  <div class="modal-header">
				  <h4 class="modal-title" id="prodModalLongTitle">Update product</h4>
				  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					  <span aria-hidden="true">&times;</span>
				  </button>
			  </div>
			  <div class="modal-body">
				  <div class="col-md-12 order-md-last">
					  <form action="/updateProduct" method="post" class="bg-white p-5 product-form">
						  <input type="hidden" name="productId" value="<%=product.getId()%>">
						  <div class="form-group avatar" align="center">
							  <figure class="figure">
								  <img class="img-rounded" id='show_img<%=i%>' src="./upload/photo/<%=product.getPhoto()%>" alt="">
							  </figure>
							  <div class="form-group">
								  <input type="file" id='pic_img<%=i%>' class="file-uploader pull-left">
								  <input type="hidden" id="photo_id<%=i%>" name="photo" value="<%=product.getPhoto()%>">
							  </div>
							  <div class="form-group">
								  <button type="button" id="upload_btn<%=i%>" onclick='upload("upload_btn<%=i%>","pic_img<%=i%>","show_img<%=i%>","photo_id<%=i%>")' class="btn btn-secondary">Update Image</button>
							  </div>
						  </div>
						  <div class="form-group">
							  <label class="control-label">Name</label>
							  <input type="text" name="name" class="form-control" value="<%=product.getName()%>" required>
						  </div>
						  <div class="form-group">
							  <label class="control-label">Price</label>
							  <input type="text" name="price" class="form-control" value="<%=product.getPrice()%>" required>
						  </div>
						  <div class="form-group">
							  <label class="control-label">Inventory</label>
							  <input type="text" name="inventory" class="form-control" value="<%=product.getInventory()%>" required>
						  </div>
						  <div class="form-group">
							  <label class="control-label">Description</label>
							  <textarea type="text" name="description" class="form-control" placeholder="<%=product.getDescription()%>" required><%=product.getDescription()%></textarea>
						  </div>
						  <div class="form-group">
							  <label class="form-group">Type</label>
							  <select name="type" class="form-control" value="<%=product.getType()%>">
								  <option value="Fruit">Fruit</option>
								  <option value="Vegetable">Vegetable</option>
								  <option value="Poultry">Poultry</option>
								  <option value="Seafood">Seafood</option>
								  <option value="Dairy">Dairy</option>
							  </select>
						  </div>
						  <div class="form-group">
							  <label class="form-group">State</label>
							  <select name="state" class="form-control">
								  <%
								  	if (product.getState() == 1){
								  %>
								  <option value="1" selected="selected">On Sale</option>
								  <option value="2">Unavailable</option>
								  <%
									}else if (product.getState() == 2){
								  %>
								  <option value="1">On Sale</option>
								  <option value="2" selected="selected">Unavailable</option>
								  <%
									}
								  %>
							  </select>
						  </div>
				  </div>
			  </div>
			  <div class="modal-footer" align="end">
				  <div>
					  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					  <button type="submit" class="btn btn-primary">Update</button>
				  </div>
			  </div>
			  </form>
		  </div>
	  </div>
  </div>
  		<%
			}
		%>

		<section class="ftco-section ftco-no-pt ftco-no-pb py-5 bg-light">
      <div class="container py-4">
        <div class="row d-flex justify-content-center py-5">
          <div class="col-md-6">
          	<h2 style="font-size: 22px;" class="mb-0">Subcribe to our Newsletter</h2>
          	<span>Get e-mail updates about our latest shops and special offers</span>
          </div>
          <div class="col-md-6 d-flex align-items-center">
            <form action="#" class="subscribe-form">
              <div class="form-group d-flex">
                <input type="text" class="form-control" placeholder="Enter email address">
                <input type="submit" value="Subscribe" class="submit px-3">
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
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

        function upload(btn,pic_img,show_img,photo_id){
            var button = document.getElementById(btn);
            button.disabled=true;
            var formData = new FormData();
            formData.append('file', $("#"+pic_img)[0].files[0]);
            $.ajax({
                url: '/productPhotoUpload',
                data: formData,
                type: "POST",
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function(data) {
                console.log(data);
                document.getElementById(show_img).src = "/upload/photo/" + data;
                document.getElementById(photo_id).value = data;
                button.disabled=false;
            },
            error: function() {
                alert("file upload system is died! please try later!")
            }
        })
        }
	</script>

  </body>
</html>