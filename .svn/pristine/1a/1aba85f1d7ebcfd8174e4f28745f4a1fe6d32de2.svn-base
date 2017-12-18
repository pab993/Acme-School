<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${urlSubmit}" modelAttribute="actorRegisterForm" >
	
	<fieldset > 
	
	<legend><b> <spring:message code="actorRegister.accountData" /> </b></legend>
	
		<acme:textbox code="actorRegister.username" path="username" mandatory="true"/>
		<br/>
	
		<acme:password code="actorRegister.password" path="password" mandatory="true"/>
		<br/>
	
		<acme:password code="actorRegister.repeatPassword" path="repeatPassword" mandatory="true"/>
	
	</fieldset>
	
	
	<fieldset > 
	
		<legend><b> <spring:message code="actorRegister.personalData" /></b> </legend>
	
	
		<acme:textbox code="actorRegister.name" path="name" mandatory="true"/>
		<br />
			
		<acme:textbox code="actorRegister.surname" path="surname" mandatory="true"/>
		<br />
		
		<acme:textbox code="actorRegister.birthdate" path="birthdate"/>
		<br />
		
		<acme:textbox code="actorRegister.phone" path="phone" mandatory="true"/>
		<br />
		
		<acme:textbox code="actorRegister.phone2" path="phone2"/>
		<br />
		
		<acme:textbox code="actorRegister.email" path="email" mandatory="true"/>
		<br />
		
		<acme:textbox code="actorRegister.address" path="address" mandatory="true"/>
		<br/>
	
	</fieldset>
	
	
	<div>
	<form:checkbox id="myCheck" onclick="comprobar();" path="check"/>
		<form:label path="check">
			<spring:message code="actorRegister.accept" />
			<a href="termAndCondition/termAndCondition.do"><spring:message code="actorRegister.lopd"/></a>
		</form:label>
	</div>
	<form:errors path="check" cssClass="error" />
	<br/><br/>

	<acme:submit name="save" code="actorRegister.submit" />
	<%-- <input type="submit" name="save" value="<spring:message code="actorRegister.submit" />" onclick="validateForm()"/> --%>

</form:form>