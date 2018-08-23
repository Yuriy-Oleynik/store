package ua.webstore.auth;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/user/*")
public class UserLoginFilter implements Filter {

    @Inject
    private AuthBean authBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (authBean.isLoggedIn()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


    }

    @Override
    public void destroy() {

    }

}

