package map;

import model.Categories;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriesMapper implements RowMapper<Categories>{
    @Override
    public Categories mapRow(ResultSet resultSet) {
        try {

            Categories categories = new Categories();
            categories.setId(resultSet.getInt("id"));
            categories.setTitle(resultSet.getString("title"));
            return categories;
        } catch(SQLException e) {
            return null;
        }
    }
}
