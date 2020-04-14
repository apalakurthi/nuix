package au.com.nuix.command;

import static org.junit.Assert.*;

import au.com.nuix.model.Game;
import org.junit.Test;

public class LeftCommandTest {

  public Game getGame(String direction) {
    DeployCommand deployCommand = new DeployCommand(new Game(), "1,2,"+ direction);
    return deployCommand.execute();
  }

  @Test
  public void testLeftForEAST() {
    LeftCommand leftCommand = new LeftCommand(getGame("EAST"), true);
    Game game = leftCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("NORTH", game.getRobot().getDirection().getName());
  }

  @Test
  public void testLeftForWEST() {
    LeftCommand leftCommand = new LeftCommand(getGame("WEST"), true);
    Game game = leftCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("SOUTH", game.getRobot().getDirection().getName());
  }

  @Test
  public void testLeftForNORTH() {
    LeftCommand leftCommand = new LeftCommand(getGame("NORTH"), true);
    Game game = leftCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("WEST", game.getRobot().getDirection().getName());
  }

  @Test
  public void testLeftForSOUTH() {
    LeftCommand leftCommand = new LeftCommand(getGame("SOUTH"), true);
    Game game = leftCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("EAST", game.getRobot().getDirection().getName());
  }
}