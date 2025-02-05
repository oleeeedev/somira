package dev.ole.somira.repository;

import dev.ole.somira.repository.methods.async.Async;
import dev.ole.somira.repository.methods.fields.UpdateBatch;
import dev.ole.somira.repository.methods.pagination.Pagination;
import dev.ole.somira.repository.methods.sort.Sort;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public interface AsyncRepository<E, ID> {

    @Async
    CompletableFuture<Long> asyncCountAll();

    @Async
    CompletableFuture<Boolean> asyncDelete(E entity);

    @Async
    CompletableFuture<Boolean> asyncDeleteAll();

    @Async
    CompletableFuture<Boolean> asyncDeleteById(ID identifier);

    @Async
    CompletableFuture<Boolean> asyncDeleteMany(Collection<E> entityList);

    @Async
    CompletableFuture<Boolean> asyncDeleteManyById(Collection<ID> idList);

    @Async
    CompletableFuture<Boolean> asyncDrop();

    @Async
    CompletableFuture<Boolean> asyncExists(E entity);

    @Async
    CompletableFuture<Boolean> asyncExistsById(ID identifier);

    @Async
    CompletableFuture<List<E>> asyncFindAll();

    @Async
    CompletableFuture<E> asyncFindFirstById(ID identifier);

    @Async
    CompletableFuture<List<E>> asyncPageAll(Pagination pagination);

    @Async
    CompletableFuture<Boolean> asyncSave(E entity);

    @Async
    CompletableFuture<Boolean> asyncSaveAll(Collection<E> entityList);

    @Async
    CompletableFuture<Boolean> asyncInsertAll(List<E> entityList);

    @Async
    CompletableFuture<List<E>> asyncSortAll(Sort sort);

    @Async
    CompletableFuture<Boolean> asyncUpdateAllFields(UpdateBatch updateBatch);
}