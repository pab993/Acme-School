/*
 * SampleTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.CreditCard;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CreditCardServiceTest extends AbstractTest {

	@Autowired
	private CreditCardService creditCardService;
	
	protected void crearCreditCard(final String username, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			CreditCard obj = creditCardService.create();
			obj.setHolderName("holderName");
			obj.setBrandName("Visa");
			obj.setNumber("5228926684479919");
			obj.setExpirationMonth(11);
			obj.setExpirationYear(2020);
			obj.setCvv("455");
			this.creditCardService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void drivercrearCreditCard() {
		final Object testingData[][] = {
			{
				"manager1", null
			}, {
				"parent1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}, {
				"admin", IllegalArgumentException.class
			}, {
				"teacher1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.crearCreditCard((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	
	protected void borrarCreditCard(final String username, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			CreditCard obj = creditCardService.findOne(12);
			this.creditCardService.delete(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverborrarCreditCard() {
		final Object testingData[][] = {
			{
				"manager1", null
			}, {
				"manager2", IllegalArgumentException.class
			}, {
				"parent1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}, {
				"admin", IllegalArgumentException.class
			}, {
				"teacher1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.borrarCreditCard((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

}
