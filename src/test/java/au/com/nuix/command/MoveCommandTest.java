package au.com.nuix.command;

import static org.junit.Assert.*;

import au.com.nuix.model.Game;
import org.junit.Test;

public class MoveCommandTest {

  public Game getGame(String direction) {
    DeployCommand deployCommand = new DeployCommand(new Game(), "2,2,"+ direction);
    return deployCommand.execute();
  }

  @Test
  public void testMoveEAST() {
    MoveCommand moveCommand = new MoveCommand(getGame("EAST"), true);
    Game game = moveCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(3, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("EAST", game.getRobot().getDirection().getName());
  }

  @Test
  public void testMoveWEST() {
    MoveCommand moveCommand = new MoveCommand(getGame("WEST"), true);
    Game game = moveCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(1, game.getRobot().getPosition().getX());
    assertEquals(2, game.getRobot().getPosition().getY());
    assertEquals("WEST", game.getRobot().getDirection().getName());
  }

  @Test
  public void testMoveNorth() {
    MoveCommand moveCommand = new MoveCommand(getGame("NORTH"), true);
    Game game = moveCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(2, game.getRobot().getPosition().getX());
    assertEquals(3, game.getRobot().getPosition().getY());
    assertEquals("NORTH", game.getRobot().getDirection().getName());
  }

  @Test
  public void testMoveSouth() {
    MoveCommand moveCommand = new MoveCommand(getGame("SOUTH"), true);
    Game game = moveCommand.execute();
    assertNotNull(game.getRobot());
    assertEquals(2, game.getRobot().getPosition().getX());
    assertEquals(1, game.getRobot().getPosition().getY());
    assertEquals("SOUTH", game.getRobot().getDirection().getName());
  }
}