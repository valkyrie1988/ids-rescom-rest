package com.ids.rescom.appointment;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ids.rescom.entities.account.Visitor;
import com.ids.rescom.entities.appointment.Appointment;
import com.ids.rescom.repositories.AppointmentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentTest {

	@Autowired AppointmentRepository appRepo;
	
	@Test
	public void addAppointment() {
		for(int i=1; i<=5; i++) {
			Visitor visitor = new Visitor();
			Appointment app = new Appointment();
			Date dt = new Date();
			
			visitor.setName("xxx");
			visitor.setIcNo("881001235067");
			visitor.setCarPlate("JQR 9394");
			
			app.setVisitor(visitor);
			app.setVisitDt(dt);
			app.setType(0);
			app.setDescription("description");
			app.setCreatedDt(dt);
			app.setCreatedBy(i);
			app.setStatus(0);
			
			appRepo.save(app);
		}
	}
}
