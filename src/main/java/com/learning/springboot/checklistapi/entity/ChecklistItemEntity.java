package com.learning.springboot.checklistapi.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity(name="ChecklistItem")
@Table(indexes = {@Index(name= "IDX_GUID_CK_IT", columnList="guid")})
public class ChecklistItemEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checklistItemId;

    private String description;

    private Boolean isCompleted;

    private LocalDate deadline;
    
    private LocalDate postDate;

    @ManyToOne
    private CategoryEntity category;
    
}
