package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.YearSchool;

@Component
@Transactional
public class YearSchoolToStringConverter implements Converter<YearSchool, String>{

	@Override
	public String convert(YearSchool entity) {
		
		String result;
		if (entity == null)
			result = null;
		else
			result = String.valueOf(entity.getId());
	
		return result;
	}
}