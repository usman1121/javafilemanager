package FileTasks;

import java.io.File;
import java.text.SimpleDateFormat;

public class FileItem {
    private String name;
    private String type; 
    private String path;
    private long size;
    private String lastModified;

    public FileItem(File file) {
        this.name = file.getName();
        this.type = file.isDirectory() ? "Directory" : "File";
        this.path = file.getAbsolutePath();
        this.size = file.isFile() ? file.length() : 0;
        this.lastModified = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .format(file.lastModified());
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getPath() { return path; }
    public long getSize() { return size; }
    public String getLastModified() { return lastModified; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s bytes) - %s", type, name, size, lastModified);
    }
}
