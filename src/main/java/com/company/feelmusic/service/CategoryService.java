package com.company.feelmusic.service;


import com.company.feelmusic.model.Category;
import com.company.feelmusic.repository.CategoryRepository;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    protected Category getCategoryById(String id){
       return categoryRepository.findById(id).orElseThrow();
    }

    protected Category getCategoryByName(String name){
        return categoryRepository.findCategoryByName(name);
    }


}
