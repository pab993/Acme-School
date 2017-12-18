package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SpecialEvent;

@Component
@Transactional
public class SpecialEventToStringConverter implements Converter<SpecialEvent, String>{

	@Override
	public String convert(SpecialEvent entity) {
		
		String result;
		if (entity == null)
			result = null;
		else
			result = String.valueOf(entity.getId());
	
		return result;
	}
}