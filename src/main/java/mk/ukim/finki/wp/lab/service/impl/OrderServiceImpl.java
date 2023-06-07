package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.repository.jpa.OrderRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepositoryJPA;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryJPA orderRepository;

    private final UserRepositoryJPA userRepositoryJPA;

    public OrderServiceImpl(OrderRepositoryJPA orderRepository, UserRepositoryJPA userRepositoryJPA) {
        this.orderRepository = orderRepository;
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @Override
    public Optional<Order> placeOrder(String balloonColor, String balloonSize, String clientName, String address, LocalDateTime dateCreated, User user) {
        return Optional.of(this.orderRepository.save(new Order(balloonColor, balloonSize, clientName, address, dateCreated, user)));
    }

    @Override
    public List<Order> findAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByDateCreatedBetween(LocalDateTime from, LocalDateTime to) {
        return orderRepository.findAllByDateCreatedBetween(from, to);
    }

    @Override
    public List<Order> findAllByName(String filter_select) {
        //return this.orderRepository.findAllByUser(filter_select);
        return null;
    }

    @Override
    public List<Order> findAllByUserId(Long orderId) {
        User user = this.userRepositoryJPA.findById(orderId).orElseThrow(() -> new InvalidArgumentsException());
        return this.orderRepository.findByUser(user);
    }

//    @Override
//    public List<Order> findAllByUser(String filter_select) {
//        return orderRepository.findAllByUser(filter_select);
//    }
//


}
