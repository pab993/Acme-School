
package forms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

public class ActorRegisterForm {

	public ActorRegisterForm() {
		super();
	}


	private String	username;
	private String	password;
	private String	repeatPassword;
	private String	name;
	private String	surname;
	private String	phone;
	private String	phone2;
	private String	email;
	private String	address;
	private Date birthdate;
	private boolean	check;


	@Column(unique = true)
	@Size(min = 5, max = 32)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getUsername() {
		return this.username;
	}
	public void setUsername(final String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	public String getPassword() {
		return this.password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return this.repeatPassword;
	}
	public void setRepeatPassword(final String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSurname() {
		return this.surname;
	}
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Pattern(regexp = "^([+]\\d{1,2}\\s?)?\\d{3}\\d+$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(final String phone) {
		this.phone = phone;
	}
	
	@Pattern(regexp = "^$|^([+]\\d{1,2}\\s?)?\\d{3}\\d+$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPhone2() {
		return this.phone2;
	}
	public void setPhone2(final String phone2) {
		this.phone2 = phone2;
	}

	@Email
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getEmail() {
		return this.email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}

	@AssertTrue(message="{actorRegister.accept.error}")
	public boolean getCheck() {
		return this.check;
	}
	public void setCheck(final boolean check) {
		this.check = check;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAddress() {
		return this.address;
	}
	public void setAddress(final String address) {
		this.address = address;
	}
	
	@Temporal(TemporalType.DATE)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}
