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
	<a href="yearSchool/admin/add.do">
		<spring:message code="yearSchool.create" />
	</a>
</div>
<br/>
<display:table name="listYearSchool" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	<jstl:set var="estilo" value=""/>
	<jstl:choose>
		<jstl:when test="${row.isActive}">
			<jstl:set var="estilo" value="success"/>
		</jstl:when>
	</jstl:choose>
	
	<spring:message code="yearSchool.anne" var="headerTag" />
	<display:column property="anne" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="child.edit" var="headerTag" />
	<display:column title="${headerTag}" class="${estilo}">
		<a href="yearSchool/admin/edit.do?id=${row.id}">
			<spring:message code="child.edit" />
		</a>
	</display:column>
	
	<spring:message code="yearSchool.activar" var="headerTag" />
	<display:column title="${headerTag}" class="${estilo}">
		<jstl:if test="${!row.isActive}">
			<acme:delete textConfi1="yearSchool.confirm.activar" url="yearSchool/admin/activar.do?id=${row.id}" text="yearSchool.activar" textConfi2="yearSchool.confirm.activar2"/>
		</jstl:if>
	</display:column>
</display:table>
