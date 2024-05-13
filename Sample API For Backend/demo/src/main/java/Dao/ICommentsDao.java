package Dao;

import model.Comments;

import java.util.List;

public interface ICommentsDao extends GenericDao<Comments>{
    Comments findOne(Integer id);
    List<Comments> findByCategoryId(Long categoryId);
    Long save(Comments newModel);
    void update(Comments updateNew);
    void delete(long id);

}
