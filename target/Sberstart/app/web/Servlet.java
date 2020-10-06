package main.webapp.app.web;

import main.webapp.app.storage.SQLStorage;
import main.webapp.app.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Servlet {

    private Storage storage = new SQLStorage("jdbc:h2:mem:storage", "root", "password");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
