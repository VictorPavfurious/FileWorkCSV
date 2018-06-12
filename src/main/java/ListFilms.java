package main.java;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;


public class ListFilms extends HttpServlet {

    private FilmsAllUse filmsAllUse;

    public ListFilms() {
        super();
        filmsAllUse = new FilmsAllUse();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

           filmsAllUse.Createdb();

            request.setAttribute("list", filmsAllUse.listAllFilms());

        }


       catch (SQLException e) {
            e.printStackTrace();
        }


        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        request.getRequestDispatcher("index.jsp").forward(request, response);


    }


}
