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

	<form:form action="${requestURI}" modelAttribute="creditCard">
	
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="manager" />
	
		<acme:textbox code="creditCard.holderName" path="holderName" />
		<br />
		<acme:textbox code="creditCard.brandName" path="brandName" />
		<br />
		<acme:textbox code="creditCard.number" path="number" />
		<br />
		<acme:textbox code="creditCard.expirationMonth" path="expirationMonth" />
		<br />
		<acme:textbox code="creditCard.expirationYear" path="expirationYear" />
		<br />
		<acme:textbox code="creditCard.cvv" path="cvv" />
		<br />
		
		<acme:submit code="child.save" name="save" />&nbsp;
		<acme:cancel url="creditCard/manager/list.do" code="messageFolder.cancel"/>
		<br />
	
	</form:form>