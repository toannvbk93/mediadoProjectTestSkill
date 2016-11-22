<nav class="navbar navbar-light bg-faded"
	style="background-color: #e3f2fd;">
	<ul class="nav navbar-nav">
		<li class="nav-item"><a class="nav-link" href="allPost">All
				post</a></li>
		<li class="nav-item"><a class="nav-link"
			href="myPost?email=${sessionEmail}&hash=${hash}">My post</a></li>
		<li class="nav-item"><a class="nav-link" href="logout">Log
				out</a></li>
	</ul>
	<form class="form-inline float-xs-left" style="margin: 8px;"
		method="get" action="search">
		<input name="namepost" class="form-control" type="text"
			placeholder="Search post">
		<button class="btn btn-outline-success" type="submit">Search</button>
	</form>
</nav>