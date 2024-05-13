package map;

import model.Users;

import java.sql.ResultSet;

public class UsersMapper implements RowMapper<Users>{


    @Override
    public Users mapRow(ResultSet resultSet) {
        try{
            Users users = new Users();
            users.setUser_id(resultSet.getInt("user_id"));
            users.setUsername(resultSet.getString("username"));
            users.setUser_password(resultSet.getString("user_password"));
            users.setUser_firstname(resultSet.getString("user_firstname"));
            users.setUser_lastname(resultSet.getString("user_lastname"));
            users.setUser_email(resultSet.getString("user_email"));
            users.setUser_image(resultSet.getString("user_image"));
            users.setUser_role(resultSet.getString("user_role"));
            users.setRandSalt(resultSet.getString("randSalt"));
            users.setToken(resultSet.getString("token"));

            return users;
        }catch(Exception e){
            return null;
        }
    }
}
