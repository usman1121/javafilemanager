package FileTasks;

import java.io.File;

public abstract class CommonFileTasks {
 
    protected boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }


    protected boolean isFile(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    protected boolean isDirectory(String path) {
        File file = new File(path);
        return file.exists() && file.isDirectory();
    }

    protected long getFileSize(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            return file.length(); 
        }
        return -1;
    }

    protected boolean deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.delete(); 
        }
        return false;
    }
    protected String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int kb = (int) (bytes / 1024);
        if (kb < 1024) return kb + " KB";
        return (kb / 1024) + " MB";
    }
}
