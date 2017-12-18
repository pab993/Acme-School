package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Petition extends DomainEntity{

	private Date moment;
	private StatePetition state;
	private Date momentModifState;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	public StatePetition getState() {
		return state;
	}
	public void setState(StatePetition state) {
		this.state = state;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMomentModifState() {
		return momentModifState;
	}
	public void setMomentModifState(Date momentModifState) {
		this.momentModifState = momentModifState;
	}
	
	//Relationships
	// =================================================================
	private Child child;
	private SchoolClass schoolClass;

	@Valid
	@ManyToOne(optional = false)
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public SchoolClass getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}
	
	
	
}
