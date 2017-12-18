<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

	<form:form action="${requestURI}" modelAttribute="school"  onsubmit="rename()">
	
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="manager" />
		<form:hidden path="teachers" />
		<form:hidden path="schoolClasses" />
		<form:hidden path="comments" />
		<form:hidden path="isArchive" />
		<form:hidden path="isCancel" />
		<form:hidden path="descriptionCancel" />
	
		<acme:textbox code="school.profileImg" path="profileImg" />
		<br />
		<acme:textbox code="school.name" path="name" />
		<br />
		<acme:textarea code="school.description" path="description" />
		<br />
		<acme:textbox code="school.address" path="address" />
		<br />
		<form:label path="galery">
			<spring:message code="school.galery" />
		</form:label><br/><br/>
		<button type="button" id="buttonAdd" class="btn btn-primary">+</button>
		<jstl:forEach items="${school.galery}" var="galery" varStatus="status">
			<div id="content${status.index}">
				<br/><br/>
				<div class="form-group">
					<div class="col-md-12 col-lg-12">
						<div class="col-md-11 col-lg-11">
							<form:input class="form-control" path="galery[${status.index}].url" />	
						</div>
						<button onclick="$('#content${status.index}').remove();" class="btn btn-danger">x</button>
					</div>
					<form:errors path="galery[${status.index}].url" cssClass="error" />
				</div>
			</div>
		</jstl:forEach>
		<br />
		
		<acme:textbox code="school.video" path="video" />
		<br />
		
		<acme:submit code="child.save" name="save" />&nbsp;
		<acme:cancel url="school/manager/list.do" code="messageFolder.cancel"/>
		<br />
	
	</form:form>
	
<script>
	$("#buttonAdd").click(function(){
		$(this).after(
			'<div id="content'+$("input[name^=galery]").size()+'">'
				+'<br/><br/><div class="form-group">'
					+'<div class="col-md-12 col-lg-12">'
						+'<div class="col-md-11 col-lg-11">'
							+'<input id="galery'+$("input[name^=galery]").size()+'.url" name="galery['+$("input[name^=galery]").size()+'].url" class="form-control" type="text" value="">'
						+'</div>'
						+'<button onclick="$(\'#content\'+'+$("input[name^=galery]").size()+').remove();" class="btn btn-danger">x</button>'
					+'</div>'
				+'</div>	'
			+'</div>'
		);
	})
	
	function rename(){
  		$.each($("input[name^=galery]"), function(index,value){
			$(value).attr("name","galery["+index+"].url");
			$(value).attr("id","galery"+index+".url");
		});
	}
</script>