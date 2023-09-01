package com.company.feelmusic.configure;

import com.company.feelmusic.model.Category;
import com.company.feelmusic.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StartupConfig implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public StartupConfig(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category.Builder().name("cazz").songs(new ArrayList<>()).build();

        System.out.println(categoryRepository.save(category));

    }
}
