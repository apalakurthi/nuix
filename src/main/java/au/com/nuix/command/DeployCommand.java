package au.com.nuix.command;

import au.com.nuix.model.Direction;
import au.com.nuix.model.ErrorMessages;
import au.com.nuix.model.Game;
import au.com.nuix.model.Position;
import au.com.nuix.model.Robot;
import au.com.nuix.exception.InvalidCommandException;
import org.apache.commons.lang3.StringUtils;


/**
 * DeployCommand deploys the robot in the specified x, y co-ordinates
 *  facing towards given position.
 */
public class DeployCommand implements Command {

  private Game game;

  private String command;

  public DeployCommand(Game game, String command) {
    this.game = game;
    this.command = command;
  }

  /**
   * Processes the input command and places robot in corresponding co-ordinates and direction.   *
   *
   * @return Game object which represents the current status of the robot.
   */
  public Game execute() {
    if (StringUtils.isBlank(command)) {
      throw new InvalidCommandException("Invalid DEPLOY command passed.");
    }
    String[] tokens = command.split(",");
    if(tokens.length != 3) {
      throw new InvalidCommandException("DEPLOY command should have x, y co-ordinates and direction");
    }

    int x, y;
    try {
      x = Integer.parseInt(tokens[0].trim());
      y = Integer.parseInt(tokens[1].trim());
    } catch(Exception e) {
      throw new InvalidCommandException("Deploy command coordinates should be integers");
    }

    if (game.isOutsideZone(x, y)) {
      game.getMessages().add(ErrorMessages.OUTSIDE_ZONE);
      return game;
    }

    Direction direction = Direction.getDirection(tokens[2].trim());

    if (direction == null) {
      throw new InvalidCommandException("Direction of the deploy command should be one of these: EAST, WEST, SOUTH, NORTH");
    }

    Position position = new Position(x, y);
    game.updateRobot(position, direction);
    return game;
  }

}
