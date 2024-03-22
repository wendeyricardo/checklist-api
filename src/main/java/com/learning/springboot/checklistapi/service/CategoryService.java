package com.learning.springboot.checklistapi.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.learning.springboot.checklistapi.entity.CategoryEntity;
import com.learning.springboot.checklistapi.exception.ResourceNotFoundException;
import com.learning.springboot.checklistapi.repository.CategoryRepository;
import com.learning.springboot.checklistapi.repository.ChecklistItemRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService {

    
    private ChecklistItemRepository checklistItemRepository;
    private CategoryRepository categoryRepository;

    public CategoryService(ChecklistItemRepository checklistItemRepository, CategoryRepository categoryRepository){
        this.checklistItemRepository = checklistItemRepository;
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity addNewCategory(String name){

        if(!StringUtils.hasText(name)){
            throw new IllegalArgumentException("category name canot be empty or null");
        }

        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setGuid(UUID.randomUUID().toString());
        newCategory.setName(name);

        log.debug("adding a new category with name [ name = {} ]", name);

        return this.categoryRepository.save(newCategory);

    }

    public CategoryEntity updateCategory(String guid, String name){
        if(guid ==null || !StringUtils.hasText(name)){
            throw new IllegalArgumentException("invalida parameter provided to update a category");
        }
        
        CategoryEntity retrievedCategoy = this.categoryRepository.findByGuid(guid).orElseThrow(
            ()-> new ResourceNotFoundException("Category not found")
        );

        retrievedCategoy.setName(name);
        log.debug("updating category [ guid = {}, newName = {}]", guid, name);

        return this.categoryRepository.save(retrievedCategoy);


    }
    
}
