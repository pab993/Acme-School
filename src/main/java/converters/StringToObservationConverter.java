package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ObservationRepository;
import domain.Observation;


@Component
@Transactional
public class StringToObservationConverter implements Converter<String, Observation>{

	@Autowired
	ObservationRepository observationRepository;

	@Override
	public Observation convert(String text) {
		Observation result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = observationRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
