package map;

import model.Post;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements RowMapper<Post> {


    @Override
    public Post mapRow(ResultSet resultSet) {
        /*private int post_id;
        private int post_category_id;
        private String post_title;
        private String post_author;
        private String post_user;
        private Date post_date;
        private String post_image;
        private String post_content;
        private String post_tags;
        private String post_comment_count;
        private String post_status;
        private int post_views_count;*/
        try{
          Post post = new Post();
          post.setPost_id(resultSet.getInt("post_id"));
          post.setPost_category_id(resultSet.getInt("post_category_id"));
          post.setPost_title(resultSet.getString("post_title"));
          post.setPost_author(resultSet.getString("post_author"));
          post.setPost_user(resultSet.getString("post_author"));
          post.setPost_date(resultSet.getDate("post_date"));
          post.setPost_image(resultSet.getString("post_image"));
          post.setPost_content(resultSet.getString("post_content"));
          post.setPost_tags(resultSet.getString("post_tags"));
          post.setPost_comment_count(resultSet.getString("post_comment_count"));
          post.setPost_status(resultSet.getString("post_status"));
          post.setPost_views_count(resultSet.getInt("post_views_count"));

            return post;
        }catch(SQLException e){
            return null;
        }
    }
}
