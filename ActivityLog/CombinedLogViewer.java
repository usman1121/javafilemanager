package ActivityLog;

import Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CombinedLogViewer {

    public void viewAllLogs() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("\n==== File Operation Logs ====");
            ResultSet rs1 = stmt.executeQuery("SELECT action, file_name, timestamp FROM file_logs ORDER BY timestamp DESC");
            while (rs1.next()) {
                System.out.println(rs1.getTimestamp("timestamp") + " - " +
                                   rs1.getString("action") + " on " +
                                   rs1.getString("file_name"));
            }

            System.out.println("\n==== Navigation Logs ====");
            ResultSet rs2 = stmt.executeQuery("SELECT action, directory_name, timestamp FROM directory_logs ORDER BY timestamp DESC");
            while (rs2.next()) {
                System.out.println(rs2.getTimestamp("timestamp") + " - " +
                                   "Moved from " + rs2.getString("action") +
                                   " → " + rs2.getString("directory_name"));
            }

        } catch (Exception e) {
            System.out.println("❌ Failed to fetch logs: " + e.getMessage());
        }
    }
}
