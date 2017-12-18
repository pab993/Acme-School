package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Observation extends DomainEntity{
	
	private String titule;
	private String description;
	private Date dateCreation;
	private Date dateVisualization;
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Size(min = 5, max = 50)
	@Column(length=50)
	public String getTitule() {
		return titule;
	}
	public void setTitule(String titule) {
		this.titule = titule;
	}
	
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Size(min=5,max=1500)
	@Column(length=1500)
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDateVisualization() {
		return dateVisualization;
	}
	public void setDateVisualization(Date dateVisualization) {
		this.dateVisualization = dateVisualization;
	}
	
	//Relationships
	private Teacher teacher;
	private Child child;
	private SchoolClass schoolClass;

	@Valid
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@Valid
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	
	@Valid
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	public SchoolClass getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}
	
	
	
}
