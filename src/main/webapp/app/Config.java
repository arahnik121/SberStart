package main.webapp.app;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    public static void initDatabase() {
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            //Getting the connection
            String mysqlUrl = "jdbc:h2:mem:storage";
            Connection con = DriverManager.getConnection(mysqlUrl, "root", "password");
            System.out.println("Connection established......");
            //Initialize the script runner
            ScriptRunner sr = new ScriptRunner(con);
            //Creating a reader object
            Reader reader = new BufferedReader(new FileReader("/Users/user1234/Projects/SberStart1/props/initDB.sql"));
            //Running the script
            sr.runScript(reader);
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
