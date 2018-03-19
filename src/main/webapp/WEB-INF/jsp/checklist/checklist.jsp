	<jsp:include page="../header/header.jsp" />
	<jsp:include page="../navigator/navigator.jsp" />
		<div class="wrapper wrapper-content">
			<div id="loadingDiv" class="loader"></div>
			<div class="row">
				<div class="col-lg-12 ibox-content" id="ibox-content">	
					<div class="col-lg-12 pull-left"><h3 class="title">Server Details &nbsp<span id="serverURL"></span></h3></div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 ibox-content" id="ibox-content">	
					<div class="col-lg-12 pull-left">
						<h4 class="title">Deployment Server Details</h4>
					</div>
					<div class="col-lg-12 pull-left">
						<table id="deploymentServerDetailTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Deployment Owner Details</th>
									<th>Operating System</th>
									<th>Web Server</th>
									<th>IP Address</th>
									<th>Last Request Served On</th>
								</tr>
							</thead>
						</table>
					</div>
					
					<div class="col-lg-12 pull-left">
						<h4 class="title">Application Server Technology</h4>
					</div>
					<div class="col-lg-12 pull-left">
						<table id="applicationServerTechnologyDetailTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Application Server Technology</th>
									<th>Descriptions</th>
								</tr>
							</thead>
						</table>
					</div>
					
					<div class="col-lg-12 pull-left">
						<h4 class="title">Server Side Programming</h4>
					</div>
					<div class="col-lg-12 pull-left">
						<table id="serverSideProgrammingTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Server Side Programming</th>
									<th>Descriptions</th>
								</tr>
							</thead>
						</table>
					</div>

					<div class="col-lg-12 pull-left">
						<h4 class="title">Client Side Programming</h4>
					</div>
					<div class="col-lg-12 pull-left">
						<table id="clientSideProgrammingTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Client Side Programming</th>
									<th>Descriptions</th>
								</tr>
							</thead>
						</table>
					</div>

				</div>				
			</div>
			<div class="row">
				<div class="col-lg-12 ibox-content" id="ibox-content">	
					<div class="col-lg-12 pull-left">
						<h4 class="title">Server Certificate Details</h4>
					</div>
					<div class="col-lg-12 pull-left">
						<table id="serverCertificateDetailTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Common Name</th>
									<th>Domains</th>
									<th>Organization</th>
									<th>Address</th>
									<th>Validity</th>
									<th>Signature Algorithm</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
<jsp:include page="../include_js/include_js.jsp" />

<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="/js/checklist/checklist.js"></script>
		
<jsp:include page="../footer/footer.jsp" />