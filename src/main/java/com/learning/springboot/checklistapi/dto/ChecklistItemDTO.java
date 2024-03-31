package com.learning.springboot.checklistapi.dto;

import java.time.LocalDate;

import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChecklistItemDTO {

    private String guid;
    private String description;
    private Boolean isCompleted;
    private LocalDate deadline;
    private LocalDate postDate;
    private CategoryDTO category;

    public static ChecklistItemDTO toDTO(ChecklistItemEntity checklistItemEntity) {
        return ChecklistItemDTO.builder()
                .guid(checklistItemEntity.getGuid())
                .description(checklistItemEntity.getDescription())
                .deadline(checklistItemEntity.getDeadline())
                .postDate(checklistItemEntity.getPostDate())
                .isCompleted(checklistItemEntity.getIsCompleted())
                .category(checklistItemEntity.getCategory() != null ?
                        CategoryDTO.builder()
                                .guid(checklistItemEntity.getCategory().getGuid())
                                .name(checklistItemEntity.getCategory().getName())
                        .build() :
                        null)
                .build();
    }
} 

