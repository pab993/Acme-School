package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Board;

@Component
@Transactional
public class BoardToStringConverter implements Converter<Board, String>{

	@Override
	public String convert(Board entity) {
		
		String result;
		if (entity == null)
			result = null;
		else
			result = String.valueOf(entity.getId());
	
		return result;
	}
}