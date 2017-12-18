package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Place;

@Component
@Transactional
public class PlaceToStringConverter implements Converter<Place, String>{

	@Override
	public String convert(Place entity) {
		
		String result;
		if (entity == null)
			result = null;
		else
			result = String.valueOf(entity.getId());
	
		return result;
	}
}