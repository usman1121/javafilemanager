package ActivityLog;

// Interface for loggging navigation actions
public interface CommonNavigationLogger {
     // Method to log a navigation action with action type and directory name
    void logNavigation(String action, String directoryName);
}
