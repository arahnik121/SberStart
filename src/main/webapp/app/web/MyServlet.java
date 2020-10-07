package main.webapp.app.web;

import main.webapp.app.Config;
import main.webapp.app.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyServlet extends HttpServlet {
    private final Storage storage = Config.initDatabase();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardID = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("cardList", storage.getAllCardsSorted());
            request.getRequestDispatcher("main/webapp/WEB-INF/jsp/index.jsp").forward(request, response);
            return;
        }
    }

}
