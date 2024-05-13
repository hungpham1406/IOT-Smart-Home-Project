<%--
  Created by IntelliJ IDEA.
  User: Huy
  Date: 4/24/2024
  Time: 7:54 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- Page Content -->
<%@include file="../commom/web/header.jsp"%>
<%@include file="../commom/web/navBar.jsp"%>
<c:url var="APIurl" value="/api-register"/>
<div class="container">

    <section class="vh-100" style="background-color: #eee;">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-lg-12 col-xl-11">
                    <div class="card text-black" style="border-radius: 25px;">
                        <div class="card-body p-md-5">
                            <div class="row justify-content-center">
                                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                    <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                                    <form id="register" class="mx-1 mx-md-4">

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div data-mdb-input-init class="form-outline flex-fill mb-0">
                                                <input name="username" type="text" id="form3Example1c" class="form-control" />
                                                <label class="form-label" for="form3Example1c">Your Name</label>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                            <div data-mdb-input-init class="form-outline flex-fill mb-0">
                                                <input name="email" type="email" id="form3Example3c" class="form-control" />
                                                <label class="form-label" for="form3Example3c">Your Email</label>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div data-mdb-input-init class="form-outline flex-fill mb-0">
                                                <input name="password" type="password" id="form3Example4c" class="form-control" />
                                                <label class="form-label" for="form3Example4c">Password</label>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                            <div data-mdb-input-init class="form-outline flex-fill mb-0">
                                                <input name="confirmPassword" type="password" id="form3Example4cd" class="form-control" />
                                                <label class="form-label" for="form3Example4cd">Repeat your password</label>
                                            </div>
                                        </div>



                                        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                            <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg">Register</button>
                                        </div>

                                    </form>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <hr>
    <script>

        document.getElementById('register').addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent the default form submission behavior

            // Collecting form data
            var formData = {
                username: document.getElementById('form3Example1c').value,
                user_password: document.getElementById('form3Example4c').value,
                user_email: document.getElementById('form3Example3c').value,

            };

            // Sending form data as JSON to the specified API endpoint
            fetch('${APIurl}', { // This is your API endpoint
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json' // Ensure the server expects a JSON body
                },
                body: JSON.stringify(formData)
            })
                .then(response => {
                    if (response.ok) {
                        return response.json(); // Process the response if it's OK
                    }
                    throw new Error('Network response was not ok.'); // Handle errors
                })
                .then(data => console.log('Success:', data)) // Log success
                .catch(error => console.error('Error:', error)); // Log any errors
        });

    </script>

    <script>
        function validateForm() {
            // Get form fields
            var username = document.getElementById('form3Example1c').value;
            var email = document.getElementById('form3Example3c').value;
            var password = document.getElementById('form3Example4c').value;
            var confirmPassword = document.getElementById('form3Example4cd').value;

            // Check if fields are empty
            if(username == "" || email == "" || password == "" || confirmPassword == "") {
                alert("All fields must be filled out");
                return false;
            }

            // Check if email is valid
            var emailRegex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
            if(!emailRegex.test(email)) {
                alert("Please enter a valid email");
                return false;
            }

            // Check if passwords match
            if(password != confirmPassword) {
                alert("Passwords do not match");
                return false;
            }

            return true;
        }

        // Attach the validateForm function to the form's submit event
        document.getElementById('register').addEventListener('submit', function(event) {
            if(!validateForm()) {
                event.preventDefault(); // Prevent form submission if validation fails
            }
        });
    </script>

<%@include file="../commom/web/footer.jsp"%>