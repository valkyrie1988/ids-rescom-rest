package com.ids.rescom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
}
