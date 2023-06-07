package mk.ukim.finki.wp.lab.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "balloon_orders")
public class Order {

    private String balloonColor;

    private String balloonSize;

    //private String clientName;

    //private String clientAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    private User user;

    private LocalDateTime dateCreated;

    public Order(String balloonColor, String balloonSize, String clientName, String clientAddress, LocalDateTime dateCreated, User user) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.dateCreated = dateCreated;
        this.user = user;
        //this.clientName = clientName;
        //this.clientAddress = clientAddress;
    }

    public Order(){
    }
}
