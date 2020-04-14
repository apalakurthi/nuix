package au.com.nuix.command;

import au.com.nuix.model.Direction;
import au.com.nuix.model.ErrorMessages;
import au.com.nuix.model.Game;

/**
 * LeftCommand changes robot direction by 90 degrees on left
 *
 * */
public class LeftCommand implements Command {

  private Game game;

  private boolean isRobotDeployed;

  public LeftCommand(Game game, boolean isRobotDeployed) {
    this.game = game;
    this.isRobotDeployed = isRobotDeployed;
  }

  /**
   * Changes the direction of the robot by 90 degrees on left side.
   *
   * @return Game object which represents the current status of the robot.
   */
  @Override
  public Game execute() {
    if(!isRobotDeployed) {
      return game.addMessages(ErrorMessages.OUTSIDE_ZONE);
    }

    Direction direction = game.getRobot().getDirection().left();
    game.updateDirection(direction);
    return game;
  }
}
