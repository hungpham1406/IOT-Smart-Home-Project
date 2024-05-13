<%--
  Created by IntelliJ IDEA.
  User: Huy
  Date: 4/24/2024
  Time: 3:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../commom/web/header.jsp"%>
<%@include file="../commom/web/navBar.jsp"%>
<% request.setAttribute("action","login"); %>
<div class="container">

    <div class="form-gap"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="text-center">


                            <h3><i class="fa fa-user fa-4x"></i></h3>
                            <h2 class="text-center">Login</h2>
                            <div class="panel-body">


                                <form  action="<c:url value='/login'/>" id="login-form" role="form" autocomplete="off" class="form" method="POST">

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user color-blue"></i></span>

                                            <input name="username" type="text" class="form-control" placeholder="Enter Username">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock color-blue"></i></span>
                                            <input name="password" type="password" class="form-control" placeholder="Enter Password">
                                        </div>
                                    </div>
                                    <%
                                            String login_error=(String) request.getAttribute("login-error");
                                            if(login_error==null){
                                                login_error="";
                                            }
                                    %>
                                    <div class="text-center"><span class="red" style="color: #f10707"><%= login_error %></span></div>
                                    <div class="form-group">

                                        <input name="action" class="btn btn-lg btn-primary btn-block" value="login" type="submit">
                                    </div>


                                </form>

                            </div><!-- Body-->

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        function validateLoginForm() {
            // Get form fields
            var username = document.getElementsByName('username')[0].value;
            var password = document.getElementsByName('password')[0].value;

            // Check if fields are empty
            if(username == "" || password == "") {
                alert("All fields must be filled out");
                return false;
            }

            return true;
        }

        // Attach the validateLoginForm function to the form's submit event
        document.getElementById('login-form').addEventListener('submit', function(event) {
            if(!validateLoginForm()) {
                event.preventDefault(); // Prevent form submission if validation fails
            }
        });
    </script>
<%@include file="../commom/web/footer.jsp"%>