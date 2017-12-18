<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<div class="btn-group col-md-12">
	<security:authentication property="principal" var="principal"/>
	<security:authorize access="hasRole('PARENT')">
		<jstl:choose>
			<jstl:when test="${principal.id==child.parent.userAccount.id}">
				<a href="child/parent/edit.do?id=${child.id}" type="button" class="btn btn-warning">
					<spring:message code="child.edit" />
				</a>
				<a href="child/parent/schoolClass/list.do?id=${school.id}" type="button" class="btn btn-warning">
					<spring:message code="child.class" />
				</a>
				<a href="child/parent/observation/list.do?id=${school.id}" type="button" class="btn btn-warning">
					<spring:message code="child.observaciones" />
				</a>
				<a href="child/parent/event/list.do?id=${school.id}" type="button" class="btn btn-warning">
					<spring:message code="child.event" />
				</a>
			</jstl:when>
		</jstl:choose>
	</security:authorize>
</div>
<br/>
<br/>

<fieldset>
	<div class="col-md-12">
		<acme:textbox code="child.name" path="child.name" readonly="true"/>
		<acme:textbox code="child.surname" path="child.surname" readonly="true"/>
		<acme:textbox code="child.birthdate" path="child.birthdate" readonly="true"/>
	</div>
</fieldset>
<br/>
		
		<acme:cancel url="child/parent/list.do" code="messageFolder.cancel"/>
