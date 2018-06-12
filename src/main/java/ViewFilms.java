package main.java;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewFilms extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        ArrayList<Films> filmlist = new ArrayList<>();

        try {
            FilmsDB.ReadDB(filmlist);


            for (Films films : filmlist)
                if(Integer.parseInt(request.getParameter("id")) == films.getId()) {
                    request.setAttribute("description", films.getOverview());
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("descriptionF.jsp").forward(request, response);

    }


}

