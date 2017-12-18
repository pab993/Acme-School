package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.BoardRepository;
import domain.Board;


@Component
@Transactional
public class StringToBoardConverter implements Converter<String, Board>{

	@Autowired
	BoardRepository boardRepository;

	@Override
	public Board convert(String text) {
		Board result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = boardRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
