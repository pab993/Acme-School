
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Parent extends Actor {

	//Relationships
	// =======================================================
	private Collection<Child> childs = new ArrayList<>();

	@Valid
	@OneToMany(mappedBy = "parent")
	public Collection<Child> getChilds() {
		return childs;
	}
	public void setChilds(Collection<Child> childs) {
		this.childs = childs;
	}

}
