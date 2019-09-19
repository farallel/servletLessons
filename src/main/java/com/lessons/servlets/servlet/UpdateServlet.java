package com.lessons.servlets.servlet;

import com.lessons.dao.PostDAO;
import com.lessons.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class UpdateServlet extends HttpServlet {
    private final AtomicReference<PostDAO> postDAO = new AtomicReference<>(new PostDAO());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Post post = postDAO.get().read(id);
        req.setAttribute("post", post);

        req.getRequestDispatcher("/WEB-INF/view/update_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String text = req.getParameter("text");
        Integer userId = Integer.valueOf(req.getParameter("user_id"));
        Post post = new Post(id, text, userId);

        postDAO.get().update(post);
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
