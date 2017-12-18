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

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PetitionServiceTest extends AbstractTest {

	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private ChildService childService;
	
	@Autowired
	private PetitionService petitionService;
	
	protected void solicitarPeticion(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			petitionService.solicitar(schoolClassService.findOne(53), childService.findOne(21));
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverSolicitarPeticion() {
		final Object testingData[][] = {
			{
				"manager1", 14,  IllegalArgumentException.class
			}, {
				"parent1", 14, null
			}, {
				null, 14, IllegalArgumentException.class
			}, {
				"admin", 14, IllegalArgumentException.class
			}, {
				"teacher1", 14, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.solicitarPeticion((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void aceptarPeticion(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			petitionService.aceptar(62);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverAceptarPeticion() {
		final Object testingData[][] = {
			{
				"manager1", 14,  IllegalArgumentException.class
			}, {
				"parent1", 14, IllegalArgumentException.class
			}, {
				null, 14, IllegalArgumentException.class
			}, {
				"admin", 14, IllegalArgumentException.class
			}, {
				"teacher1", 14, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.aceptarPeticion((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void cancelarPeticion(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			petitionService.cancelar(62);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCancelarPeticion() {
		final Object testingData[][] = {
			{
				"manager1", 14,  IllegalArgumentException.class
			}, {
				"parent1", 14, IllegalArgumentException.class
			}, {
				null, 14, IllegalArgumentException.class
			}, {
				"admin", 14, IllegalArgumentException.class
			}, {
				"teacher1", 14, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.cancelarPeticion((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
}
