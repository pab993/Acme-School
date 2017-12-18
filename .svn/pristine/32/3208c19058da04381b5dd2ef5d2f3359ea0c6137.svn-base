<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="shortcut icon" href="favicon.ico"/> 

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="scripts/bootstrap.js"></script>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.js"></script>
<script type="text/javascript" src="scripts/jmenu.js"></script>

<link rel="stylesheet" href="styles/bold.css" type="text/css">
<link rel="stylesheet" href="styles/bootstrap.css" type="text/css">
<link rel="stylesheet" href="styles/common.css" type="text/css">
<link rel="stylesheet" href="styles/jmenu.css" media="screen"
	type="text/css" />
<link rel="stylesheet" href="styles/displaytag.css" type="text/css">


<!-- CookieBar -->

<link rel="stylesheet" href="styles/jquery.cookiebar.css" type="text/css" />
<!-- <link rel="stylesheet" href="lib/jquery-cookieBar/jquery.cookiebar.css" type="text/css" /> -->
<script type="text/javascript" src="lib/jquery-cookieBar/jquery.cookiebar.js"></script>

<script>
	$(document).ready(function() {
		$.cookieBar({
			message : '<spring:message code="master.page.cookie" />',
			acceptText : '<spring:message code="master.page.acceptcookie" />'
		});
	});
</script>


<style type="text/css">body{margin:0px; width: 100%}</style>

<title><tiles:insertAttribute name="title" ignore="true" /></title>

<script type="text/javascript">
	$(document).ready(function() {
		$("#jMenu").jMenu();
	});

	function askSubmission(msg, form) {
		if (confirm(msg))
			form.submit();
	}
</script>

</head>

<body>

	<div style="margin:0;">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="col-md-10 col-md-offset-1">
		<jstl:if test="${message != null && message != ''}">
			<div class="alert alert-danger">
				¡ERROR! <spring:message code="${message}" />
			</div>
		</jstl:if>
		<jstl:if test="${messageSuccess != null && messageSuccess != ''}">
			<div class="alert alert-success">
				<spring:message code="${messageSuccess}" />
			</div>
		</jstl:if>
		<h1>
			<tiles:insertAttribute name="title" />
		</h1>
		<tiles:insertAttribute name="body" />	
	</div>
	<div class="footer">
		<tiles:insertAttribute name="footer" />
	</div>
	
	<script type="text/javascript">
			function relativeRedir(loc) {	
				var b = document.getElementsByTagName('base');
				if (b && b[0] && b[0].href) {
		  			if (b[0].href.substr(b[0].href.length - 1) == '/' && loc.charAt(0) == '/')
		    		loc = loc.substr(1);
		  			loc = b[0].href + loc;
				}
				window.location.replace(loc);
			}
	</script>

</body>
</html>