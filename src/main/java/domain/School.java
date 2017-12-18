package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

import domain.dateType.Url;

@Entity
@Access(AccessType.PROPERTY)
public class School extends EventEntity{
	
	private String name;
	private String description;
	private String address;
	private String profileImg;
	private Collection<Url> galery = new ArrayList<>();
	private String video;
	private Boolean isArchive = false;
	private Boolean isCancel = false;
	private String descriptionCancel;
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotBlank
	@URL
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	@Valid
	@ElementCollection
	@Column(name = "galery")
	@JoinColumn(name = "id")
	public Collection<Url> getGalery() {
		return galery;
	}
	public void setGalery(Collection<Url> galery) {
		this.galery = galery;
	}
	
	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	
	@NotNull
	public Boolean getIsArchive() {
		return isArchive;
	}
	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}
	
	@NotNull
	public Boolean getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}
	
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescriptionCancel() {
		return descriptionCancel;
	}
	public void setDescriptionCancel(String descriptionCancel) {
		this.descriptionCancel = descriptionCancel;
	}
	
	//Relationships
	// =================================================================
	
	private Manager manager;
	private Collection<Teacher> teachers = new ArrayList<>();
	private Collection<SchoolClass> schoolClasses = new ArrayList<>();
	private Collection<Comment> comments = new ArrayList<>();
	
	@Valid
	@ManyToOne(optional = false)
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	@Valid
	@OneToMany(mappedBy="school", fetch=FetchType.LAZY)
	public Collection<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Collection<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	@Valid
	@OneToMany(mappedBy="school", fetch=FetchType.LAZY)
	public Collection<SchoolClass> getSchoolClasses() {
		return schoolClasses;
	}
	public void setSchoolClasses(Collection<SchoolClass> schoolClasses) {
		this.schoolClasses = schoolClasses;
	}
	
	@Valid
	@OneToMany(mappedBy="school")
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

}
