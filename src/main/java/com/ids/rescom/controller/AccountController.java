package com.ids.rescom.controller;

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
import com.ids.rescom.repositories.AccountRepository;

@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepo;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public Account getAccountInfoById(@PathVariable Long id) {
		Account accountObj = accountRepo.findById(id);
	
		log.info("getAccountInfoById returns {}", accountObj);
		return accountObj;
    }
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateAccount(
			@PathVariable Long id,
			@RequestParam String name, 
			@RequestParam String email
			) {
		
		log.info("id:{}, name:{} , email: {}", id, name, email);
		
		try {
			Account account = accountRepo.findById(id);
			
			if(account == null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ACCOUNT_NOT_FOUND");
			} else {
				account.getProfile().setName(name);
				account.setEmail(email);
				
				accountRepo.save(account);
				return ResponseEntity.ok("success");
			}
		} catch (Exception e) {
			log.error("Failed to update account.",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UNABLE_TO_UPDATE");
		}
	}
}
