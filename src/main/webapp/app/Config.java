package main.webapp.app;

import main.webapp.app.storage.SQLStorage;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    public static SQLStorage initDatabase() {
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            //Getting the connection
            String mysqlUrl = "jdbc:h2:mem:storage1";
            Connection con = DriverManager.getConnection(mysqlUrl, "root", "password");
            System.out.println("Connection established......");
            //Initialize the script runner
            ScriptRunner sr = new ScriptRunner(con);
            //Creating a reader object
            Reader reader = new BufferedReader(new FileReader("C:/JavaStudy/SberStart/props/initDB.sql"));
            //Running the script
            sr.runScript(reader);
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new SQLStorage("jdbc:h2:mem:storage1", "root", "password");
    }
}
