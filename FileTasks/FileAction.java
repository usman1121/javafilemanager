package FileTasks;

import java.io.File;

public interface FileAction {
    void createFile(String type, File currentPath);
    boolean deleteFile(File filename);
    void renameFile(File oldname, File newname);
    void listFiles();
    void searchFile();
    void navigatDiretory();
}
