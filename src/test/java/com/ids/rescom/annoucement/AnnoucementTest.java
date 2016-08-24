package com.ids.rescom.annoucement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
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
	
	@Test
	public void addAnnouncement() {
		for(int i=1; i<=5; i++) {
			Announcement announcement = new Announcement();
			DateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt = new Date();
			
			announcement.setTitle("Title " + i);
			announcement.setShortDesc("ShortDesc " + i);
			announcement.setLongDesc("LongDesc " + i);
			announcement.setType(0);
			announcement.setSequence(i);
			announcement.setStartDate(dt);
			announcement.setEndDate(dt);
			announcement.setCreateDate(dt);
			announcement.setLastUpdate(dt);
			announcement.setHref(null);
			announcement.setImportantLevel(0);
			announcement.setStatus(1);
			
			announceRepo.save(announcement);
		}
	}
}
