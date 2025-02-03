package dev.ole.somira.repository;

import com.mongodb.client.MongoCollection;
import dev.ole.somira.repository.methods.fields.UpdateBatch;
import dev.ole.somira.repository.methods.pagination.Pagination;
import dev.ole.somira.repository.methods.sort.Sort;

import java.util.Collection;
import java.util.List;

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

    List<E> pageAll(Pagination pagination);

    boolean save(E entity);

    boolean saveAll(Collection<E> entityList);

    boolean insertAll(List<E> entityList);

    List<E> sortAll(Sort sort);

    boolean updateAllFields(UpdateBatch updateBatch);

    MongoCollection<E> getNativeCollection();
}
