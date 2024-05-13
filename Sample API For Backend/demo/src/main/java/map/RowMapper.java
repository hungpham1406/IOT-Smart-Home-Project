package map;

import model.Categories;

import java.sql.ResultSet;

public interface RowMapper <T>{

    T mapRow(ResultSet resultSet);
}
