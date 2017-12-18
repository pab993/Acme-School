package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SpecialEventRepository;
import domain.SpecialEvent;


@Component
@Transactional
public class StringToSpecialEventConverter implements Converter<String, SpecialEvent>{

	@Autowired
	SpecialEventRepository specialeventRepository;

	@Override
	public SpecialEvent convert(String text) {
		SpecialEvent result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = specialeventRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
