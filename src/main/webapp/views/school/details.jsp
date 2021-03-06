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
	<security:authorize access="hasRole('MANAGER')">
		<security:authentication property="principal" var="principal"/>
		<jstl:choose>
			<jstl:when test="${principal.id==school.manager.userAccount.id}">
				<a href="school/manager/edit.do?id=${school.id}" type="button" class="btn btn-warning">
					<spring:message code="child.edit" />
				</a>
				<a href="teacher/manager/${school.id}/list.do" type="button" class="btn btn-warning">
					<spring:message code="school.teacher" />
				</a>
				<a href="school/classes/manager/list.do?idSchool=${school.id}" type="button" class="btn btn-warning">
					<spring:message code="school.class" />
				</a>
			</jstl:when>
		</jstl:choose>
	</security:authorize>
	<jstl:if test="${principal==null || principal!=school.manager.userAccount}">
		<a href="school/classes/list.do?idSchool=${school.id}" type="button" class="btn btn-warning">
			<spring:message code="school.class" />
		</a>
	</jstl:if>
	<a href="school/specialEvent/list.do?idSchool=${school.id}" type="button" class="btn btn-warning">
		<spring:message code="school.event" />
	</a>
	<a href="school/board/list.do?idSchool=${school.id}" type="button" class="btn btn-warning">
		<spring:message code="school.board" />
	</a>
</div>
<br/>
<br/>
<fieldset>
	<div class="col-md-12">
		<div id="myCarousel" class="carousel slide" data-ride="carousel" >
			<div class="carousel-inner" style="height: 200px;">
				<jstl:forEach items="${school.galery}" var="galery" varStatus="status">
					<div class="item <jstl:if test="${status.index==0}">active</jstl:if>">
						<img class="d-block w-100 img-responsive center-block" src="${galery.url}">
					</div>
				</jstl:forEach>
			</div>
			<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span>
			    <span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
  		</div>
  		<br/>
		<br/>
		<br/>
	</div>
	<div class="col-md-12">
		<div class="col-md-4">
			<img src="${school.profileImg}" class="img-responsive center-block" alt="profileImg" width="250" height="250"/>
		</div>
		<div class="col-md-8">
			<acme:textbox code="school.name" path="school.name" readonly="true"/>
			<acme:textarea code="school.description" path="school.description" readonly="true"/>
			<acme:textbox code="school.address" path="school.name" readonly="true"/>
			<br/>
			<br/>
			<br/>
		</div>
	</div>
	<div class="col-md-12">
		<div class="col-md-offset-3 col-md-6">
			<div class="embed-responsive embed-responsive-16by9">
			  <iframe class="embed-responsive-item" src="${school.video}"></iframe>
			</div>
		</div>
	</div>
</fieldset>
<br><br>
<fieldset>
	<div class="col-md-12">
		<h2><spring:message code="school.comment" /></h2>
		<security:authorize access="isAuthenticated()">
			<jstl:if test="${!school.isArchive&&!school.isCancel }">
				<div>
					<a href="comment/actor/comentar.do?id=${school.id}">
						<spring:message code="school.comentar" />
					</a>
				</div>
				<br/>
			</jstl:if>
		</security:authorize>
		<hr>
		<div class="col-md-12">
			<jstl:forEach items="${school.comments}" var="comment">
				<jstl:if test="${!comment.ban}">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3>
								${comment.title} 
								<jstl:forEach begin="1" end="${comment.stars}">
									<span class="glyphicon glyphicon-star" style="color: #FFA303;" aria-hidden="true"></span>
								</jstl:forEach> 
								<jstl:forEach begin="${comment.stars}" end="4">
									<span class="glyphicon glyphicon-star-empty" style="color: #FFA303;" aria-hidden="true"></span>
								</jstl:forEach> 
							</h3>
							<h5><fmt:formatDate pattern = "dd/MM/yyyy HH:mm" value = "${comment.createMoment}" /></h5>
						</div>
						<div class="panel-body">
							${comment.text}
						</div>
					</div>
				</jstl:if>
			</jstl:forEach>
		</div>
	</div>
</fieldset>
<br/>
		
<%-- 		<acme:cancel url="school/manager/list.do" code="messageFolder.cancel"/> --%>

<acme:atras/>