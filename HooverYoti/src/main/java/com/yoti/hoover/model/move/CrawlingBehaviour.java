package com.yoti.hoover.model.move;

import java.awt.Point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoti.hoover.model.Room;
/*
 * This is implemented for crawling of the hoover.
 */
public class CrawlingBehaviour implements MoveBehaviour {

	private final Logger logger = LoggerFactory.getLogger(CrawlingBehaviour.class);

	@Override
	public void move(Point nextPosition, Room roomToClean) {
		
		setHoverPositionOnRoomWall(nextPosition, roomToClean);
	}

	private void setHoverPositionOnRoomWall(Point nextPosition, Room roomToClean) {
		/*
		 * Business logic for crawling of the robo hoover.
		 */
		logger.info("Request came into class {} to move the hoover. " + CrawlingBehaviour.class);
		roomToClean.moveHoover(nextPosition);

	}

}
