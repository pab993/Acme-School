
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
public class Manager extends Actor {

	//Relationships
	private Collection<CreditCard> creditCards = new ArrayList<>();
	private Collection<Place> places = new ArrayList<>();
	private Collection<School> schools = new ArrayList<>();
	
	@Valid
	@OneToMany(mappedBy="manager", fetch=FetchType.LAZY)
	public Collection<CreditCard> getCreditCards() {
		return creditCards;
	}
	public void setCreditCards(Collection<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}
	
	@Valid
	@OneToMany(mappedBy="manager", fetch=FetchType.LAZY)
	public Collection<Place> getPlaces() {
		return places;
	}
	public void setPlaces(Collection<Place> places) {
		this.places = places;
	}
	
	@Valid
	@OneToMany(mappedBy="manager", fetch=FetchType.LAZY)
	public Collection<School> getSchools() {
		return schools;
	}
	public void setSchools(Collection<School> schools) {
		this.schools = schools;
	}
	
	
}
