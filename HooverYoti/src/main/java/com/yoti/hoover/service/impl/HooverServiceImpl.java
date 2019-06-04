package com.yoti.hoover.service.impl;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.yoti.hoover.model.Hoover;
import com.yoti.hoover.model.HooverInput;
import com.yoti.hoover.model.HooverOutput;
import com.yoti.hoover.model.WallHoover;
import com.yoti.hoover.model.factory.Hoovers;
import com.yoti.hoover.repository.HooverInputRepository;
import com.yoti.hoover.service.HooverService;

/*
 * This is the client code for taking the services of Hoover  to execute the given instructions.
 * @author     Vinod Kumar
 */
@Service
public class HooverServiceImpl implements HooverService {

	private final Logger logger = LoggerFactory.getLogger(HooverServiceImpl.class);
	
	/*
	 * Autowiring hooverInputReposiotry to connect with data base.
	 */
	@Autowired
	private HooverInputRepository hooverInputRepository;
	
	/*
	 * (non-Javadoc)
	 * @see com.yoti.hoover.service.HooverService#moveHooverAndCleanPatches(com.yoti.hoover.model.HooverInput)
	 * this method will be used to clean and move the hoover
	 */
	@Override
	public HooverInput moveHooverAndCleanPatches(HooverInput hooverInput) {
		logger.info("Message came to move hover and clean room");
		
		logger.info("Get the default hoover based on hooverInput {}",hooverInput);
		Hoover hoover = Hoovers.newDefaultHoover(hooverInput);
		
		logger.info("Converting String instrucitons into Character array");
		IntStream is = hooverInput.getInstructions().chars();
        Stream<Character> characterStream = is.mapToObj(c -> (char) c);
        Character[] chars= characterStream.toArray(Character[]::new);
        logger.info("hoover executed with instruction {} ",chars.toString());
        hoover.executeCommands(Lists.newArrayList(chars));
		
        //Creating Hoover output for response and putting into database for future reference.
        HooverOutput output = new HooverOutput();
		int[] finalPosition = new int[2];
		finalPosition[0]=(int) hoover.hooverPosition().getX();
		finalPosition[1]=(int) hoover.hooverPosition().getY();
		output.setCoords(finalPosition);
		output.setPatches(hoover.getRoomToClean().getPatchesRemovalCount());
		hooverInput.setHooverOutput(output);
		hooverInput = hooverInputRepository.save(hooverInput);
		
		return hooverInput;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yoti.hoover.service.HooverService#moveHooverAndCleanPatchesTest(com.yoti.hoover.model.HooverInput)
	 * it will move and clean the room but data will not be seved into database. this is written for testing.
	 */
	@Override
	public HooverOutput moveHooverAndCleanPatchesTest(HooverInput hooverInput) {
		logger.info("Message came to move hover and clean room");
		
		logger.info("Get the default hoover based on hooverInput {}",hooverInput);
		Hoover hoover = Hoovers.newDefaultHoover(hooverInput);
		
		logger.info("Converting String instrucitons into Character array");
		IntStream is = hooverInput.getInstructions().chars();
        Stream<Character> characterStream = is.mapToObj(c -> (char) c);
        Character[] chars= characterStream.toArray(Character[]::new);
       
        logger.info("hoover executed with instruction {} ",chars.toString());
        hoover.executeCommands(Lists.newArrayList(chars));
		
        HooverOutput output = new HooverOutput();
		int[] finalPosition = new int[2];
		finalPosition[0]=(int) hoover.hooverPosition().getX();
		finalPosition[1]=(int) hoover.hooverPosition().getY();
		output.setCoords(finalPosition);
		output.setPatches(hoover.getRoomToClean().getPatchesRemovalCount());
		
		return output;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yoti.hoover.service.HooverService#getAllHooverMoves()
	 * Get all hoover input and output from mongodb
	 */
	@Override
	public List<HooverInput> getAllHooverMoves() {
		// TODO Auto-generated method stub
		return hooverInputRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * @see com.yoti.hoover.service.HooverService#moveAndCleanDummyHoover(com.yoti.hoover.model.HooverInput)
	 * this service layer dummy hoover created that will throw move or clean not suppotred exception.
	 */
	@Override
	public void moveAndCleanDummyHoover(HooverInput hooverInput) {
		Hoover hoover = Hoovers.newDummyHoover(hooverInput);
		
		logger.info("Converting String instrucitons into Character array");
		IntStream is = hooverInput.getInstructions().chars();
        Stream<Character> characterStream = is.mapToObj(c -> (char) c);
        Character[] chars= characterStream.toArray(Character[]::new);
       
        logger.info("hoover executed with instruction {} ",chars.toString());
        hoover.executeCommands(Lists.newArrayList(chars));
		
	}
}
