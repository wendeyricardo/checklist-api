package com.learning.springboot.checklistapi.repository;

import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecklistItemRepository extends PagingAndSortingRepository<ChecklistItemEntity, Long>{
    
    Optional<ChecklistItemEntity> findByGuid(String guid);

    List<ChecklistItemEntity> findByDescriptionAndIsCompleted(String description, Boolean isCompleted);

    List<ChecklistItemEntity> findByCategoryGuid(String guid);

}
