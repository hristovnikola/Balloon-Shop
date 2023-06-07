package mk.ukim.finki.wp.lab.web.servlet.controller;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.OrderService;
import mk.ukim.finki.wp.lab.service.UserService;
import mk.ukim.finki.wp.lab.service.impl.OrderServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.UserServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderService;
    private final UserServiceImpl userService;

    public OrderController(OrderServiceImpl orderService, UserServiceImpl userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String getOrdersPage(Model model, Authentication authentication,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                @RequestParam(required = false) String filter_select){
        List<Order> orders;
        List<User> users;
        if (from != null && to != null)
            orders = this.orderService.findAllByDateCreatedBetween(from, to);
        else
            orders = this.orderService.findAllOrders();
        model.addAttribute("orders", orders);
        users = this.userService.findAll();
        model.addAttribute("all_users", users);
        //String user = authentication.name();
        //model.addAttribute("user", user);
        return "userOrders";
    }

    @GetMapping("/filterUsers")
    public String filterUsers(Model model,
                              @RequestParam(required = false) String filter_select,
                              @RequestParam(required = false) Long orderId){

        List<Order> orders;
        List<User> users;
        users = this.userService.findAll();
        orders = this.orderService.findAllByUserId(orderId);
        model.addAttribute("orders", orders);
        model.addAttribute("all_users", users);
        return "userOrders";
    }
}
