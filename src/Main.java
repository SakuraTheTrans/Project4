import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    // static ArrayList<Task> taskList = new ArrayList<>();
    static TaskCollection taskCollector = new TaskCollection();
    static ObjectMapper map = new ObjectMapper();

    static void listTasks() {
        if (!taskCollector.isEmpty()) {

            Collections.sort(taskCollector.curTask);

            System.out.println("Would you like to :\n   1. List All Tasks\n   2. List Tasks Of Specific Priority\nPlease Input Choice (Number): ");
            int userListSelection = input.nextInt();
            int userPrioritySelection = 0;
            input.nextLine();
            if (userListSelection > 2) {
                System.out.println("\nNot an option! Returning to main menu\n");
                return;
            }
            if (userListSelection == 2) {
                System.out.println("What priority would you like to check? (0 - 5)");
                userPrioritySelection = input.nextInt();
                input.nextLine();
            }
            for (Task task : taskCollector) {
                if (task.getPriority() == userPrioritySelection || userListSelection == 1) {
                    System.out.println("====================================================================================================");
                    System.out.println("Title : " + task.getTitle());
                    System.out.println("Priority : " + task.getPriority());
                    System.out.println("====================================================================================================");
                    System.out.println("Description : " + task.getDesc());
                    System.out.println("====================================================================================================\n\n\n");
                }
            }
        } else {
            System.out.println("\nNo tasks to list! Please add a task!\n");
        }
    }

    static void editTask() {
        String userTitleChange = "";
        String userDescChange = "";
        int userPriorityChange = 0;
        int userTaskSelection;
        while (true) {
            System.out.println("Which task would you like to edit?");
            for (int i = 0; i < taskCollector.taskCollectorSize(); i++) {
                System.out.println((i+1) + ". " + taskCollector.getTask(i).getTitle());
            }
            System.out.println("Please Input Choice (Number):");
            try {
                userTaskSelection = (input.nextInt()-1);
                input.nextLine();
                if (userTaskSelection > (taskCollector.taskCollectorSize()-1)){
                    System.out.println("\nNot a task, please try again!\n");
                    continue;
                }
            } catch (Exception e) {
                input.nextLine();
                System.out.println("\nNot a task, please try again!\n");
                continue;
            }


            break;
        }

        System.out.println("\nWhat Would You Like To Edit?\n   1. Title\n   2. Description\n   3. Priority\nPlease Input Choice (Number):");
        int userEditSelection = input.nextInt();
        input.nextLine();
        if (userEditSelection == 1) {
            System.out.println("New Title?");
            userTitleChange = input.nextLine();
        } else if (userEditSelection == 2) {
            System.out.println("New Description?");
            userDescChange = input.nextLine();
        } else if (userEditSelection == 3) {
            System.out.println("New Priority? (0 - 5)");
            boolean correct = false;
            while (!correct) {
                try {
                    userPriorityChange = input.nextInt();
                    if (userPriorityChange <= 5 && userPriorityChange >= 0){
                        correct = true;
                    } else {
                        System.out.println("\nPlease input a number 0 - 5, try again!\n");
                    }
                } catch (Exception e) {
                    input.nextLine();
                    System.out.println("\nNot an int, please try again!\n");
                }
            }

        }

        switch (userEditSelection) {
            case 1 -> taskCollector.getTask(userTaskSelection).setTitle(userTitleChange);
            case 2 -> taskCollector.getTask(userTaskSelection).setDesc(userDescChange);
            case 3 -> taskCollector.getTask(userTaskSelection).setPriority(userPriorityChange);
        }
    }

    static void addTask() {
        System.out.println("Name Of The New Task?");
        String newTaskName = input.nextLine();
        System.out.println("Description Of THe New Task?");
        String newTaskDesc = input.nextLine();
        boolean correct = false;
        int newTaskPriority = 0;
        while (!correct) {
            System.out.println("Priority Of Task? (0 - 5)");
            try {
                newTaskPriority = input.nextInt();
                input.nextLine();
                if (newTaskPriority <= 5 && newTaskPriority >= 0) {
                    correct = true;
                } else {
                    System.out.println("\nPlease choose a number between 0 - 5\n");
                }
            } catch (Exception e) {
                input.nextLine();
                System.out.println("\nNot a number! Please try again\n");
            }
        }
        Task placeholder = new Task(newTaskName, newTaskDesc, newTaskPriority);
        taskCollector.addTask(placeholder);
        System.out.println("Thank you! Task Created");
    }

    static void removeTask() {
        int userTaskSelection;
        while (true) {
            System.out.println("Which task would you like to remove?");
            for (int i = 0; i < taskCollector.taskCollectorSize(); i++) {
                System.out.println((i+1) + ". " + taskCollector.getTask(i).getTitle());
            }
            System.out.println("Please Input Choice (Number):");
            try {
                userTaskSelection = (input.nextInt()-1);
                input.nextLine();
                if (userTaskSelection > (taskCollector.taskCollectorSize()-1)) {
                    System.out.println("\nNot a task, please try again!\n");
                    continue;
                }
            } catch (Exception e) {
                input.nextLine();
                System.out.println("\nNot a task, please try again!\n");
                continue;
            }

            taskCollector.removeTask(userTaskSelection);
            System.out.println("Task Removed!");
            break;
        }

    }

    public static void main(String[] args) throws IOException {
        try {
            File file = new File("data.json");
            TaskCollection savedTaskList = map.readValue(file, TaskCollection.class);
            for (Task task : savedTaskList) {
                taskCollector.addTask(task);
            }
        } catch (Exception e) {}
        
        int userInput;
        while (true) {
            System.out.println("Would you like to :\n   1. Add Task\n   2. Remove Task\n   3. Edit Task\n   4. List Tasks\n   5. Exit\nPlease Input Choice (Number):");
            try {
                userInput = input.nextInt();
                input.nextLine();
                if (userInput > 5) {
                    System.out.println("\nNot an option, please try again!\n");
                    continue;
                }
            } catch (Exception e){
                input.nextLine();
                System.out.println("\nNot an option, please try again!\n");
                continue;
            }

            switch (userInput) {
                case 1 -> addTask();
                case 2 -> removeTask();
                case 3 -> editTask();
                case 4 -> listTasks();
                case 5 -> {
                    map.writeValue(new File("data.json"),taskCollector);
                    return;
                }
            }
        }
    }
}