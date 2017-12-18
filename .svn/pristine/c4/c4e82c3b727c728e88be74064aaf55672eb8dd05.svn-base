 <%--
 * login.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form class="form-horizontal" role="form" action="j_spring_security_check" modelAttribute="credentials">
	<div class="form-group">
		<form:label path="username" class="col-lg-2 control-label">
			<spring:message code="security.username" />
		</form:label>
		<div class="col-lg-10">
			<form:input path="username" class="form-control"/>	
			<form:errors class="error" path="username" />
		</div>
	</div>
	<br />

	<div class="form-group">
		<form:label path="password" class="col-lg-2 control-label">
			<spring:message code="security.password" />
		</form:label>
		<div class="col-lg-10">
			<form:password path="password" class="form-control"/>	
			<form:errors class="error" path="password" />
		</div>
	</div>
	
	<jstl:if test="${showError == true}">
		<div class="error col-lg-offset-2 col-lg-10">
			<spring:message code="security.login.failed" />
		</div>
		<br />
		<br />
	</jstl:if>
	<div class="form-group">
	    <div class="col-lg-offset-2 col-lg-10">
			<input type="submit" class="btn btn-default" value="<spring:message code="security.login" />" />
		</div>
	</div>
</form:form>