package main.java;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class ListFilms extends HttpServlet {

    private CrudFilms crudFilms;

    public ListFilms() {
        super();
        crudFilms = new CrudFilms();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("list", crudFilms.listAllFilms());

       request.getRequestDispatcher("index.jsp").forward(request, response);


    }


}
