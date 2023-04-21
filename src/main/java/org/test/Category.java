package org.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Category {
    private String name;
    private List<String> keyWords;
    private Category majorCategory;
    private List<Category> subCategory;

    public Category(String name, List<String> keyWords, Category majorCategory) {
        this.name = name;
        this.keyWords = keyWords;
        this.majorCategory = majorCategory;
        this.subCategory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public Category getMajorCategory() {
        return majorCategory;
    }

    public void setMajorCategory(Category majorCategory) {
        this.majorCategory = majorCategory;
    }

    public List<Category> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<Category> subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(keyWords, category.keyWords) && Objects.equals(majorCategory, category.majorCategory) && Objects.equals(subCategory, category.subCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, keyWords, majorCategory, subCategory);
    }

    public void addSubCategory(Category subCategory) {
        this.subCategory.add(subCategory);
    }

    public List<String> getAllKeyWords() {
        if (this.majorCategory != null) {
            List<String> inheritedKeys = this.majorCategory.getAllKeyWords();
            return Stream.concat(this.keyWords.stream(), inheritedKeys.stream())
                    .distinct()
                    .collect(Collectors.toList());

        } else {
            return new ArrayList<>(this.keyWords);
        }
    }

    public int getCategoryLevel() {
        int level = 0;
        if (majorCategory == null) {
            return level;
        } else {
            level = 1 + majorCategory.getCategoryLevel();
        }
        return level;
    }
}
