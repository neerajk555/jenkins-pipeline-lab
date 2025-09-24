import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelloWorldTest {
    
    @Test
    public void testGetMessage() {
        HelloWorld hello = new HelloWorld();
        String expected = "Hello, Jenkins!";
        String actual = hello.getMessage();
        assertEquals(expected, actual, "Message should match expected value");
    }
    
    @Test
    public void testMessageNotNull() {
        HelloWorld hello = new HelloWorld();
        assertNotNull(hello.getMessage(), "Message should not be null");
    }
}
