<%--<%@ page import="com.freshmel.dataMapper.OrderMapper" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page import="com.freshmel.model.Order" %>--%>
<%--<%@ page import="com.freshmel.model.Customer" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
    <%--Customer customer = (Customer) session.getAttribute("customer");--%>
    <%--OrderMapper orderMapper = new OrderMapper();--%>
    <%--List<Order> orders = orderMapper.findByCustomerId(customer.getId());--%>
    <%--System.out.println(orders.size());--%>
<%--%>--%>
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
                <p class="breadcrumbs"><span class="mr-2"><a href="index.jsp">Home</a></span> <span>My Order</span></p>
                <h1 class="mb-0 bread">Coming soon, Next Feature...</h1>
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
                            <th>Product List</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Delivery Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="text-md-center">
                            <td class="product-remove">
                                <span class="form-group"><a href="#"><i class="fa fa-paper-plane"></i></a>
                                    <ul class="meta list list-unstyled">
                                        <li class="name" style="font-size: 20px;">Order 1</li>
                                        <li class="email" style="font-size: 10px;"></i>20:45 10-10-2019</li>
                                    </ul>
                                </span>
                            </td>
                        </tr>
                        <tr class="text-center">

                            <td class="image-prod"><div class="img" style="background-image:url(images/product-1.jpg);"></div></td>

                            <td class="product-name">
                                <h3>Bell Pepper</h3>
                                <p>Far far away, behind the word mountains, far from the countries</p>
                            </td>

                            <td class="price">$4.90</td>

                            <td class="quantity">100</td>

                            <td class="state">10-09-2019</td>
                        </tr><!-- END TR-->

                        <tr class="text-center">

                            <td class="image-prod"><div class="img" style="background-image:url(images/product-2.jpg);"></div></td>

                            <td class="product-name">
                                <h3>Bell Pepper</h3>
                                <p>Far far away, behind the word mountains, far from the countries</p>
                            </td>

                            <td class="price">$15.70</td>

                            <td class="quantity">
                                <div class="input-group mb-3">
                                    <input type="text" name="quantity" class="quantity form-control input-number" value="1" min="1" max="100">
                                </div>
                            </td>

                            <td class="total">$15.70</td>
                        </tr><!-- END TR-->
                        </tbody>
                        <tbody>
                        <tr class="text-left">
                            <td class="product-remove">
                                <span class="form-group"><a href="#"><i class="fa fa-info"></i></a> Order 2</span>
                            </td>
                        </tr>
                        <tr class="text-center">
