package org.test.service;

import org.test.domain.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoryService {

    private Category category;

    public CategoryService(Category category){
        this.category = category;
    }

    public void addSubCategory(Category subCategory) {
        category.getSubCategory().add(subCategory);
    }

    public List<String> getAllKeyWords() {
        if (category.getMajorCategory() != null) {
            CategoryService majorCategoryService = new CategoryService(category.getMajorCategory());
            List<String> inheritedKeys = majorCategoryService.getAllKeyWords();
            return Stream.concat(category.getKeyWords().stream(), inheritedKeys.stream())
                    .distinct()
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>(category.getKeyWords());
        }
    }

    public int getCategoryLevel() {
        int level = 0;
        if (category.getMajorCategory() == null) {
            return level;
        } else {
            CategoryService majorCategoryManager = new CategoryService(category.getMajorCategory());
            level = 1 + majorCategoryManager.getCategoryLevel();
        }
        return level;
    }

}
