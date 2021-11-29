package com.okay.core;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractBaseConverter<D extends BaseModel, E extends BaseEntity> implements ModelConverter<D, E>, EntityConverter<D, E> {

    private Class<D> dtoClass;

    private Class<E> entityClass;

    public AbstractBaseConverter() {
        this.dtoClass = (Class<D>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        this.entityClass = (Class<E>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    public Set<D> convertToDtoSet(Collection<E> entitySet) {
        return entitySet == null ? null : entitySet.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    public List<D> convertToDtoList(Collection<E> entityList) {
        return entityList == null ? null : entityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public D convertToDto(E entity) {
        if (entity == null) {
            return null;
        }

        D dto = createDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Set<E> convertToEntitySet(Collection<D> dtoSet) {
        return dtoSet == null ? null
                : dtoSet.stream().map(this::convertToEntity).collect(Collectors.toSet());
    }

    public List<E> convertToEntityList(Collection<D> dtoList) {
        return dtoList == null ? null : dtoList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    public E convertToEntity(D dto) {
        if (dto == null) {
            return null;
        }

        E entity = createEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private D createDto() {
        try {
            Constructor<D> ctor = dtoClass.getDeclaredConstructor();
            ReflectionUtils.makeAccessible(ctor);
            return ctor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private E createEntity() {
        try {
            Constructor<E> ctor = entityClass.getDeclaredConstructor();
            ReflectionUtils.makeAccessible(ctor);
            return ctor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}