package com.lessons.servlets.servlet;

import com.lessons.dao.PostDAO;
import com.lessons.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class DeleteServlet extends HttpServlet {
    private final AtomicReference<PostDAO> postDAO = new AtomicReference<>(new PostDAO());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String qwe = req.getParameter("id");
        Integer id = Integer.valueOf(qwe);
        postDAO.get().delete(id);
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
