<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<!-- Statr header -->
<div class="container">
	<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		<ul class="nav navbar-nav">
			<li class="nav-item"><a class="nav-link"
				href="http://localhost:8080/projectTest/">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="goLogin">Login</a></li>
		</ul>
	</nav>
	<!-- End header -->

	<form class="form-horizontal" method="post" action="register"
		autocomplete="off">
		<div class="form-group">
			<label class="control-label col-sm-2" for="email">Email:</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="email"
					placeholder="Enter email" name="email" autocomplete="off">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="username">Usernme:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="username"
					placeholder="Enter username" name="username" autocomplete="off">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="pass1">Password:</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="pass1"
					placeholder="Enter password" name="password" autocomplete="off">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="pass2">Confirm
				Password:</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="pass2"
					placeholder="Confirm password" name="password_confirm"
					onkeyup="checkPass(); return false;" autocomplete="off"> <span
					id="confirmMessage" class="confirmMessage"></span>
			</div>

		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</div>
	</form>
</div>
<!-- check confirm password -->
<script>
	function checkPass() {
		var pass1 = document.getElementById('pass1');
		var pass2 = document.getElementById('pass2');
		var message = document.getElementById('confirmMessage');
		var goodColor = "#66cc66";
		var badColor = "#ff6666";
		if (pass1.value == pass2.value) {
			pass2.style.backgroundColor = goodColor;
			message.style.color = goodColor;
			message.innerHTML = "Passwords Match!"
		} else {
			pass2.style.backgroundColor = badColor;
			message.style.color = badColor;
			message.innerHTML = "Passwords Do Not Match!"
		}
	}
</script>
<jsp:include page="/WEB-INF/view/footer.jsp" />