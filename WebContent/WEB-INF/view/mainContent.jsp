<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<%
	if (request.getSession().getAttribute("sessionEmail") == null) {
		response.sendRedirect("index.jsp");
	}
%>
<div class="container">
	<!-- Statr header -->
	<jsp:include page="/WEB-INF/view/navbar.jsp" />
	<!-- End header -->
	<div class="row">
		<div class="col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
					All post
				</div>
				<div class="panel-body">
					<ul class="list-group">
						<c:forEach var="post" items="${listPost}">
							<li class="list-group-item"><a class="black-text"
								href="postDetail?postId=${post.id}"> <span
									class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
									<c:out value="${post.title}" />
							</a>
								<p>
									<c:out value="${post.content}" />
								</p></li>
						</c:forEach>
					</ul>
					<!-- Add post -->
					<form method="POST" action="addPost">
						<div class="form-group">
							<input type="hidden" name="userId" value="${sessionUserId}" />
							<label for="title">Title: </label>
							<input type="text" id="title" class="form-control" placeholder="Title" name = "title"/>
							<table class="table">
								<tr>
									<td>
										<label for="tag">Enter tag: </label>
									 	<input type="text" class="form-control" id="tag" placeholder="Create new tag" name = "tag">
									</td>
									<td>
										<label for="sel1">Select Tag relation:</label>
										<select class="form-control" id="tagRelation" name="tagRelation">
											<c:forEach var="tag" items="${listTag}">
												<option value="${tag.id}">${tag.name}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</table>
							<label for="content">Content:</label>
							<textarea type="text" class="form-control"
								placeholder="Comment here..." rows="5" id="content" name = "content"></textarea>
							<br />

							<button type="submit" class="btn btn-default">Submit</button>
						</div>
					</form>
					<!-- End add post -->
					<!-- Paging -->
					<div class="text-center">
						<%--For displaying Previous link except for the 1st page --%>
						<c:if test="${currentPage > 1}">
							<td><ul class="pager">
									<li class="previous"><a href="?page=${currentPage - 1}">Previous</a></li>
								</ul></td>
						</c:if>
						<%--For displaying Next link --%>
						<c:if test="${currentPage lt noOfPages}">
							<td><ul class="pager">
									<li class="next"><a href="?page=${currentPage + 1}">Next</a></li>
								</ul></td>
						</c:if>
					</div>
					<!-- End paging -->
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/view/profileUser.jsp" /></div>
</div>
<jsp:include page="/WEB-INF/view/footer.jsp" />