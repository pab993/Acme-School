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

	<form:form action="${requestURI}" modelAttribute="place">
	
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="specialEvents" />
		<form:hidden path="manager" />
		<jstl:if test="${edit}">
			<form:hidden path="spaceMax" />
		</jstl:if>
		
		<acme:textbox code="place.name" path="name" />
		<br />
		
		<acme:textbox code="place.address" path="address" />
		<br />
		
		<jstl:if test="${create}">
			<acme:textbox code="place.spaceMax" path="spaceMax" />
			<br />
		</jstl:if>

		<acme:submit code="child.save" name="save" />&nbsp;
		<acme:cancel url="place/manager/list.do" code="messageFolder.cancel"/>
		<br />
	
	</form:form>