package controller.web;

import Dao.impl.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

import java.io.IOException;
@WebServlet(urlPatterns = {"/login","/logout","/changePassword","/register"})

public class HomeController extends HttpServlet {
    private String url="/views/web/home.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if(action.equals("login")){
            login(req,resp);
        }else if(action.equals("logout")){
            logout(req,resp);
        }else if(action.equals("register")){

        }else if(action.equals("changed password")){
            changePassword(req,resp);
        }else if(action.equals("change-profile")){

        }else if(action==null){
            RequestDispatcher rd= getServletContext().getRequestDispatcher("/views/web/home.jsp");
            rd.forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        Users user = new Users();
        UserDao userDao = new UserDao();
        user = userDao.findByUserNameAndPassword(userName, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            url = "/index.jsp";
        } else {
            request.setAttribute("login-error", "You are fill the wrong password or userName please Try again");
            url = "/views/login.jsp";
        }
        RequestDispatcher rd= getServletContext().getRequestDispatcher(url);
        rd.forward(request,response);
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
           // destroy session
            session.invalidate();

            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();

            response.sendRedirect(url + "/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void changePassword(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        String change_pass_error="";

        String url="/views/changePassword.jsp";
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        Users user = new Users();
        if(obj!=null){
            user=(Users) obj;
        }
        if(user==null){
            change_pass_error="You haven't login yet";
        }else {
            String userName = user.getUsername();
            String password = user.getUser_password();
            String inputUserPass= request.getParameter("password");
            String inputUserConfirmPass= request.getParameter("ConfirmPassword");
            if(!inputUserPass.equals(inputUserConfirmPass)){
                change_pass_error="Your confrim password and you password don't match";
            }else{
                UserDao userDao= new UserDao();
                userDao.changePassordOfUser(inputUserPass,userName,password);
                change_pass_error="you have changed the password successfully";
            }

        }
        request.setAttribute("changePasswordError",change_pass_error);
        RequestDispatcher rd= getServletContext().getRequestDispatcher(url);
        rd.forward(request,response);
    }
    private void register(HttpServletRequest request,HttpServletResponse response){
        return;
    }
}