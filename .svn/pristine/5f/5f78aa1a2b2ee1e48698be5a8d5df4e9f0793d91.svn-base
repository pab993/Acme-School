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
import domain.Message;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MessageServiceTest extends AbstractTest {

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private MessageService messageService;
	
	protected void crearMensaje(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Message obj = messageService.create();
			obj.setBody("ddddddddddddddd");
			obj.setFolder(folderService.findOne(id));
			obj.setMoment(new Date((new Date()).getTime()-100000));
			obj.setSubject("aaaa");
			this.messageService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearMensaje() {
		final Object testingData[][] = {
			{
				"manager1", 14,  null
			}, {
				"parent1", 14,  null
			}, {
				null, 14, IllegalArgumentException.class
			}, {
				"admin", 14, null
			}, {
				"teacher1", 14, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.crearMensaje((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
}
