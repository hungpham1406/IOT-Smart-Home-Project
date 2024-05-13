package controller.web.api;

import Dao.impl.UserDao;
import Utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

import java.io.IOException;
@WebServlet("/api-register")
public class RegisterAPI extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Users user = HttpUtil.of(req.getReader()).toModel(Users.class);
        UserDao userDao= new UserDao();
        userDao.save(user);
        mapper.writeValue(resp.getOutputStream(),user);


    }
}
