package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Teacher;

@Component
@Transactional
public class TeacherToStringConverter implements Converter<Teacher, String>{

	@Override
	public String convert(Teacher entity) {
		
		String result;
		if (entity == null)
			result = null;
		else
			result = String.valueOf(entity.getId());
	
		return result;
	}
}