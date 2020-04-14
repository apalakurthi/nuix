package au.com.nuix.service;

import static org.junit.Assert.*;
import au.com.nuix.exception.InvalidCommandException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class RobotSimulatorTest {

  private RobotSimulator robotSimulator;

  @Before
  public void setUp() {
    robotSimulator = new RobotSimulator();
  }

  @Test
  public void test1() {
    String string = "DEPLOY 0,0,NORTH\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    List<String> result = robotSimulator.process(inputStream);
    assertEquals(1, result.size());
    assertEquals("0,1,NORTH", result.get(0));
  }

  @Test
  public void test2() {
    String string = "DEPLOY 0,0,NORTH\n"
        + "PIT 0,1\n"
        + "PIT 8,6\n"
        + "MOVE\n"
        + "RIGHT\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    List<String> result = robotSimulator.process(inputStream);
    assertEquals(2, result.size());
    assertEquals("PIT Detected: Ignored", result.get(0));
    assertEquals("1,0,EAST", result.get(1));
  }

  @Test
  public void test3() {
    String string = "DEPLOY 1,2,EAST\n"
        + "MOVE\n"
        + "MOVE\n"
        + "LEFT\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    List<String> result = robotSimulator.process(inputStream);
    assertEquals(1, result.size());
    assertEquals("3,3,NORTH", result.get(0));
  }

  @Test
  public void test4() {
    String string = "MOVE\n"
        + "DEPLOY 4,6,EAST\n"
        + "PIT 2,4\n"
        + "PIT 5,5\n"
        + "MOVE\n"
        + "RIGHT\n"
        + "MOVE\n"
        + "LEFT\n"
        + "LEFT\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    List<String> result = robotSimulator.process(inputStream);
    assertEquals(3, result.size());
    assertEquals("Outside Zone: Ignored", result.get(0));
    assertEquals("PIT Detected: Ignored", result.get(1));
    assertEquals("5,7,NORTH", result.get(2));
  }

  @Test
  public void testInvalidDeployCommand() {
    String string = "MOVE\n"
        + "DEPLOY -4,6,EAST\n"
        + "PIT 2,4\n"
        + "PIT 5,5\n"
        + "MOVE\n"
        + "RIGHT\n"
        + "MOVE\n"
        + "LEFT\n"
        + "LEFT\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    List<String> messages = robotSimulator.process(inputStream);
    assertEquals(9 , messages.size());
  }

  @Test(expected = InvalidCommandException.class)
  public void testInvalidPitCommand() {
    String string = "MOVE\n"
        + "DEPLOY 4,6,EAST\n"
        + "PIT -2,4\n"
        + "PIT 5,5\n"
        + "MOVE\n"
        + "RIGHT\n"
        + "MOVE\n"
        + "LEFT\n"
        + "LEFT\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    robotSimulator.process(inputStream);

  }

  @Test(expected = InvalidCommandException.class)
  public void testInvalidCommand() {
    String string = "MOVE1\n"
        + "DEPLOY 4,6,EAST\n"
        + "PIT 2,4\n"
        + "PIT 5,5\n"
        + "MOVE\n"
        + "RIGHT\n"
        + "MOVE\n"
        + "LEFT\n"
        + "LEFT\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    robotSimulator.process(inputStream);
  }

  @Test
  public void test5() {
    String string = "PIT 1,4\n"
        + "DEPLOY 4,6,EAST\n"
        + "PIT 2,4\n"
        + "PIT 5,5\n"
        + "MOVE\n"
        + "RIGHT\n"
        + "MOVE\n"
        + "LEFT\n"
        + "LEFT\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    List<String> result = robotSimulator.process(inputStream);
    assertEquals(2, result.size());
    assertEquals("PIT Detected: Ignored", result.get(0));
    assertEquals("5,7,NORTH", result.get(1));
  }

  @Test
  public void test6() {
    String string = "PIT 1,4\n"
        + "DEPLOY 1,1,EAST\n"
        + "PIT 2,4\n"
        + "PIT 5,5\n"
        + "MOVE\n"
        + "RIGHT\n"
        + "MOVE\n"
        + "MOVE\n"
        + "MOVE\n"
        + "LEFT\n"
        + "LEFT\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    List<String> result = robotSimulator.process(inputStream);
    assertEquals(3, result.size());
    assertEquals("Outside Zone: Ignored", result.get(0));
    assertEquals("Outside Zone: Ignored", result.get(1));
    assertEquals("2,1,NORTH", result.get(2));
  }

  @Test
  public void testMultipleDeploys() {
    String string = "PIT 1,4\n"
        + "DEPLOY 1,1,EAST\n"
        + "PIT 2,4\n"
        + "PIT 5,5\n"
        + "MOVE\n"
        + "RIGHT\n"
        + "DEPLOY 1,1,NORTH\n"
        + "MOVE\n"
        + "MOVE\n"
        + "MOVE\n"
        + "LEFT\n"
        + "LEFT\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    List<String> result = robotSimulator.process(inputStream);
    assertEquals(2, result.size());
    assertEquals("PIT Detected: Ignored", result.get(0));
    assertEquals("1,2,SOUTH", result.get(1));
  }

  @Test
  public void testMultipleDeploys1() {
    String string = "PIT 1,4\n"
        + "DEPLOY 1,1,EAST\n"
        + "PIT 2,4\n"
        + "PIT 5,5\n"
        + "MOVE\n"
        + "RIGHT\n"
        + "DEPLOY -1,1,NORTH\n"
        + "MOVE\n"
        + "MOVE\n"
        + "MOVE\n"
        + "LEFT\n"
        + "LEFT\n"
        + "MOVE\n"
        + "REPORT";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    List<String> result = robotSimulator.process(inputStream);
    assertEquals(4, result.size());
    assertEquals("Outside Zone: Ignored", result.get(0));
    assertEquals("Outside Zone: Ignored", result.get(1));
    assertEquals("Outside Zone: Ignored", result.get(2));
    assertEquals("2,1,NORTH", result.get(3));
  }

  @Test
  public void test() {
    String string = "DEPLOY  1 , 1, EAST\n"
        + "PIT  2,4\n"
        + "MOVE \n"
        + "REPORT ";

    InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    List<String> result = robotSimulator.process(inputStream);
    assertEquals(1, result.size());

    assertEquals("2,1,EAST", result.get(0));
  }
}