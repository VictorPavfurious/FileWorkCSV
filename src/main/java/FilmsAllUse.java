package main.java;


import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FilmsAllUse {



    public List<Films> listAllFilms() throws SQLException {

        ArrayList<Films> list = new ArrayList<>();

        FilmsDB.ReadDB(list);

       return list;

    }

    public void Createdb() throws SQLException, ClassNotFoundException, IOException {
        FilmsDB.Conn();
        FilmsDB.DropDB();
        FilmsDB.CreateDB();
        FilmsDB.WriteDB();
    }

    }
