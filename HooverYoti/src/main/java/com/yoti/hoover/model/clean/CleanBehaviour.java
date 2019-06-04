package com.yoti.hoover.model.clean;

import com.yoti.hoover.model.Room;
/*
 * This is integral part of Hoover. To get clean behaviour out of Hoover, we created CleanBehaviour interface.
 */
public interface CleanBehaviour {

	/*
	 * Method to clean the room.
	 */
	public void cleanAndIncrement(Room roomToClean);
}
