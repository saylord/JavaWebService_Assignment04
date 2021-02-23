package com.example.demoWebService.data;

import com.example.demoWebService.data.interfaces.IDB;

import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://localhost:5432/forjavahw";
        try {
            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "saylord");

            return con;
        } catch (Exception e) {
            throw new ServerErrorException("Cannot connect to DB", 500);
        }
    }
}
