package by.academy.fitness.domain.dto;

public class UserLoginDTO {

	private String mail;
	private String password;

	public UserLoginDTO() {
		super();
	}

	public UserLoginDTO(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
