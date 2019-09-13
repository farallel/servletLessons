package com.lessons.servlets.servlet;

import com.lessons.dao.PostDAO;
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
    private final AtomicReference<PostDAO> postDAO = new AtomicReference<>(new PostDAO());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
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
        String text = req.getParameter("text");
        Integer userId = (Integer) req.getSession().getAttribute("id");
        postDAO.get().create(new Post(text, userId));
        doGet(req, resp);
    }
}
