package com.learning.springboot.checklistapi.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springboot.checklistapi.dto.ChecklistITemDTO;
import com.learning.springboot.checklistapi.dto.ChecklistItemDTO;
import com.learning.springboot.checklistapi.service.ChecklistItemService;

@RestController
@RequestMapping("/api/v1/checklist-items")
public class ChecklistItemController {

    private ChecklistItemService checklistItemService;

    public ChecklistItemController(ChecklistItemService checklistItemService){
        this.checklistItemService = checklistItemService;
    }

    @GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChecklistItemDTO>> getAllChecklistItems(){
     
        List<ChecklistItemDTO> resp = StreamSupport.stream(this.checklistItemService.findAllChecklistItems().spliterator(), false)
                .map(checklistItemEntity-> ChecklistItemDTO.toDTO(checklistItemEntity))
                .collect(Collectors.toList());
                
        return new ResponseEntity<>(resp, HttpStatus.OK);        

    }
    
}
