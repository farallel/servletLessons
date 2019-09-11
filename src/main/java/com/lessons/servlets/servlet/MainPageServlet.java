package com.lessons.servlets.servlet;

import com.lessons.dao.PostDAO;
import com.lessons.dao.UserDAO;
import com.lessons.model.Post;
import com.lessons.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
//        final AtomicReference<PostDAO> postDAO = (AtomicReference<PostDAO>) req.getServletContext().getAttribute("post_dao");
        final AtomicReference<PostDAO> postDAO = new AtomicReference<>(new PostDAO());
        ArrayList<Post> posts = postDAO.get().readAll();
        req.setAttribute("posts", posts);

        final Role role = (Role) session.getAttribute("role");
        System.out.println(role);
        if (role == Role.ADMIN) {
            req.getRequestDispatcher("/WEB-INF/view/admin_page.jsp").forward(req, resp);
        } else if (role == Role.USER) {
            req.getRequestDispatcher("/WEB-INF/view/user_page.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO Add adding functionality.
        doGet(req, resp);
    }
}
