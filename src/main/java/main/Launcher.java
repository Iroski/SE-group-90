package main;

import javafx.application.Application;
import model.dao.base.DataHouse;

public class Launcher {
    static DataHouse db;
    public static void main(String[] args) {

        db= DataHouse.getInstance();
        db.init("src/main/resources/database");
        Application.launch(Main.class, args);
    }
}

