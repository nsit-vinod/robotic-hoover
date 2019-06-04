package com.yoti.hoover.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yoti.hoover.model.HooverInput;
import com.yoti.hoover.model.HooverOutput;
import com.yoti.hoover.service.HooverService;
import com.yoti.hoover.utils.HooverInputValidationUtils;
/*
 * API for the robo hoover to operate.
 * @author        Vinod Kumar 
 */
@RestController
public class HooverController {

	private final Logger logger = LoggerFactory.getLogger(HooverController.class);
	
	/*
	 * Autowiring hooverService for service layer
	 */
	@Autowired
	private HooverService hooverService;
	
	/*
	 * POST api for move and clean patches in room. It is getting HooverInput as the request body.
	 */
	@PostMapping(path ="/hoover/move-clean")
	public ResponseEntity<HooverOutput> moveAndClean(@RequestBody @Valid HooverInput hooverInput) {
		logger.info(hooverInput.getCoords().toString());
		//Validate hoover input
		//HooverInputValidationUtils.validateHooverInput(hooverInput);
		HooverInput savedMoves = hooverService.moveHooverAndCleanPatches(hooverInput);
		logger.info("Hoover output is "+savedMoves);
		
		URI location = ServletUriComponentsBuilder
		    .fromCurrentRequest()
		    .path("/{id}")
		    .buildAndExpand(savedMoves.getId())
		    .toUri();
		
		ResponseEntity<HooverOutput>  hooverOut = ResponseEntity.created(location).body(savedMoves.getHooverOutput());
		return hooverOut;
	}
	/*
	 * GET Api for getting input and output hoover moves from database
	 */
	@GetMapping(path ="/hoover/move-clean")
	public List<HooverInput> getAllHooverMoves() {
		return hooverService.getAllHooverMoves();
	}
	
}
