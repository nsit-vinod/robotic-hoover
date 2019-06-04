package com.yoti.hoover.service;

import java.util.List;

import com.yoti.hoover.model.HooverInput;
import com.yoti.hoover.model.HooverOutput;
/*
 * Interface of the HooverService to be implemented.
 * @author           Vinod Kumar
 */
public interface HooverService {

	public HooverInput moveHooverAndCleanPatches(HooverInput hooverInput);

	HooverOutput moveHooverAndCleanPatchesTest(HooverInput hooverInput);

	public List<HooverInput> getAllHooverMoves();
	
	public void moveAndCleanDummyHoover(HooverInput hooverInput);
}
