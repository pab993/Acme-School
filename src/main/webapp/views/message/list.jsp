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
	<a href="message/actor/create.do">
		<spring:message code="message.sendAMessage" />
	</a>
</div>
<br/>
<display:table name="messages" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	
	<spring:message code="message.sender" var="headerTag" />
	<display:column property="send.name" title="${headerTag}"/>
	
<%-- 	<spring:message code="message.recipient" var="headerTag" /> --%>
<%-- 	<display:column title="${headerTag}"> --%>
<%-- 		[<jstl:forEach items="${row.receives}" var="receive"> ${receive.name}; </jstl:forEach>] --%>
<%-- 	</display:column> --%>
	
	<spring:message code="message.subject" var="headerTag" />
	<display:column property="subject" title="${headerTag}"/>
	
	<spring:message code="message.view" var="headerTag" />
	<display:column title="${headerTag}">
		<a href="message/actor/details.do?messageId=${row.id}">
			<spring:message code="message.view" />
		</a>
	</display:column>
	
	<spring:message code="message.mover" var="headerTag" />
	<display:column title="${headerTag}">
		<a href="message/actor/mover.do?messageId=${row.id}">
			<spring:message code="message.mover" />
		</a>
	</display:column>
	
	<spring:message code="message.delete" var="headerTag" />
	<display:column title="${headerTag}">
		<acme:delete textConfi1="messageFolder.confirm.delete" url="message/actor/trash.do?messageId=${row.id}" text="messageFolder.delete" textConfi2="messageFolder.confirm.delete"/>
	</display:column>
</display:table>

		<!-- boton para volver atr�s -->
		<input type="button" name="volver" onclick="history.back()"
			value="<spring:message code="message.volver"/>" />
