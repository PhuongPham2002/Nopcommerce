package data.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    public static void main(String[] args) {
       String DB_URL = "jdbc:sqlserver://ROSIEGALAXY\\AUTOMATIONFC:1433;databaseName=nopcommerce";
       String USERNAME = "sa";
       String PASSWORD ="Phuong2002@";

        try ( Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            System.out.println("✅ Connected successfully!");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

}
