package com.yoti.hoover.utils;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import com.yoti.hoover.exception.InvalidInputException;
import com.yoti.hoover.model.HooverInput;
/*
 * A utitlity class for validating the input for the hoover and room
 */
public class HooverInputValidationUtils {

	public static void validateHooverInput(HooverInput hooverInput) {
		
		int[] roomSize  = hooverInput.getRoomSize();
		//cheking dimentation of room
		if(roomSize.length>2) {
			throw new InvalidInputException("Initial position should be in two Dimensional");
		}
		
		//dimentation should be positive
		if(roomSize[0]<=0 || roomSize[1]<=0) {
			throw new InvalidInputException("Room size should be positive");
		}
		
		//intials dimentation should be positive and two dimentational
		int[] initialPosition = hooverInput.getCoords();
		if(initialPosition.length>2) {
			throw new InvalidInputException("Hoover initial position should be in two Dimensional");
		}
		
		if(initialPosition[0]<0 || initialPosition[1]<0) {
			throw new InvalidInputException("Hoover initails positions should be positive");
		}
		
		if(initialPosition[0]>=roomSize[0] || initialPosition[1]>=roomSize[1]) {
			throw new InvalidInputException("Hoover initails positions should be in range ");
		}
		
		for(int[] patch: hooverInput.getPatches()) {
			if(patch.length>2) {
				throw new InvalidInputException("Patches position should be in two Dimensional");
			}
			Point patchPoint = new Point(patch[0],patch[1]);
			if(patchPoint.getX()<0 || patchPoint.getY()<0) {
				throw new InvalidInputException("Patches dimentation should be positive");
			}
			if(patch[0]>=roomSize[0] || patch[1]>=roomSize[1]) {
				throw new InvalidInputException("Patch positions should be in range ");
			}
		}
	}
}
