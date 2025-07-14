package Menu;

import FileTasks.FileTaskHandler;
import ActivityLog.Logger;
import ActivityLog.LogViewer;
import java.util.Scanner;

public class MenuManager {
    private final FileTaskHandler fileTaskHandler = new FileTaskHandler();
    private final Logger logger = new Logger();
    private final LogViewer logViewer = new LogViewer();
    private final Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        while (true) {
            System.out.println("\n== File Manager ==");
            System.out.println("1. Create File");
            System.out.println("2. Delete File");
            System.out.println("3. Rename File");
            System.out.println("4. List Files");
            System.out.println("5. Search File");
            System.out.println("6. View Logs");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> fileTaskHandler.createFile("-d");
                case "2" -> fileTaskHandler.deleteFile();
                case "3" -> fileTaskHandler.renameFile();
                case "4" -> fileTaskHandler.listFiles();
                case "5" -> fileTaskHandler.searchFile();
                case "6" -> logViewer.viewLogs();
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
