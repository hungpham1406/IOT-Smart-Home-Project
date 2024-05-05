
<%@include file="../../../commom/admin/header.jsp"%>
<div id="wrapper">
    <%@include file="../../../commom/admin/navBar.jsp"%>
    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">

                    <h1 class="page-header">
                        Welcome to admin
                        <small>Author</small>
                    </h1>

                </div>

<form action="" method="post" enctype="multipart/form-data">


    <div class="form-group">
        <label for="title">Post Title</label>
        <input value=""  type="text" class="form-control" name="post_title" id="title">
    </div>

    <div class="form-group">
        <label for="categories">Categories</label>
        <select name="post_category" id="categories">








            <option selected value='{$cat_id}'>{$cat_title}</option>




            echo "<option value='{$cat_id}'>{$cat_title}</option>";













        </select>

    </div>

    <div class="form-group">
        <label for="users">Users</label>
        <select name="post_user" id="users">



            <option value='{$username}'>{$username}</option>




        </select>

    </div>




    <div class="form-group">
        <select name="post_status" >

            <option value=''></option>




            <option value='draft'>Draft</option>";





            <option value='published'>Publish</option>";




        </select>
    </div>






    <div class="form-group">

        <img width="100" src="" alt="">
        <input  type="file" name="image">
    </div>

    <div class="form-group">
        <label for="post_tags">Post Tags</label>
        <input value=""  type="text" class="form-control" name="post_tags" id="post_tags">
    </div>

    <div class="form-group">
        <label for="post_content">Post Content</label>
        <textarea  class="form-control "name="post_content" id="post_content" cols="30" rows="10">


         </textarea>
    </div>



    <div class="form-group">
        <input class="btn btn-primary" type="submit" name="update_post" value="Update Post">
    </div>


</form>









            </div>
        </div>
        <!-- /.row -->

    </div>
    <!-- /.container-fluid -->

</div>

<%@include file="../../../commom/admin/footer.jsp"%>