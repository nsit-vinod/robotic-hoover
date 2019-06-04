package com.yoti.hoover.model.move;

import java.awt.Point;

import com.yoti.hoover.exception.MoveNotSupportedException;
import com.yoti.hoover.model.Room;

public interface MoveBehaviour {

	public void move(Point nextPosition, Room roomToClean) throws MoveNotSupportedException;
}
