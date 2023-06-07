package mk.ukim.finki.wp.lab.web.servlet;


import mk.ukim.finki.wp.lab.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name="BalloonOrder", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonService balloonService;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("balloon", req.getSession().getAttribute("balloon"));
        resp.setContentType("text/html;charset=utf-8");
        if(context.getSession().getAttribute("balloon") == null){
            resp.sendRedirect("/");
        }else{
            context.setVariable("balloonSize", req.getSession().getAttribute("balloonSize"));
            springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String clientName = req.getParameter("clientName");
            String clientAddress = req.getParameter("clientAddress");
            LocalDateTime dateCreated =  LocalDateTime.parse(req.getParameter("dateTime"));
            req.getSession().setAttribute("clientName", clientName);
            req.getSession().setAttribute("clientAddress", clientAddress);
            req.getSession().setAttribute("dateCreated", dateCreated);
            resp.sendRedirect("/ConfirmationInfo");
    }
}
