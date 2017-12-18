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
import domain.Comment;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentServiceTest extends AbstractTest {

	@Autowired
	private CommentService commentService;
	
	protected void crearComentario(final String username, int id, final Class<?> expected){
		Class<?> error = null;
		try {
			super.authenticate(username);
			Comment obj = commentService.create(id);
			obj.setCreateMoment(new Date((new Date().getTime()-100000)));
			obj.setStars(5);
			obj.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			obj.setTitle("aaaaaaaaaa");
			this.commentService.save(obj);
			super.unauthenticate();
		} catch (final Throwable oops) {
			error = oops.getClass();
		}
		super.checkExceptions(expected,error);
	}
	
	@Test
	public void driverCrearComentario() {
		final Object testingData[][] = {
			{
				"parent1", 14, null
			}, {
				"manager1", 14, null
			}, {
				null, 14, IllegalArgumentException.class
			}, {
				"admin", 14, null
			}, {
				"teacher1", 14, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.crearComentario((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}