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

@WebServlet(name="SelectBalloonServlet", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {

    private final BalloonService balloonService;
    private final SpringTemplateEngine springTemplateEngine;

    public SelectBalloonServlet(BalloonService balloonService, SpringTemplateEngine springTemplateEngine) {
        this.balloonService = balloonService;
        this.springTemplateEngine = springTemplateEngine;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("balloon", req.getSession().getAttribute("balloon"));
        //context.setVariable("bodyContent", "selectBalloonSize");
        resp.setContentType("text/html");
        if(context.getSession().getAttribute("balloon") == null){
            resp.sendRedirect("/");
        }else{
            //springTemplateEngine.process("master-template", context, resp.getWriter());
            springTemplateEngine.process("selectBalloonSize.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String balloonSize = req.getParameter("size");
            req.getSession().setAttribute("balloonSize", balloonSize);
            resp.sendRedirect("/BalloonOrder");
    }
}
