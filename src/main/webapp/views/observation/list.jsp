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

<br/>
<display:table name="listObservation" pagesize="20" class="displaytag" requestURI="${requestURI}" id="row">
	
	<spring:message code="observation.dateCreation" var="headerTag" />
	<display:column format="{0,date,dd/MM/yyyy HH:mm}" property="dateCreation" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="observation.dateVisualization" var="headerTag" />
	<display:column format="{0,date,dd/MM/yyyy HH:mm}" property="dateVisualization" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="observation.nameClass" var="headerTag" />
	<display:column property="schoolClass.name" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="observation.nameTeacher" var="headerTag" />
	<display:column property="teacher.name" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="observation.title" var="headerTag" />
	<display:column property="titule" title="${headerTag}" class="${estilo}"/>
	
	<spring:message code="observation.description" var="headerTag" />
	<display:column property="description" title="${headerTag}" class="${estilo}"/>
	
</display:table>

<acme:atras/>