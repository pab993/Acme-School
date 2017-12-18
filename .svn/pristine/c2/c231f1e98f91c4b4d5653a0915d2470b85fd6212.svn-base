package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class SpecialEvent extends DomainEntity{

	private Date dateInit;
	private Date dateFin;
	private String title;
	private String description;
	private Boolean isCancel=false;
	private PriorityEvent priority;
	private Integer space;
	
	@Future
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDateInit() {
		return dateInit;
	}
	public void setDateInit(Date dateInit) {
		this.dateInit = dateInit;
	}

	@Future
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	public PriorityEvent getPriority() {
		return priority;
	}
	public void setPriority(PriorityEvent priority) {
		this.priority = priority;
	}
	
	@Min(0)
	@NotNull
	public Integer getSpace() {
		return space;
	}
	public void setSpace(Integer space) {
		this.space = space;
	}
	
	private EventEntity eventEntity;
	private Place place;
	private Collection<Child> child;
	
	@Valid
	@ManyToOne
	public EventEntity getEventEntity() {
		return eventEntity;
	}
	public void setEventEntity(EventEntity eventEntity) {
		this.eventEntity = eventEntity;
	}
	
	@Valid
	@ManyToOne
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	
	@Valid
	@ManyToMany(fetch=FetchType.LAZY)
	public Collection<Child> getChild() {
		return child;
	}
	public void setChild(Collection<Child> child) {
		this.child = child;
	}
	
	
}
