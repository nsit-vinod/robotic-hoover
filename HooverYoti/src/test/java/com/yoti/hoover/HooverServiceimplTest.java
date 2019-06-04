package com.yoti.hoover;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.yoti.hoover.exception.CleanNotSupportedException;
import com.yoti.hoover.exception.InvalidInputException;
import com.yoti.hoover.exception.InvalidInstructionException;
import com.yoti.hoover.model.HooverInput;
import com.yoti.hoover.model.HooverOutput;
import com.yoti.hoover.model.WallHoover;
import com.yoti.hoover.model.factory.Hoovers;
import com.yoti.hoover.repository.HooverInputRepository;
import com.yoti.hoover.service.HooverService;
import com.yoti.hoover.service.impl.HooverServiceImpl;
import com.yoti.hoover.utils.HooverInputValidationUtils;


@RunWith(SpringRunner.class)
public class HooverServiceimplTest {

	@TestConfiguration
	static class HooverServiceImplTestContextConfiguration {
		@Bean
		public HooverService hooverService() {
			return new HooverServiceImpl();
		}
	}
	
	@Autowired
	private HooverService hooverService;
	
	@MockBean
	private HooverInputRepository hooverRepository;
	
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
	public void testNumberOfCleanedPatches() {
		
		HooverOutput output = hooverService.moveHooverAndCleanPatchesTest(hooverInput);
		Assert.assertEquals(1, output.getPatches());
	}
	
	@Test
	public void testPositionOfHoover() {
		
		HooverOutput output = hooverService.moveHooverAndCleanPatchesTest(hooverInput);
		Assert.assertArrayEquals(new int[] {1,3}, output.getCoords());
	}
	
	@Test(expected = CleanNotSupportedException.class)
	public void testDymmyHoover() {
		
		 hooverService.moveAndCleanDummyHoover(hooverInput);
		//Assert.assertArrayEquals(new int[] {1,3}, output.getCoords());
	}
	
	@Test(expected = InvalidInputException.class)
	public void testInvalidRoomSizeException() {
		HooverInput hooverIn = new HooverInput();
		hooverIn.setCoords(new int[] {1,2});
		hooverIn.setRoomSize(new int[] {5,-5});
		Set<int[]> patches = new HashSet<>();
		patches.add(new int[] {1,2});
		patches.add(new int[] {2,2});
		patches.add(new int[] {2,3});
		hooverIn.setPatches(patches);
		hooverIn.setInstructions("NNESEESWNWW");
		HooverInputValidationUtils.validateHooverInput(hooverIn);
	   
	}
	
	@Test(expected = InvalidInputException.class)
	public void testInvalidCoordinateSizeException() {
		HooverInput hooverIn = new HooverInput();
		hooverIn.setCoords(new int[] {1,-2});
		hooverIn.setRoomSize(new int[] {5,5});
		Set<int[]> patches = new HashSet<>();
		patches.add(new int[] {1,2});
		patches.add(new int[] {2,2});
		patches.add(new int[] {2,3});
		hooverIn.setPatches(patches);
		hooverIn.setInstructions("NNESEESWNWW");
		HooverInputValidationUtils.validateHooverInput(hooverIn);
	   
	}
	
	@Test(expected = InvalidInputException.class)
	public void testInvaliddimentationSizeException() {
		HooverInput hooverIn = new HooverInput();
		hooverIn.setCoords(new int[] {1,2,3});
		hooverIn.setRoomSize(new int[] {5,5,3});
		Set<int[]> patches = new HashSet<>();
		patches.add(new int[] {1,2});
		patches.add(new int[] {2,2});
		patches.add(new int[] {2,3});
		hooverIn.setPatches(patches);
		hooverIn.setInstructions("NNESEESWNWW");
		HooverInputValidationUtils.validateHooverInput(hooverIn);
	   
	}
	@Test(expected = InvalidInputException.class)
	public void testInvalidPathcesSizeException() {
		HooverInput hooverIn = new HooverInput();
		hooverIn.setCoords(new int[] {1,2,3});
		hooverIn.setRoomSize(new int[] {5,5});
		Set<int[]> patches = new HashSet<>();
		patches.add(new int[] {1,2,3});
		patches.add(new int[] {2,2,-2});
		patches.add(new int[] {2,3});
		hooverIn.setPatches(patches);
		hooverIn.setInstructions("NNESEESWNWW");
		HooverInputValidationUtils.validateHooverInput(hooverIn);
	   
	}
	
	@Test(expected = InvalidInstructionException.class)
	public void testInvalidInstructionException() {
		HooverInput hooverIn = new HooverInput();
		hooverIn.setCoords(new int[] {1,2,3});
		hooverIn.setRoomSize(new int[] {5,5});
		Set<int[]> patches = new HashSet<>();
		patches.add(new int[] {1,2,3});
		patches.add(new int[] {2,2,-2});
		patches.add(new int[] {2,3});
		hooverIn.setPatches(patches);
		hooverIn.setInstructions("NNESEESWNWWT");
		hooverService.moveHooverAndCleanPatchesTest(hooverIn);
	   
	}
}
