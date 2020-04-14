package au.com.nuix.command;

import au.com.nuix.model.ErrorMessages;
import au.com.nuix.model.Game;

/**
 * MoveCommand moves the robot by 1 step in the current direction
 *
 * */
public class MoveCommand implements Command {

  private Game game;

  private boolean isRobotDeployed;

  public MoveCommand(Game game, boolean isRobotDeployed) {
    this.game = game;
    this.isRobotDeployed = isRobotDeployed;
  }

  /**
   * Moves the robot by 1 step in the direction where robot was there initially.
   *
   * @return Game status of the game and robot .
   */
  @Override
  public Game execute() {

    if (!isRobotDeployed) {
      return game.addMessages(ErrorMessages.OUTSIDE_ZONE);
    }

    int x = game.getRobot().getPosition().getX();
    int y = game.getRobot().getPosition().getY();
    switch (game.getRobot().getDirection()) {
      case EAST:
        ++x;
        updateCoordinates(x, y);
        break;
      case WEST:
        --x;
        updateCoordinates(x, y);
        break;
      case NORTH:
        ++y;
        updateCoordinates(x, y);
        break;
      case SOUTH:
        --y;
        updateCoordinates(x, y);
        break;
    }
    return game;
  }

  private void updateCoordinates(int x, int y) {
    if (isValidMove(x, y)) {
      game.updateRobotPosition(x, y);
    }
  }

  private boolean isValidMove(int x, int y) {
    if (game.isPitPosition( x, y)) {
      game.getMessages().add(ErrorMessages.PIT_DETECTED);
      return false;
    }

    if(game.isOutsideZone(x, y)) {
      game.getMessages().add(ErrorMessages.OUTSIDE_ZONE);
      return false;
    }
    return true;
  }

}
