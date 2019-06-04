package com.yoti.hoover.model;

import java.awt.Point;
import java.util.Optional;
import java.util.Set;

import org.springframework.util.Assert;
/*
 * Room class represent edges, patches and hoover position.
 * @author      Vinod Kumar
 */
public class Room {

	private final int[] edges;

	private Set<Point> patches;

	private Optional<Point> hooverPosition;

	private int patchesRemovalCount = 0;

	public Room(int[] edges, Set<Point> patches) {
		super();
		this.edges = edges;
		this.patches = patches;
		this.hooverPosition = Optional.empty();
	}

	public Room(RoomBuilder roomBuilder) {
		this.edges = roomBuilder.edges;
		this.patches = roomBuilder.patches;
		this.hooverPosition = roomBuilder.hooverPosition;
		this.patchesRemovalCount=0;
	}
	/*
	 * Initialize initial hoover position on the wall
	 */
	public void initHoover(Point initialPosition) {

		this.hooverPosition = Optional.of(initialPosition);
	}

	/*
	 * set hoover position at new point on wall.
	 */
	public void moveHoover(Point newPosition) {
		this.hooverPosition = Optional.of(newPosition);
	}

	/*
	 * increment and remove catches on room
	 */
	public void incrementPatchesRemovelCountAndRemovePatch() {
		Assert.isTrue(hasAnyPatche(), "No Patch left to clean");
		if (hasAnyPatche() && patches.contains(hooverPosition.get())) {
			patchesRemovalCount++;
			patches.remove(hooverPosition.get());
		}
	}

	/*
	 * Check whether room has any patch left or not.
	 */
	public boolean hasAnyPatche() {
		return patches.stream().filter(patch -> patch.equals(hooverPosition.get())).findFirst().isPresent();
	}

	public int[] getEdges() {
		return edges;
	}

	public Set<Point> getPatches() {
		return patches;
	}

	public Point getHooverPosition() {
		return hooverPosition.orElseThrow(IllegalStateException::new);
	}

	public int getPatchesRemovalCount() {
		return patchesRemovalCount;
	}
	/*
	 * Builder for the Room object. This is following builder pattern.
	 */
	public static class RoomBuilder {
		
		private int[] edges;

		private Set<Point> patches;

		private Optional<Point> hooverPosition;

		public RoomBuilder edges(int[] edges) {
			 this.edges = edges;
			 return this;
		}
		
		public RoomBuilder patches(Set<Point> patches) {
			 this.patches = patches;
			 return this;
		}
		
		public RoomBuilder hooverPosition(Point startingPosition) {
			
			 this.hooverPosition = Optional.of(startingPosition);
			 return this;
		}
		
		public Room build() {
			return new Room(this);
		}
	}
}
