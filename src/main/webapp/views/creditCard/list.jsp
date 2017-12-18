<%@page import="domain.CreditCard"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<div>
	<a href="creditCard/manager/add.do">
		<spring:message code="creditCard.create" />
	</a>
</div>
<br/>
<display:table name="listCreditCard" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	
	<spring:message code="creditCard.holderName" var="headerTag" />
	<display:column property="holderName" title="${headerTag}"/>
	
	<spring:message code="creditCard.brandName" var="headerTag" />
	<display:column property="brandName" title="${headerTag}"/>
	
	<spring:message code="creditCard.number" var="headerTag" />
	<display:column title="${headerTag}">
		************ <% out.println(((CreditCard)row).getNumber().substring(12, 16)); %>
	</display:column>
	
	<spring:message code="creditCard.expirationMonth" var="headerTag" />
	<display:column property="expirationMonth" title="${headerTag}"/>
	
	<spring:message code="creditCard.expirationYear" var="headerTag" />
	<display:column property="expirationYear" title="${headerTag}"/>
	
	<spring:message code="child.delete" var="headerTag" />
	<display:column title="${headerTag}">
		<acme:delete textConfi1="messageFolder.confirm.delete" url="creditCard/manager/delete.do?id=${row.id}" text="messageFolder.delete" textConfi2="messageFolder.confirm.delete"/>
	</display:column>
</display:table>
