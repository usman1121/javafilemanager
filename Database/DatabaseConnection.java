package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/oopfinalproject";
    private static final String USER = "postgres";
    private static final String PASSWORD = "834927615"; 


    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found. Did you add the JAR?");
            return null;
        }
    }
}
