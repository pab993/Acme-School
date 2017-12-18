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
import domain.Observation;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ObservationServiceTest extends AbstractTest {

	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private ObservationService observationService;
	
	@Autowired
	private ChildService childService;
	
	protected void crearObservacion(final String username, final int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Observation obj = observationService.create(childService.findOne(19),schoolClassService.findOne(53));
			obj.setDescription("ddddddddddddddd");
			obj.setTitule("sssssss");
			this.observationService.add(schoolClassService.findOne(53),obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearObservacion() {
		final Object testingData[][] = {
			{
				"manager1", 14,  IllegalArgumentException.class
			}, {
				"parent1", 14,  IllegalArgumentException.class
			}, {
				null, 14, IllegalArgumentException.class
			}, {
				"admin", 14, IllegalArgumentException.class
			}, {
				"teacher1", 14, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.crearObservacion((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
}
