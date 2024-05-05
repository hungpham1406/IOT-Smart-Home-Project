package services.implement;

import Dao.IPostDao;
import Dao.impl.PostDao;
import jakarta.inject.Inject;
import model.Post;
import services.IPostServices;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class PostServices implements IPostServices {


    @Override
    public Post save(Post newPost) {
        LocalDate currentDate = LocalDate.now();
        newPost.setPost_date(Date.valueOf(currentDate));
        PostDao postDao= new PostDao();
        Integer id= postDao.save(newPost);
        if (id == null) {
            // Handle the situation when id is null, for example, throw an exception or return null
            throw new RuntimeException("Failed to save the post");
        }
        return postDao.findOne(Long.valueOf(id));
    }

    @Override
    public Post update(Post updateNew) {
        return null;
    }

    @Override
    public void delete(long[] ids) {

    }




    @Override
    public int getTotalItem() {
        return 0;
    }

    @Override
    public Post findOne(long id) {
        PostDao postDao= new PostDao();
        return postDao.findOne(id);
    }
}
