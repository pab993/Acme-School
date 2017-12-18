package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Parent;

@Component
@Transactional
public class ParentToStringConverter implements Converter<Parent, String>{

	@Override
	public String convert(Parent entity) {
		
		String result;
		if (entity == null)
			result = null;
		else
			result = String.valueOf(entity.getId());
	
		return result;
	}
}