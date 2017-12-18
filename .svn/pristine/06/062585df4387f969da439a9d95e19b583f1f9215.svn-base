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
	<div>
		<a href="school/manager/add.do">
			<spring:message code="school.create" />
		</a>
	</div>
	<br/>
</security:authorize>
<br/>
<div class="col-md-12" style="margin-bottom: 25px;">
	<form:form action="${requestURI}" modelAttribute="school" method="GET">
		<div class="col-md-12">
			<label for="profileImg">
				<spring:message code="school.keyword" />
			</label>	
		</div>
		<div class="col-md-3">
			<input id="keyword" name="keyword" class="form-control" type="text" value="${keyword}">	
		</div>
		<div class="col-md-8">
			<acme:submit code="buscar" name="save" />
		</div>
	</form:form>
</div>
<br/>
<br/>
<display:table name="listSchool" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	<jstl:set var="estilo" value=""/>
	<jstl:choose>
		<jstl:when test="${row.isCancel}">
			<jstl:set var="estilo" value="danger"/>
		</jstl:when>
		<jstl:when test="${row.isArchive}">
			<jstl:set var="estilo" value="warning"/>
		</jstl:when>
	</jstl:choose>
	
	<spring:message code="school.profileImg" var="headerTag" />
	<display:column title="${headerTag}" class="${estilo}">
		<img src="${row.profileImg}" alt="profileImg" width="32" height="32"/>
	</display:column>
	
	<spring:message code="school.name" var="headerTag" />
	<display:column property="name" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="school.description" var="headerTag" />
	<display:column property="description" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="school.address" var="headerTag" />
	<display:column property="address" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="child.view" var="headerTag" />
	<display:column title="${headerTag}" class="${estilo}">
		<a href="school/detail.do?id=${row.id}">
			<spring:message code="message.view" />
		</a>
	</display:column>
	
	<security:authorize access="hasRole('MANAGER')">
		<jstl:choose>
			<jstl:when test="${principal.id!=row.manager.userAccount.id || row.isCancel}">
				<spring:message code="school.archive" var="headerTag" />
				<display:column title="${headerTag}" class="${estilo}">
				</display:column>
			</jstl:when>
			<jstl:when test="${!row.isArchive}">
				<spring:message code="school.archive" var="headerTag" />
				<display:column title="${headerTag}" class="${estilo}">
					<acme:delete textConfi1="school.archive.confirm" url="school/manager/archivar.do?id=${row.id}" text="school.archive" textConfi2="school.archive.confirm"/>
				</display:column>
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="school.desarchive" var="headerTag" />
				<display:column title="${headerTag}" class="${estilo}">
					<a href="school/manager/desarchivar.do?id=${row.id}">
						<spring:message code="school.desarchive" />
					</a>
				</display:column>
			</jstl:otherwise>
		</jstl:choose>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<jstl:choose>
			<jstl:when test="${!row.isCancel}">
				<spring:message code="school.cancelar" var="headerTag" />
				<display:column title="${headerTag}" class="${estilo}">
					<acme:delete textConfi1="school.cancel.confirm" url="school/admin/cancelar.do?id=${row.id}" text="school.cancelar" textConfi2="school.cancel.confirm"/>
				</display:column>
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="school.activar" var="headerTag" />
				<display:column title="${headerTag}" class="${estilo}">
					<a href="school/admin/activar.do?id=${row.id}">
						<spring:message code="school.activar" />
					</a>
				</display:column>
			</jstl:otherwise>
		</jstl:choose>
	</security:authorize>
</display:table>

<acme:atras/>