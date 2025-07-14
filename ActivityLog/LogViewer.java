package ActivityLog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Database.DatabaseConnection;

public class LogViewer {
    public void viewLogs() {
        System.out.println("\n Log History:\n-------------------");

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String query = "SELECT action, file_name, timestamp FROM file_logs ORDER BY timestamp DESC";
            ResultSet rs = stmt.executeQuery(query);

            int count = 0;
            while (rs.next()) {
                String action = rs.getString("action");
                String fileName = rs.getString("file_name");
                String time = rs.getString("timestamp");

                System.out.printf("• [%s] %s - %s\n", time, action, fileName);
                count++;
            }

            if (count == 0) {
                System.out.println("No logs found.");
            }

        } catch (Exception e) {
            System.out.println("❌ Failed to fetch logs: " + e.getMessage());
        }
    }
}
