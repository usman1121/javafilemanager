package ActivityLog;

public class LogEntry {
    private String action;     
    private String fileName;
    private String timestamp;

    public LogEntry(String action, String fileName, String timestamp) {
        this.action = action;
        this.fileName = fileName;
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + action.toUpperCase() + " - " + fileName;
    }
}
