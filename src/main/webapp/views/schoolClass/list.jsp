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
	<jstl:if test="${principal.id==school.manager.userAccount.id && !school.isCancel}">
		<div>
			<a href="school/classes/manager/add.do?idSchool=${idSchool}">
				<spring:message code="schoolClasses.create" />
			</a>
		</div>
		<br/>
	</jstl:if>
</security:authorize>
<br/>
<div class="col-md-12" style="margin-bottom: 25px;">
	<form:form action="${requestURI}" modelAttribute="school" method="GET">
		<input id="idSchool" name="idSchool" class="form-control" type="hidden" value="${idSchool}" >	
		<div class="col-md-12">
			<label for="profileImg">
				<spring:message code="school.keyword" />
			</label>	
		</div>
		<div class="col-md-3">
			<input id="keyword" name="keyword" class="form-control" type="text" value="${keyword}">	
		</div>
		<div class="col-md-12">
			<label for="profileImg">
				<spring:message code="school.yearSchool" />
			</label>
		</div>
		<div class="col-md-3">
			<select name="yearSchoolId" class="form-control">
				<jstl:forEach items="${yearSchoolList}" var="year">
					<option value="${year.id}" <jstl:if test="${year.id==yearSchoolId}">selected="selected"</jstl:if>>${year.anne}</option>
				</jstl:forEach>
			</select>
		</div>
		<div class="col-md-8">
			<acme:submit code="buscar" name="save" />
		</div>
	</form:form>
</div>
<br/>
<br/>
<display:table name="listSchoolClass" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	
	<jstl:set var="estilo" value=""/>
	<jstl:choose>
		<jstl:when test="${row.isArchive}">
			<jstl:set var="estilo" value="warning"/>
		</jstl:when>
	</jstl:choose>
	
	<spring:message code="schoolClasses.name" var="headerTag" />
	<display:column property="name" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="schoolClasses.description" var="headerTag" />
	<display:column property="description" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="child.view" var="headerTag" />
	<display:column title="${headerTag}" class="${estilo}">
		<a href="school/classes/detail.do?id=${row.id}">
			<spring:message code="child.view" />
		</a>
	</display:column>

	<security:authorize access="hasRole('PARENT')">
	<jstl:if test="${solicitado==null}">
		<spring:message code="schoolClasses.solicitar" var="headerTag" />
		<display:column title="${headerTag}" class="${estilo}">
			<a href="school/classes/parent/${row.id}/solicitar.do">
				<spring:message code="schoolClasses.solicitar" />
			</a>
		</display:column>
	</jstl:if>
	</security:authorize>

	<security:authorize access="hasRole('MANAGER')">
<%-- 		<spring:message code="child.view" var="headerTag" /> --%>
<%-- 		<display:column title="${headerTag}" class="${estilo}"> --%>
<%-- 			<a href="school/classes/detail.do?id=${row.id}"> --%>
<%-- 				<spring:message code="child.view" /> --%>
<!-- 			</a> -->
<%-- 		</display:column> --%>
		<spring:message code="child.edit" var="headerTag" />
		<jstl:if test="${principal.id==row.school.manager.userAccount.id && !row.school.isCancel}">
			<display:column title="${headerTag}" class="${estilo}">
				<a href="school/classes/manager/edit.do?id=${row.id}">
					<spring:message code="child.edit" />
				</a>
			</display:column>
		</jstl:if>
		<jstl:choose>
			<jstl:when test="${principal.id!=row.school.manager.userAccount.id || row.school.isCancel}">
<%-- 				<spring:message code="school.archive" var="headerTag" /> --%>
<%-- 				<display:column title="${headerTag}" class="${estilo}"> --%>
<%-- 				</display:column> --%>
			</jstl:when>
			<jstl:when test="${!row.isArchive}">
				<spring:message code="school.archive" var="headerTag" />
				<display:column title="${headerTag}" class="${estilo}">
					<acme:delete textConfi1="schoolClass.archive.confirm" url="school/classes/manager/archivar.do?id=${row.id}" text="school.archive" textConfi2="schoolClass.archive.confirm"/>
				</display:column>
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="school.archive" var="headerTag" />
				<display:column title="${headerTag}" class="${estilo}">
					<a href="school/classes/manager/desarchivar.do?id=${row.id}">
						<spring:message code="school.desarchive" />
					</a>
				</display:column>
			</jstl:otherwise>
		</jstl:choose>
	</security:authorize>
</display:table>

<acme:atras/>