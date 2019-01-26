package pl.sda.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "jdbc:mysql://localhost:3306/sda-jdbc?serverTimezone=UTC" +
                "&useSSL=false";
//        String databaseUsername = "root";
//        String databasePassword = "root";
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("database.properties"));
//        properties.load(Files.newInputStream(Paths.get("C:\\Users\\BRITENET\\Projects\\jdbcsda2\\src\\main\\resources\\database.properties")));
//        properties.put("user", databaseUsername);
//        properties.put("password", databasePassword);
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            createStatment(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void createStatment(Connection connection) {

    }
}
