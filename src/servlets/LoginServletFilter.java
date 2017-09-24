package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Александр on 21.09.2017.
 */
@WebFilter("/LoginServletFilter")
public class LoginServletFilter implements Filter {
    private FilterConfig filterConfig;
    private static ArrayList<String> pages;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        pages = new ArrayList<String>();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String[] list = request.getRequestURI().split("/");
            String page = null;
            if (list[list.length-1].indexOf(".jsp") > 0){
                page = list[list.length-1];
            }
            if((page != null) && page.equalsIgnoreCase("index.jsp")){
                if (pages.contains("AuthorizationUser.jsp") || pages.contains("CreateUser.jsp")){
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }else {
                    ServletContext servletContext = filterConfig.getServletContext();
                    RequestDispatcher dispatcher = servletContext.getRequestDispatcher("views/user/AuthorizationUser.jsp");
                    dispatcher.forward(servletRequest, servletResponse);
                    return;
                }
            } else if (page != null){
                if (!pages.contains(page)) pages.add(page);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
