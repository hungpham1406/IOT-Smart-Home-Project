package map;

import model.Categories;
import model.Comments;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentsMapper implements RowMapper<Comments>{

    @Override
    public Comments mapRow(ResultSet resultSet) {
        try{
            Comments comments = new Comments();
            comments.setId(resultSet.getInt("id"));
            comments.setComment_post_id(resultSet.getInt("comment_post_id"));
            comments.setComment_author(resultSet.getString("comment_author"));
            comments.setComment_email(resultSet.getString("comment_email"));
            comments.setComment_content(resultSet.getString("comment_content"));
            comments.setComment_status(resultSet.getString("comment_status"));
            comments.setComment_date(resultSet.getDate("comment_date"));
            return comments;
        }catch(SQLException e){
            return null;
        }
    }
}
