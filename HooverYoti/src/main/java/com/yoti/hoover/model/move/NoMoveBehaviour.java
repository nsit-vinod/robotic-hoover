package com.yoti.hoover.model.move;

import java.awt.Point;

import com.yoti.hoover.exception.MoveNotSupportedException;
import com.yoti.hoover.model.Room;
/*
 * NoMoveBehaviour class of MoveBehavour interface. This behaviour is for not supporting move
 */
public class NoMoveBehaviour implements MoveBehaviour {

	@Override
	public void move(Point nextPosition, Room roomToClean) throws MoveNotSupportedException {
		throw new MoveNotSupportedException("Hoover cannot be moved");

	}

}
