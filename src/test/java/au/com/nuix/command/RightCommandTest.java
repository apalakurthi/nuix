package au.com.nuix.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import au.com.nuix.model.Game;
import org.junit.Test;

public class RightCommandTest {

  public Game getGame(String direction) {
    DeployCommand deployCommand = new DeployCommand(new Game(), "1,2,"+ direction);
    return deployCommand.execute();
  }

  @Test
  public void testLeftForEAST() {
    RightCommand rightCommand = new RightCommand(getGame("EAST"), true);
    Game game = rightCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("SOUTH", game.getRobot().getDirection().getName());
  }

  @Test
  public void testLeftForWEST() {
    RightCommand rightCommand = new RightCommand(getGame("WEST"), true);
    Game game = rightCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("NORTH", game.getRobot().getDirection().getName());
  }

  @Test
  public void testLeftForNORTH() {
    RightCommand rightCommand = new RightCommand(getGame("NORTH"), true);
    Game game = rightCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("EAST", game.getRobot().getDirection().getName());
  }

  @Test
  public void testLeftForSOUTH() {
    RightCommand rightCommand = new RightCommand(getGame("SOUTH"), true);
    Game game = rightCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("WEST", game.getRobot().getDirection().getName());
  }
}