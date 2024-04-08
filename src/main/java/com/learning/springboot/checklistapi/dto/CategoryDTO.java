package com.learning.springboot.checklistapi.dto;

import javax.validation.constraints.NotBlank;

import com.learning.springboot.checklistapi.entity.CategoryEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryDTO {
    private String guid;

    @NotBlank(message = "category name cannot be either null or empty")
    private String name;

    public static CategoryDTO toDTO(CategoryEntity categoryEntity){
        return CategoryDTO.builder()
        .guid(categoryEntity.getGuid())
        .name(categoryEntity.getName())
        .build();
    }
    
}
