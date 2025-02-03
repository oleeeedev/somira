package dev.ole.somira.repository;

import java.util.Collection;

@SuppressWarnings("unused")
public interface Repository<E, ID> {

    long countAll();

    boolean delete(E entity);

    boolean deleteAll();

    boolean deleteById(ID identifier);

    boolean deleteMany(Collection<E> entityList);

    boolean deleteManyById(Collection<ID> idList);

    boolean drop();

    boolean exists(E entity);

    boolean existsById(ID identifier);

    List<E> findAll();

    E findFirstById(ID identifier);

    String getCollectionName();

    Class<E> getEntityClass();

    Class<ID> getEntityUniqueIdClass();

    ID getUniqueId(E entity);
}
