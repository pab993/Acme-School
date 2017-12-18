<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div style="background-color: #337AB7;">
	<ul id="jMenu">
		<li>
			<img src="images/logo.png" alt="Acme-School Co., Inc." />
		</li>
		<li style="background-color: black;padding: 2px;"></li>
		<security:authorize access="!hasAnyRole('MANAGER','ADMIN')">
			<li><a href="school/list.do"><spring:message
						code="master.page.school" /></a></li>
		</security:authorize>
						
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a href="school/admin/list.do"><spring:message
						code="master.page.school" /></a></li>
			<li><a href="yearSchool/admin/list.do"><spring:message
						code="master.page.yearSchool" /></a></li>
			<li><a class="fNiv" href="dashboard/list.do"><spring:message
						code="master.page.dashboard" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('PARENT')">
			<li><a class="fNiv" href="child/parent/list.do"><spring:message
						code="master.page.child" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv"> <spring:message code="master.page.school" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="school/list.do"><spring:message
						code="master.page.school.list" /></a></li>
					<li><a href="school/manager/list.do"><spring:message
						code="master.page.school.myList" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="place/manager/list.do"><spring:message
						code="master.page.place" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('TEACHER')">
			<li><a href="school/classes/teacher/list.do"><spring:message
						code="master.page.schoolClass.my" /></a></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv"> <spring:message code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="parentRegister/register.do"><spring:message
						code="master.page.register.parentRegister" /></a></li>
					<li><a href="managerRegister/register.do"><spring:message
						code="master.page.register.managerRegister" /></a></li>
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="folder/actor/list.do"><spring:message
								code="master.page.mailbox" /></a></li>
					<li><a href="actor/seeProfile.do"><spring:message
								code="master.page.actor.profile" /></a></li>
					<security:authorize access="hasRole('MANAGER')">
						<li><a href="creditCard/manager/list.do"><spring:message
								code="master.page.creditCard" /> </a></li>
					</security:authorize>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div class="col-md-12">
	<div class="text-right">
		<a href="?language=en">en</a> | <a href="?language=es">es</a>
	</div>
</div>

