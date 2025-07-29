package Menu;

import FileTasks.FileTaskHandler;
import ActivityLog.LogViewer;

import java.io.File;
import java.util.Scanner;

public class MenuManager {
    private FileTaskHandler fileTaskHandler = new FileTaskHandler();
    private final LogViewer logViewer = new LogViewer();
    private final Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        File currentPath = new File(".");

        while (true) {
            System.out.println("\n==========Java File Manager ==========");
            System.out.println("Current Directory: " + currentPath.getAbsolutePath());
            File[] files = currentPath.listFiles();
            System.out.println("Files:" + files.length);
            if (files != null && files.length > 0) {
                for (File file : files) {
                    System.out.println( (file.isDirectory() ? "d " : "- ") + file.getName());
                }
            } else {
                System.out.println(" (No files or directories)");
            }
            System.out.println("---------------------------------------------");
            System.out.println("1. Create File/Directory");
            System.out.println("2. Delete File/Directory");
            System.out.println("3. Rename File/Directory");
            System.out.println("4. List Files");
            System.out.println("5. Search File");
            System.out.println("6. View Logs");
            System.out.println("7. Navigate Directory");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1": {
                    String type = UserInput.getInput("Enter -f for file or -d for directory: ");
                    fileTaskHandler.createFile(type, currentPath);
                    break;
                }
                case "2": {
                    String name = UserInput.getInput("Enter name to delete: ");
                    File target = new File(currentPath, name);
                    fileTaskHandler.deleteFile(target);
                    break;
                }
                case "3": {
                    String oldName = UserInput.getInput("Enter current name: ");
                    String newName = UserInput.getInput("Enter new name: ");
                    File oldFile = new File(currentPath, oldName);
                    File newFile = new File(currentPath, newName);
                    fileTaskHandler.renameFile(oldFile, newFile);
                    break;
                }
                case "4": {
                    fileTaskHandler.listFiles();
                    break;
                }
                case "5": {
                    fileTaskHandler.searchFile();
                    break;
                }
                case "6": {
                    logViewer.viewLogs();
                    break;
                }
                case "7": {
                    fileTaskHandler.navigatDiretory();
                    break;
                }
                case "0": {
                    System.out.println("Goodbye!");
                    return;
                }
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

    }
}
