package by.academy.fitness.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "dish", schema = "app")
public class Dish implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private UUID uuid;
	@Column(name = "dt_create")
	private LocalDateTime dtCreate;
	@Column(name = "dt_update")
	@Version
	private LocalDateTime dtUpdate;
	@Column
	private String name;
	@OneToMany
    @JoinColumn(name = "ingridient_id", referencedColumnName = "uuid")
	private List<Ingridient> ingridients;
//	@ManyToMany
//    @JoinColumn(name = "user", referencedColumnName = "uuid")
//	private User user;

	
	

}
