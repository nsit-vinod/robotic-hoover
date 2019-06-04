package com.yoti.hoover.model.factory;

import java.awt.Point;
import java.util.HashSet;import java.util.Optional;
import java.util.Set;

import com.yoti.hoover.model.HooverInput;
import com.yoti.hoover.model.Room;
import com.yoti.hoover.model.WallHoover;
import com.yoti.hoover.model.clean.CleanBehaviour;
import com.yoti.hoover.model.clean.CleanNoBehaviour;
import com.yoti.hoover.model.clean.CleanWithAir;
import com.yoti.hoover.model.move.FlyMoveBehaviour;
import com.yoti.hoover.model.move.MoveBehaviour;
import com.yoti.hoover.model.move.NoMoveBehaviour;
import com.yoti.hoover.utils.HooverInputValidationUtils;
/*
 * This the static factory for creating some default Hoover
 */
public class Hoovers {

	/*
	 * newDefaultHoover accepting hoover instruction and create default hoover with fly and clean behaviour.
	 */
	public static WallHoover newDefaultHoover(HooverInput hooverInput) {

		//HooverInputValidationUtils.validateHooverInput(hooverInput);
		int[] initialPosition = hooverInput.getCoords();
		/*
		 * Creating starting point from input initial position.
		 */
		Point startingPoint = new Point(initialPosition[0],initialPosition[1]);
		/*
		 * Set of patches created from input.
		 */
		Set<Point> patches = new HashSet<>();
		for(int[] patch: hooverInput.getPatches()) {
			Point patchPoint = new Point(patch[0],patch[1]);
			patches.add(patchPoint);
		}
		/*
		 * We can use factory or Builder of room
		 */
		//Room roomToClean = Rooms.getRoom(hooverInput.getRoomSize(), patches, startingPoint);

		Room roomToClean = new Room.RoomBuilder().edges(hooverInput.getRoomSize()).patches(patches).hooverPosition(startingPoint).build();
		
		MoveBehaviour movingBehaviour = new FlyMoveBehaviour();

		CleanBehaviour cleanBehaviour = new CleanWithAir();

		return new WallHoover(startingPoint, roomToClean,movingBehaviour,cleanBehaviour );
	}

	/*
	 * newDummyHoover method creating dummy hoover that can not be used for moving and cleaning.
	 * We can change the behaviour of hoover by putting clening and moving behavouring dynamically.
	 */
	public static WallHoover newDummyHoover(HooverInput hooverInput) {
		HooverInputValidationUtils.validateHooverInput(hooverInput);
		int[] initialPosition = hooverInput.getCoords();
		Point startingPoint = new Point(initialPosition[0],initialPosition[1]);
		Set<Point> patches = new HashSet<>();
		for(int[] patch: hooverInput.getPatches()) {
			Point patchPoint = new Point(patch[0],patch[1]);
			patches.add(patchPoint);
		}
		/*
		 * Room can be initialized by Rooms getRoom() factory or Room builder.
		 */
		//Room roomToClean = Rooms.getRoom(hooverInput.getRoomSize(), patches,startingPoint);
		
		Room roomToClean = new Room.RoomBuilder().edges(hooverInput.getRoomSize()).patches(patches).hooverPosition(startingPoint).build();

		MoveBehaviour movingBehaviour = new NoMoveBehaviour();

		CleanBehaviour cleanBehaviour = new CleanNoBehaviour();

		return new WallHoover(startingPoint, roomToClean,movingBehaviour,cleanBehaviour );

	}

}
