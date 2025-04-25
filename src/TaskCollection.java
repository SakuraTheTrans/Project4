import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class TaskCollection implements Iterable<Task> {
    ArrayList<Task> curTask = new ArrayList<>();

    void addTask(Task a) {
        curTask.add(a);
    }

    public Task getTask(int a) {
        return curTask.get(a);
    }

    void removeTask(int a) {
        curTask.remove(a);
    }

    @JsonIgnore
    public boolean isEmpty() {
        return curTask.isEmpty();
    }

    public int taskCollectorSize() {
        return curTask.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return curTask.iterator();
    }

    @Override
    public void forEach(Consumer action) {
        curTask.forEach(action);
    }
    
    public List<Task> getCurTask() {
        return curTask;
    }
}
