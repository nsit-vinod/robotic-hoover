package com.yoti.hoover.model.clean;

import com.yoti.hoover.exception.CleanNotSupportedException;
import com.yoti.hoover.model.Room;
/*
 * No clean supported class implementing CleanBehaviour
 */
public class CleanNoBehaviour implements CleanBehaviour {

	@Override
	public void cleanAndIncrement(Room roomToClean) {
		throw new CleanNotSupportedException("Clean not supported exception");

	}

}
