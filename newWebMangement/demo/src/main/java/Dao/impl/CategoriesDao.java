package Dao.impl;

import Dao.ICategoriesDao;
import map.CategoriesMapper;
import model.Categories;

import java.util.List;

public class CategoriesDao extends AbstractDao<Categories> implements ICategoriesDao {
    @Override
    public List<Categories> findAll() {
        String sql="SELECT * FROM categories";
        return query(sql,new CategoriesMapper());
    }

    @Override
    public Categories findOne(Integer id) {
        String sql="SELECT * FROM categories WHERE cat_id = ?";
        List<Categories> categories= query(sql,new CategoriesMapper(),id);
        return categories.isEmpty()? null: categories.get(0);
    }

    @Override
    public Categories findOneByCode(String code) {
        String sql="SELECT * FROM categories WHERE cat_id = ?";
        List<Categories> categories= query(sql,new CategoriesMapper(),code);
        return categories.isEmpty()? null: categories.get(0);
    }
}
