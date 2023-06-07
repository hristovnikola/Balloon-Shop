package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.dataholder.DataHolder;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderRepository {

    public List<Order> findOrders(){
        return DataHolder.orders;
    }

    public Order saveOrder(String balloonColor, String balloonSize, String clientName, String clientAddress, LocalDateTime dateCreated, User user){
        Order order = new Order(balloonColor, balloonSize, clientName, clientAddress, dateCreated, user);
        DataHolder.orders.add(order);
        return order;
    }

}
