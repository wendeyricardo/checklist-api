package com.learning.springboot.checklistapi.entity;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistItemEntity extends BaseEntity{

    private Long checklistItem;

    private String description;

    private Boolean isCompleted;

    private LocalTime deadline;
    
    private LocalTime postDate;

    private CategoryEntity category;
    
}
