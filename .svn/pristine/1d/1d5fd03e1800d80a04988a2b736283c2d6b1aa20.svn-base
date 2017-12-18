package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PetitionRepository;
import domain.Petition;


@Component
@Transactional
public class StringToPetitionConverter implements Converter<String, Petition>{

	@Autowired
	PetitionRepository petitionRepository;

	@Override
	public Petition convert(String text) {
		Petition result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = petitionRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
