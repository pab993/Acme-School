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
import domain.YearSchool;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class YearSchoolServiceTest extends AbstractTest {

	@Autowired
	private YearSchoolService yearSchoolService;
	
	protected void crearAnnoEscolar(final String username, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			YearSchool obj = yearSchoolService.create();
			obj.setAnne("2017/2018");
			this.yearSchoolService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearAnnoEscolar() {
		final Object testingData[][] = {
			{
				"manager1", IllegalArgumentException.class
			}, {
				"parent1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}, {
				"admin", null
			}, {
				"teacher1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.crearAnnoEscolar((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	
	protected void activarAnnoEscholar(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			yearSchoolService.activar(id);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverActivarAnnoEscholar() {
		final Object testingData[][] = {
			{
				"teacher1", 7, IllegalArgumentException.class
			}, {
				"parent1", 7, IllegalArgumentException.class
			}, {
				null, 7, IllegalArgumentException.class
			}, {
				"admin", 7, null
			}, {
				"manager1", 7, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.activarAnnoEscholar((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
}
