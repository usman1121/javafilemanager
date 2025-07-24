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
            System.out.println("5. Copy File");
            System.out.println("6. Search File");
            System.out.println("7. View Logs");
            System.out.println("8. Move File/Directory");
            System.out.println("9. Navigate Directory");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    String type = UserInput.getInput("Enter -f for file or -d for directory: ");
                    fileTaskHandler.createFile(type);
                    break;
            
                case "2":
                    fileTaskHandler.deleteFile();
                    break;
            
                case "3":
                    fileTaskHandler.renameFile();
                    break;
            
                case "4":
                    fileTaskHandler.listFiles();
                    break;
                case "5":
                    fileTaskHandler.copyFile();
                    break;

                case "6":
                    fileTaskHandler.searchFile();
                    break;
                case "7":
                    logViewer.viewLogs();
                    break;
                    
                case "8":
                    fileTaskHandler.moveFile();
                    break;
            
                case "9": 
                    fileTaskHandler.navigatDiretory();
                    break;
        
            
                case "0":
                    System.out.println("Goodbye!");
                    return;
            
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            
        }
    }
}