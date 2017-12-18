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
	<a href="schoolClass/specialEvent/list.do?idSchoolClass=${schoolClass.id}" type="button" class="btn btn-warning">
		<spring:message code="school.event" />
	</a>
	<a href="schoolClass/board/list.do?idSchoolClass=${schoolClass.id}" type="button" class="btn btn-warning">
		<spring:message code="school.board" />
	</a>
</div>
<br/>
<br/>

<fieldset>
	<div class="col-md-12">
	
		<acme:textbox code="schoolClasses.name" path="schoolClass.name"  readonly="true"/>
		<acme:textbox code="schoolClasses.description" path="schoolClass.description"  readonly="true"/>
		
		<br/><br/><br/>
		
		<h3><spring:message code="profesores"/></h3>
		<div class="col-md-12">
			<security:authentication property="principal" var="principal"/>
			<security:authorize access="hasRole('MANAGER')">
				<jstl:if test="${principal.id==schoolClass.school.manager.userAccount.id && !schoolClass.school.isCancel && !schoolClass.isArchive}">
					<div>
						<a href="school/classes/manager/${schoolClass.id}/asignar.do?idSchool=${schoolClass.school.id}">
							<spring:message code="schoolClasses.asignar" />
						</a>
					</div>
					<br/>
				</jstl:if>
			</security:authorize>	
			
			<display:table name="schoolClass.teacher" pagesize="${schoolClass.teacher.size()}" class="displaytag" requestURI="${requestURI}" id="row">
		
				<jstl:set var="estilo" value=""/>
				
				<spring:message code="child.name" var="headerTag" />
				<display:column property="name" title="${headerTag}" class="${estilo}"/>
				
				<spring:message code="child.surname" var="headerTag" />
				<display:column property="surname" title="${headerTag}" class="${estilo}"/>
				
				<security:authorize access="hasRole('MANAGER')">
					<jstl:choose>
						<jstl:when test="${principal!=row.school.manager.userAccount||row.school.isArchive||row.school.isCancel}">
							<spring:message code="school.archive" var="headerTag" />
							<display:column title="${headerTag}" class="${estilo}">
							</display:column>
						</jstl:when>
						<jstl:otherwise>
							<spring:message code="school.desasignar" var="headerTag" />
							<display:column title="${headerTag}" class="${estilo}">
								<acme:delete textConfi1="messageFolder.confirm.delete" url="school/classes/manager/${schoolClass.id}/desasignar.do?id=${row.id}" text="school.archive" textConfi2="messageFolder.confirm.delete"/>
							</display:column>
						</jstl:otherwise>
					</jstl:choose>
				</security:authorize>
				
			</display:table>
		</div>
		<br/><br/><br/>
		
		<security:authorize access="hasRole('TEACHER')">
		<jstl:if test="${teacher}">
			<h3><spring:message code="alumnos"/></h3>
<!-- 			alumnos -->
			<div class="col-md-12">
				<display:table list="${schoolClass.getPetitionsAccepte()}" pagesize="${schoolClass.getPetitionsAccepte().size()}" class="displaytag" requestURI="${requestURI}" id="row">
					
						<spring:message code="child.name" var="headerTag" />
						<display:column property="child.name" title="${headerTag}" class="${estilo}"/>
						
						<spring:message code="child.surname" var="headerTag" />
						<display:column property="child.surname" title="${headerTag}" class="${estilo}"/>
						
						<spring:message code="child.birthdate" var="headerTag" />
						<display:column property="child.birthdate" format="{0,date,dd/MM/yyyy}" title="${headerTag}" class="${estilo}"/>
						
						<spring:message code="schoolClass.observacion" var="headerTag" />
						<display:column title="${headerTag}" class="${estilo}">
							<a href="school/classes/teacher/${schoolClass.id}/addObservacion.do?id=${row.child.id}">
								<spring:message code="schoolClass.observacion" />
							</a>
						</display:column>
						
				</display:table>
			</div>
			<br/><br/><br/>
			<h3><spring:message code="peticiones"/></h3>
<!-- 			peticiones -->
			<div class="col-md-12">
				<display:table list="${schoolClass.getPetitionsWait()}" pagesize="${schoolClass.getPetitionsWait().size()}" class="displaytag" requestURI="${requestURI}" id="row">
					
						<spring:message code="child.name" var="headerTag" />
						<display:column property="child.name" title="${headerTag}" class="${estilo}"/>
						
						<spring:message code="child.surname" var="headerTag" />
						<display:column property="child.surname" title="${headerTag}" class="${estilo}"/>
						
						<spring:message code="child.moment" var="headerTag" />
						<display:column property="moment" format="{0,date,dd/MM/yyyy HH:mm}" title="${headerTag}" class="${estilo}"/>
						
						<spring:message code="child.state" var="headerTag" />
						<display:column property="state" title="${headerTag}" class="${estilo}"/>
						
						<spring:message code="schoolClass.aceptar" var="headerTag" />
						<display:column title="${headerTag}" class="${estilo}">
							<acme:delete textConfi1="application.confirm.accept" url="school/classes/teacher/${schoolClass.id}/aceptar.do?id=${row.id}" text="schoolClass.aceptar" textConfi2="application.confirm.accept"/>
						</display:column>
						
						<spring:message code="schoolClass.cancelar" var="headerTag" />
						<display:column title="${headerTag}" class="${estilo}">
							<acme:delete textConfi1="application.confirm.cancel" url="school/classes/teacher/${schoolClass.id}/cancelar.do?id=${row.id}" text="schoolClass.cancelar" textConfi2="application.confirm.cancel"/>
						</display:column>
			
				</display:table>
			</div>
		</jstl:if>
		</security:authorize>
		<br/><br/><br/>
		
		<br/>
<%-- 		<acme:cancel url="school/classes/manager/list.do?idSchool=${schoolClass.school.id}" code="messageFolder.cancel"/> --%>
		<acme:atras/>
		<br />
	
	</div>
</fieldset>
<br/>
		
