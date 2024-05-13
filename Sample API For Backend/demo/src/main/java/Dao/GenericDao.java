package Dao;

import map.RowMapper;

import java.util.ArrayList;
import java.util.List;

public interface GenericDao <T>{
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
    void update (String sql, Object... parameters);
    Integer insert (String sql, Object... parameters);
    int count(String sql, Object... parameters);
    T selectById(T t);
    int delete(T t);
    int deleteAll(ArrayList<T> arrayList);



}
