<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp"></jsp:include>
<%
	if (request.getSession().getAttribute("sessionEmail") == null) {
		response.sendRedirect("index.jsp");
	}
%>
<div class="container">
	<!-- Statr header -->
	<jsp:include page="/WEB-INF/view/navbar.jsp" />
	</nav>
	<!-- End header -->
	<div class="row">
		<div class="col-md-8">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3>${post.title}</h3>
				</div>
				<div class="panel-body">
					<div class="col-md-11">
						<table class="table">
							<tbody>
								<tr>
									<td>
										<p>${post.content}</p>
									</td>
									<table class="table">
										<tr>
											<td><a href="#">${user.username}</a></td>
											<td>${post.createAt}</tb>
											<td><c:forEach var="tag" items="${listTag}">
													<a class="black-text" href="#"><span
														class="glyphicon glyphicon-tag" aria-hidden="true">
															${tag.name} </span></a>
												</c:forEach></td>
											<td><a data-toggle="modal" data-target="#myModal">Comments
													<span class="badge">${countComment}</span>
											</a><br></td>
										</tr>
									</table>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/profileUser.jsp" />
		<!--Modal popup -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">All comment</h4>
					</div>
					<div class="modal-body">
						<c:forEach var="userComment" items="${listUserComment}">
							<td class="col-xs-2">${userComment.comment.content}</td>
							<table class="table">
								<td>Comment by <a href="#">${userComment.user.username}</a></td>
								<td>${userComment.comment.createAt}</td>
							</table>
						</c:forEach>
						<form action="postDetail?postId=${post.id}" method="post">
							<input type="hidden" name="userCommentId" value="${sessionUserId}">
							<div class="form-group">
								<textarea rows="4" cols="50" type="text" class="form-control"
									placeholder="Comment..." name="comment"></textarea>
							</div>
							<button type="submit" class="btn btn-default" href="">Submit</button>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal end -->
	</div>
	<jsp:include page="/WEB-INF/view/footer.jsp"></jsp:include>