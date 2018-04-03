	<jsp:include page="../header/header.jsp" />
	<jsp:include page="../navigator/navigator.jsp" />
		<div class="wrapper wrapper-content">
			<div id="loadingDiv" class="loader"></div>
			<div class="row">
				<div class="col-lg-12 ibox-content" id="ibox-content">	
					<div class="col-lg-12 pull-left">
						<h4 class="title">Request Level Vulnerability</h4>
					</div>
					<div class="col-lg-12 pull-left">
						<table id="requestVulnerabilityCategory" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Vulnerabilities</th>
									<th>Description</th>
									<th>Level</th>
									<th>Details</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Cookie Security: Cookie not Sent Over SSL</td>
									<td>Description</td>
									<td>Cookie</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>XML External Entity Injection</td>
									<td>Description</td>
									<td>XML</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Cross Site Scripting : DOM</td>
									<td>Description</td>
									<td>XML</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>File Disclosure: J2EE</td>
									<td>Description</td>
									<td>URL Mapping</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Header Manipulation</td>
									<td>Description</td>
									<td>Header</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>JSON Injection</td>
									<td>Description</td>
									<td>JSON Object</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>SQL Injection</td>
									<td>Description</td>
									<td>SQL</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Open Redirect</td>
									<td>Description</td>
									<td>URL Redirection</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Path Manipulation</td>
									<td>Description</td>
									<td>Request Parameter</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="col-lg-12 ibox-content" id="ibox-content">	
					<div class="col-lg-12 pull-left">
						<h4 class="title">Code Level Vulnerability</h4>
					</div>
					<div class="col-lg-12 pull-left">
						<table id="codeVulnerabilityCategory" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Vulnerabilities</th>
									<th>Description</th>
									<th>Level</th>
									<th>Details</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Dynamic Code Evaluation: Unsafe Deserialization</td>
									<td>Description</td>
									<td>Cookie</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>J2EE Bad Practices: Non-Serializable Object Stored in Session</td>
									<td>Description</td>
									<td>XML</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Key Management: Hardcoded Encryption Key</td>
									<td>Description</td>
									<td>XML</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Null Dereference</td>
									<td>Description</td>
									<td>URL Mapping</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Password Management: Hardcoded Password</td>
									<td>Description</td>
									<td>Header</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Password Management: Password in Configuration File</td>
									<td>Description</td>
									<td>JSON Object</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Race Condition : Singleton Member Field</td>
									<td>Description</td>
									<td>SQL</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Unreleased Resource: Database</td>
									<td>Description</td>
									<td>URL Redirection</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Unreleased Resource: Files & Streams</td>
									<td>Description</td>
									<td>Request Parameter</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
								<tr>
									<td>Weak Encryption</td>
									<td>Description</td>
									<td>Request Parameter</td>
									<td><a href="" ><i class="fa fa-info-circle"></i></a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	<jsp:include page="../include_js/include_js.jsp" />

	<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
	<script src="/js/checklist/securityChecklist.js"></script>
			
	<jsp:include page="../footer/footer.jsp" />