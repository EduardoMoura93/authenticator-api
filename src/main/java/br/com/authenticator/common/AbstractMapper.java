package br.com.authenticator.common;

import java.util.List;

public interface AbstractMapper<ENTITY, RESOURCE> {

    ENTITY toEntity(RESOURCE resource);

    RESOURCE toResource(ENTITY entity);

    List<ENTITY> toEntities(List<RESOURCE> resources);

    List<RESOURCE> toResources(List<ENTITY> entities);
}