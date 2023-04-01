<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Shopping cart</title>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${base}/css/customerCSS/style.css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	
	<!-- Import thư viện spring-form -->
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
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

    <main>
        <div class="container">
            <div class="title">Gio hang cua ban</div>
            
            <div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th scope="col" class="border-0 bg-light">
								<div class="p-2 px-3 text-uppercase">Product</div>
							</th>
							<th scope="col" class="border-0 bg-light">
								<div class="py-2 text-uppercase">Price</div>
							</th>
							<th scope="col" class="border-0 bg-light">
								<div class="py-2 text-uppercase">Quantity</div>
							</th>
							<th scope="col" class="border-0 bg-light">
								<div class="py-2 text-uppercase">Remove</div>
							</th>
						</tr>
					</thead>
					<tbody>
						
						<!-- hiển thị danh sách sản phẩm trong giỏ hàng -->
						<!-- cart: là data trên session -->
						<!-- dữ liệu trên session sẽ đọc được ở bất page jsp nào -->
						<c:forEach items="${cart.cartItems }" var="ci">
							<tr id="item_${ci.productId }">
								<th scope="row" class="border-0">
									<div class="p-2">
									<img alt="..." src="${base }/upload/${ci.avatar }" style="max-width: 50px">
										<div class="ml-3 d-inline-block align-middle">
											<h5 class="mb-0">
												<a href="#" class="text-dark d-inline-block align-middle">
													${ci.productName }
												</a>
											</h5>
											<span class="text-muted font-weight-normal font-italic d-block">${ci.category}</span>
										</div>
									</div>
								</th>
								<td class="border-0 align-middle">
									<fmt:setLocale value="vi_VN"/>
									<strong><fmt:formatNumber value="${ci.priceUnit }" type="currency"/></strong>
								</td>
								<td class="border-0 align-middle">
									<button onclick="UpdateQuanlityCart('${base }', ${ci.productId}, -1)" value="-">-</button>
									<strong><input id="quanlity_${ci.productId}" value="${ci.quanlity }" name="nquanlity_${ci.productId}"></strong>
									<button onclick="UpdateQuanlityCart('${base }', ${ci.productId}, 1)" value="+">+</button>
								</td>
								<td class="border-0 align-middle">
									<button onclick="DeleteItemsCart('${base }', ${ci.productId}, 1)" value="+"><i class="fa fa-trash">Xóa</i></button>
								</td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
			
        </div>


		<!-- form cho việc thanh toán -->
			<form action="${base }/cart/checkout" method="post">
			
				<div class="row py-5 p-4 bg-white rounded shadow-sm">
					<div class="col-lg-6">
						<!-- <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Coupon code</div>
						<div class="p-4">
							<p class="font-italic mb-4">If you have a coupon code, please enter it in the box below</p>
							<div class="input-group mb-4 border rounded-pill p-2">
								<input type="text" placeholder="Apply coupon" aria-describedby="button-addon3" class="form-control border-0">
								<div class="input-group-append border-0">
									<button id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill">
										<i class="fa fa-gift mr-2"></i>Apply coupon
									</button>
								</div>
							</div>
						</div> -->
						<div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thông tin khách hàng</div>
						<div class="p-4">
						
							<div class="form-group" style="margin-bottom: 5px;">
								<label for="customerPhone">Họ và tên khách hàng</label>
								<input type="text" class="form-control" id="customerFullName" name="customerFullName" value="${userLogined.username }" placeholder="Full name">
							</div>
							<div class="form-group" style="margin-bottom: 5px;">
								<label for="customerEmail">Địa chỉ Email</label>
								<input type="email" class="form-control" id="customerEmail" name="customerEmail" value="${userLogined.email }" placeholder="Enter email">
								<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
							</div>
							<div class="form-group" style="margin-bottom: 5px;">
								<label for="customerPhone">Phone</label>
								<input type="tel" class="form-control" id="customerPhone" name="customerPhone" value="${userLogined.phone }" placeholder="Phone">
							</div>
							<div class="form-group" style="margin-bottom: 5px;">
								<label for="customerAddress">Địa chỉ giao hàng</label>
								<input type="text" class="form-control" id="customerAddress" name="customerAddress" value="${userLogined.shipping_address }" placeholder="Address">
							</div>
							
							
						</div>
					</div>
					<div class="col-lg-6">
						<div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thông tin thanh toán</div>
						<div class="p-4">
							<p class="font-italic mb-4">Shipping and additional costs are calculated based on values you have entered.</p>
							<ul class="list-unstyled mb-4">
								<li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Order Subtotal </strong><strong>${totalItems }</strong></li>
								<li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Shipping and handling</strong><strong>$0.00</strong></li>
								<li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tax</strong><strong>$0.00</strong></li>
								<li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong> <h5 class="font-weight-bold totalPrice">${totalPrice}</h5></li>
							</ul>
							<button type="submit" class="btn btn-dark rounded-pill py-2 btn-block">Procceed to checkout</button>
						</div>
					</div>
				</div>

			</form>

    </main>

    <jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include>

    <script src="${base}/js/customerJS/jquery-3.6.1.min.js"></script>
    <script src='${base}/js/customerJS/scriptCart.js'></script>
</body>
</html>