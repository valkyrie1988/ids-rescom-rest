package com.ids.rescom.controller;

import java.util.Date;
import java.util.List;

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
	
	@RequestMapping(value = "/appointment/create/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> createAppointment(
			@PathVariable Long id,
			@RequestParam String name,
			@RequestParam String icNo,
			@RequestParam String carPlate,
			@RequestParam Date visitDt, 
			@RequestParam int type,
			@RequestParam String description
			) {
		
		log.info("[APPOINTMENT_CREATE] - id:{}, name:{} , icNo: {}, carPlate : {}, visitDt : {}, description : {} ", id, name, icNo, carPlate, visitDt, description);
		
		try {
			Account account = accountRepo.findById(id);
			Visitor visitor = new Visitor();
			Appointment app = new Appointment();
			Date dt = new Date();
			
			if(account == null){
				log.error("ACCOUNT_NOT_FOUND");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ACCOUNT_NOT_FOUND");
			} else {
				visitor.setName(name);
				visitor.setIcNo(icNo);
				visitor.setCarPlate(carPlate);
				
				app.setVisitor(visitor);
				app.setVisitDt(visitDt);
				app.setType(type);
				app.setDescription(description);
				app.setCreatedDt(dt);
				app.setCreatedBy(id);
				app.setStatus(1);
				
				appRepo.save(app);
				return ResponseEntity.ok("success");
			}
		} catch (Exception e) {
			log.error("Failed to create schedule for appointment.",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("APPOINTMENT_CREATE_FAILED");
		}
	}
	
	@RequestMapping(value = "/appointment/{id}", method = RequestMethod.GET)
	public List<Appointment> getAppointmentList(@PathVariable Long id) {
		log.info("[APPOINTMENT_LIST] - id:{}", id);
		List<Appointment> appointmentObj = appRepo.findByCreatedByAndStatusOrderByVisitDtDesc(id, 0);
		
		log.info("getAccountInfoById returns {}", appointmentObj);
		return appointmentObj;
	}
	
	@RequestMapping(value = "/appointment/update/{id}/{appointmentId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateAppointment(
			@PathVariable Long id, 
			@PathVariable Long appId,
			@RequestParam Date visitDt,
			@RequestParam String name,
			@RequestParam String icNo,
			@RequestParam String carPlate) {
		log.info("[APPOINTMENT_UPDATE] - id:{} , appointmentId:{} , visitDt:{} , name:{} , icNo:{} , carPlate:{}", id, appId, visitDt, name, icNo, carPlate);
		try {
			Account account = accountRepo.findById(id);
			
			if(account == null) {
				log.error("ACCOUNT_NOT_FOUND");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ACCOUNT_NOT_FOUND");
			} else {
				Appointment app = appRepo.findByIdAndStatus(appId, 0);
				
				if(app == null) {
					log.error("APPOINTMENT_NOT_FOUND");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("APPOINTMENT_NOT_FOUND");
				} else {
					app.setVisitDt(visitDt);
					app.getVisitor().setName(name);
					app.getVisitor().setIcNo(icNo);
					app.getVisitor().setCarPlate(carPlate);
					
					appRepo.save(app);
				}
			}
			
			return ResponseEntity.ok("success");
		} catch(Exception e) {
			log.error("Failed to update schedule for appointment.", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("APPOINTMENT_UPDATE_FAILED");
		}
	}
	
	@RequestMapping(value = "/appointment/cancel/{id}/{appointmentId}", method = RequestMethod.PUT)
	public ResponseEntity<String> cancelAppointment(@PathVariable Long id, @PathVariable Long appId) {
		log.info("[APPOINTMENT_CANCEL] - id:{} , appointmentId:{}", id, appId);
		try {
			Account account = accountRepo.findById(id);
			
			if(account == null) {
				log.error("ACCOUNT_NOT_FOUND");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ACCOUNT_NOT_FOUND");
			} else {
				Appointment app = appRepo.findByIdAndStatus(appId, 0);
				
				if(app == null) {
					log.error("APPOINTMENT_NOT_FOUND");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("APPOINTMENT_NOT_FOUND");
				} else {
					app.setStatus(-1);
					appRepo.save(app);
				}
			}
			
			return ResponseEntity.ok("success");
		} catch(Exception e) {
			log.error("Failed to cancel schedule for appointment.", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("APPOINTMENT_CANCEL_FAILED");
		}
	}
}
