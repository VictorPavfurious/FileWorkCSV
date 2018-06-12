
package main.java;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import java.util.Scanner;

public class FilmsDB {

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;


    public static void Conn() throws ClassNotFoundException, SQLException
    {
        File f = new File("OpenFilms.db");
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("JDBC:sqlite:"+f.getAbsolutePath());



    }

    public static void DropDB() throws SQLException {
        statmt = conn.createStatement();
        statmt.execute("DROP TABLE if EXISTS films");


    }

    public static void CreateDB() throws SQLException
    {
        statmt = conn.createStatement();

        statmt.execute("CREATE TABLE if NOT EXISTS films (id int, title varchar(40), description varchar(200))");

    }

    public static void WriteDB() throws SQLException, IOException {

        URL url = new URL ("https://api.themoviedb.org/3/movie/upcoming?api_key=60d60bb4759635c09cde63d375d1b094");

        Scanner scanner = new Scanner(url.openStream());

        String str= new String();
        while(scanner.hasNext())
            str += scanner.nextLine();
        scanner.close();

        JSONObject obj = new JSONObject(str);

        JSONArray array = obj.getJSONArray("results");


        for (int i = 0; i < array.length(); i++) {
           int id = array.getJSONObject(i).getInt("id");
           String title = array.getJSONObject(i).getString("title");
           String description = array.getJSONObject(i).getString("overview");

            PreparedStatement prst = conn.prepareStatement("INSERT INTO films VALUES (?,?,?)");
            prst.setInt(1, id);
            prst.setString(2, title);
            prst.setString(3, description);
            prst.executeUpdate();
        }




    }

    public static void ReadDB(ArrayList<Films> list) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM films");

        while(resSet.next())
        {

            list.add((new Films(resSet.getInt("id"),resSet.getString("title"),
                    resSet.getString("description"))));

        }



    }

}
