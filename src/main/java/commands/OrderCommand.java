package commands;

import entities.Order;
import entities.RoleEnum;
import entities.User;
import service.LoginService;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

public class OrderCommand {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole() == RoleEnum.CLIENT) {
            Collection<Order> orders = OrderService.getClientOrders(user.getId());
            request.setAttribute("clientOrderList", orders);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/order-client.jsp");
            try {
                dispatcher.forward(request, response);
                return;
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

        if (user.getRole() == RoleEnum.ADMIN) {
            String order_id = request.getParameter("order_id");
            if(order_id != null){
                Order order = OrderService.getOrderById(Long.valueOf(order_id));
                request.setAttribute("order", order);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/order-edit.jsp");
                try {
                    dispatcher.forward(request, response);
                    return;
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }
            Collection<Order> orders = OrderService.getAllOrders();
            request.setAttribute("orderList", orders);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/order-all.jsp");
            try {
                dispatcher.forward(request, response);
                return;
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if(user.getRole() == RoleEnum.CLIENT){
            String content = request.getParameter("content");
            OrderService.create(user.getId(), content);
            Collection<Order> orders = OrderService.getClientOrders(user.getId());

            request.setAttribute("clientOrderList", orders);
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/templates/order-client.jsp");
            try {
                dispatcher.forward(request, response);
                return;
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        if(user.getRole() == RoleEnum.ADMIN){
            Order order = obtainOrder(request);
            OrderService.update(order);
            Collection<Order> orders = OrderService.getAllOrders();

            request.setAttribute("orderList", orders);
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/templates/order-all.jsp");
            try {
                dispatcher.forward(request, response);
                return;
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

    private Order obtainOrder(HttpServletRequest request){
        String order_id = request.getParameter("order_id");
        String client_id = request.getParameter("client_id");
        String price = request.getParameter("price");
        String comment = request.getParameter("_comment");
        String status = request.getParameter("status");
        String content = request.getParameter("content");

        return new Order(Long.valueOf(order_id), comment, Long.valueOf(client_id), BigDecimal.valueOf(Long.parseLong(price)), status, content);
    }
}
