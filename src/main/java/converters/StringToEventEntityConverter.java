package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.EventEntityRepository;
import repositories.SchoolClassRepository;
import repositories.SchoolRepository;
import domain.EventEntity;


@Component
@Transactional
public class StringToEventEntityConverter implements Converter<String, EventEntity>{

	@Autowired
	EventEntityRepository evententityRepository;
	@Autowired
	SchoolRepository schoolRepository;
	@Autowired
	SchoolClassRepository schoolClassRepository;
	
	@Override
	public EventEntity convert(String text) {
		EventEntity result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = evententityRepository.findOne(id);
			if(result==null){
				result = schoolRepository.findOne(id);
			}
			if(result==null){
				result = schoolClassRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
