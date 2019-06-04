package com.yoti.hoover.model;

import java.awt.Point;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoti.hoover.exception.InvalidInstructionException;
import com.yoti.hoover.exception.MoveNotSupportedException;
import com.yoti.hoover.model.clean.CleanBehaviour;
import com.yoti.hoover.model.move.MoveBehaviour;
/*
 * FloorHoover extending Hoover abstract class and implementing executing and move implementation
 */
public class FloorHoover extends Hoover {

	private final Logger logger = LoggerFactory.getLogger(FloorHoover.class);
	
	public FloorHoover(Point initialPosition, Room roomToClean, MoveBehaviour moveBehaviour,
			CleanBehaviour cleanBehaviour) {
		super(initialPosition, roomToClean, moveBehaviour, cleanBehaviour);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see com.yoti.hoover.model.Hoover#executeCommands(java.util.List)
	 * executeCommands method is implemented that take commands as argument.
	 * this is calling clean and move behaviour.
	 */
	@Override
	public void executeCommands(List<Character> commands) {

		logger.info("Executing commands: " + commands.toString());
		commands.stream().forEach(command -> {
			switch (Character.toUpperCase(command)) {
			case 'U':
				moveHoover(moveNorth());
				break;
			case 'L':
				moveHoover(moveEast());
				break;
			case 'B':
				moveHoover(moveSouth());
				break;
			case 'R':
				moveHoover(moveWest());
				break;
			default:
				logger.error("Command not recognised: " + command);
				throw new InvalidInstructionException("Invalid instruction "+command +" to move.");
			}
			getCleanBehaviour().cleanAndIncrement(getRoomToClean());
		});
	}

	/*
	 * mooving hoover at the location nextPosition
	 */
	private void moveHoover(Point nextPosition) {
		try {
			getMoveBehaviour().move(nextPosition, getRoomToClean());
		} catch (MoveNotSupportedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * moving hoover towards north
	 */
	public Point moveNorth() {
		Point currentPosition = getRoomToClean().getHooverPosition();
		int upperRoomEdge = getRoomToClean().getEdges()[1];
		if (currentPosition.y < upperRoomEdge) {
			return new Point(currentPosition.x, currentPosition.y + 1);
		}
		return currentPosition;
	}

	/*
	 * moving hoover towards south
	 */
	public Point moveSouth() {
		Point currentPosition = getRoomToClean().getHooverPosition();
		if (currentPosition.y > 0) {
			return new Point(currentPosition.x, currentPosition.y - 1);
		}
		return currentPosition;
	}

	/*
	 * moving hoover towards west
	 */
	public Point moveWest() {
		Point currentPosition = getRoomToClean().getHooverPosition();
		if (currentPosition.x > 0) {
			return new Point(currentPosition.x - 1, currentPosition.y);
		}
		return currentPosition;
	}

	/*
	 * moving hoover towards East
	 */
	public Point moveEast() {
		Point currentPosition = getRoomToClean().getHooverPosition();
		int easternRoomEdge = getRoomToClean().getEdges()[0];
		if (currentPosition.x < easternRoomEdge) {
			return new Point(currentPosition.x + 1, currentPosition.y);
		}
		return currentPosition;
	}
}
