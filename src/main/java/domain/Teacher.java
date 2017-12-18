
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Teacher extends Actor {

	private Boolean isArchive = false;
	
	public Boolean getIsArchive() {
		return isArchive;
	}
	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}
	
	//Relationships
	// =======================================================
	
	private School school;
	private Collection<SchoolClass> schoolClass= new ArrayList<>();

	@Valid
	@ManyToOne(optional = false)
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	public Collection<SchoolClass> getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(Collection<SchoolClass> schoolClass) {
		this.schoolClass = schoolClass;
	}
	
}
