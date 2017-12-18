package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class SchoolClass extends EventEntity{

	private String name;
	private String description;
	private Boolean isArchive = false;
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@Size(min=50,max=5000)
	@Column(length=5000)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getIsArchive() {
		return isArchive;
	}
	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}
	
	//Relationships
	// =================================================================
	
	private School school;
	private YearSchool yearSchool;
	private Collection<Teacher> teacher = new ArrayList<>();
	private Collection<Petition> petitions = new ArrayList<>();
	
	@Valid
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public YearSchool getYearSchool() {
		return yearSchool;
	}
	public void setYearSchool(YearSchool yearSchool) {
		this.yearSchool = yearSchool;
	}
	
	@Valid
	@ManyToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY)
	public Collection<Teacher> getTeacher() {
		return teacher;
	}
	public void setTeacher(Collection<Teacher> teacher) {
		this.teacher = teacher;
	}
	
	@Valid
	@OneToMany(mappedBy = "schoolClass")
	public Collection<Petition> getPetitions() {
		return petitions;
	}
	public void setPetitions(Collection<Petition> petitions) {
		this.petitions = petitions;
	}
	
	@Transient
	public Collection<Petition> getPetitionsAccepte(){
		Collection<Petition> petitions = new ArrayList<>();
		for(Petition p : getPetitions()){
			if(p.getState().equals(StatePetition.ACCEPTE)){
				petitions.add(p);
			}
		}
		
		return petitions;
	}
	
	@Transient
	public Collection<Petition> getPetitionsWait(){
		Collection<Petition> petitions = new ArrayList<>();
		for(Petition p : getPetitions()){
			if(p.getState().equals(StatePetition.WAIT)){
				petitions.add(p);
			}
		}
		
		return petitions;
	}
	
}
