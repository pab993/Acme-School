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
	<a href="place/manager/add.do">
		<spring:message code="place.create" />
	</a>
</div>
<br/>
<display:table name="listPlace" pagesize="5" class="displaytag" requestURI="/place/manager/list.do" id="row">
	
	<spring:message code="place.name" var="headerTag" />
	<display:column property="name" title="${headerTag}"/>
	
	<spring:message code="place.address" var="headerTag" />
	<display:column property="address" title="${headerTag}"/>
	
	<spring:message code="place.spaceMax" var="headerTag" />
	<display:column property="spaceMax" title="${headerTag}"/>
	
	<spring:message code="child.edit" var="headerTag" />
	<display:column title="${headerTag}" class="${estilo}">
		<a href="place/manager/edit.do?id=${row.id}">
			<spring:message code="child.edit" />
		</a>
	</display:column>

</display:table>
