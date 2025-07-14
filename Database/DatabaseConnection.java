package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/oopfinalproject";
        String user = "postgres";
        String password = "834927615"; 

        return DriverManager.getConnection(url, user, password);
    }
}
