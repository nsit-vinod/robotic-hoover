package com.yoti.hoover.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*
 * Hoover input giving instruction for room and hoover moves
 * @author      Vinod Kumar
 */
@Document(collection ="hoover_move")
public class HooverInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;
	/*
	 * Size of the room. size dimentional should be positive.
	 */
	@Size(min = 2, max = 2, message 
		      = "Room size should be in two dimensional")
	private int[] roomSize;
	
	/*
	 * Initial position of hoover in room
	 */
	@Size(min = 2, max = 2, message 
		      = "Initiational position of hoover to be in two dimensional")
	private int[] coords;
	
	/*
	 * Set of patches point in room
	 */
	private Set<int[]> patches;
	
	/*
	 * Instructions to be be followed by hoover fot move and clean patches if available.
	 */
	private String instructions;

	/*
	 * hoover output after following instruction.
	 */
	private HooverOutput hooverOutput;
	
	public int[] getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int[] roomSize) {
		this.roomSize = roomSize;
	}

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
	}

	public Set<int[]> getPatches() {
		return patches;
	}

	public void setPatches(Set<int[]> patches) {
		this.patches = patches;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	@JsonIgnore
	public ObjectId getId() {
		return id;
	}
	@JsonIgnore
	public void setId(ObjectId id) {
		this.id = id;
	}

	public HooverOutput getHooverOutput() {
		return hooverOutput;
	}

	public void setHooverOutput(HooverOutput hooverOutput) {
		this.hooverOutput = hooverOutput;
	}

	@Override
	public String toString() {
		return "HooverInput [id=" + id + ", roomSize=" + Arrays.toString(roomSize) + ", coords="
				+ Arrays.toString(coords) + ", patches=" + patches + ", instructions=" + instructions + "]";
	}
	
	
}
