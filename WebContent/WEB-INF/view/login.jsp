<jsp:include page="/WEB-INF/view/header.jsp" />
<div class="container">
	<!-- Statr header -->
	<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		<ul class="nav navbar-nav">
			<li class="nav-item"><a class="nav-link"
				href="http://localhost:8080/projectTest/">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="registerForm">Register</a></li>
		</ul>
	</nav>
	<!-- End header -->
	<%
		Cookie[] cookies = request.getCookies();
		String email = "", password = "", remember = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("email")) {
					email = cookie.getValue();
				}
				if (cookie.getName().equals("password")) {
					password = cookie.getValue();
				}
				if (cookie.getName().equals("remember")) {
					remember = cookie.getValue();
				}
			}
		}
	%>
	<div class="row">
		<div class="login" style="margin: 5px auto; width: 400px">
			<h2>If you have account:</h2>
			<form class="form-horizontal" method="POST" action="login"
				autocomplete="off">
				<div class="form-group">
					<label class="col-sm-2 control-label" for="email">Email</label>
					<div class="col-sm-10">
						<input type="email" id="email" name="email" class="form-control"
							placeholder="Email" autocomplete="off" for="email"
							value="<%=email%>" 　required="true">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input type="password" name="password" class="form-control"
							id="inputPassword3" placeholder="Password" autocomplete="off"
							value="<%=password%>" 　required="true">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label> <input type="checkbox" name="rememberMe"
								value="Y"> Remember me
							</label>
						</div>
					</div>

				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Sign in</button>
					</div>
				</div>
			</form>
			<div class="col-sm-offset-2 col-sm-10">
				<div class="popover-markup">
					<a href="#" class="trigger">Forgot password</a>
					<div class="content hide">
						<form action="resetpassword" method="post">
							<div class="form-group">
								<input type="text" class="form-control"
									placeholder="Email login" name="email">
							</div>
							<div class="form-group">
								<input type="password" class="form-control"
									placeholder="Password reset" name="password">
							</div>
							<button type="submit" class="btn btn-default btn-xs">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Forgot password -->
<script>
	$('.popover-markup>.trigger').popover({
		html : true,
		title : function() {
			return $(this).parent().find('.head').html();
		},
		content : function() {
			return $(this).parent().find('.content').html();
		}
	});
</script>
<jsp:include page="/WEB-INF/view/footer.jsp" />