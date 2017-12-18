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
import domain.SchoolClass;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SchoolClassServiceTest extends AbstractTest {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	protected void crearSchoolClass(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			SchoolClass obj = schoolClassService.create(id);
			obj.setName("aaaaaaaa");
			obj.setDescription("aaaaaaaaaaaaaaaaaa");
			this.schoolClassService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearSchoolClass() {
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
			this.crearSchoolClass((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void archivarSchoolClass(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			schoolClassService.archivar(id);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverArchivarSchoolClass() {
		final Object testingData[][] = {
			{
				"manager1", 53, null
			}, {
				"parent1", 53, IllegalArgumentException.class
			}, {
				null, 53, IllegalArgumentException.class
			}, {
				"admin", 53, IllegalArgumentException.class
			}, {
				"teacher1", 53, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.archivarSchoolClass((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void desarchivarSchoolClass(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			schoolClassService.desarchivar(id);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverDesarchivarSchoolClass() {
		final Object testingData[][] = {
			{
				"manager1", 54, null
			}, {
				"parent1", 54, IllegalArgumentException.class
			}, {
				null, 54, IllegalArgumentException.class
			}, {
				"admin", 54, IllegalArgumentException.class
			}, {
				"teacher1", 54, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.desarchivarSchoolClass((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void desasignarProfesorASchoolClass(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			schoolClassService.desasignar(53, 17);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverDesasignarProfesorASchoolClass() {
		final Object testingData[][] = {
			{
				"manager1", 54, null
			}, {
				"parent1", 54, IllegalArgumentException.class
			}, {
				null, 54, IllegalArgumentException.class
			}, {
				"admin", 54, IllegalArgumentException.class
			}, {
				"teacher1", 54, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.desasignarProfesorASchoolClass((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void asignarProfesorASchoolClass(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			schoolClassService.desasignar(53, 17);
			schoolClassService.asignar(schoolClassService.findOne(53), teacherService.findOne(17));
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverAsignarProfesorASchoolClass() {
		final Object testingData[][] = {
			{
				"manager1", 54, null
			}, {
				"parent1", 54, IllegalArgumentException.class
			}, {
				null, 54, IllegalArgumentException.class
			}, {
				"admin", 54, IllegalArgumentException.class
			}, {
				"teacher1", 54, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.asignarProfesorASchoolClass((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
