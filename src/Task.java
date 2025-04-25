public class Task implements Comparable<Task>{
    private String title;
    private String desc;
    private int priority;

    public Task() {}

    public Task(String title, String desc, int priority) {
        this.title = title;
        this.desc = desc;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Task task) {
        if (this.priority == task.priority) {
            return this.title.compareTo(task.title);
        } else if (this.priority > task.priority) {
            return -1;
        } else {
            return 1;
        }
    }
}
