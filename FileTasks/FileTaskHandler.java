package FileTasks;

import Menu.MenuManager;
import Menu.UserInput;
import ActivityLog.Logger;
import java.io.File;

public class FileTaskHandler extends CommonFileTasks implements FileAction {

    @Override
    public void createFile(String type, File currentPath) {
        String name = UserInput.getInput("Enter name: ");
        File target = new File(currentPath, name);

        if (type.equals("-d")) {
            if (target.mkdir()) {
                System.out.println("Directory created at " + target.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory.");
            }
        } else {
            try {
                if (target.createNewFile()) {
                    System.out.println("File created at " + target.getAbsolutePath());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (Exception e) {
                System.out.println("Could not create file: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean deleteFile(File filename) {
        Logger logger = new Logger();
        File file = filename;

        if (file.isFile()) {
            if (file.exists() && file.delete()) {
                System.out.println("File deleted.");
                logger.logAction("File Deleted", filename.getName());
                return true;
            } else {
                System.out.println("File not found or couldn't be deleted.");
                return false;
            }
        } else if (file.isDirectory()) {
            String[] contents = file.list();
            if (contents != null && contents.length == 0) {
                if (file.delete()) {
                    System.out.println("Directory deleted.");
                    logger.logAction("Directory Deleted", filename.getName());
                    return true;
                } else {
                    System.out.println("Directory couldn't be deleted.");
                    return false;
                }
            } else {
                System.out.println("Directory is not empty. You can't delete it!");
                String ch = UserInput.getInput("Enter 'n' to navigate or any other key to cancel: ");
                if (ch.equalsIgnoreCase("n")) {
                    navigatDiretory();
                } else {
                    System.out.println("Cancelled.");
                }
                return false;
            }
        } else {
            System.out.println("File or directory not found.");
            return false;
        }
    }

    @Override
    public void renameFile(File oldName, File newName) {
        // String oldName = UserInput.getInput("Enter current file name: ");
        // String newName = UserInput.getInput("Enter new file name: ");
        File oldFile = oldName;
        File newFile = newName;
        if (oldFile.exists() && oldFile.renameTo(newFile)) {
            System.out.println("File or Directory renamed to: " + newFile.getName());
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
    
    @Override
    public void navigatDiretory() {
        File currentPath = new File(".").getAbsoluteFile();

        while (true) {
            System.out.println("\n Current Directory: " + currentPath.getAbsolutePath());

            File[] files = currentPath.listFiles();
            if (files == null || files.length == 0) {
                System.out.println("Directory is empty.");
            } else {
                System.out.println("Total Contents:" + files.length);
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    String icon = file.isDirectory() ? "d" : "-";
                    System.out.println((i + 1) + ". " + icon + " " + file.getName());
                }
            }

            System.out.println("\nOptions:");
            System.out.println("a. Create File/Directory");
            System.out.println("b. Delete File/Directory");
            System.out.println("c. Rename File/Directory");
            System.out.println("0. Go Up");
            System.out.println("-1. Exit Navigation");

            String choice = UserInput.getInput("Enter choice: ");

            try {
                if (choice.equals("-1"))
                    break;

                else if (choice.equals("0")) {
                    File parent = currentPath.getParentFile();
                    if (parent != null)
                        currentPath = parent;
                    else
                        System.out.println("At root. Can't go higher.");
                }

                else if (choice.equalsIgnoreCase("a")) {
                    String type = UserInput.getInput("Enter -f for file or -d for directory: ");
                    createFile(type, currentPath);
                }

                else if (choice.equalsIgnoreCase("b")) {
                    String name = UserInput.getInput("Enter name to delete: ");
                    deleteFile(currentPath.getAbsolutePath() + "/" + name);

                }

                else if (choice.equalsIgnoreCase("c")) {
                    String oldName = UserInput.getInput("Enter current name: ");
                    String newName = UserInput.getInput("Enter new name: ");
                    File oldFile = new File(oldName);
                    File newFile = new File(newName);
                    renameFile(oldFile, newFile);
                }

                else {
                    int selected = Integer.parseInt(choice);
                    if (selected > 0 && selected <= files.length) {
                        File selectedFile = files[selected - 1];
                        if (selectedFile.isDirectory()) {
                            currentPath = selectedFile;
                        } else {
                            System.out.println(" That is a file, not a directory.");
                        }
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

}
