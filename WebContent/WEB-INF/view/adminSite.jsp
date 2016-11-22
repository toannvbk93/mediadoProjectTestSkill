<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<%
	if (request.getSession().getAttribute("sessionUser") == null) {
		response.sendRedirect("index.jsp");
	}
%>
<div class="container">
	<div class="row">
		<h1>All post</h1>
		<table class="table table-bordered">
			<tr>
				<td align="center">No</td>
				<td align="center">Title</td>
				<td align="center">Content</td>
				<td align="center">Edit</td>
				<td align="center">Delete</td>
			</tr>
			<c:forEach var="adminPost" items="${listAdminPost}">
				<tr>
					<td align="center">${adminPost.post.id}</td>
					<td align="center">
						<p>${adminPost.post.title}</p>
					</td>
					<td align="center">
						<p>${adminPost.post.content}</p>
						<table class="table">
							<tr>
								<td><a href="#">${adminPost.user.username}</a></td>
								<td>${adminPost.post.createAt }</tb>
								<td><a class="black-text" href="#"> <span
										class="glyphicon glyphicon-tag" aria-hidden="true">html</span>
								</a></td>
							</tr>
						</table>
					</td>
					<td align="center"><a href="editPost?editPostId=${post.id }"
						class="btn btn-info" role="button">Edit</a></td>
					<td align="center"><a
						href="deletePost?deletePostId=${post.id}" class="btn btn-info"
						role="button">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="row">
		<h1>All user</h1>
		<table class="table table-bordered">
			<thread>
			<tr>
				<td align="center">User id</td>
				<td align="center">Email</td>
				<td align="center">Username</td>
				<td align="center">Password</td>
				<td align="center" s>Edit</td>
				<td>Delete</td>
			</tr>
			</thread>
			<tbody>
				<c:forEach var="user" items="${listUser}">
					<tr>
						<form action="adminUser?userId=${user.id}" method="post">
							<td>${user.id }</td>
							<td><input value="${user.email}" name="email" type="text"
								class="form-control" /></td>
							<td><input value="${user.username}" name="username"
								type="text" class="form-control" /></td>
							<td><input value="${password}" name="password"
								type="password" class="form-control" /></td>
							<td align="center"><input name="action" class="btn btn-info"
								type="submit" value="Edit" /></td>
							<td align="center"><input name="action" class="btn btn-info"
								type="submit" value="Delete" /></td> <input value="${user.id}"
								name="id" type="hidden" class="form-control" />
						</form>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />