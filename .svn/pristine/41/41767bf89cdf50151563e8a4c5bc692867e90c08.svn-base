package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Place extends DomainEntity{

	private String name;
	private String address;
	private Integer spaceMax;
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotNull
	@Min(0)
	public Integer getSpaceMax() {
		return spaceMax;
	}
	public void setSpaceMax(Integer spaceMax) {
		this.spaceMax = spaceMax;
	}
	
	private Collection<SpecialEvent> specialEvents = new ArrayList<>();
	private Manager manager;
	
	@Valid
	@OneToMany(mappedBy="place", fetch=FetchType.LAZY)
	public Collection<SpecialEvent> getSpecialEvents() {
		return specialEvents;
	}
	public void setSpecialEvents(Collection<SpecialEvent> specialEvents) {
		this.specialEvents = specialEvents;
	}
	
	@Valid
	@ManyToOne
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
}
