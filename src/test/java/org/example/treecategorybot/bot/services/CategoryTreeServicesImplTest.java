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
        Category categoryDG = new Category("gggg", categoryAD);
        Category categoryBF = new Category("ffff", categoryB);
        categoryA.setChildren(new ArrayList<>());
        categoryA.getChildren().add(categoryAC);
        categoryA.getChildren().add(categoryAD);
        categoryB.setChildren(new ArrayList<>());
        categoryB.getChildren().add(categoryBF);
        categoryAD.setChildren(new ArrayList<>());
        categoryAD.getChildren().add(categoryDE);
        categoryAD.getChildren().add(categoryDG);

        /*Category categoryA = new Category("aaaa");
        Category categoryB = new Category("bbbb");
        Category categoryAC = new Category("cccc", new Category("aaaa"));
        Category categoryAD = new Category("dddd", new Category("aaaa"));
        Category categoryDE = new Category("eeee", new Category("dddd", new Category("aaaa")));
        Category categoryDG = new Category("gggg", new Category("dddd", new Category("aaaa")));
        Category categoryBF = new Category("ffff", new Category("bbbb"));*/
        //List<Category> categories = List.of(categoryBF, categoryDG, categoryB, categoryAC, categoryA, categoryAD, categoryDE);
        List<Category> categories = List.of(categoryA, categoryB);

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