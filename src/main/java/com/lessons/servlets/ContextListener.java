package com.lessons.servlets;

import com.lessons.dao.UserDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

@WebListener
public class ContextListener implements ServletContextListener {
//    private AtomicReference<UserDAO> userDAO;
    private UserDAO userDAO;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        userDAO = new AtomicReference<>(new UserDAO());
        userDAO = new UserDAO();
        final ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute("user_dao", userDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        userDAO = null;
    }
}
