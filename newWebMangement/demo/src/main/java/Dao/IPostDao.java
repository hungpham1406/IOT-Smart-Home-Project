package Dao;

import model.Post;

import java.util.List;

public interface IPostDao extends GenericDao<Post>{
    Post findOne(Long id);
    List<Post> findByCategoryId(Long categoryId);
    Integer save(Post newModel);
    void update(Post updateNew);
    void delete(long id);
    int getTotalItem();
}
