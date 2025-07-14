package FileTasks;

import Menu.UserInput;
import ActivityLog.Logger;
import java.io.File;

public class FileTaskHandler extends CommonFileTasks implements FileAction {

    @Override
    public void createFile(String type) {
        String name = UserInput.getInput("Enter -f for file or -d for directory name to create: ");
        Logger logger = new Logger();
    
        if (type.equals("-d")) {
            try {
                File dirname = new File(name);
                if (dirname.mkdir()) {
                    System.out.println("Directory created: " + dirname.getAbsolutePath());
                    logger.logAction("CREATE_DIRECTORY", name);  
                } else {
                    System.out.println("Error: Directory already exists.");
                }
            } catch (Exception e) {
                System.out.println("Could not create directory: " + e.getMessage());
            }
        } else {
            try {
                File file = new File(name);
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                    logger.logAction("CREATE_FILE", name);  
                } else {
                    System.out.println("Error: File already exists.");
                }
            } catch (Exception e) {
                System.out.println("Could not create file: " + e.getMessage());
            }
        }
    }
    

    @Override
    public void deleteFile() {
        String name = UserInput.getInput("Enter file name to delete: ");
        File file = new File(name);
        if (file.exists() && file.delete()) {
            System.out.println("File deleted.");
        } else {
            System.out.println("File not found or couldn't be deleted.");
        }
    }

    @Override
    public void renameFile() {
        String oldName = UserInput.getInput("Enter current file name: ");
        String newName = UserInput.getInput("Enter new file name: ");
        File oldFile = new File(oldName);
        File newFile = new File(newName);
        if (oldFile.exists() && oldFile.renameTo(newFile)) {
            System.out.println("File or Directory renamed to: "+ newFile.getName());
        } else {
            System.out.println("Rename failed.");
        }
    }

    @Override
    public void listFiles() {
        String dir = UserInput.getInput("Enter directory to list (or . for current): ");
        File folder = new File(dir);
        File[] files = folder.listFiles();
        if (folder.exists() && folder.isDirectory() && files != null && files.length > 0) {
            System.out.println("\nFiles in: " + folder.getAbsolutePath());
            for (File file : files) {
                System.out.println("• " + file.getName());
            }
        } else {
            System.out.println("Directory is empty or invalid.");
        }
    }

    @Override
    public void searchFile() {
        String fileName = UserInput.getInput("Enter file name to search: ");
        String dir = UserInput.getInput("Enter directory to search in (or . for current): ");
        File folder = new File(dir);
        File[] files = folder.listFiles();
        boolean found = false;

        if (folder.exists() && folder.isDirectory() && files != null) {
            for (File file : files) {
                if (file.getName().equalsIgnoreCase(fileName)) {
                    System.out.println("File found: " + file.getAbsolutePath());
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("File not found.");
        }
    }
}
