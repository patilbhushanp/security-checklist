$(document).ready(function() {
	$('#loadingDiv').hide();
	initSecurityCheckListTable();
});

function initSecurityCheckListTable() {
	$('#requestVulnerabilityCategory').DataTable({
		"columns" : [ {
			"width" : "40%"
		}, {
			"width" : "40%"
		}, {
			"width" : "15%"
		}, {
			"width" : "5%"
		} ],
		searching : false,
		paging : false,
		info : false
	});

	$('#codeVulnerabilityCategory').DataTable({
		"columns" : [ {
			"width" : "40%"
		}, {
			"width" : "40%"
		}, {
			"width" : "15%"
		}, {
			"width" : "5%"
		} ],
		searching : false,
		paging : false,
		info : false
	});
}