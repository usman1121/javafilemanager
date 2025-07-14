package MainApp;

import Menu.MenuManager;

public class FileManagerApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the Java File Manager");
        System.out.println("----------------------------------");

        MenuManager menu = new MenuManager();
        menu.startMenu();
    }
}
