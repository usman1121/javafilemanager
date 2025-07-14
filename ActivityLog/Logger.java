package ActivityLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import Database.DatabaseConnection;

public class Logger {

    public void logAction(String action, String fileName) {
        System.out.println("Logging: [" + action.toUpperCase() + "] on file: " + fileName);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO file_logs (action, file_name, timestamp) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, action.toUpperCase());
            stmt.setString(2, fileName);
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Failed to log action: " + e.getMessage());
        }
    }
}
