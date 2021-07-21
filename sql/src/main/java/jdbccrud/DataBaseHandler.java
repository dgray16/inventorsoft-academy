package com.homework.jdbccrud;

import com.sun.tools.javac.Main;
import org.springframework.lang.NonNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class DataBaseHandler extends Configs {
    protected Connection dbconnection;
    public Connection getDbconnection() throws ClassNotFoundException, SQLException{
        String connectionStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");
        this.dbconnection = DriverManager.getConnection(connectionStr, dbUser, dbPassword);

        return dbconnection;
    }

    protected String getSqlCode(@NonNull String filename) throws IOException {
        URL sqlResource = Main.class.getClassLoader().getResource(filename);
        File file = new File(sqlResource.getPath());
        return new String(Files.readAllBytes(Paths.get(file.getPath())));
    }

    protected void regularStatement() {
        try (Statement statement = this.dbconnection.createStatement()) {
            String initSql = getSqlCode("script_1.sql");
            String insertSql = getSqlCode("insert_1.sql");
            statement.executeQuery(initSql);
            statement.executeQuery(insertSql);

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM MATCH")) {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (resultSet.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = resultSet.getString(i);
                        System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    }
                    System.out.println("");
                }
            }
        }catch(Exception exception){
            exception.getMessage();
        }
    }

}
