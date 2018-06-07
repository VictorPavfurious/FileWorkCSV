package main.java;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ViewFilms extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String COMMA_DELIMITER = ",";
        File file = new File("filmdata.csv");
        ArrayList<Films> filmlist = new ArrayList<>();
        String line = "";
        BufferedReader fileReader = new BufferedReader(new FileReader(file));



        while ((line = fileReader.readLine()) != null) {
            String[] tokens = line.split(COMMA_DELIMITER);
            if (tokens.length > 0) {
                filmlist.add (new Films(Integer.parseInt(tokens[0]),tokens[1], tokens[2]));

            }
        }

        for (Films films : filmlist)
            if(request.getParameter("id").equals(String.valueOf(films.getId()))) {
                request.setAttribute("description", films.getOverview());
            }





        request.getRequestDispatcher("descriptionF.jsp").forward(request, response);

    }


}

