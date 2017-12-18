package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Child extends DomainEntity{

	private String name;
	private String surname;
	private Date birthdate;
	
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSurname() {
		return this.surname;
	}
	public void setSurname(final String surname) {
		this.surname = surname;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	//Relationships
	// =================================================================
	private Parent parent;
	private Collection<Observation> observations = new ArrayList<>();
	private Collection<Petition> petitions = new ArrayList<>();
	private Collection<SpecialEvent> specialEvent=new ArrayList<>();

	@Valid
	@ManyToOne(optional = false)
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	
	@Valid
	@OneToMany(mappedBy = "child")
	public Collection<Observation> getObservations() {
		return observations;
	}
	public void setObservations(Collection<Observation> observations) {
		this.observations = observations;
	}
	
	@Valid
	@OneToMany(mappedBy = "child")
	public Collection<Petition> getPetitions() {
		return petitions;
	}
	public void setPetitions(Collection<Petition> petitions) {
		this.petitions = petitions;
	}
	
	@Valid
	@ManyToMany(mappedBy="child", fetch=FetchType.LAZY)
	public Collection<SpecialEvent> getSpecialEvent() {
		return specialEvent;
	}
	public void setSpecialEvent(Collection<SpecialEvent> specialEvent) {
		this.specialEvent = specialEvent;
	}
	
	
	
}
