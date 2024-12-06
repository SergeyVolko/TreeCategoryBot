package org.example.treecategorybot.bot.logic;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddElementCommandTest {

    private final CategoryRepository categoryRepository;

    @Autowired
    public AddElementCommandTest(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Test
    public void template() {
        String text = "/addElement <родительский элемент> <дочерний элемент>";
        String regex = "/addElement\\s+<[^<>]+>(\\s+<[^<>]+>)?";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            System.out.println("Строка соответствует формату.");
        } else {
            System.out.println("Строка не соответствует формату.");
        }
        System.out.println(text);
        String[] elements = text.substring(text.indexOf("<") + 1, text.length() - 1)
                .split("> <");
        System.out.println(Arrays.toString(elements));

    }


    @Test
    public void testRepository() {
        categoryRepository.deleteAll();
        Category categoryA = new Category("aaaa");
        Category categoryB = new Category("bbbb");
        Category categoryAC = new Category("cccc", categoryA);
        Category categoryAD = new Category("dddd", categoryA);
        Category categoryDE = new Category("eeee", categoryAD);
        Category categoryBF = new Category("ffff", categoryB);
        List<Category> categories = List.of(categoryA, categoryB, categoryAC, categoryAD, categoryDE, categoryBF);
        categoryRepository.saveAll(categories);
        List<Category> categoryFromData = categoryRepository.findAll();
        List<Category> treeCategories = getTreeCategories(categoryFromData);
        System.out.println(showCategoriesTree(treeCategories));
        //Category category = categoryRepository.findByName("cccc");
        //category.setParent(null);
        //categoryRepository.save(category);
        //categoryRepository.delete(category);

        //categoryRepository.delete(categoryE);
        //categoryRepository.delete(categoryC);
        //deleteCategory(categoryC.getId());
        //someMethod(categoryA);
        //categoryRepository.deleteById(6L);
    }

    @Transactional
    public void deleteCategory(Long id) {

    }

    @Transactional
    public void addCategory(Category newCategory, Category parent) {
        if (parent != null) {
            // Загружаем родителя из БД для обеспечения его сохранения
            Category parentCategory = categoryRepository.findById(parent.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent category not found"));
            newCategory.setParent(parentCategory);
        }
        // Сохраняем новую категорию с установкой родительской категории
        categoryRepository.save(newCategory);
    }

    @Transactional
    public void someMethod(Category param) {
        Category category = categoryRepository.findById(param.getId()).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        // Доступ к ленивой коллекции children
        List<Category> children = category.getChildren();
        System.out.println(children);
    }

    @Test
    @Transactional
    public  void whenDeleteCategory() {

    }

    List<Category> getTreeCategories(List<Category> categories) {
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

    String showCategoriesTree(List<Category> categories) {
        StringBuilder builder = new StringBuilder();
        recurseTree(categories, 0, builder);
        return builder.toString();
    }

    public  void recurseTree(List<Category> categories, int level, StringBuilder builder) {
        for (Category category : categories) {
            if (category.getParent() != null) {
                builder.append("  ".repeat(level - 1));
                builder.append("|");
                builder.append("_");
            }
            builder.append(category.getName()).append("\n");
            List<Category> children = category.getChildren();
            if (!children.isEmpty()) {
                recurseTree(children, level + 1, builder);
            }
        }
    }

}