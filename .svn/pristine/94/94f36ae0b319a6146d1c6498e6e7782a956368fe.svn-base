package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class YearSchool extends DomainEntity{

	private String anne;
	private Boolean isActive = false;
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "^\\d{4}[/]\\d{4}$")
	public String getAnne() {
		return anne;
	}
	public void setAnne(String anne) {
		this.anne = anne;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	//Relationships
	// =================================================================
	
	private Collection<SchoolClass> schoolClass = new ArrayList<>();
	
	@Valid
	@OneToMany(mappedBy = "yearSchool")
	public Collection<SchoolClass> getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(Collection<SchoolClass> schoolClass) {
		this.schoolClass = schoolClass;
	}
	
}
