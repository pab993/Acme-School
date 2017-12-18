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
import domain.Board;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BoardServiceTest extends AbstractTest {

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private BoardService boardService;
	
	protected void crearAnuncioColegio(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Board obj = boardService.create(this.schoolService.findOne(id));
			obj.setDateFinValidation(new Date((new Date().getTime()+100000)));
			obj.setDescription("description");
			this.boardService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearAnuncioColegio() {
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
			this.crearAnuncioColegio((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void crearAnuncioClase(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Board obj = boardService.create(this.schoolClassService.findOne(id));
			obj.setDateFinValidation(new Date((new Date().getTime()+100000)));
			obj.setDescription("description");
			this.boardService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearAnuncioClase() {
		final Object testingData[][] = {
			{
				"teacher1", 53, null
			}, {
				"parent1", 53, IllegalArgumentException.class
			}, {
				null, 53, IllegalArgumentException.class
			}, {
				"admin", 53, IllegalArgumentException.class
			}, {
				"manager1", 53, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.crearAnuncioClase((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void borrarAnuncioColegio(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Board obj = boardService.findOne(id);
			this.boardService.delete(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverBorrarAnuncioColegio() {
		final Object testingData[][] = {
			{
				"manager1", 49, null
			}, {
				"manager2", 49, IllegalArgumentException.class
			}, {
				"parent1", 49, IllegalArgumentException.class
			}, {
				null, 49, IllegalArgumentException.class
			}, {
				"admin", 49, IllegalArgumentException.class
			}, {
				"teacher1", 49, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.borrarAnuncioColegio((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void borrarAnuncioClase(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Board obj = boardService.findOne(id);
			this.boardService.delete(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverBorrarAnuncioClase() {
		final Object testingData[][] = {
			{
				"teacher1", 51, null
			}, {
				"parent1", 51, IllegalArgumentException.class
			}, {
				null, 51, IllegalArgumentException.class
			}, {
				"admin", 51, IllegalArgumentException.class
			}, {
				"manager1", 51, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.borrarAnuncioClase((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
}
