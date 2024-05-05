package Dao;

import model.Categories;

import java.util.List;

public interface ICategoriesDao extends GenericDao<Categories>{
    List<Categories> findAll();
    Categories findOne(Integer id);
    Categories findOneByCode(String code);
}
