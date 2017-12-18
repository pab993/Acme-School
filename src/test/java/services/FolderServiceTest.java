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
import domain.Folder;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FolderServiceTest extends AbstractTest {

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FolderService folderService;
	
	protected void crearCarpeta(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Folder obj = folderService.create(actorService.findByPrincipal());
			obj.setDescription("Description");
			obj.setName("name");
			this.folderService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearCarpeta() {
		final Object testingData[][] = {
			{
				"manager1", 14, null
			}, {
				"parent1", 14, null
			}, {
				null, 14, IllegalArgumentException.class
			}, {
				"admin", 14, null
			}, {
				"teacher1", 14, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.crearCarpeta((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void editarCarpeta(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Folder obj = folderService.findOne(id);
			this.folderService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverEditarCarpeta() {
		final Object testingData[][] = {
			{
				"manager1", 22, IllegalArgumentException.class
			}, {
				"parent1", 22, IllegalArgumentException.class
			}, {
				null, 22, IllegalArgumentException.class
			}, {
				"admin", 22, null
			}, {
				"teacher1", 22, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.editarCarpeta((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void borrarCarpeta(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Folder obj = folderService.findOne(id);
			this.folderService.delete(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverBorrarCarpeta() {
		final Object testingData[][] = {
			{
				"manager1", 22, IllegalArgumentException.class
			}, {
				"parent1", 22, IllegalArgumentException.class
			}, {
				null, 22, IllegalArgumentException.class
			}, {
				"admin", 22, IllegalArgumentException.class
			}, {
				"teacher1", 22, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.borrarCarpeta((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
}
