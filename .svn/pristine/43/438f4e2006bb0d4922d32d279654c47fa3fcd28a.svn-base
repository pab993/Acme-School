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
import domain.School;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SchoolServiceTest extends AbstractTest {

	@Autowired
	private SchoolService schoolService;
	
	protected void crearColegio(final String username, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			School obj = schoolService.create();
			obj.setDescription("description");
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearColegio() {
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
			this.crearColegio((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	
	protected void archivarColegio(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			schoolService.archivar(id);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverArchivarColegio() {
		final Object testingData[][] = {
			{
				"manager1", 14, null
			}, {
				"parent1", 14, IllegalArgumentException.class
			}, {
				null, 14, IllegalArgumentException.class
			}, {
				"admin", 14, IllegalArgumentException.class
			}, {
				"teacher1", 14, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.archivarColegio((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void desarchivarColegio(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			schoolService.desarchivar(id);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverDesarchivarColegio() {
		final Object testingData[][] = {
			{
				"manager1", 15, null
			}, {
				"parent1", 15, IllegalArgumentException.class
			}, {
				null, 15, IllegalArgumentException.class
			}, {
				"admin", 15, IllegalArgumentException.class
			}, {
				"teacher1", 15, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.desarchivarColegio((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void desactivarColegio(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			schoolService.cancelar(id);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverDesactivarColegio() {
		final Object testingData[][] = {
			{
				"manager1", 14, IllegalArgumentException.class
			}, {
				"parent1", 14, IllegalArgumentException.class
			}, {
				null, 14, IllegalArgumentException.class
			}, {
				"admin", 14, null
			}, {
				"teacher1", 14, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.desactivarColegio((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void activarColegio(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			schoolService.activar(id);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverActivarColegio() {
		final Object testingData[][] = {
			{
				"manager1", 16, IllegalArgumentException.class
			}, {
				"parent1", 16, IllegalArgumentException.class
			}, {
				null, 16, IllegalArgumentException.class
			}, {
				"admin", 16, null
			}, {
				"teacher1", 16, IllegalArgumentException.class
			}, {
				"manager1", 15, IllegalArgumentException.class
			}, {
				"parent1", 15, IllegalArgumentException.class
			}, {
				null, 15, IllegalArgumentException.class
			}, {
				"admin", 15, IllegalArgumentException.class
			}, {
				"teacher1", 15, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.activarColegio((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
}
