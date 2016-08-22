package com.ids.rescom.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ids.rescom.entities.account.Account;
import com.ids.rescom.entities.account.Profile;
import com.ids.rescom.entities.account.Unit;
import com.ids.rescom.repositories.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {
	
	@Autowired AccountRepository accountRepo;
	
	@Test
	public void addAccount() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
//		EntityManager entitymanager = emfactory.createEntityManager( );
//		entitymanager.getTransaction( ).begin( );
		
		for(int i=1; i<=5; i++) {
			Account account = new Account();
			Profile profile = new Profile();
			
			profile.setName("User" + i);
			profile.setHpContact("0123456789" + i);
			profile.setDeviceId("xxx");
			
			Unit unit = new Unit();
			unit.setBlock("C");
			unit.setFloor(String.valueOf(i));
			unit.setNumber("10");
			
			account.setEmail("test" + i + "@gmail.com");
			account.setProfile(profile);
			account.setUnit(unit);
			
			accountRepo.save(account);
		}
	}


}
