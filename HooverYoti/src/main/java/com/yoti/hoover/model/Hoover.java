package com.yoti.hoover.model;

import java.awt.Point;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoti.hoover.exception.MoveNotSupportedException;
import com.yoti.hoover.model.clean.CleanBehaviour;
import com.yoti.hoover.model.move.MoveBehaviour;
/*
 * Hoover abstract class for representing Hoover super type.
 * @author       Vinod Kumar
 */
public abstract class Hoover {

	private final Logger logger = LoggerFactory.getLogger(Hoover.class);

	/*
	 * inital position of hoover in room
	 */
	private  Point initialPosition;

	/*
	 * room to be clean by this hoover
	 */
	private  Room roomToClean;

	/*
	 * MoveBehaviour that can be dynamically changable by implementation of MoveBehaviour
	 */
	private MoveBehaviour moveBehaviour;

	/*
	 * CleanBehaviour that can be dynamically changable by implementation of CleanBehaviour
	 */
	private CleanBehaviour cleanBehaviour;

	/*
	 * Constructor of Hoover that can access initialPosition: Point, roomToClean: Room, MoveBehaviour: moverBehavioiur,
	 * CleanBehaviour cleanBehaviour as parameter
	 */
	public Hoover(Point initialPosition, Room roomToClean, MoveBehaviour moveBehaviour,CleanBehaviour cleanBehaviour) {
		logger.info("Object of Hoover is getting created.");
		this.initialPosition = initialPosition;
		this.roomToClean = roomToClean;
		this.cleanBehaviour = cleanBehaviour;
		this.moveBehaviour = moveBehaviour;
		initHoover(initialPosition);
	}

	/*
	 * abstract executeCommand to execute the according to command. This will be implemented by concrete Hoover.
	 */
	public abstract void executeCommands(List<Character> commands);
	
	/*
	 * Get initial position of hoover
	 */
	public Point getInitialPosition() {
		return initialPosition;
	}

	/*
	 * get current position of hoover in room to clean.
	 */
	public Point hooverPosition() {
		return this.roomToClean.getHooverPosition();
	}

	/*
	 * initialize hoover in room to clean. Point: initPosition as argument.
	 */
	private void initHoover(Point initPosition) {
		this.roomToClean.initHoover(initPosition);
	}

	public Room getRoomToClean() {
		return roomToClean;
	}

	public MoveBehaviour getMoveBehaviour() {
		return moveBehaviour;
	}

	public void setMoveBehaviour(MoveBehaviour moveBehaviour) {
		this.moveBehaviour = moveBehaviour;
	}

	public CleanBehaviour getCleanBehaviour() {
		return cleanBehaviour;
	}

	public void setCleanBehaviour(CleanBehaviour cleanBehaviour) {
		this.cleanBehaviour = cleanBehaviour;
	}
	
}
