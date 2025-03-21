package com.learning.springboot.checklistapi.repository;

import com.learning.springboot.checklistapi.entity.CategoryEntity;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {
    
    Optional<CategoryEntity> findByName(String name);

    Optional<CategoryEntity> findByGuid(String guid);
}
