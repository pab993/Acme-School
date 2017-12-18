package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SchoolClassRepository;
import domain.SchoolClass;


@Component
@Transactional
public class StringToSchoolClassConverter implements Converter<String, SchoolClass>{

	@Autowired
	SchoolClassRepository schoolclassRepository;

	@Override
	public SchoolClass convert(String text) {
		SchoolClass result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = schoolclassRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
