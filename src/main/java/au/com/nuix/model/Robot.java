package au.com.nuix.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Robot {

  private Direction direction;

  private Position position;
}
