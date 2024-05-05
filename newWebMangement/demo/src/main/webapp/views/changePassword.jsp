<%--
  Created by IntelliJ IDEA.
  User: Huy
  Date: 5/2/2024
  Time: 2:22 PM
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
                            <h2 class="text-center">Change Password</h2>
                            <div class="panel-body">


                                <form  action="<c:url value='/changePassword'/>" id="login-form" role="form" autocomplete="off" class="form" method="POST">

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock color-blue"></i></span>
                                            <input name="password" type="password" class="form-control" placeholder="Enter Password">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock color-blue"></i></span>
                                            <input name="ConfirmPassword" type="password" class="form-control" placeholder="Enter confirm Password">
                                        </div>
                                    </div>
                                    <%
                                        String message= request.getAttribute("changePasswordError")+"";
                                        if(message==null){
                                            message="";
                                        }

                                    %>
                                    <div class="text-center"><span class="red" style="color: #f10707"><%=message%></span></div>
                                    <div class="form-group">

                                        <input name="action" class="btn btn-lg btn-primary btn-block" value="changed password" type="submit">
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
        function validateChangePasswordForm() {
            // Get form fields
            var password = document.getElementsByName('password')[0].value;
            var confirmPassword = document.getElementsByName('ConfirmPassword')[0].value;

            // Check if fields are empty
            if(password == "" || confirmPassword == "") {
                alert("All fields must be filled out");
                return false;
            }

            // Check if passwords match
            if(password != confirmPassword) {
                alert("Passwords do not match");
                return false;
            }

            return true;
        }

        // Attach the validateChangePasswordForm function to the form's submit event
        document.getElementById('login-form').addEventListener('submit', function(event) {
            if(!validateChangePasswordForm()) {
                event.preventDefault(); // Prevent form submission if validation fails
            }
        });
    </script>
<%@include file="../commom/web/footer.jsp"%>