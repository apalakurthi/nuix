package au.com.nuix.command;

import au.com.nuix.model.ErrorMessages;
import au.com.nuix.model.Game;

/**
 * Report command adds the current co-ordinates and position of the robot as a
 * message to the Game object.
 */
public class ReportCommand implements Command{

  private Game game;

  private boolean isRobotDeployed;

  public ReportCommand(Game game, boolean isRobotDeployed) {
    this.game = game;
    this.isRobotDeployed = isRobotDeployed;
  }

  /**
   * @return Game status of the game and robot.
   *
   */
  @Override
  public Game execute() {
    if(!isRobotDeployed) {
      return game.addMessages(ErrorMessages.OUTSIDE_ZONE);
    }

    String result =  String.join(",",
        Integer.toString(game.getRobot().getPosition().getX()),
        Integer.toString(game.getRobot().getPosition().getY()),
        game.getRobot().getDirection().getName());
    game.addMessages(result);
    return game;
  }
}
