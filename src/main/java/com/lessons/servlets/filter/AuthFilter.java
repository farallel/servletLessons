package com.lessons.servlets.filter;

import com.lessons.dao.UserDAO;
import com.lessons.model.Role;
import com.lessons.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

//        @SuppressWarnings("unchecked")
//        final AtomicReference<UserDAO> userDAO = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("user_dao");
        final AtomicReference<UserDAO> userDAO = new AtomicReference<>(new UserDAO());
        final HttpSession session = req.getSession();

        //Logged user.
        if (nonNull(session) &&
                nonNull(session.getAttribute("username")) &&
                nonNull(session.getAttribute("password"))) {

            final Role role = (Role) session.getAttribute("role");

            moveToMenu(req, resp, role);
        } else if (userDAO.get().isUserExistByUsernameAndPassword(username, password)) {
            final Role role = userDAO.get().getRoleByUsernameAndPassword(username, password);

            req.getSession().setAttribute("username", username);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("role", role);

            moveToMenu(req, resp, role);
        } else {
            moveToMenu(req, resp, Role.UNKNOWN);
        }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse resp,
                            final Role role) throws ServletException, IOException {
        if (role == Role.ADMIN || role == Role.USER) {
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.getRequestDispatcher("/WEB-INF/view/login_page.jsp").forward(req, resp);
        }

//        if (role == Role.ADMIN) {
//            req.getRequestDispatcher("/WEB-INF/view/admin_page.jsp").forward(req, resp);
//        } else if (role == Role.USER) {
//            req.getRequestDispatcher("/WEB-INF/view/user_page.jsp").forward(req, resp);
//        } else {
//            req.getRequestDispatcher("/WEB-INF/view/login_page.jsp").forward(req, resp);
//        }
    }

    @Override
    public void destroy() {
    }
}
