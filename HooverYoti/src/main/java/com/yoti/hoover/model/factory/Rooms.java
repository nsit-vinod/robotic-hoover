package com.yoti.hoover.model.factory;

import java.awt.Point;
import java.util.Set;

import org.springframework.util.Assert;

import com.yoti.hoover.exception.InvalidInputException;
import com.yoti.hoover.model.Room;
/*
 * Factory of creating rooms of different size and putting hoover position and patches
 */
public class Rooms {

	public static Room getRoom(int[] roomSize, Set<Point> patches, Point hooverPosition) {

		if(roomSize.length>2) {
			throw new InvalidInputException("Initial position should be in 2 Dimensional");
		}
		
		if(roomSize[0]<0 || roomSize[1]<0) {
			throw new InvalidInputException("Element should be positive");
		}
		Room room = new Room(roomSize, patches);
		room.initHoover(hooverPosition);
		return room;
	}

	public static Room getRoom(int[] roomSize, Set<Point> patches) {
		Point hooverPosition = new Point(0, 0);
		return getRoom(roomSize,patches,hooverPosition);
	}
}
