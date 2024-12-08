package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryTreeServicesImplTest {

    private final CategoryTreeServicesImpl categoryTreeServicesImpl;

    @Autowired
    CategoryTreeServicesImplTest(CategoryTreeServicesImpl categoryTreeServicesImpl) {
        this.categoryTreeServicesImpl = categoryTreeServicesImpl;
    }


    @Test
    void whenAddElement() {

    }

    @Test
    void addElementToParent() {
    }

    @Test
    void removeElement() {
    }

    @Test
    void getHelp() {
    }

    @Test
    void showAllElements() {
        Category categoryA = new Category("aaaa");
        Category categoryB = new Category("bbbb");
        Category categoryAC = new Category("cccc", categoryA);
        Category categoryAD = new Category("dddd", categoryA);
        Category categoryDE = new Category("eeee", categoryAD);
        Category categoryBF = new Category("ffff", categoryB);
        List<Category> categories = List.of(categoryA, categoryB, categoryAC, categoryAD, categoryDE, categoryBF);
        List<Category> treeCategories = buildTree(categories);
    }

    public List<Category> buildTree(List<Category> categories) {
        List<Category> result = new ArrayList<>();
        for (Category category : categories) {
            Category parent = category.getParent();
            if (parent != null) {
                List<Category> children = parent.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                parent.getChildren().add(category);
            } else {
                result.add(category);
            }

        }
        return result;
    }

    void recurseBuildTree(Category parent, Set<Category> categories) {

    }


}