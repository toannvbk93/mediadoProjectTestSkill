<div class="col-md-4">
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">
				<span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>
				Profile user
			</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class=" col-md-9 col-lg-9 ">
					<table class="table table-user-information">
						<tbody>
							<tr>
								<td><span class="glyphicon glyphicon-user"></span></td>
								<td>${sessionUsername}</td>
							</tr>
							<tr>
								<td><span class="glyphicon glyphicon-envelope"></span></td>
								<td>${sessionEmail}</td>
							</tr>
							<tr>
								<td><span class="glyphicon glyphicon-time"></span></td>
								<td>${sessionCreateat}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="panel-footer">
			<a data-original-title="Broadcast Message" data-toggle="tooltip"
				type="button" class="btn btn-sm btn-primary" href="https://facebook.com"><i
				class="glyphicon glyphicon-envelope" ></i></a> <span class="pull-right">
				<a data-toggle="modal" data-target="#editProfile"
				data-original-title="Edit this user" data-toggle="tooltip"
				type="button" class="btn btn-sm btn-warning"><i
					class="glyphicon glyphicon-edit"></i></a>
			</span>
		</div>
	</div>
</div>
<!--Modal popup -->
<div class="modal fade" id="editProfile" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">Edit profile</div>
			<div class="modal-body">
				<form action="editProfile" method="post">
					<div class="form-group">
						<label for="name">Username</label> <input type="text"
							class="form-control" id="username" placeholder="Username"
							value="${sessionUsername}" name="username">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" class="form-control" id="exampleInputPassword1"
							placeholder="Enter password" name="password"> <input
							type="hidden" name="userId" value="${sessionUserId}">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal end -->