package app.web;

import app.Config;
import app.storage.Storage;

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
        request.setAttribute("cardList", storage.getAllCardsSorted());
        request.getRequestDispatcher("web/index.jsp").forward(request, response);
    }

}
