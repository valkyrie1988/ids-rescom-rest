package com.ids.rescom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ids.rescom.entities.announcement.Announcement;
import com.ids.rescom.repositories.AnnouncementRepository;

@RestController
public class AnnouncementController {

	@Autowired
	private AnnouncementRepository annoucementRepo;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/announcements", method = RequestMethod.GET)
    public List<Announcement> getAnnouncements() {
		List<Announcement> announcements = annoucementRepo.findAllByOrderBySequence();
	
		log.info("getAnnouncements returns {}", announcements);
		return announcements;
    }
}
