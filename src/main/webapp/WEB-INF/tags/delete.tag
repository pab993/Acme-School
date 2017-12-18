
<%@ tag language="java" body-content="empty" %>
 
 <%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<%-- Attributes --%> 
<%@ attribute name="text" required="true" %>
<%@ attribute name="textConfi1" required="true" %>
<%@ attribute name="textConfi2" required="true" %>
<%@ attribute name="url" required="true" %>

<%-- Definition --%>

<a href="${url}" onclick="if(confirm('<spring:message code="${textConfi1}" />')){return confirm('<spring:message code="${textConfi2}" />')}else{return false};">
	<spring:message code="${text}" />
</a>