<%--                            <td class="product-remove">--%>
<%--                                <a href="#">--%>
<%--                                    <span class="fa fa-trash"></span>--%>
<%--                                </a>--%>
<%--                                <a href="#" data-toggle="modal" data-target="#prodModal">--%>
<%--                                    <span class="fa fa-edit"></span>--%>
<%--                                </a>--%>
<%--                            </td>--%>

                            <td class="image-prod"><div class="img" style="background-image:url(images/product-1.jpg);"></div></td>

                            <td class="product-name">
                                <h3>Bell Pepper</h3>
                                <p>Far far away, behind the word mountains, far from the countries</p>
                            </td>

                            <td class="price">$4.90</td>

                            <td class="inventory">100</td>

                            <td class="state">On Sale</td>
                        </tr><!-- END TR-->

                        <tr class="text-center">
                            <td class="product-remove"><a href="#"><span class="ion-ios-close"></span></a></td>

                            <td class="image-prod"><div class="img" style="background-image:url(images/product-2.jpg);"></div></td>

                            <td class="product-name">
                                <h3>Bell Pepper</h3>
                                <p>Far far away, behind the word mountains, far from the countries</p>
                            </td>

                            <td class="price">$15.70</td>

                            <td class="quantity">
                                <div class="input-group mb-3">
                                    <input type="text" name="quantity" class="quantity form-control input-number" value="1" min="1" max="100">
                                </div>
                            </td>

                            <td class="total">$15.70</td>
                        </tr><!-- END TR-->

                        <tr class="text-center">
                            <td class="product-remove"><a href="#"><span class="ion-ios-close"></span></a></td>

                            <td class="image-prod"><div class="img" style="background-image:url(images/product-3.jpg);"></div></td>

                            <td class="product-name">
                                <h3>Bell Pepper</h3>
                                <p>Far far away, behind the word mountains, far from the countries</p>
                            </td>

                            <td class="price">$15.70</td>

                            <td class="quantity">
                                <div class="input-group mb-3">
                                    <input type="text" name="quantity" class="quantity form-control input-number" value="1" min="1" max="100">
                                </div>
                            </td>

                            <td class="total">$15.70</td>
                        </tr><!-- END TR-->

                        <tr class="text-center">
                            <td class="product-remove"><a href="#"><span class="ion-ios-close"></span></a></td>

                            <td class="image-prod"><div class="img" style="background-image:url(images/product-4.jpg);"></div></td>

                            <td class="product-name">
                                <h3>Bell Pepper</h3>
                                <p>Far far away, behind the word mountains, far from the countries</p>
                            </td>

                            <td class="price">$15.70</td>

                            <td class="quantity">
                                <div class="input-group mb-3">
                                    <input type="text" name="quantity" class="quantity form-control input-number" value="1" min="1" max="100">
                                </div>
                            </td>

                            <td class="total">$15.70</td>
                        </tr><!-- END TR-->

                        <tr class="text-center">
                            <td class="product-remove"><a href="#"><span class="ion-ios-close"></span></a></td>

                            <td class="image-prod"><div class="img" style="background-image:url(images/product-5.jpg);"></div></td>

                            <td class="product-name">
                                <h3>Bell Pepper</h3>
                                <p>Far far away, behind the word mountains, far from the countries</p>
                            </td>

                            <td class="price">$15.70</td>

                            <td class="quantity">
                                <div class="input-group mb-3">
                                    <input type="text" name="quantity" class="quantity form-control input-number" value="1" min="1" max="100">
                                </div>
                            </td>

                            <td class="total">$15.70</td>
                        </tr><!-- END TR-->

                        <tr class="text-center">
                            <td class="product-remove"><a href="#"><span class="ion-ios-close"></span></a></td>

                            <td class="image-prod"><div class="img" style="background-image:url(images/product-6.jpg);"></div></td>

                            <td class="product-name">
                                <h3>Bell Pepper</h3>
                                <p>Far far away, behind the word mountains, far from the countries</p>
                            </td>

                            <td class="price">$15.70</td>

                            <td class="quantity">
                                <div class="input-group mb-3">
                                    <input type="text" name="quantity" class="quantity form-control input-number" value="1" min="1" max="100">
                                </div>
                            </td>

                            <td class="total">$15.70</td>
                        </tr><!-- END TR-->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Product Modal -->
<div class="modal fade" id="prodModal" tabindex="-1" role="dialog" aria-labelledby="prodModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="prodModalLongTitle">Bell Pepper</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-md-12 order-md-last">
                    <form action="#" class="bg-white p-5 product-form">
                        <div class="form-group avatar" align="center">
                            <figure class="figure">
                                <img class="img-rounded" src="https://www.w3schools.com/howto/img_avatar.png" alt="">
                            </figure>
                            <div class="form-group">
                                <input type="file" class="file-uploader pull-left">
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-secondary">Update Image</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Name</label>
                            <input type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Price</label>
                            <input type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Description</label>
                            <textarea type="text" class="form-control" required></textarea>
                        </div>
                        <div class="form-group">
                            <label class="form-group">Type</label>
                            <select name="" id="" class="form-control">
                                <option value="">Fruit</option>
                                <option value="">Vegetable</option>
                                <option value="">Poultry & Egg</option>
                                <option value="">Seafood</option>
                                <option value="">Dairy</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="form-group">State</label>
                            <select name="" id="" class="form-control">
                                <option value="">On Sale</option>
                                <option value="">For Sale</option>
                                <option value="">Sold Out</option>
                            </select>
                        </div>
                </div>
            </div>
            <div class="modal-footer" align="end">
                <div>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Confirm</button>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>
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