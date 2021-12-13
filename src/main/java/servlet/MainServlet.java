package servlet;

import commands.LoginCommand;
import commands.OrderCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet{

    @Override
    public void init() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processGetRequest(request, response);
    }

    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processPostRequest(request, response);
    }

    private void processGetRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        switch (request.getServletPath()){
            case "/":
                LoginCommand loginCommand = new LoginCommand();
                loginCommand.doGet(request, response);
                break;
            case "/orders":
                OrderCommand orderCommand = new OrderCommand();
                orderCommand.doGet(request, response);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + request.getServletPath());
        }
    }

    private void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        switch (request.getServletPath()){
            case "/":
                LoginCommand loginCommand = new LoginCommand();
                loginCommand.doPost(request, response);
                break;
            case "/orders":
                OrderCommand orderCommand = new OrderCommand();
                orderCommand.doPost(request, response);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + request.getServletPath());
        }
    }

}
