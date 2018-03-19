		<nav class="navbar navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Security Checklist</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="/about">About</a></li>
					</ul>
					<form action="/" method="post">
						<ul class="nav navbar-nav pull-right" style="padding-top: 15px;">
							<%
								String websiteURL = request.getParameter("websiteURL");
								websiteURL = websiteURL == null ? "" : websiteURL;
							%>
							<li><input type="text" size=30 name="websiteURL" id="websiteURL" placeholder="enter website name..." value="<%=websiteURL%>" /></li>
							<li>&nbsp;</li>
							<li><input type="submit" value="search" class="btn-default" name="searchButton" id="searchButton" /></li>
						</ul>
					</form>
				</div>
			</div>
		</nav>
	