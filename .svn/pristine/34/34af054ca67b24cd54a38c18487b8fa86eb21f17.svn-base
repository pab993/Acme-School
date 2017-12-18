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
import domain.PriorityEvent;
import domain.SpecialEvent;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SpecialEventServiceTest extends AbstractTest {

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private SpecialEventService specialEventService;
	
	@Autowired
	private PlaceService placeService;
	
	protected void crearSpecialEventColegio(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			SpecialEvent obj = specialEventService.create(this.schoolService.findOne(id));
			obj.setDateFin(new Date((new Date().getTime()+100000)));
			obj.setDateInit(new Date((new Date().getTime()+10000)));
			obj.setPlace(placeService.findOne(42));
			obj.setPriority(PriorityEvent.IMPORTANT);
			obj.setSpace(20);
			obj.setTitle("title");
			obj.setDescription("description");
			this.specialEventService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearSpecialEventColegio() {
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
			this.crearSpecialEventColegio((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void crearSpecialEventClase(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			SpecialEvent obj = specialEventService.create(this.schoolClassService.findOne(id));
			obj.setDateFin(new Date((new Date().getTime()+100000)));
			obj.setDateInit(new Date((new Date().getTime()+10000)));
			obj.setPlace(placeService.findOne(42));
			obj.setPriority(PriorityEvent.IMPORTANT);
			obj.setSpace(20);
			obj.setTitle("title");
			obj.setDescription("description");
			this.specialEventService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearSpecialEventClase() {
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
			this.crearSpecialEventClase((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void borrarSpecialEventColegio(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			SpecialEvent obj = specialEventService.findOne(id);
			this.specialEventService.delete(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverBorrarSpecialEventColegio() {
		final Object testingData[][] = {
			{
				"manager1", 45, null
			}, {
				"manager2", 45, IllegalArgumentException.class
			}, {
				"parent1", 45, IllegalArgumentException.class
			}, {
				null, 45, IllegalArgumentException.class
			}, {
				"admin", 45, IllegalArgumentException.class
			}, {
				"teacher1", 45, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.borrarSpecialEventColegio((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void borrarSpecialEventClase(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			SpecialEvent obj = specialEventService.findOne(id);
			this.specialEventService.delete(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverBorrarSpecialEventClase() {
		final Object testingData[][] = {
			{
				"teacher1", 47, null
			}, {
				"parent1", 47, IllegalArgumentException.class
			}, {
				null, 47, IllegalArgumentException.class
			}, {
				"admin", 47, IllegalArgumentException.class
			}, {
				"manager1", 47, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.borrarSpecialEventClase((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void inscribirseEnSpecialEvent(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			specialEventService.inscribirse(19, id);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverInscribirseEnSpecialEvent() {
		final Object testingData[][] = {
			{
				"teacher1", 46, IllegalArgumentException.class
			}, {
				"parent1", 46, null
			}, {
				null, 46, IllegalArgumentException.class
			}, {
				"admin", 46, IllegalArgumentException.class
			}, {
				"manager1", 46, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.inscribirseEnSpecialEvent((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
}
