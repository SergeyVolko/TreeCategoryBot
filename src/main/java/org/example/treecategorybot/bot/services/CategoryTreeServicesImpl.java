package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryTreeServicesImpl implements CategoriesTreeService {

    private final CategoryRepository categoryRepository;

    public CategoryTreeServicesImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public String viewTree() {
        List<Category> categoriesFromDB = getAllCategories();
        if (categoriesFromDB.isEmpty()) {
            return "Дерево категорий пустое.";
        }
        List<Category> categories = getTreeCategories(categoriesFromDB);
        StringBuilder builder = new StringBuilder("Текущее дерево категорий:\n");
        recurseTree(categories, 0, builder);
        return builder.toString();
    }

    @Override
    public Category addElement(String nameCategory) {
        if (categoryRepository.findCategoryByName(nameCategory) != null) {
            return null;
        }
        return categoryRepository.save(new Category(nameCategory));
    }

    @Transactional
    @Override
    public Category addElementToParent(String parentNameCategory, String nameCategory) {
        Category parentCategory = categoryRepository.findCategoryByName(parentNameCategory);
        Category category = categoryRepository.findCategoryByName(nameCategory);
        if (parentCategory == null || category != null) {
            return null;
        }
        Category addedCategory = new Category(nameCategory, parentCategory);
        parentCategory.getChildren().add(addedCategory);

        return categoryRepository.save(addedCategory);
    }

    @Override
    public Category removeElement(String nameCategory) {
        Category deletedCategory = categoryRepository.findCategoryByName(nameCategory);
        if (deletedCategory == null) {
            return null;
        }
        if (deletedCategory.getParent() != null) {
            deletedCategory.setParent(null);
            categoryRepository.save(deletedCategory);
        }
        categoryRepository.deleteById(deletedCategory.getId());
        return deletedCategory;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    @Override
    public void removeAll() {
        categoryRepository.deleteAll();
    }

    private void recurseTree(List<Category> categories, int level, StringBuilder builder) {
        for (Category category : categories) {
            if (category.getParent() != null) {
                builder.append("     ".repeat(level));
                builder.append("-");
            }
            builder.append(category.getName()).append("\n");
            List<Category> children = category.getChildren();
            if (!children.isEmpty()) {
                recurseTree(children, level + 1, builder);
            }
        }
    }

    private List<Category> getTreeCategories(List<Category> categories) {
        return categories.stream()
                .peek(category -> {
                    category.setChildren(new ArrayList<>());
                    Category parent = category.getParent();
                    if (parent != null) {
                        parent.getChildren().add(category);
                    }
                }).filter(category -> category.getParent() == null)
                .toList();
    }
}
