package au.com.nuix.model;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Direction {

  NORTH(0 , "NORTH"),
  EAST(1, "EAST"),
  SOUTH(2, "SOUTH"),
  WEST(3, "WEST");

  private int ordinal;
  private String name;

  private static Map<Integer, Direction> map = new HashMap<>();


  static {
    for (Direction directionEnum : Direction.values()) {
      map.put(directionEnum.ordinal, directionEnum);
    }
  }

  public Direction left() {
    return rotate(-1);
  }

  public Direction right() {
    return rotate(1);
  }

  private Direction rotate(int step) {

    int newOrdinal = (this.ordinal + step) < 0 ?
        map.size() - 1 :
        (this.ordinal + step) % map.size();

    return map.get(newOrdinal);
  }

  public static Direction getDirection(String name) {
    switch (name) {
      case "NORTH":
        return Direction.NORTH;
      case "SOUTH":
        return Direction.SOUTH;
      case "EAST":
        return Direction.EAST;
      case "WEST":
        return Direction.WEST;
      default:
        return null;
    }
  }
}
