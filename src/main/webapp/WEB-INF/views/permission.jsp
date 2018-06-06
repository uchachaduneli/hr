<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="ge.economy.hr.controller.auth.LoginInterceptor" %>
<%@page import="ge.economy.hr.dto.PersonalDTO" %>
<%@page import="ge.economy.hr.dto.SysPermissionDTO" %>

<%!
  public boolean hasPermissions(HttpServletRequest request, String permisson) {
//        ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
    PersonalDTO su = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
    return su.getRights().contains(permisson);
  }
%>