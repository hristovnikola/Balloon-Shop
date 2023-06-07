/*
package mk.ukim.finki.wp.lab.web.servlet.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class SelectBalloonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();
        String color = (String) request.getSession().getAttribute("balloon");
        if(!servletPath.startsWith("/balloons") && !servletPath.equals("") && !servletPath.equals("/orders") && color == null){
            response.sendRedirect("/balloons");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
*/