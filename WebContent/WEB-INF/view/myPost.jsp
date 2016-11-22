<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<%
	if (request.getSession().getAttribute("sessionEmail") == null) {
		response.sendRedirect("index.jsp");
	}
%>
<div class="container">
	<!-- Statr header -->
	<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		<ul class="nav navbar-nav">
			<li class="nav-item"><a class="nav-link" href="allPost">All post</a></li>
			<li class="nav-item"><a class="nav-link" href="#">My post</a></li>
			<li class="nav-item"><a class="nav-link" href="logout">Log
					out</a></li>
		</ul>
	</nav>
	<!-- End header -->
	<div class="row">
		<div class="col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">All post</div>
				<div class="panel-body">
					<ul class="list-group">
						<c:forEach var="post" items="${listPost}">
							<li class="list-group-item"><a class="black-text" href="postDetail?postId=${post.id}">
									<c:out value="${post.title}" />
							</a>
								<p>
									<c:out value="${post.content}" />
								</p></li>
						</c:forEach>
					</ul>
					<div class="text-center">
						<nav aria-label="Page navigation">
							<ul class="pagination" style="margin: 0px auto;">
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Previous</span>
								</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">4</a></li>
								<li class="page-item"><a class="page-link" href="#">5</a></li>
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Next</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Profile user</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class=" col-md-9 col-lg-9 ">
							<table class="table table-user-information">
								<tbody>
									<tr>
										<td>Username:</td>
										<td>${sessionUsername}</td>
									</tr>
									<tr>
										<td>Email:</td>
										<td>${sessionEmail}</td>
									</tr>
									<tr>
										<td>Create at: </td>
										<td>${sessionCreateat}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<a data-original-title="Broadcast Message" data-toggle="tooltip"
						type="button" class="btn btn-sm btn-primary"><i
						class="glyphicon glyphicon-envelope"></i></a> <span class="pull-right">
						<a href="#" data-original-title="Edit this user"
						data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i
							class="glyphicon glyphicon-edit"></i></a>
					</span>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />