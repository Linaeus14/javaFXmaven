package com.pbo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class connection extends Service<Boolean> {
    public static Connection conn;

    @Override
    protected Task<Boolean> createTask() {
        return new Task<Boolean>() {
            @Override
            protected Boolean call() {
                try {
                    Thread.sleep(1000);
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbjava", "root", "");
                    return true;
                } catch (Exception e) {
                    System.out.println(e);
                    return false;
                }
            }
        };
    }
}
