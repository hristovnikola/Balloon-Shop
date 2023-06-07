package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepositoryJPA extends JpaRepository<Order, Long> {
    List<Order> findAllByDateCreatedBetween(LocalDateTime from, LocalDateTime to);

    //List<Order> findAllByUser(String fiter_user);

    List<Order> findByUser(User user);

    //List<Order> findAllByOrderId(Long id);
}
