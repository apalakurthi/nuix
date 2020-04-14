package au.com.nuix.command;

import static org.junit.Assert.*;

import au.com.nuix.exception.InvalidCommandException;
import au.com.nuix.model.Game;
import org.junit.Test;

public class PitCommandTest {

  @Test
  public void testPitCommand() {
    PitCommand pitCommand = new PitCommand(getGame("SOUTH"), "5,3");
    Game game = pitCommand.execute();
    assertNotNull(game.getPitPositions());
    assertEquals(1 , game.getPitPositions().size());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("SOUTH", game.getRobot().getDirection().getName());
  }

  @Test(expected = InvalidCommandException.class)
  public void testInvalidPitCommand() {
    PitCommand pitCommand = new PitCommand(getGame("SOUTH"), "a,3");
    pitCommand.execute();
  }

  private Game getGame(String direction) {
    DeployCommand deployCommand = new DeployCommand(new Game(), "1,2,"+ direction);
    return deployCommand.execute();
  }
}