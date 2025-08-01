// package ActivityLog;

// import java.sql.Connection;
// import java.sql.ResultSet;
// import java.sql.Statement;

// import Database.DatabaseConnection;

// public class NavigationViewer {

//     public void viewNavigations() {
//         System.out.println("\n📜 Navigation History:");
//         System.out.println("-----------------------");
//         try (Connection conn = DatabaseConnection.getConnection()) {
//             String sql = "SELECT action, directory_name, timestamp FROM directory_logs ORDER BY timestamp DESC";
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql);

//             while (rs.next()) {
//                 String action = rs.getString("action");
//                 String directory = rs.getString("directory_name");
//                 String timestamp = rs.getString("timestamp");

//                 System.out.println(timestamp + " | " + action + " | " + directory);
//             }
//         } catch (Exception e) {
//             System.out.println("❌ Failed to fetch navigations: " + e.getMessage());
//         }
//     }
// }
