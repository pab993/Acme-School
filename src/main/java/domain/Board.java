package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Board extends DomainEntity{
	
	private String description;
	private Date dateFinValidation;

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
	
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDateFinValidation() {
		return dateFinValidation;
	}
	public void setDateFinValidation(Date dateFinValidation) {
		this.dateFinValidation = dateFinValidation;
	}
	
	private EventEntity eventEntity;
	
	@Valid
	@ManyToOne
	public EventEntity getEventEntity() {
		return eventEntity;
	}
	public void setEventEntity(EventEntity eventEntity) {
		this.eventEntity = eventEntity;
	}

}
