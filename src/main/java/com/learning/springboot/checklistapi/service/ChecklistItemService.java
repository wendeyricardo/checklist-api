package com.learning.springboot.checklistapi.service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.learning.springboot.checklistapi.entity.CategoryEntity;
import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import com.learning.springboot.checklistapi.exception.ResourceNotFoundException;
import com.learning.springboot.checklistapi.repository.CategoryRepository;
import com.learning.springboot.checklistapi.repository.ChecklistItemRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChecklistItemService {

    
    @Autowired
    private ChecklistItemRepository checklistItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private void validateChecklistItemData(String description, Boolean isCompleted, LocalDate deadline, String categoryGuid){

        if(StringUtils.hasText(description)){
            throw new IllegalArgumentException("Checklist item must have a description");
        }

        if(StringUtils.hasText(categoryGuid)){
            throw new IllegalArgumentException("Checklist item category guid must be provided");
        }

        if(isCompleted ==null){
            throw new IllegalArgumentException("Checklist item must have a flag indication if it is completed or not");
        }

        if(deadline ==null){
            throw new IllegalArgumentException("Checklist item must have a deadline");
        }
    }

    public ChecklistItemEntity addNewChecklistItemEntity(String description, Boolean isCompleted, LocalDate deadline, String categoryGuid){

        this.validateChecklistItemData(description, isCompleted, deadline, categoryGuid);

        CategoryEntity retrievedCategory = this.categoryRepository.findByGuid(categoryGuid)
                .orElseThrow(()-> new ResourceNotFoundException("category not found"));

        ChecklistItemEntity checklistItemEntity = new ChecklistItemEntity();
        checklistItemEntity.setGuid(UUID.randomUUID().toString());
        checklistItemEntity.setDescription(description);
        checklistItemEntity.setDeadline(deadline);
        checklistItemEntity.setPostDate(LocalDate.now());
        checklistItemEntity.setCategory(retrievedCategory);

        log.debug("adding new checklist item [ checklistItem = {}]", checklistItemEntity);

        return checklistItemRepository.save(checklistItemEntity);
    }

    public Iterable<ChecklistItemEntity> findAllChecklistItems(){
        return this.checklistItemRepository.findAll();
    }

    public void deleteChecklistItem(String guid){
        if(StringUtils.hasText(guid)){
            throw new IllegalArgumentException("guig canot be null or empty");
        }
        ChecklistItemEntity retrievedITem = this.checklistItemRepository.findByGuid(guid)
                .orElseThrow(() -> new ResourceNotFoundException("check item not found"));
        
        log.debug("deleting checklist item [ guid ={}]", guid);

        this.checklistItemRepository.delete(retrievedITem);   
    }

    
}
