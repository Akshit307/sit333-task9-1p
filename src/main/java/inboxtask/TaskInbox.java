package inboxtask;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskInbox represents a simplified OnTrack function that retrieves
 * all submitted tasks for a given student ID.
 *
 * Each task has:
 *   - taskId   : unique task identifier (e.g. "9.1P")
 *   - status   : "Submitted", "Complete", or "Resubmit"
 *   - feedback : tutor feedback string (may be empty)
 */
public class TaskInbox {

    // Simulated data store: studentId -> list of tasks
    private final List<String[]> taskStore = new ArrayList<>();

    /**
     * Add a task to the inbox store (used for test setup / system loading).
     *
     * @param studentId  student ID string
     * @param taskId     task identifier string
     * @param status     submission status
     * @param feedback   tutor feedback
     */
    public void addTask(String studentId, String taskId, String status, String feedback) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be null or blank");
        }
        if (taskId == null || taskId.isBlank()) {
            throw new IllegalArgumentException("Task ID cannot be null or blank");
        }
        taskStore.add(new String[]{studentId, taskId, status, feedback});
    }

    /**
     * Get submitted tasks for a student.
     *
     * @param studentId  the student's ID
     * @return list of String[] {taskId, status, feedback}, never null
     * @throws IllegalArgumentException if studentId is null or blank
     */
    public List<String[]> getTasksForStudent(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be null or blank");
        }

        List<String[]> result = new ArrayList<>();
        for (String[] record : taskStore) {
            if (record[0].equals(studentId)) {
                result.add(new String[]{record[1], record[2], record[3]});
            }
        }
        return result;
    }

    /**
     * Get tasks for a student filtered by status.
     *
     * @param studentId  the student's ID
     * @param status     status to filter by (e.g. "Submitted", "Complete", "Resubmit")
     * @return filtered list of String[] {taskId, status, feedback}
     */
    public List<String[]> getTasksByStatus(String studentId, String status) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be null or blank");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or blank");
        }

        List<String[]> result = new ArrayList<>();
        for (String[] record : taskStore) {
            if (record[0].equals(studentId) && record[2].equals(status)) {
                result.add(new String[]{record[1], record[2], record[3]});
            }
        }
        return result;
    }

    /**
     * Returns the total number of tasks submitted by a student.
     *
     * @param studentId  the student's ID
     * @return task count (0 if none)
     */
    public int getTaskCount(String studentId) {
        return getTasksForStudent(studentId).size();
    }
}