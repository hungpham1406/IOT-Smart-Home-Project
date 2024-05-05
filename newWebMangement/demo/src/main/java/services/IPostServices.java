package services;

import model.Post;

import java.util.Iterator;
import java.util.List;

public interface IPostServices {
    Post save(Post newModel);
    Post update(Post updateNew);
    void delete(long[] ids);
    int getTotalItem();
    Post findOne(long id);
}
