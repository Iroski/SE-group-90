package main;

import javafx.application.Application;
import model.dao.base.DataHouse;

public class Launcher {
    static DataHouse db;
    public static void main(String[] args) {

        db= DataHouse.getInstance();
//        if(args==null||args.length==0){
//            System.out.println("no args");
//            db.init("database");
//        }
//        else
            db.init("src/main/resources/database");
        Application.launch(Main.class, args);
    }
}

