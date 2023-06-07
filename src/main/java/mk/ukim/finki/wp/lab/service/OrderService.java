package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> placeOrder(String balloonColor, String balloonSize, String clientName, String address, LocalDateTime dateCreated, User user);

    List<Order> findAllOrders();

    List<Order> findAllByDateCreatedBetween(LocalDateTime from, LocalDateTime to);

    List<Order> findAllByName(String filter_select);

    //List<Order> findAllByUser(String filter_select);

    List<Order> findAllByUserId(Long orderId);
}
