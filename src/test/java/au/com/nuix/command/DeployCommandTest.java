package au.com.nuix.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import au.com.nuix.exception.InvalidCommandException;
import au.com.nuix.model.Game;
import org.junit.Test;

public class DeployCommandTest {

  @Test
  public void testValidDeploy() {
    DeployCommand deployCommand = new DeployCommand(new Game(), "1,2,EAST");
    Game game = deployCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("EAST", game.getRobot().getDirection().getName());
  }

  @Test(expected = InvalidCommandException.class)
  public void testInvalidPosition() {
    DeployCommand deployCommand = new DeployCommand(new Game(), "a,2,EAST");
    deployCommand.execute();
  }

  @Test(expected = InvalidCommandException.class)
  public void testInvalidDirection() {
    DeployCommand deployCommand = new DeployCommand(new Game(), "1,2,EAST12");
    deployCommand.execute();
  }

  @Test
  public void testOutsidezoneDeploy() {
    DeployCommand deployCommand = new DeployCommand(new Game(), "11,2,EAST");
    Game game = deployCommand.execute();
    assertNull(game.getRobot());
  }
}