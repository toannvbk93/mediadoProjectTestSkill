<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
		<h1>Admin site login system</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<form class="form-horizontal" action="#" method="post">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3"
							placeholder="Email" name="email">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="inputPassword3"
							placeholder="Password" name="password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Sign in</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />