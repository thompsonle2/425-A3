import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;

class A3Test {

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private ByteArrayOutputStream captureOutput() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }

    @Test
    void testNoCoursesNoHalls() {
        provideInput("0 0\n");
        ByteArrayOutputStream output = captureOutput();

        A3-BFS-route.main(new String[]{});

        assertEquals("0\n", output.toString());
    }

    @Test
    void testOneCourseOneHallAvailable() {
        provideInput("1 1\n1 0\n");
        ByteArrayOutputStream output = captureOutput();

        A3-BFS-route.main(new String[]{});

        assertEquals("1\n", output.toString());
    }

    @Test
    void testOneCourseOneHallUnavailable() {
        provideInput("1 1\n0\n");
        ByteArrayOutputStream output = captureOutput();

        A3-BFS-route.main(new String[]{});

        assertEquals("0\n", output.toString());
    }

    @Test
    void testTwoCoursesTwoHallsPerfectMatch() {
        provideInput("2 2\n1 0\n1 1\n");
        ByteArrayOutputStream output = captureOutput();

        A3-BFS-route.main(new String[]{});

        assertEquals("2\n", output.toString());
    }

    @Test
    void testTwoCoursesOneHall() {
        provideInput("2 1\n1 0\n1 0\n");
        ByteArrayOutputStream output = captureOutput();

        A3-BFS-route.main(new String[]{});

        assertEquals("1\n", output.toString());
    }

    @Test
    void testHallAcceptedByMultipleCourses() {
        provideInput("3 2\n2 0 1\n1 0\n1 1\n");
        ByteArrayOutputStream output = captureOutput();

        A3-BFS-route.main(new String[]{});

        assertEquals("2\n", output.toString());
    }

    @Test
    void testNoHallsAcceptedByAnyCourse() {
        provideInput("2 2\n0\n0\n");
        ByteArrayOutputStream output = captureOutput();

        A3-BFS-route.main(new String[]{});

        assertEquals("0\n", output.toString());
    }

    @Test
    void testMoreHallsThanCourses() {
        provideInput("2 4\n1 0\n2 1 2\n");
        ByteArrayOutputStream output = captureOutput();

        A3-BFS-route.main(new String[]{});

        assertEquals("2\n", output.toString());
    }
}
