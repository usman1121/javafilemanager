package ActivityLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import Database.DatabaseConnection;

public class NavigationLogger implements CommonNavigationLogger {

    @Override
    public void logNavigation(String action, String directoryName) {
        System.out.println("Logging navigation: [" + action + "] into " + directoryName);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO directory_logs (action, directory_name, timestamp) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, action);
            stmt.setString(2, directoryName);
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Failed to log navigation: " + e.getMessage());
        }
    }
}
