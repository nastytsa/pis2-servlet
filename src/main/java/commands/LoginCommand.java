package commands;

import entities.RoleEnum;
import entities.User;
import service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/login.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Long id = Long.valueOf(request.getParameter("id"));
        String password = request.getParameter("password");
        User user = LoginService.getUser(id, password);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        response.sendRedirect("/orders");

    }
}
