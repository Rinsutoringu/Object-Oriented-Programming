package test.logic;

import database.db.DataBase;;

public class TestDBConncet {

    public static void main(String[] args) {
        DataBase db = new DataBase();
        db.connect();
        System.out.println();
    }
}
