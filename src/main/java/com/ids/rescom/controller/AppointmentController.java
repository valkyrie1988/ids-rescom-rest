package com.ids.rescom.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ids.rescom.entities.account.Account;
import com.ids.rescom.entities.account.Visitor;
import com.ids.rescom.entities.appointment.Appointment;
import com.ids.rescom.repositories.AccountRepository;
import com.ids.rescom.repositories.AppointmentRepository;

@RestController
public class AppointmentController {

	@Autowired 
	private AppointmentRepository appRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/appointment/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> createAppointment(
			@PathVariable Long id,
			@RequestParam String name,
			@RequestParam String icNo,
			@RequestParam String carPlate,
			@RequestParam Date visitDt, 
			@RequestParam int type,
			@RequestParam String description
			) {
		
		log.info("id:{}, name:{} , icNo: {}, carPlate : {}, visitDt : {}, description : {} ", id, name, icNo, carPlate, visitDt, description);
		
		try {
			log.info("123321");
			Account account = accountRepo.findById(id);
			log.info("234432");
			Visitor visitor = new Visitor();
			Appointment app = new Appointment();
			Date dt = new Date();
			
			if(account == null){
				log.info("ACCOUNT_NOT_FOUND");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ACCOUNT_NOT_FOUND");
			} else {
				log.info("ACCOUNT_FOUND");
				visitor.setName(name);
				visitor.setIcNo(icNo);
				visitor.setCarPlate(carPlate);
				
				log.info("name:{}, icNo:{}, carPlate:{}", name, icNo, carPlate);
				
				app.setVisitor(visitor);
				app.setVisitDt(visitDt);
				app.setType(type);
				app.setDescription(description);
				app.setCreatedDt(dt);
				app.setCreatedBy(id);
				app.setStatus(0);
				
				log.info("visitDt:{}, type:{}, desc:{}, createdDt:{}, createdBy:{}", visitDt, type, description, dt, id);
				
				appRepo.save(app);
				return ResponseEntity.ok("success");
			}
		} catch (Exception e) {
			log.error("Failed to create schedule for appointment.",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UNABLE_TO_UPDATE");
		}
	}
}
