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
    public void copyFile() {
    String sourcePath = UserInput.getInput("Enter source file path to copy: ");
    String destinationPath = UserInput.getInput("Enter destination directory: ");
    Logger logger = new Logger();
    
    File source = new File(sourcePath);
    File destinationDir = new File(destinationPath);
    File destination = new File(destinationDir, source.getName()); // Preserve original file name

    if (!source.exists()) {
        System.out.println("Error: Source file does not exist.");
        return;
    }

    if (!source.isFile()) {
        System.out.println("Error: Only regular files can be copied.");
        return;
    }

    if (!destinationDir.exists() || !destinationDir.isDirectory()) {
        System.out.println("Error: Destination directory is invalid.");
        return;
    }

    try (java.io.FileInputStream in = new java.io.FileInputStream(source);
         java.io.FileOutputStream out = new java.io.FileOutputStream(destination)) {

        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }

        System.out.println("File copied to: " + destination.getAbsolutePath());
        logger.logAction("COPY_FILE", "From: " + sourcePath + " To: " + destination.getAbsolutePath());

    } catch (Exception e) {
        System.out.println("Error: Failed to copy file. " + e.getMessage());
    }
}

    public void moveFile() {
        String sourcePath = UserInput.getInput("Enter source file/directory name to move: ");
        String destinationPath = UserInput.getInput("Enter destination path: ");
        File source = new File(sourcePath);
        File destination = new File(destinationPath, source.getName()); // Preserves original name

        if (!source.exists()) {
            System.out.println("Error: Source file/directory does not exist.");
            return;
        }

        if (destination.exists()) {
            System.out.println("Error: Destination already contains a file/directory with the same name.");
            return;
        }

        boolean success = source.renameTo(destination);
        if (success) {
            System.out.println("File/Directory moved to: " + destination.getAbsolutePath());
            Logger logger = new Logger();
            logger.logAction("MOVE_FILE", "From: " + sourcePath + " To: " + destination.getAbsolutePath());
        } else {
            System.out.println("Error: Failed to move file/directory.");
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
}
