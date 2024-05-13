

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








                    <form action="" method=''>

                        <table class="table table-bordered table-hover">


                            <div id="bulkOptionContainer" class="col-xs-4">

                                <select class="form-control" name="bulk_options" id="">
                                    <option value="">Select Options</option>
                                    <option value="published">Publish</option>
                                    <option value="draft">Draft</option>
                                    <option value="delete">Delete</option>
                                    <option value="clone">Clone</option>
                                </select>

                            </div>


                            <div class="col-xs-4">

                                <input type="submit" name="submit" class="btn btn-success" value="Apply">
                                <a class="btn btn-primary" href="posts.php?source=add_post">Add New</a>

                            </div>



                            <thead>
                            <tr>
                                <th><input id="selectAllBoxes" type="checkbox"></th>
                                <th>Id</th>
                                <th>Users</th>
                                <th>Title</th>
                                <th>Category</th>
                                <th>Status</th>
                                <th>Image</th>
                                <th>Tags</th>
                                <th>Comments</th>
                                <th>Date</th>
                                <th>View Post</th>
                                <th>Edit</th>
                                <th>Delete</th>
                                <th>Views</th>
                            </tr>
                            </thead>

                            <tbody>


                            <td><input class='checkBoxes' type='checkbox' name='checkBoxArray[]' value=''></td>




                            <td>post_id </td>




                            <td>post_author</td>










                            <td>post_title</td>






                            <td>catgories_title</td>





                            <td>$post_status</td>
                            <td><img width='100' src='../images/$post_image' alt='image'></td>
                            <td>$post_tags</td>




                            <td><a href='post_comments.php?id=$post_id'>$count_comments</a></td>



                            <td>$post_date </td>
                            <td><a class='btn btn-primary' href='../post.php?p_id={$post_id}'>View Post</a></td>
                            <td><a class='btn btn-info' href='posts.php?source=edit_post&p_id={$post_id}'>Edit</a></td>




                            <form method="post">

                                <input type="hidden" name="post_id" value="">



                                <td><input class="btn btn-danger" type="submit" name="delete" value="Delete"></td>



                            </form>















                            <td><a href='posts.php?reset={$post_id}'>post_views_count</a></td>;
                            </tr>;









                            </tbody>
                        </table>

                    </form>




                </div>
            </div>
            <!-- /.row -->

        </div>
    <!-- /.container-fluid -->

</div>

<%@include file="../../../commom/admin/footer.jsp"%>