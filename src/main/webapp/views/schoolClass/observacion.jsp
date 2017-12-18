<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

	<form:form action="${requestURI}" modelAttribute="observation" method="POST">
	
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="dateCreation" />
		<form:hidden path="dateVisualization" />
		<form:hidden path="teacher" />
		<form:hidden path="child" />
		<form:hidden path="schoolClass" />

		<acme:textbox code="observation.titule" path="titule" />
		<br />
		
		<acme:textarea code="observation.description" path="description" />
		<br />
		
		<acme:submit code="child.save" name="save" />&nbsp;
		<acme:cancel url="school/classes/detail.do?id=${observation.schoolClass.id }" code="messageFolder.cancel"/>
		<br />
	
	</form:form>