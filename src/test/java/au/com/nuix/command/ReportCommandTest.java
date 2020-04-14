package au.com.nuix.command;

import static org.junit.Assert.assertEquals;

import au.com.nuix.model.Game;
import org.junit.Test;

public class ReportCommandTest {


  @Test
  public void execute() {
    Game game = getGame("SOUTH");
    game = new ReportCommand(game,true).execute();
    assertEquals(1, game.getMessages().size());
    assertEquals("1,2,SOUTH", game.getMessages().get(0));
  }

  public Game getGame(String direction) {
    DeployCommand deployCommand = new DeployCommand(new Game(), "1,2,"+ direction);
    return deployCommand.execute();
  }
}