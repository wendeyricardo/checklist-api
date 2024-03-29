package com.learning.springboot.checklistapi.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springboot.checklistapi.dto.ChecklistItemDTO;
import com.learning.springboot.checklistapi.entity.ChecklistItemEntity;
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
    
    @PostMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewChecklistItem(@RequestBody ChecklistItemDTO checklistItemDTO){

        if(checklistItemDTO.getCategoryGuid() == null){

            throw new ValidationException("Category cannot null");
        }

        ChecklistItemEntity newChecklistItem = this.checklistItemService.addNewChecklistItemEntity(
            checklistItemDTO.getDescription(), checklistItemDTO.getIsCompleted(),
            checklistItemDTO.getDeadline(),
            checklistItemDTO.getCategory().getGuid()
        );

        return new ResponseEntity<>(newChecklistItem.getGuid(), HttpStatus.CREATED);

    }

    @PutMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateChecklistItem(@RequestBody ChecklistItemDTO checklistItemDTO){
        
        if(StringUtils.hasLength(checklistItemDTO.getGuid())){
            throw new ValidationException("Checklist item guid is mandatory");
        }
        this.checklistItemService.updateChecklistItem(
            checklistItemDTO.getIsCompleted(),
            checklistItemDTO.getDeadline(),
            checklistItemDTO.getCategory().getGuid());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    
}
