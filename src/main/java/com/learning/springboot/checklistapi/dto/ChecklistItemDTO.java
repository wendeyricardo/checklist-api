package com.learning.springboot.checklistapi.dto;

import java.time.LocalDate;

import org.hibernate.annotations.Check;

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
    private String categoryGuid;

    public static ChecklistItemDTO toDTO(ChecklistItemEntity checklistItemEntity) {

    return ChecklistItemDTO.builder()
        .guid(checklistItemEntity.getGuid())
        .description(checklistItemEntity.getDescription())
        .deadline(checklistItemEntity.getDeadline())
        .postDate(checklistItemEntity.getPostDate())
        .isCompleted(checklistItemEntity.getIsCompleted())
        .categoryGuid(checklistItemEntity.getCategory().getGuid())
        .build();

    }
    
}
