package Menu;

import ActivityLog.LogViewer;
import ActivityLog.Logger;
import FileTasks.FileTaskHandler;
import java.util.Scanner;

public class MenuManager {
    private final FileTaskHandler fileTaskHandler = new FileTaskHandler();
    @SuppressWarnings("unused")
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
            System.out.println("5. Copy File");
            System.out.println("6. Search File");
            System.out.println("7. View Logs");
            System.out.println("8. Move File/Directory");
            System.out.println("9. Navigate Directory");
            System.out.println("10. Import File");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    String type = UserInput.getInput("Enter -f for file or -d for directory: ");
                    fileTaskHandler.createFile(type);
                }
            
                case "2" -> fileTaskHandler.deleteFile();
            
                case "3" -> fileTaskHandler.renameFile();
            
                case "4" -> fileTaskHandler.listFiles();
                    
                case "5" -> fileTaskHandler.copyFile();

                case "6" -> fileTaskHandler.searchFile();
                case "7" -> logViewer.viewLogs();

                case "8" -> fileTaskHandler.moveFile();
            
                case "9" -> fileTaskHandler.navigatDiretory();
                    
                case "10" -> fileTaskHandler.importFile();
            
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
            
                default -> System.out.println("Invalid choice. Try again.");
            }
            
        }
    }
}