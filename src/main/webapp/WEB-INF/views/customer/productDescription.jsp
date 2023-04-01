<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Detail</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
    <link rel='stylesheet' type='text/css' media='screen' href='${base}/css/customerCSS/stylePD.css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<!-- Jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- Web Icon -->
<link rel="shortcut icon" href="${base}/img/WebIcon.png" />
</head>
<body>
    <header>
<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
        <div class="logo">
            <a href="${base }/home">
                <p>Be.pro</p>
            </a>
        </div>
        <div class="navi">
            <div class="home">
                <a href="${base }/home">
                    <h4>Home</h4>
                </a>
            </div>
            <div class="shop">
                <a href="">
                    <h4>Shop</h4>
                </a>
            </div>
            <div class="port">
                <a href="">
                    <h4>Portfolio</h4>
                </a>
            </div>
            <div class="page">
                <a href="">
                    <h4>Page</h4>
                </a>
            </div>
            <div class="blog">
                <a href="">
                    <h4>Blog</h4>
                </a>
            </div>
            <div class="elements">
                <a href="">
                    <h4>Elements</h4>
                </a>
            </div>
        </div>
        <div class="nav-right">
            <c:choose>
        	<c:when test="${userLogined.username == null }">
            	<div class="register">
                <div class="register1">
                    <a href="${base }/register">
                        Sign up
                    </a>
                </div>
                <div class="login">
                    <a href="${base }/login">
                       Log In
                    </a>
                </div>
            </div>
            </c:when>
            <c:otherwise>
            <div class="register">
            <div class="register1">
            		<a href="${base }/logout">Log out</a>
            </div>
            	</div>
            	
            	
            </c:otherwise>
        </c:choose>
            <div class="cart-icon">
                <a href="${base }/cart/checkout">
                    <i class="fa-sharp fa-solid fa-bag-shopping">
                    <span id="iconShowTotalItemsInCart">${totalItems }</span>
                    </i>
                    
                </a>
            </div>
            <div class="find-icon">
                <a href="">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </a>
            </div>
        </div> 
    </header>

	<div class="direction">
		<div class="title">
	            <p>Men's Lifestyle Clothing</p>
	        </div>
	
	        <div class="director">
	            <a href="">Home</a> >
	            <a href="">Catalog</a> >
	            <a href="">Men</a> >
	            <a href="">Clothing</a>
	        </div>
	</div>
    <main>
        <div class="main-pro">
            <div class="desc">
                <h4>${product.category.name }</h4>
                <h2>${product.title }</h2>
                <img src="${base}/img/customerIMG/star-rate.png" alt="">
                <div class="cas">
                    <div class="color">
                         COLOR: 
                        <div class="colors">
                            <span id="W"></span>
                            <span id="R"></span>
                            <span id="Y"></span>
                            <span id="G"></span>
                        </div>
                    </div>
                </div>
                <div class="action">
                    <div class="price">
                        <h5 class="new-price">${product.price }</h5>
                        <h5 class="old-price">${product.priceSale }</h5>
                    </div>
                    <div class="pro-amount">
                        <button class="btn-minus" data-type="minus">-</button>
                        <input type="text" data-price=${product.price } class="input-amount" value="1" style="height: 40px; margin-top: 2px">
                        <button class="btn-add" data-type="add">+</button>
                    </div>
                        <button class="addtocart" style="border: 0; background-color: #3853d8; color: white;">Add</button>
                </div>
            </div>
            <div class="image">
                <div class="pro-img">
                    <img src="${base}/upload/${product.avatar }" alt="" width="250px">
                </div>
            </div>
            
        </div>

        <div class="comments">
            <div class="choose">
                <a href="${base }/productDetail/${product.id }">
                    REVIEW
                </a>
                <a href="">
                     SPECIFICATION 
                </a>
                <a href="${base }/productDetail/${product.id }/description" class="active">
                     DESCRIPTION 
                </a>
            </div>
            <div class="allreview">
                <h3>Description:</h3>
            </div>  
            <div class="comment">
                <div id="nar" style="display: block;">
                	<h4>Hình ảnh</h4>
                    <c:forEach var="image" items="${product.productList }">
                    	<img src="${base}/upload/Product/images/${image.title }" alt="" style="width: 150px">
                    </c:forEach>
                </div>
                <div class="descriptions">
                	<div class="shortDes">
                		<h4>Mô tả ngắn: </h4>
                		<p>${product.shortDes }</p>
                	</div>
                	<div class="detailDes">
                		<h4>Thông tin chi tiết:</h4>
                		
                		<p>${product.detailDes }</p>
                	</div>
                </div>
            </div>
        </div>

        <div class="brands">
            <i class="fa-solid fa-angle-left"></i>
            <div class="brand">
                <a href="">
                    <img src="${base}/img/customerIMG/bottom-logo-1.png" alt="">
                </a>
                <a href="">
                    <img src="${base}/img/customerIMG/bottom-logo-2.png" alt="">
                </a>
                <a href="">
                    <img src="${base}/img/customerIMG/bottom-logo-3.png" alt="">
                </a>
                <a href="">
                    <img src="${base}/img/customerIMG/bottom-logo-4.png" alt="">
                </a>
            </div>
            <i class="fa-solid fa-angle-right"></i>
        </div>
    </main>

    

    <footer>
        <div class="first-footer">
            <div>
                <a href="">
                    <i>ModeX</i>
                </a>
            </div>
            <div>
                <a href="">
                    <img src="${base}/img/customerIMG/social-media-logo.png" alt="">
                </a>
            </div>
        </div>

        <div class="second-footer">
            <div class="other">
                <a href="">Man</a>
                <a href="">Woman</a>
                <a href="">Lookbook</a>
                <a href="">sale</a>
                <a href="">Blog</a>
            </div>
            <div class="subscribe">
                <h3>Subscribe to news</h3>
                <input type="email" name="" id="" placeholder="Email adress">
                <button type="submit">Submit</button>
            </div>
        </div>

        <div class="third-footer">
            <div class="tit">Contact us</div>
            <div class="address">
                San Fransisco, Caalifornia
            </div>
            <div>400 Castro St, San Fransisco, CA</div>
            <div class="phone">(+1)686-868-9999</div>
            <img src="../images/visa.png" alt="">
        </div>
        
        <div>
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3723.8091968812764!2d105.81231251485464!3d21.040319185992125!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab11822392d9%3A0x35e9bf7fa41ae21f!2zQ3VuZyB0aOG7gyB0aGFvIFF14bqnbiBOZ-G7sWE!5e0!3m2!1svi!2s!4v1679581625752!5m2!1svi!2s" width="600" height="450" style="border:0;width: 250px; height: 200px; margin-top: 30px; margin-left: 30px" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></div>
    </footer>
    
    <script type="text/javascript">
    	$(document).ready(function(){
    		$(".addtocart").click(function() {
    			var quantity = $(".input-amount").val();
    			AddProductToCart('${base}', ${product.id }, quantity, ${product.priceSale});
			})
    	});
    	
    </script>

    <script src='${base}/js/customerJS/scriptCart.js'></script>
</body>
</html>