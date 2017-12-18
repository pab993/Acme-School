package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ChildRepository;
import domain.Child;


@Component
@Transactional
public class StringToChildConverter implements Converter<String, Child>{

	@Autowired
	ChildRepository childRepository;

	@Override
	public Child convert(String text) {
		Child result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = childRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
