package FileTasks;

public interface FileAction {
    // void createFile();
    void createFile(String fileType);
    void deleteFile();
    void renameFile();
    void listFiles();
    void searchFile();
    void moveFile();
}
