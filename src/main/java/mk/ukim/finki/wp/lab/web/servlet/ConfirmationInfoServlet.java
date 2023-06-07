package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.OrderService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name="Confirmation Info", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonService balloonService;
    private final OrderService orderService;
    private final UserService userService;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService, OrderService orderService, UserService userService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("balloon", req.getSession().getAttribute("balloon"));
        resp.setContentType("text/html");
        if(context.getSession().getAttribute("balloon") == null){
            resp.sendRedirect("/");
        }else{
            context.setVariable("clientName", req.getSession().getAttribute("clientName"));
            context.setVariable("clientAddress", req.getSession().getAttribute("clientAddress"));
            context.setVariable("balloonSize", req.getSession().getAttribute("balloonSize"));
            context.setVariable("ipAddress", req.getRemoteAddr());
            context.setVariable("clientAgent", req.getHeader("User-Agent"));
            springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getSession());

        String balloonColor = (String) req.getSession().getAttribute("balloon");
        String balloonSize = (String) req.getSession().getAttribute("balloonSize");
        String clientName = (String) req.getSession().getAttribute("clientName");
        String clientAddress = (String) req.getSession().getAttribute("clientName");
        LocalDateTime dateCreated = (LocalDateTime) req.getSession().getAttribute("dateCreated");
//        User user = (User) req.getSession().getAttribute("user");
        String username = req.getRemoteUser();
        User user = userService.findByUsername(username).orElse(null);
        this.orderService.placeOrder(balloonColor, balloonSize, clientName, clientAddress, dateCreated, user);
        //req.getSession().invalidate();
        System.out.println(req.getSession());// ne se pecati bidejki ne postoi poveke
        resp.sendRedirect("/orders");
    }
}
