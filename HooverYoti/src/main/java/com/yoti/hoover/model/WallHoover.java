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
 * Hoover designed specificaly for wall.
 */
public class WallHoover extends Hoover {

	private final Logger logger = LoggerFactory.getLogger(WallHoover.class);
	
	public WallHoover(Point initialPosition, Room roomToClean, MoveBehaviour moveBehaviour,
			CleanBehaviour cleanBehaviour) {
		super(initialPosition, roomToClean, moveBehaviour, cleanBehaviour);
		// TODO Auto-generated constructor stub
	}
	/*
	 * (non-Javadoc)
	 * @see com.yoti.hoover.model.Hoover#executeCommands(java.util.List)
	 */
	@Override
	public void executeCommands(List<Character> commands) {

		logger.info("Executing commands: " + commands.toString());
		commands.stream().forEach(command -> {
			switch (Character.toUpperCase(command)) {
			case 'N':
				moveHoover(moveNorth());
				break;
			case 'E':
				moveHoover(moveEast());
				break;
			case 'S':
				moveHoover(moveSouth());
				break;
			case 'W':
				moveHoover(moveWest());
				break;
			default:
				logger.error("Command not recognised: " + command);
				throw new InvalidInstructionException("Invalid instruction "+command +" to move.");
			}
			getCleanBehaviour().cleanAndIncrement(getRoomToClean());
		});
	}

	private void moveHoover(Point nextPosition) {
		try {
			getMoveBehaviour().move(nextPosition, getRoomToClean());
		} catch (MoveNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public Point moveNorth() {
		Point currentPosition = getRoomToClean().getHooverPosition();
		int upperRoomEdge = getRoomToClean().getEdges()[1];
		if (currentPosition.y < upperRoomEdge-1) {
			return new Point(currentPosition.x, currentPosition.y + 1);
		}
		return currentPosition;
	}

	public Point moveSouth() {
		Point currentPosition = getRoomToClean().getHooverPosition();
		if (currentPosition.y > 0) {
			return new Point(currentPosition.x, currentPosition.y - 1);
		}
		return currentPosition;
	}

	public Point moveWest() {
		Point currentPosition = getRoomToClean().getHooverPosition();
		if (currentPosition.x > 0) {
			return new Point(currentPosition.x - 1, currentPosition.y);
		}
		return currentPosition;
	}

	public Point moveEast() {
		Point currentPosition = getRoomToClean().getHooverPosition();
		int easternRoomEdge = getRoomToClean().getEdges()[0];
		if (currentPosition.x < easternRoomEdge-1) {
			return new Point(currentPosition.x + 1, currentPosition.y);
		}
		return currentPosition;
	}
}
