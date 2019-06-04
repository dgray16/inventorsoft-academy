package com.inventorsoft.domain.service.base;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeneralService<T, ID extends Integer> {

    CrudRepository<T, ID> repository;

    public GeneralService(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public T getById(ID id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

}
