package com.learning.springboot.checklistapi.entity;

import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

    private String guid;
    
}
