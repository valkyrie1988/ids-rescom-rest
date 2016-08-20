package com.ids.rescom.annoucement;

import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ids.rescom.entities.announcement.Announcement;
import com.ids.rescom.repositories.AnnouncementRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnoucementTest {

	@Autowired AnnouncementRepository announceRepo;
	
	public void addAnnouncement() {
		for(int i=1; i<=5; i++) {
			Announcement announcement = new Announcement();
			
			announcement.setTitle("Title " + i);
			announcement.setDescription("Description " + i);
			announcement.setType(0);
			announcement.setSequence(i);
			announcement.setStartDate(new Date());
			announcement.setEndDate(new Date());
			announcement.setCreateDate(new Date());
			announcement.setLastUpdate(new Date());
			announcement.setHref(null);
			announcement.setImportantLevel(0);
			
			announceRepo.save(announcement);
		}
	}
}
