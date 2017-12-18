package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SchoolRepository;
import domain.School;


@Component
@Transactional
public class StringToSchoolConverter implements Converter<String, School>{

	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public School convert(String text) {
		School result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = schoolRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
