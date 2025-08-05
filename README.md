# 📂 Java File Manager

A simple **command-line based File Manager** built with Java.  
It allows users to **create, delete, rename, search, Files and Directories(Folders) and navigate directories** with activity logging to a PostgreSQL database.

---

##  Features

- 📁 **File & Directory Operations**
  - Create files and directories
  - Delete files or empty directories
  - Rename files and directories
  - List all files in the current directory
  - Search files by name
  
- 🔍 **Navigation**
  - Navigate through directories
  - Go back to parent folder
  - Exit navigation safely
  - Logs every navigation event

- 📝 **Activity Logging**
  - Stores file actions (create, delete, rename, search)
  - Stores navigation actions (moving between directories)
  - Logs are saved into PostgreSQL database
  - View all logs in one place

- 🛠 **Error Handling**
  - Prevents deleting non-empty directories
  - Prevents navigating into files
  - Handles invalid inputs

---
## 📦 Project Structure

<pre>
javafilemanager/
├── ActivityLog/
│ ├── CombinedLogViewer.java
│ ├── CommonLogger.java
│ ├── CommonNavigationLogger.java
│ ├── LogEntry.java
│ ├── LogViewer.java
│ ├── Logger.java
│ ├── NavigationLogger.java
│ └── NavigationViewer.java
│
├── Database/
│ ├── DatabaseConnection.java
│ └── postgresql-42.7.7.jar
│
├── FileTasks/
│ ├── CommonFileTasks.java
│ ├── FileAction.java
│ ├── FileItem.java
│ └── FileTaskHandler.java
│
├── MainApp/
│ └── FileManagerApp.java
│
├── Menu/
│ ├── MenuManager.java
│ └── UserInput.java
│
└── README.md
</pre>


## 🗄 Database Setup

The project uses **PostgreSQL** to log actions.

1. Create database:
```sql
CREATE DATABASE oopfinalproject;
CREATE TABLE file_logs (
    id SERIAL PRIMARY KEY,
    action VARCHAR(50),
    file_name VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE navigation_logs (
    id SERIAL PRIMARY KEY,
    from_path TEXT,
    to_path TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

# Database url
```
String_url = "jdbc:postgresql://localhost:5432/oopfinalproject";
String user = "postgres";
String password = "your_password";
```
## 🚀 Compile and Run

### Compile the project  

## First clone the project

```
https://github.com/usman1121/javafilemanager/tree/usman-branch
```
## Then cd /javafilemanager
```
cd javafilemanager

```
**Linux/MacOS**
```bash
javac -cp ".:Database/postgresql-42.7.7.jar" MainApp/*.java ActivityLog/*.java Database/*.java FileTasks/*.java Menu/*.java
```
Windows (PowerShell / CMD)
```
javac -cp ".;Database/postgresql-42.7.7.jar" MainApp/*.java ActivityLog/*.java Database/*.java FileTasks/*.java Menu/*.java
```
Run the program

Linux / MacOS
```
java -cp ".:postgresql-42.7.7.jar" MainApp.FileManagerApp
```
Windows (PowerShell / CMD)
```
java -cp ".;postgresql-42.7.7.jar" MainApp.FileManagerApp
```


## Output
<pre>
Welcome to java File manager:

== File Manager ==
1. Create File
2. Delete File
3. Rename File
4. List Files
5. Copy File
6. Search File
7. View Logs
8. Move File/Directory
9. Navigate Directory
10. Import File
0. Exit
Choose an option:
</pre>

