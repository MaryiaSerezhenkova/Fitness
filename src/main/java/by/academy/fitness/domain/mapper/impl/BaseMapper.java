package by.academy.fitness.domain.mapper.impl;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import by.academy.fitness.domain.entity.IEntity;
import by.academy.fitness.domain.mapper.Mapper;

public abstract class BaseMapper<E extends IEntity, D> implements Mapper<E, D> {
	@Autowired
	protected ModelMapper modelMapper;
	private Class<E> entityType;
	private Class<D> dtoType;

	@PostConstruct
	protected void init() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void setType() {
		entityType = (Class<E>) GenericTypeResolver.resolveTypeArguments(getClass(), BaseMapper.class)[0];
		dtoType = (Class<D>) GenericTypeResolver.resolveTypeArguments(getClass(), BaseMapper.class)[1];
	}


	@Override
	public D toDTO(E entity) {
		if (entity == null) {
			return null;
		}
		return modelMapper.map(entity, dtoType);
	}

	@Override
	public E toEntity(D dto) {
		if (dto == null) {
			return null;
		}
		return modelMapper.map(dto, entityType);
	}


}
