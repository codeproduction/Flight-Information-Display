package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertTest {
    private Alert emergencyAlert;

    @BeforeEach
    void setup() {
        Alert.resetId();
        emergencyAlert = new Alert("This is a test emergency alert");
    }

    @Test
    public void testConstructor() {
        assertEquals("This is a test emergency alert", emergencyAlert.getAlert());
        assertEquals(1, emergencyAlert.getId());
    }

    @Test
    public void testConstructorMultipleTimes() {
        Alert emergencyAlert2 = new Alert("Test alert with id 2");
        Alert emergencyAlert3 = new Alert("Test alert with id 3");
        Alert emergencyAlert4 = new Alert("Test alert with id 4");
        Alert emergencyAlert5 = new Alert("Test alert with id 5");

        //tests for id
        assertEquals(1, emergencyAlert.getId());
        assertEquals(2, emergencyAlert2.getId());
        assertEquals(3, emergencyAlert3.getId());
        assertEquals(4, emergencyAlert4.getId());
        assertEquals(5, emergencyAlert5.getId());

        //tests for String alert
        assertEquals("This is a test emergency alert", emergencyAlert.getAlert());
        assertEquals("Test alert with id 2", emergencyAlert2.getAlert());
        assertEquals("Test alert with id 3", emergencyAlert3.getAlert());
        assertEquals("Test alert with id 4", emergencyAlert4.getAlert());
        assertEquals("Test alert with id 5", emergencyAlert5.getAlert());
    }
}
