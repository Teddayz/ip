package tasks;

import tasks.EventTask;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds the task to the list of tasks.
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the task from the list of tasks.
     * @param index the index of the task in the list to be removed.
     */
    public void removeTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task deletedTask = taskList.get(index);
            taskList.remove(index);
            System.out.print(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n",
                    deletedTask.getDescription(), taskList.size()));
        } else {
            System.out.print("There is no such task number!");
        }
    }

    /**
     * Writes all tasks in the list into the file save point.
     * @param filePath the save point.
     * @throws IOException when the file cannot be found.
     */
    public void write(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Task task : taskList) {
            String line = taskToString(task);
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Gets the string of all the different types of tasks to be saved into the save point.
     * @param task the task to be converted into the proper format.
     * @return a string of the task details.
     */
    private String taskToString(Task task) {

        if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return eventTask.toString();
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return deadlineTask.toString();
        } else {
            ToDoTask toDoTask = (ToDoTask) task;
            return toDoTask.toString();
        }
    }

    /**
     * Gets the size of the TaskList.
     * @return an integer to denote the size of the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Checks whether the TaskList is empty.
     * @return a boolean value to denote whether the TaskList is empty.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Prints the entire TaskList.
     */
    public void printList() {
        if (taskList.isEmpty()) {
            System.out.print("No items in the list yet!\n");
        }
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String response = String.format("%d. %s", i + 1, task.getDescription());
            System.out.println(response);
        }
    }

    /**
     * Marks the indexed task as done.
     * @param index the index of the task in the list.
     */
    public void mark(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task t = taskList.get(index);
            t.markAsDone();
            System.out.println(String.format("Nice I've marked this task as done: \n%s %s",
                    line(), t.getDescription()));
        } else {
            System.out.println("Invalid Task Number.\n");
        }
    }

    /**
     * Marks the indexed task as undone.
     * @param index the index of the task in the list.
     */
    public void unmark(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task t = taskList.get(index);
            t.unMark();
            System.out.println(String.format("Nice I've marked this task as not done yet: \n%s %s",
                    line(), t.getDescription()));
        } else {
            System.out.println("Invalid Task Number.\n");
        }
    }

    /**
     * A line to separate messages.
     * @return a string message of the segmentation between messages.
     */
    public String line() {
        return "____________________________________________________________\n";

    }
}
