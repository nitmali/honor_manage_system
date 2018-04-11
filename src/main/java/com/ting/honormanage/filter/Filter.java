package com.ting.honormanage.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author nitmali@126.com
 * @date 2018/4/10 15:43
 */
@WebFilter(filterName = "Filter", urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        boolean flag = true;
        String login = "login";
        String logout = "logout";
        String root = "/";
        String webjars = "webjars";
        String css = "css";
        String js = "js";
        String publicResources = "publicResources";
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userType = (String) request.getSession().getAttribute("userType");
        String uri = request.getRequestURI();

        if (userType != null) {
            if (uri.contains(userType)) {
                flag = false;
            }
        }

        if (uri.contains(login) || Objects.equals(uri, root) || uri.contains(webjars)
                || uri.contains(css) || uri.contains(js) || uri.contains(logout)
                || uri.contains(publicResources)) {
            flag = false;
        }
//        flag = false;
        if (flag) {
            response.sendRedirect("/");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

}