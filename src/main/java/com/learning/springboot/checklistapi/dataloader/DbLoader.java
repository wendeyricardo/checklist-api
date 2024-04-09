package com.learning.springboot.checklistapi.dataloader;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.learning.springboot.checklistapi.entity.CategoryEntity;
import com.learning.springboot.checklistapi.repository.CategoryRepository;
import com.learning.springboot.checklistapi.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Profile("local")
@Slf4j
@Component
public class DbLoader implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception{

        log.info("population db with categories");

        List<String> categoriesNames = Arrays.asList(
            "Trabalho", "Saúde", "Educação", "Pessoal", "Outros"
        );

        for(String categoryName :  categoriesNames){
            Optional<CategoryEntity> catOpt =
                this.categoryRepository.findByName(categoryName);

                if(!catOpt.isPresent()){
                    categoryService.addNewCategory(categoryName);

                }
        }

    }
    
}
