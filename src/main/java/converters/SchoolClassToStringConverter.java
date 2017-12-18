package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SchoolClass;

@Component
@Transactional
public class SchoolClassToStringConverter implements Converter<SchoolClass, String>{

	@Override
	public String convert(SchoolClass entity) {
		
		String result;
		if (entity == null)
			result = null;
		else
			result = String.valueOf(entity.getId());
	
		return result;
	}
}