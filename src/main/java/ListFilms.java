package main.java;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ListFilms extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        URL url = new URL ("https://api.themoviedb.org/3/movie/upcoming?api_key=60d60bb4759635c09cde63d375d1b094");

        Scanner scanner = new Scanner(url.openStream());
        String str= new String();
        while(scanner.hasNext())
            str += scanner.nextLine();
        scanner.close();

        JSONObject obj = new JSONObject(str);


        String COMMA_DELIMITER = ",";

        ArrayList<Films> films = new ArrayList<Films>();
        JSONArray array = obj.getJSONArray("results");

        for (int i = 0; i < array.length(); i++) {
            films.add(new Films(array.getJSONObject(i).getInt("id"), array.getJSONObject(i).getString("title"),
                    array.getJSONObject(i).getString("overview")));
        }

        File file = new File("src\\data\\filmdata.csv");
        FileWriter fileWriter = new FileWriter(file);


        for (int i = 0; i < films.size(); i++) {
            fileWriter.write(String.valueOf(films.get(i).getId()));
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(films.get(i).getTitle());
            fileWriter.write(COMMA_DELIMITER);


        }
        fileWriter.flush();
        fileWriter.close();


        FileReader fileReader = new FileReader(file);
        Scanner sc= new Scanner(fileReader);

        while (sc.hasNextLine()) {
            request.setAttribute("filmlist", sc.nextLine());
        }

        fileReader.close();

       request.getRequestDispatcher("index.jsp").forward(request, response);


    }


}
