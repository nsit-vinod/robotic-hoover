package com.yoti.hoover.model.move;

import java.awt.Point;

import com.yoti.hoover.model.Room;
/*
 * MoveBehaviour interface implementation class.
 */
public class FlyMoveBehaviour implements MoveBehaviour {

	@Override
	public void move(Point nextPosition,  Room roomToClean) {
		
		roomToClean.moveHoover(nextPosition);
	}

}
