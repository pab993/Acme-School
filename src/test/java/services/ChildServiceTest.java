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

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Child;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChildServiceTest extends AbstractTest {

	@Autowired
	private ChildService childService;
	
	protected void crearChild(final String username, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Child obj = childService.create();
			obj.setBirthdate(new Date((new Date().getTime()-100000)));
			obj.setName("name");
			obj.setSurname("surname");
			this.childService.saveNew(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearChild() {
		final Object testingData[][] = {
			{
				"parent1", null
			}, {
				"manager1", IllegalArgumentException.class
			}, {
				null, IllegalArgumentException.class
			}, {
				"admin", IllegalArgumentException.class
			}, {
				"teacher1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.crearChild((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void editarChild(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Child obj = childService.findOne(id);
			this.childService.edit(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverEditarChild() {
		final Object testingData[][] = {
			{
				"parent1", 19, null
			}, {
				"manager2", 19, IllegalArgumentException.class
			}, {
				"manager1", 19, IllegalArgumentException.class
			}, {
				null, 19, IllegalArgumentException.class
			}, {
				"admin", 19, IllegalArgumentException.class
			}, {
				"teacher1", 19, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.editarChild((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void observacionesVistasChild(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Child obj = childService.findOne(id);
			this.childService.observacionesVistas(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverObservacionesVistasChild() {
		final Object testingData[][] = {
			{
				"parent1", 19, null
			}, {
				"manager2", 19, IllegalArgumentException.class
			}, {
				"manager1", 19, IllegalArgumentException.class
			}, {
				null, 19, IllegalArgumentException.class
			}, {
				"admin", 19, IllegalArgumentException.class
			}, {
				"teacher1", 19, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.observacionesVistasChild((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
