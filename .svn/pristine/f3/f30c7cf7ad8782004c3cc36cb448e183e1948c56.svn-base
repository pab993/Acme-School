package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ParentRepository;
import domain.Parent;


@Component
@Transactional
public class StringToParentConverter implements Converter<String, Parent>{

	@Autowired
	ParentRepository parentRepository;

	@Override
	public Parent convert(String text) {
		Parent result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = parentRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
