package com.learning.springboot.checklistapi.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChecklistItemDTO {

    private String guid;

    @NotBlank(message = "checklist item description cannot be either null or empty")
    private String description;

    @NotNull(message = "is completed is mandatory")
    private Boolean isCompleted;

    @NotNull(message = "is completed is mandatory")
    private LocalDate deadline;

    @NotBlank(message = "category guid cannot be either null or empty")
    private String categoryGuid;

    public static ChecklistItemDTO toDTO(ChecklistItemEntity checklistItemEntity) {
        return ChecklistItemDTO.builder()
                .guid(checklistItemEntity.getGuid())
                .description(checklistItemEntity.getDescription())
                .deadline(checklistItemEntity.getDeadline())
                .isCompleted(checklistItemEntity.getIsCompleted())
                .categoryGuid(checklistItemEntity.getCategory().getGuid())
                .build();
    }
} 

