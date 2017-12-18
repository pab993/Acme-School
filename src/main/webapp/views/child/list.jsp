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
	<a href="child/parent/add.do">
		<spring:message code="child.create" />
	</a>
</div>
<br/>
<display:table name="listChild" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	
	<spring:message code="child.name" var="headerTag" />
	<display:column property="name" title="${headerTag}"/>
	
	<spring:message code="child.surname" var="headerTag" />
	<display:column property="surname" title="${headerTag}"/>
	
	<spring:message code="child.birthdate" var="headerTag" />
	<display:column property="birthdate" format="{0,date,dd/MM/yyyy}" title="${headerTag}"/>
	
	<spring:message code="child.view" var="headerTag" />
	<display:column title="${headerTag}">
		<a href="child/parent/detail.do?id=${row.id}">
			<spring:message code="message.view" />
		</a>
	</display:column>
	
<%-- 	<spring:message code="child.delete" var="headerTag" /> --%>
<%-- 	<display:column title="${headerTag}"> --%>
<%-- 		<acme:delete textConfi1="messageFolder.confirm.delete" url="child/parent/delete.do?id=${row.id}" text="messageFolder.delete" textConfi2="messageFolder.confirm.delete"/> --%>
<%-- 	</display:column> --%>
</display:table>

<acme:atras/>