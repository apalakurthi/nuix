# Rover Robot Simulator Application

##Description
1. The application is a simulation of a Rover Robot moving on a square zone to be explored, of
dimensions 10 units x 10 units. Coordinates start at 0,0
2. The first command to the application will always be DEPLOY, and will provide coordinates to
place the robot on a particular X,Y position on that 10x10 grid
3. The DEPLOY command can be followed by one or more PIT commands. A PIT command
specifies coordinates on the 10x10 Grid that the Rover Robot SHOULD NOT land on, as it would
fall on a trench and the Rover Robot will become trapped permanently
4. All PIT commands will be given sequentially, and once the last PIT command is received, all
commands that follow are for the Rover Robot to move, turn or be deployed again. Besides the
specified PITS there are no other obstructions on the square exploration zone.
5. The Rover Robot is free to roam around the surface of the square exploration zone, but must be
prevented from falling outside of the zone to its destruction, and prevented from landing in a PIT.
Any movement that would result in the robot falling outside of the specified zone or in a PIT must
be prevented, however further valid movement commands must still be allowed.
6. DEPLOY will land the Rover Robot on the Exploration Zone at position X,Y and Facing(F) =
NORTH, SOUTH, EAST or WEST.
7.  The origin (0,0) can be considered to be the SOUTH WEST most corner.
8. The first valid command to the robot is a DEPLOY command, followed by one or more PIT
commands, then followed by commands to move or turn. Any sequence of commands may be
issued, in any order, including another DEPLOY command. The application should ignore all
commands in the sequence until a valid DEPLOY command has been executed.
9. MOVE will move the Rover Robot one unit forward in the direction it is currently facing.
10. LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the
position of the robot.
11. REPORT will announce the current X,Y and F of the robot. This can be in any form, but standard
output is sufficient.
12. A Rover Robot that is not on the exploration zone must ignore the MOVE, LEFT, RIGHT and
REPORT commands. A Rover Robot about to enter a PIT coordinate (previously specified in the
initial commands) should also ignore that MOVE command. Important: Any time a command is
ignored because of the robot is not in the exploration zone, or when about to enter a PIT, an
“IGNORE” message should be reported (e.g. on Standard Output)

##Constraints
1. The Rover Robot must not navigate outside of the 10x10 Zone during movement. This also
includes the initial placement of the Rover Robot with a DEPLOYcommand.
2. Any move that would cause the robot to fall outside of the 10x10 zone must beignored.
3. Any move that would cause the Rover Robot to land in a PIT coordinate must beignored.
