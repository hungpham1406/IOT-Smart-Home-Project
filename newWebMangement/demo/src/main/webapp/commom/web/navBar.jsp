<%@ page import="model.Users" %><%--
  Created by IntelliJ IDEA.
  User: Huy
  Date: 4/24/2024
  Time: 3:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../tablib.jsp" %>
<!-- Navigation -->
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath();


%>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="<%=url+"/views/web/home.jsp"%>">Start Bootstrap</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="#">About</a>
                </li>


                <%
                            Object obj= session.getAttribute("user");
                            if(obj!=null) {
                                obj = (Users) obj;
                            }
                %>
                <% if(obj!=null){
                    String cusomterName= ((Users) obj).getUsername();
                    boolean isAdmin= false;
                    if(((Users) obj).getUser_role().equals("admin")){
                        isAdmin=true;
                    }else {
                        isAdmin=false;
                    }

                %>
                <% if(isAdmin){
                %>
                    <li>
                        <a href="<%=url+"/views/admin/home.jsp"%>">Admin</a>
                    </li>
                <%}%>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%=cusomterName%><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="<%=url+"/views/changePassword.jsp"%>"><i class="fa fa-fw fa-gear"></i> Change Your Password</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value='/logout'/>?action=logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
                <%}else {%>
                <li>
                    <a href="<%=url+"/views/login.jsp"%>">Login</a>


                </li>

                <li>
                    <a href="<%=url+"/views/register.jsp"%>">Register</a>
                </li>

                <%}%>

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
