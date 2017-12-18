package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public abstract class EventEntity extends DomainEntity{

	private Collection<SpecialEvent> specialEvent = new ArrayList<>();
	private Collection<Board> board = new ArrayList<>();
	
	@Valid
	@OneToMany(mappedBy="eventEntity", fetch=FetchType.LAZY)
	public Collection<SpecialEvent> getSpecialEvent() {
		return specialEvent;
	}

	public void setSpecialEvent(Collection<SpecialEvent> specialEvent) {
		this.specialEvent = specialEvent;
	}

	@Valid
	@OneToMany(mappedBy="eventEntity", fetch=FetchType.LAZY)
	public Collection<Board> getBoard() {
		return board;
	}

	public void setBoard(Collection<Board> board) {
		this.board = board;
	}
	
}
