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

<security:authentication property="principal" var="principal"/>


<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${manager}">
		<div>
			<a href="school/board/manager/add.do?idSchool=${idSchool}">
				<spring:message code="board.create" />
			</a>
		</div>
		<br/>
	</jstl:if>
</security:authorize>
<security:authorize access="hasRole('TEACHER')">
	<jstl:if test="${teacher}">
		<div>
			<a href="schoolClass/board/teacher/add.do?idSchoolClass=${idSchool}">
				<spring:message code="board.create" />
			</a>
		</div>
		<br/>
	</jstl:if>
</security:authorize>

<display:table name="listBoard" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	
	<spring:message code="board.description" var="headerTag" />
	<display:column property="description" title="${headerTag}"/>
	
	<spring:message code="board.dateFinValidation" var="headerTag" />
	<display:column format="{0,date,dd/MM/yyyy HH:mm}" property="dateFinValidation" title="${headerTag}"/>
	
<%-- 	<spring:message code="child.view" var="headerTag" /> --%>
<%-- 	<display:column title="${headerTag}"> --%>
<%-- 		<a href="school/board/detail.do?id=${row.id}"> --%>
<%-- 			<spring:message code="message.view" /> --%>
<!-- 		</a> -->
<%-- 	</display:column> --%>
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${principal.id==row.eventEntity.manager.userAccount.id}">
			<spring:message code="child.delete" var="headerTag" />
			<display:column title="${headerTag}">
				<acme:delete textConfi1="messageFolder.confirm.delete" url="school/board/manager/delete.do?id=${row.id}" text="messageFolder.delete" textConfi2="messageFolder.confirm.delete"/>
			</display:column>
		</jstl:if>
	</security:authorize>
	<security:authorize access="hasRole('TEACHER')">
		<jstl:if test="${teacher}">
			<spring:message code="child.delete" var="headerTag" />
			<display:column title="${headerTag}">
				<acme:delete textConfi1="messageFolder.confirm.delete" url="schoolClass/board/teacher/delete.do?id=${row.id}" text="messageFolder.delete" textConfi2="messageFolder.confirm.delete"/>
			</display:column>
		</jstl:if>
	</security:authorize>
</display:table>

<acme:atras/>
<%-- <acme:cancel url="school/detail.do?id=${idSchool}" code="messageFolder.cancel"/> --%>
		