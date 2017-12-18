<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<strong>
<spring:message code="actor.name" />
:
</strong>
<jstl:out value="${actor.name}"></jstl:out>
<br>
<strong>
<spring:message code="actor.surname" />
:
</strong>
<jstl:out value="${actor.surname}"></jstl:out>
<br>
<strong>
<spring:message code="actorRegister.birthdate" />
:
</strong>
<fmt:formatDate pattern = "dd/MM/yyyy" value = "${actor.birthdate}" />
<br>
<strong>
<spring:message code="actor.email" />
:
</strong>
<jstl:out value="${actor.email}"></jstl:out>
<br>
<strong>
<spring:message code="actor.phone" />
:
</strong>
<jstl:out value="${actor.phone}"></jstl:out>
<br>
<strong>
<spring:message code="actorRegister.phone2" />
:
</strong>
<jstl:out value="${actor.phone2}"></jstl:out>
<br>
<strong>
<spring:message code="actor.postalAddress" />
:
</strong>
<jstl:out value="${actor.address}"></jstl:out>

<br>
<br>
<jstl:if test="${actor.id == principal.id}">
	<a href="actor/editProfile.do"><spring:message
			code="actor.edit.profile" /></a>
</jstl:if>