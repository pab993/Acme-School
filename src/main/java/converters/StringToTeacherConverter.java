package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.TeacherRepository;
import domain.Teacher;


@Component
@Transactional
public class StringToTeacherConverter implements Converter<String, Teacher>{

	@Autowired
	TeacherRepository teacherRepository;

	@Override
	public Teacher convert(String text) {
		Teacher result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = teacherRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
