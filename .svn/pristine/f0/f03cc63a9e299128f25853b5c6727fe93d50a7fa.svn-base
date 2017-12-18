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

	<form:form action="${requestURI}" modelAttribute="child">
	
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="parent" />
		<form:hidden path="petitions" />
		<form:hidden path="observations" />
	
		<acme:textbox code="child.name" path="name" />
		<br />
		
		<acme:textbox code="child.surname" path="surname" />
		<br />
		
		<acme:textbox code="child.birthdate" path="birthdate" />
		<br />
		
		<acme:submit code="child.save" name="save" />&nbsp;
		<acme:cancel url="child/parent/list.do" code="messageFolder.cancel"/>
		<br />
	
	</form:form>