<%--
 * list.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<fieldset class="panel panel-default">
	<div class="panel-heading"><h2><spring:message code="desvianzaMediaMaxMinNinoPorClaseActiva"/></h2></div>
	<div class="panel-body">	
		<h4>DEV: ${desvianzaMediaMaxMinNinoPorClaseActiva[0]}</h4>
		
		<h4>AVG: ${desvianzaMediaMaxMinNinoPorClaseActiva[1]}</h4>
		
		<h4>MAX: ${desvianzaMediaMaxMinNinoPorClaseActiva[2]}</h4>
		
		<h4>MIN: ${desvianzaMediaMaxMinNinoPorClaseActiva[3]}</h4>
	</div>
</fieldset>	
<fieldset class="panel panel-default">
	<div class="panel-heading"><h2><spring:message code="porcentajePadresConNinosRegistradosEnClasesActivas"/></h2></div>
	<div class="panel-body">
		<h4>${porcentajePadresConNinosRegistradosEnClasesActivas} %</h4>
	</div>
</fieldset>
<br />
