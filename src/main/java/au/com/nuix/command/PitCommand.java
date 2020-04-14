package au.com.nuix.command;

import au.com.nuix.model.Game;
import au.com.nuix.exception.InvalidCommandException;
import org.apache.commons.lang3.StringUtils;

/**
 * PitCommand will register the dangerous positions into Game object.
 *
 * */

public class PitCommand implements Command {

  private Game game;

  private String command;

  public PitCommand(Game game, String command) {
    this.game = game;
    this.command = command;
  }

  /**
   * Creates and adds pit position record to game object.
   *
   * @return Game status of the game and robot.
   */
  @Override
  public Game execute() {
    if (StringUtils.isBlank(command)) {
      throw new InvalidCommandException("Invalid PIT command passed.");
    }
    String[] tokens = command.split(",");

    if(tokens.length != 2) {
      throw new InvalidCommandException("PIT command should have x, y co-ordinates.");
    }
    int x, y;
    try {
      x = Integer.parseInt(tokens[0].trim());
      y = Integer.parseInt(tokens[1].trim());
    } catch(Exception e) {
      throw new InvalidCommandException("PIT command coordinates should be integers");
    }

    if (x < 0  || y < 0) {
      throw new InvalidCommandException("x,y co-ordinates of PIT command should be greater than or equal to zero.");
    }
    return game.addPitPosition(x, y);
  }
}
