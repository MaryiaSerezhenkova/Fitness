package by.academy.fitness.domain.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import by.academy.fitness.domain.entity.User.ROLE;

@Entity
@Table(name = "roles")
public class Role implements IEntity {
	@Id
	private UUID uuid;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ROLE name;

	public Role() {

	}

	public Role(ROLE name) {
		this.name = name;
	}

	public ROLE getName() {
		return name;
	}

	public void setName(ROLE name) {
		this.name = name;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public UUID getUuid() {
		return uuid;
	}
}