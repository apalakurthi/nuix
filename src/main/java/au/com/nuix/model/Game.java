package au.com.nuix.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

  private Board board;

  private Robot robot;

  private List<String> messages;

  private List<Position> pitPositions;

  public Game() {
    messages = new ArrayList<>();
    pitPositions = new ArrayList<>();
    board = new Board(10, 10);
  }

  public Game(int x, int y) {
    messages = new ArrayList<>();
    pitPositions = new ArrayList<>();
    board = new Board(x, y);
  }

  public boolean isPitPosition(int x, int y) {

    if (pitPositions.isEmpty()) {
      return false;
    }

    for (Position position : pitPositions) {
      if (position.getX() == x && position.getY() == y) {
        return true;
      }
    }
    return false;
  }

  public boolean isOutsideZone(int xcordinate, int ycordinate) {
    return xcordinate > board.getX() || xcordinate < 0 || ycordinate > board.getY() || ycordinate < 0;
  }

  public Game updateRobot(Position position, Direction direction) {
    robot = new Robot(direction, position);
    return this;
  }

  public Game updateDirection(Direction direction) {
    robot.setDirection(direction);
    return this;
  }

  public Game updateRobotPosition(int x, int y) {
    Position position = new Position(x, y);
    robot.setPosition(position);
    return this;
  }

  public Game addPitPosition(int x, int y) {
    Position position = new Position(x, y);
    pitPositions.add(position);
    return this;
  }

  public Game addMessages(String message) {
    messages.add(message);
    return this;
  }
}
