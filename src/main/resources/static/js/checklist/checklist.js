var websiteURL;

$(document).ready(function() {
	$('#loadingDiv').hide();
	websiteURL = $('#websiteURL').val();
	if (websiteURL != '') {
		$('#loadingDiv').show();
		var $regExForDomain = /^[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\.[a-zA-Z]{2,}$/;
		if (websiteURL.match($regExForDomain)) {
			$.ajax({
			  type: "GET",
			  url: "/getApplicationDetails?websiteURL="+websiteURL,
			  dataType: "json",
			  success: function(resultData) {},
			  error: function(){
				  $('#loadingDiv').hide();
				 alert("Failed to load data for - " + websiteURL);
			  }
			});
			
			$.ajax({
			  type: "GET",
			  async: "false",
			  url: "/getApplicationSSLDetails?websiteURL="+websiteURL,
			  dataType: "json",
			  success: function(resultData) { 
				$('#loadingDiv').hide();
				$('#websiteURL').val('');
				getDeploymentServerDetailTable();
				getApplicationServerTechnologyDetailTable();
				getServerSideProgrammingTable();
				getClientSideProgrammingTable();
				getServerCertificateDetailTable();
				$('#serverURL').html('<small>URL - ' + websiteURL + '</small>')
			  },
			  error: function(){
				  $('#loadingDiv').hide();
				  alert("Failed to load data for - " + websiteURL);
			  }
			});
		} else {
			alert("Wrong Website URL - " + websiteURL);
			$('#websiteURL').val('');
		}
	}
	
});

function getDeploymentServerDetailTable() {
	$('#deploymentServerDetailTable').DataTable({
		"ajax" : '/getDeploymentServerDetails',
		"columns" : [ {
			"data" : "netblockOwner",
			"width" : "30%"
		}, {
			"data" : "operatingSystem",
			"width" : "20%"
		}, {
			"data" : "webServer",
			"width" : "20%"
		}, {
			"data" : "ipaddress",
			"width" : "15%"
		}, {
			"data" : "lastUsedOn",
			"width" : "15%"
		} ],
		searching : false,
		paging : false,
		info : false
	});
}

function getApplicationServerTechnologyDetailTable() {
	$('#applicationServerTechnologyDetailTable').DataTable({
		"ajax" : '/getApplicationServerDetails',
		"columns" : [ {
			"data" : "applicationServerTechnology",
			"width" : "30%"
		}, {
			"data" : "description",
			"width" : "70%"
		} ],
		searching : false,
		paging : false,
		info : false
	});
}

function getServerSideProgrammingTable() {
	$('#serverSideProgrammingTable').DataTable({
		"ajax" : '/getServerSideTechnologyDetails',
		"columns" : [ {
			"data" : "serverSideTechnology",
			"width" : "30%"
		}, {
			"data" : "description",
			"width" : "70%"
		} ],
		searching : false,
		paging : false,
		info : false
	});
}

function getClientSideProgrammingTable() {
	$('#clientSideProgrammingTable').DataTable({
		"ajax" : '/getClientSideTechnologyDetails',
		"columns" : [ {
			"data" : "clientSideTechnology",
			"width" : "30%"
		}, {
			"data" : "description",
			"width" : "70%"
		} ],
		searching : false,
		paging : false,
		info : false
	});
}

function getServerCertificateDetailTable() {
	$('#serverCertificateDetailTable').DataTable({
		"ajax" : '/getSSLDetails',
		"columns" : [ {
			"data" : "commonName",
			"width" : "15%"
		}, {
			"data" : "sans",
			"width" : "20%"
		}, {
			"data" : "organizations",
			"width" : "15%"
		}, {
			"data" : "location",
			"width" : "20%"
		}, {
			"data" : "validity",
			"width" : "20%"
		}, {
			"data" : "signatureAlgorithm",
			"width" : "10%"
		} ],
		searching : false,
		paging : false,
		info : false
	});
}
