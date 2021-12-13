package service;

import dao.implementations.OrderDaoImpl;
import entities.Order;
import entities.User;

import java.util.Collection;

public class OrderService {
    public static void create(Long client_id, String content){
        Order order = new Order(client_id, content);
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.save(order);
    }

    public static Collection<Order> getClientOrders(Long client_id){
        OrderDaoImpl orderDao = new OrderDaoImpl();

        return orderDao.getByClientId(client_id);
    }

    public static Collection<Order> getAllOrders(){
        OrderDaoImpl orderDao = new OrderDaoImpl();

        return orderDao.getAll();
    }

    public static Order getOrderById(Long id){
        OrderDaoImpl orderDao = new OrderDaoImpl();

        return orderDao.get(id).get();
    }

    public static void update(Order order){
        OrderDaoImpl orderDao = new OrderDaoImpl();

        orderDao.update(order);
    }
}
