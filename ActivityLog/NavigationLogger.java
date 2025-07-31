package ActivityLog;

// Import necessary classes for database and time handling
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import Database.DatabaseConnection;

// Implements the CommonNavigationLogger interface to log navigation actions
public class NavigationLogger implements CommonNavigationLogger {

 // Logs a navigation action to the database
    @Override
    public void logNavigation(String action, String directoryName) {
         // Print log message to the console
        System.out.println("Logging navigation: [" + action + "] into " + directoryName);

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Prepare SQL statement to insert the log entry
            String sql = "INSERT INTO directory_logs (action, directory_name, timestamp) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, action);
            stmt.setString(2, directoryName);
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();
        } catch (Exception e) {
             // Print error message if logging fails
            System.out.println("Failed to log navigation: " + e.getMessage());
        }
    }
}
