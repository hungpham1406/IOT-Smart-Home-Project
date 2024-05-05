package Dao.impl;

import Dao.IUserDao;
import map.UsersMapper;
import model.Users;

import java.util.List;

public class UserDao extends AbstractDao<Users> implements IUserDao {
    @Override
    public Users findByUserNameAndPassword(String userName, String password) {
        String sql="SELECT * FROM users WHERE username = ? AND user_password = ?";
        List<Users> user= query(sql, new UsersMapper(),userName,password);
        return user==null? null:user.get(0);
    }

    @Override
    public void changePassordOfUser(String newPassword,String userName, String password) {
        String sql="UPDATE users SET user_password = ? WHERE username = ? AND user_password = ?";
        update(sql,newPassword,userName,password);
        return;
    }

    @Override
    public Integer save(Users users) {
        String sql= "INSERT INTO users(username,user_password,user_firstname,user_lastname,user_email,user_image,user_role,randSalt,token) VALUES(?,?,?,?,?,?,?,?,?)";
        return insert(sql,users.getUsername(),users.getUser_password(),"","",users.getUser_email(),"","","","");
    }

}
