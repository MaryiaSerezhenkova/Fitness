package by.academy.fitness.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recipe", schema = "app")
public class Recipe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private UUID uuid;
	@Column(name = "dt_create")
	private LocalDateTime dtCreate;
	@Column(name = "dt_update")
	private LocalDateTime dtUpdate;
	@ManyToMany
    @JoinColumn(name = "product", referencedColumnName = "uuid")
	private Product product;
	@Column
	private int weight;
	@ManyToOne
    @JoinColumn(name = "dish", referencedColumnName = "uuid")
	private Dish dish;
}
