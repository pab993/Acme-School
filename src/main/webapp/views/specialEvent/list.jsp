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
		<a href="school/specialEvent/manager/add.do?idSchool=${idSchool}">
			<spring:message code="specialEvent.create" />
		</a>
	</div>
	<br/>
</jstl:if>
</security:authorize>
<security:authorize access="hasRole('TEACHER')">
<jstl:if test="${teacher}">
	<div>
		<a href="schoolClass/specialEvent/teacher/add.do?idSchoolClass=${idSchool}">
			<spring:message code="specialEvent.create" />
		</a>
	</div>
	<br/>
</jstl:if>
</security:authorize>

<display:table name="listBoard" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	
	<spring:message code="specialEvent.title" var="headerTag" />
	<display:column property="title" title="${headerTag}"/>
	
	<spring:message code="specialEvent.description" var="headerTag" />
	<display:column property="description" title="${headerTag}"/>
	
	<spring:message code="specialEvent.dateInit" var="headerTag" />
	<display:column format="{0,date,dd/MM/yyyy HH:mm}" property="dateInit" title="${headerTag}"/>
	
	<spring:message code="specialEvent.dateFin" var="headerTag" />
	<display:column format="{0,date,dd/MM/yyyy HH:mm}" property="dateFin" title="${headerTag}"/>
	
	<spring:message code="specialEvent.priority" var="headerTag" />
	<%-- <display:column property="priority" title="${headerTag}"/> --%>
	<display:column title="${headerTag }">
		<jstl:choose>
			<jstl:when test="${row.priority == 'VERY_IMPORTANT' }"><spring:message code="message.priority.very.important"/></jstl:when>
			<jstl:when test="${row.priority == 'IMPORTANT' }"><spring:message code="message.priority.important"/></jstl:when>
			<jstl:when test="${row.priority == 'NORMAL' }"><spring:message code="message.priority.normal"/></jstl:when>
		</jstl:choose>
	</display:column>
	
	<spring:message code="specialEvent.place" var="headerTag" />
	<display:column property="place.name" title="${headerTag}"/>
	
	<spring:message code="specialEvent.space" var="headerTag" />
	<display:column title="${headerTag}">
		${row.child.size()}/${row.space}
	</display:column>
	
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
				<acme:delete textConfi1="messageFolder.confirm.delete" url="school/specialEvent/manager/delete.do?id=${row.id}" text="messageFolder.delete" textConfi2="messageFolder.confirm.delete"/>
			</display:column>
		</jstl:if>
	</security:authorize>
	<security:authorize access="hasRole('TEACHER')">
	<jstl:if test="${teacher}">
		<spring:message code="child.delete" var="headerTag" />
		<display:column title="${headerTag}">
			<acme:delete textConfi1="messageFolder.confirm.delete" url="schoolClass/specialEvent/teacher/delete.do?id=${row.id}" text="messageFolder.delete" textConfi2="messageFolder.confirm.delete"/>
		</display:column>
	</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('PARENT')">
	<jstl:if test="${child!=null}">
		<jstl:choose>
			<jstl:when test="${row.child.contains(child)}">
				<spring:message code="specialEvent.inscribirse" var="headerTag" />
				<display:column title="${headerTag}" class="${estilo}">
					<spring:message code="specialEvent.inscrito"/>
				</display:column>
			</jstl:when>
			<jstl:when test="${row.space>row.child.size()}">
				<spring:message code="specialEvent.inscribirse" var="headerTag" />
				<display:column title="${headerTag}" class="${estilo}">
					<acme:delete textConfi1="specialEvent.confirm.incribirse2" url="child/parent/event/${child.id}/inscribirse.do?id=${row.id}" text="specialEvent.inscribirse" textConfi2="specialEvent.confirm.incribirse"/>
				</display:column>
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="specialEvent.inscribirse" var="headerTag" />
				<display:column title="${headerTag}" class="${estilo}">
				</display:column>
			</jstl:otherwise>
		</jstl:choose>
		
	</jstl:if>
	</security:authorize>
</display:table>

<acme:atras/>
<%-- <acme:cancel url="school/detail.do?id=${idSchool}" code="messageFolder.cancel"/> --%>
		