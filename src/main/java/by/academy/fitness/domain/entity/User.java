package by.academy.fitness.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name = "users", schema = "app", uniqueConstraints = { @UniqueConstraint(columnNames = "nick"),
		@UniqueConstraint(columnNames = "email") })
public class User  implements IEntity {

	private static final long serialVersionUID = 1L;

	public enum ROLE {
		ADMIN, USER

	}

	public enum USERSTATUS {
		WAITING_ACTIVATION, ACTIVATED, DEACTIVATED

	}

	@Id
	private UUID uuid;
	@Column(name = "dt_create")
	private LocalDateTime dtCreate;
	@Column(name = "dt_update")
	@Version
	private LocalDateTime dtUpdate;
	@Column(length = 50)
	private String email;
	@Column(length = 20)
	private String nick;
	@Column
	@Enumerated(EnumType.STRING)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_uuid"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	// private ROLE role;
	@Column
	@Enumerated(EnumType.STRING)
	private USERSTATUS status;
	@Column(length = 120)
	private String password;

	public User() {
		super();
	}

	public User(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String email, String nick, Set<Role> roles,
			USERSTATUS status, String password) {
		super();
		this.uuid = uuid;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.email = email;
		this.nick = nick;
		this.roles = roles;
		this.status = status;
		this.password = password;
	}

	public User(String email, String nick, String password) {
		this.email = email;
		this.nick = nick;
		this.password = password;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
	}

	public LocalDateTime getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public USERSTATUS getStatus() {
		return status;
	}

	public void setStatus(USERSTATUS status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [uuid=");
		builder.append(uuid);
		builder.append(", dtCreate=");
		builder.append(dtCreate);
		builder.append(", dtUpdate=");
		builder.append(dtUpdate);
		builder.append(", email=");
		builder.append(email);
		builder.append(", nick=");
		builder.append(nick);
		builder.append(", roles=");
		builder.append(roles);
		builder.append(", status=");
		builder.append(status);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}

}
