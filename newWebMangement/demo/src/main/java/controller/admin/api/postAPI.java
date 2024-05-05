package controller.admin.api;

import Utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import services.IPostServices;
import services.implement.PostServices;

import java.io.IOException;

@WebServlet("/api-Post")
public class postAPI extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Post newPost= HttpUtil.of(req.getReader()).toModel(Post.class);
        PostServices postServices= new PostServices();
        newPost= postServices.save(newPost);
        mapper.writeValue(resp.getOutputStream(), newPost);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Post newPost= HttpUtil.of(req.getReader()).toModel(Post.class);
        PostServices postServices= new PostServices();
        newPost= postServices.save(newPost);
        mapper.writeValue(resp.getOutputStream(), newPost);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
