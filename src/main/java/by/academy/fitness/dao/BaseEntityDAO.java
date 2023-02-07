package by.academy.fitness.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.academy.fitness.dao.interf.IDao;
import by.academy.fitness.domain.entity.IEntity;

@Repository
public abstract class BaseEntityDAO<I, E extends IEntity> implements IDao<E> {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	public E find(UUID uuid, LocalDateTime dtUpdate) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> criteria = builder.createQuery(getClazz());
		Root<E> root = criteria.from(getClazz());
		criteria.select(root);

		criteria.where(
				builder.and(builder.equal(root.get("uuid"), uuid), builder.equal(root.get("dt_update"), dtUpdate)));
		return entityManager.createQuery(criteria).getResultList().stream().findFirst().orElse(null);

	}

	public List<E> findAll(Integer amount, Integer skip, List<Sorting> sortings, List<Filtering> filters) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> criteria = builder.createQuery(getClazz());
		Root<E> root = criteria.from(getClazz());
		criteria.select(root);

		if (sortings != null) {
			for (Sorting sort : sortings) {
				if (sort.isDesc()) {
					criteria.orderBy(builder.desc(root.get(sort.getId())));
				} else {
					criteria.orderBy(builder.asc(root.get(sort.getId())));
				}
			}
		}

		List<Predicate> predicates = new ArrayList<>();
		if (filters != null) {
			for (Filtering filter : filters) {
				Object value = filter.getValue();
				if (filter.getValue() == null) {
					predicates.add(filter.isNot() ? builder.isNotNull(root.get(filter.getId()))
							: builder.isNull(root.get(filter.getId())));
				} else {
					if (value != null && value instanceof List) {
						predicates.add(filter.isNot() ? builder.not(root.get(filter.getId()).in(value))
								: root.get(filter.getId()).in(value));
					} else {
						predicates.add(filter.isNot() ? builder.notEqual(root.get(filter.getId()), filter.getValue())
								: builder.equal(root.get(filter.getId()), filter.getValue()));
					}
				}
			}
		}
		criteria.where(builder.and(predicates.toArray(new Predicate[0])));
		TypedQuery<E> q = getEntityManager().createQuery(criteria);

		if (skip != null) {
			q.setFirstResult(skip);
		}

		if (amount != null && amount > 0) {
			q.setMaxResults(amount);
		}
		return q.getResultList();
	}

	public Integer count() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Long> countingQuery = builder.createQuery(Long.class);
		Root<E> root = countingQuery.from(getClazz());

		countingQuery.select(builder.count(root));
		return getEntityManager().createQuery(countingQuery).getSingleResult().intValue();
	}

	public Integer count(List<Filtering> filters) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Long> countingQuery = builder.createQuery(Long.class);
		Root<E> root = countingQuery.from(getClazz());

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (filters != null) {
			for (Filtering filter : filters) {
				Object value = filter.getValue();
				if (filter.getValue() == null) {
					predicates.add(filter.isNot() ? builder.isNotNull(root.get(filter.getId()))
							: builder.isNull(root.get(filter.getId())));
				} else {
					if (value != null && value instanceof List) {
						predicates.add(filter.isNot() ? builder.not(root.get(filter.getId()).in(value))
								: root.get(filter.getId()).in(value));
					} else {
						predicates.add(filter.isNot() ? builder.notEqual(root.get(filter.getId()), filter.getValue())
								: builder.equal(root.get(filter.getId()), filter.getValue()));
					}
				}
			}
		}

		countingQuery.select(builder.count(root));
		countingQuery.where(builder.and(predicates.toArray(new Predicate[0])));
		return getEntityManager().createQuery(countingQuery).getSingleResult().intValue();
	}

	public E findByUuid(I entityId) {
		E entity = getEntityManager().find(getClazz(), entityId);
		return entity;
	}

	
	public E create(E item) {
		try {
			entityManager.persist(item);
			return item;
		} catch (Exception e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}

	}

	protected abstract void updateFields(CriteriaUpdate<E> criteria, E entity);

	public E update(UUID uuid, LocalDateTime dtUpdate, E entity) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaUpdate<E> criteria = builder.createCriteriaUpdate(getClazz());
		Root<E> root = criteria.from(getClazz());
		updateFields(criteria, entity);
		criteria.where(
				builder.and(builder.equal(root.get("uuid"), uuid), builder.equal(root.get("dt_update"), dtUpdate)));
		return entity;
	}

	public UUID update(E entity) {
		getEntityManager().merge(entity);
		return entity.getUuid();
	}

	public boolean delete(I entityId) {
		try {
			E e = getEntityManager().getReference(getClazz(), entityId);
			if (e != null && e.getUuid() != null) {
				return delete(e);
			}
			return false;
		} catch (EntityNotFoundException ex) {
			// Entity already removed
			return true;
		}
	}

	public boolean delete(E e) {
		getEntityManager().remove(e);
		return true;
	}

	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaDelete<E> criteria = builder.createCriteriaDelete(getClazz());
		Root<E> root = criteria.from(getClazz());
		criteria.where(
				builder.and(builder.equal(root.get("uuid"), uuid), builder.equal(root.get("dt_update"), dtUpdate)));
		entityManager.createQuery(criteria).executeUpdate();
	}

	protected CriteriaQuery<E> createCriteriaBuilder() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> criteria = builder.createQuery(getClazz());
		Root<E> root = criteria.from(getClazz());
		criteria.select(root);
		return criteria;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected abstract Class<E> getClazz();

}