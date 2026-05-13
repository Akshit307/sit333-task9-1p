package inboxtask;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

/**
 * TDD tests for TaskInbox.
 * Tests follow the AAA (Arrange, Act, Assert) structure.
 * Written BEFORE the implementation to drive development.
 */
public class TaskInboxTest {

    private TaskInbox inbox;

    // =========================================================
    // ARRANGE – shared setup before each test
    // =========================================================
    @Before
    public void setUp() {
        inbox = new TaskInbox();

        // Seed data for student s224011787
        inbox.addTask("s224011787", "9.1P", "Submitted",  "");
        inbox.addTask("s224011787", "6.2D", "Complete",   "Good work!");
        inbox.addTask("s224011787", "7.1P", "Resubmit",   "Please add more tests.");

        // Seed data for a second student
        inbox.addTask("s999999999", "1.1P", "Complete", "Well done.");
    }

    // =========================================================
    // Test 1: Valid student with tasks returns correct count
    // =========================================================
    @Test
    public void testGetTaskCount_ValidStudent() {
        // Arrange (done in setUp)
        // Act
        int count = inbox.getTaskCount("s224011787");
        // Assert
        Assert.assertEquals(3, count);
    }

    // =========================================================
    // Test 2: Student with no tasks returns empty list
    // =========================================================
    @Test
    public void testGetTasksForStudent_NoTasks_ReturnsEmpty() {
        // Arrange
        String unknownId = "s000000000";
        // Act
        List<String[]> tasks = inbox.getTasksForStudent(unknownId);
        // Assert
        Assert.assertNotNull(tasks);
        Assert.assertTrue(tasks.isEmpty());
    }

    // =========================================================
    // Test 3: Null student ID throws IllegalArgumentException
    // =========================================================
    @Test(expected = IllegalArgumentException.class)
    public void testGetTasksForStudent_NullId_ThrowsException() {
        // Arrange + Act + Assert (exception expected)
        inbox.getTasksForStudent(null);
    }

    // =========================================================
    // Test 4: Blank student ID throws IllegalArgumentException
    // =========================================================
    @Test(expected = IllegalArgumentException.class)
    public void testGetTasksForStudent_BlankId_ThrowsException() {
        inbox.getTasksForStudent("   ");
    }

    // =========================================================
    // Test 5: Filter by status "Submitted" returns correct task
    // =========================================================
    @Test
    public void testGetTasksByStatus_Submitted() {
        // Arrange (done in setUp)
        // Act
        List<String[]> tasks = inbox.getTasksByStatus("s224011787", "Submitted");
        // Assert
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals("9.1P", tasks.get(0)[0]);
    }

    // =========================================================
    // Test 6: Filter by status "Complete" returns correct tasks
    // =========================================================
    @Test
    public void testGetTasksByStatus_Complete() {
        // Act
        List<String[]> tasks = inbox.getTasksByStatus("s224011787", "Complete");
        // Assert
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals("6.2D", tasks.get(0)[0]);
        Assert.assertEquals("Good work!", tasks.get(0)[2]);
    }

    // =========================================================
    // Test 7: Filter by status "Resubmit" returns correct task
    // =========================================================
    @Test
    public void testGetTasksByStatus_Resubmit() {
        // Act
        List<String[]> tasks = inbox.getTasksByStatus("s224011787", "Resubmit");
        // Assert
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals("7.1P", tasks.get(0)[0]);
    }

    // =========================================================
    // Test 8: Null status throws IllegalArgumentException
    // =========================================================
    @Test(expected = IllegalArgumentException.class)
    public void testGetTasksByStatus_NullStatus_ThrowsException() {
        inbox.getTasksByStatus("s224011787", null);
    }

    // =========================================================
    // Test 9: Tasks for one student don't bleed into another
    // =========================================================
    @Test
    public void testGetTasksForStudent_Isolation() {
        // Act
        List<String[]> tasks = inbox.getTasksForStudent("s999999999");
        // Assert
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals("1.1P", tasks.get(0)[0]);
    }

    // =========================================================
    // Test 10: addTask with null studentId throws exception
    // =========================================================
    @Test(expected = IllegalArgumentException.class)
    public void testAddTask_NullStudentId_ThrowsException() {
        inbox.addTask(null, "1.1P", "Submitted", "");
    }
}