package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.YearSchoolRepository;
import domain.YearSchool;


@Component
@Transactional
public class StringToYearSchoolConverter implements Converter<String, YearSchool>{

	@Autowired
	YearSchoolRepository yearschoolRepository;

	@Override
	public YearSchool convert(String text) {
		YearSchool result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = yearschoolRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
