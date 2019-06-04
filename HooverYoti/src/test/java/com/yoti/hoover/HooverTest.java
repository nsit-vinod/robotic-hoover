package com.yoti.hoover;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yoti.hoover.model.Hoover;
import com.yoti.hoover.model.HooverInput;
import com.yoti.hoover.model.WallHoover;
import com.yoti.hoover.model.factory.Hoovers;

public class HooverTest {

	WallHoover hoover = null;
	HooverInput hooverInput = null;
	@Before
	public void setUp() throws Exception {
		
		hooverInput = new HooverInput();
		hooverInput.setCoords(new int[] {1,2});
		hooverInput.setRoomSize(new int[] {5,5});
		Set<int[]> patches = new HashSet<>();
		patches.add(new int[] {1,0});
		patches.add(new int[] {2,2});
		patches.add(new int[] {2,3});
		hooverInput.setPatches(patches);
		hooverInput.setInstructions("NNESEESWNWW");
		hoover = Hoovers.newDefaultHoover(hooverInput);
	}

	@Test
	public void testMoveNorth() {
		Assert.assertEquals(new Point(1,3),hoover.moveNorth());
	}

	@Test
	public void testMoveSouth() {
		Assert.assertEquals(new Point(1,1),hoover.moveSouth());
	}

	@Test
	public void testMoveWest() {
		Assert.assertEquals(new Point(0,2),hoover.moveWest());
	}

	@Test
	public void testMoveEast() {
		Assert.assertEquals(new Point(2,2),hoover.moveEast());
	}

}
