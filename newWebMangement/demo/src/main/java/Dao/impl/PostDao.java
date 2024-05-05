package Dao.impl;

import Dao.IPostDao;
import map.PostMapper;
import model.Post;

import java.util.List;

public class PostDao extends AbstractDao<Post> implements IPostDao {
    @Override
    public Post findOne(Long id) {
        String sql="SELECT * FROM posts where post_id = ?";
        List<Post> posts= query(sql, new PostMapper(),id);
        return posts==null? null : posts.get(0);
    }

    @Override
    public List<Post> findByCategoryId(Long categoryId) {
        return List.of();
    }

    @Override
    public Integer save(Post post) {
        StringBuilder sql = new StringBuilder("INSERT INTO posts(post_category_id, post_title,");
        sql.append(" post_author, post_user, post_date, post_image, post_content, post_tags, post_comment_count, post_status, post_views_count)");
        sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(),post.getPost_category_id(),post.getPost_title(),post.getPost_author(),
                post.getPost_user(),post.getPost_date(),post.getPost_image(),post.getPost_content(),post.getPost_tags(),post.getPost_comment_count(),
        post.getPost_status(),post.getPost_views_count());
    }

    @Override
    public void update(Post updateNew) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public int getTotalItem() {
        return 0;
    }
}
