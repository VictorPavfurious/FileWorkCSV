package main.java;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudFilms {


    public List<Films> listAllFilms() throws IOException {

        URL url = new URL ("https://api.themoviedb.org/3/movie/upcoming?api_key=60d60bb4759635c09cde63d375d1b094");

        Scanner scanner = new Scanner(url.openStream());

        String str= new String();
        while(scanner.hasNext())
            str += scanner.nextLine();
        scanner.close();

        JSONObject obj = new JSONObject(str);


        String COMMA_DELIMITER = ",";
        String NEW_LINE = "\n";

        JSONArray array = obj.getJSONArray("results");

        File file = new File("filmdata.csv");
        FileWriter fileWriter = new FileWriter(file);



        for (int i = 0; i < array.length(); i++) {
            fileWriter.write(String.valueOf(array.getJSONObject(i).getInt("id")));
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(array.getJSONObject(i).getString("title"));
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(array.getJSONObject(i).getString("overview"));
            fileWriter.write(NEW_LINE);

        }

        fileWriter.flush();
        fileWriter.close();

        List<Films> list = new ArrayList<>();

        String line = "";
        BufferedReader fileReader = new BufferedReader(new FileReader(file));



        while ((line = fileReader.readLine()) != null) {
            String[] tokens = line.split(COMMA_DELIMITER);
            if (tokens.length > 0) {
                list.add (new Films(Integer.parseInt(tokens[0]),tokens[1], tokens[2]));

            }
        }

        return list;


    }


    }
