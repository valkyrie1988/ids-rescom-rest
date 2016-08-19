package com.ids.rescom.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ids.rescom.entities.advertisements.Advertisement;
import com.ids.rescom.repositories.AdvertisementRepository;


@RestController
public class AdvertisementController {

	@Autowired
	private AdvertisementRepository adsRepo;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/advertisements", method = RequestMethod.GET)
    public List<Advertisement> getAdvertisements() {
		List<Advertisement> advertisements = adsRepo.findAllByOrderBySequence();
	
		log.info("getAdvertisements returns {}", advertisements);
		return advertisements;
    }
	
	
	@RequestMapping(value = "/advertisements/{id}", method = RequestMethod.GET)
    public Advertisement getAdvertisementByID(@PathVariable Long id) {
		Advertisement advertisement = adsRepo.findOne(id);
	
		log.info("getAdvertisements returns {}", advertisement);
		return advertisement;
    }
}
