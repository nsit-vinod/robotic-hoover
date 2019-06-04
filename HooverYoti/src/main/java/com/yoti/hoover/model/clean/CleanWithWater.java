package com.yoti.hoover.model.clean;

import com.yoti.hoover.model.Room;
/*
 * Clean with water behaviour.
 */
public class CleanWithWater implements CleanBehaviour {

	/*
	 * (non-Javadoc)
	 * @see com.yoti.hoover.model.clean.CleanBehaviour#cleanAndIncrement(com.yoti.hoover.model.Room)
	 * This mehod will clean the room and removing pathes
	 */
	@Override
	public void cleanAndIncrement(Room roomToClean) {
		
		if (roomToClean.hasAnyPatche()) {
			roomToClean.incrementPatchesRemovelCountAndRemovePatch();
		}
	}

}
