package by.academy.fitness.domain.dto;

public class UserRegistrationDTO {

	private String mail;
	private String nick;
	private String password;

	public UserRegistrationDTO() {
		super();
	}

	public UserRegistrationDTO(String mail, String nick, String password) {
		super();
		this.mail = mail;
		this.nick = nick;
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
