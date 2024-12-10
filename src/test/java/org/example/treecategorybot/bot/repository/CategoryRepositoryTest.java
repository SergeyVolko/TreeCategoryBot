package org.example.treecategorybot.bot.repository;

import org.example.treecategorybot.bot.entities.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testSaveCategory() {
        Category category = new Category("Test Category");
        Category savedCategory = categoryRepository.save(category);
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo("Test Category");
    }

    @Test
    public void testFindCategoryByName() {
        String name = "Фрукты";
        Category category = new Category(name);
        categoryRepository.save(category);
        Category findedCategory = categoryRepository.findCategoryByName(name);
        assertThat(findedCategory).isNotNull();
        assertThat(findedCategory.getId()).isNotNull();
        assertThat(findedCategory.getName()).isEqualTo(name);
    }

    @Test
    public void testFindAllCategories() {
        Category a = new Category("aaaa", null);
        Category b = new Category("bbbb", null);
        Category c = new Category("cccc", a);
        Category d = new Category("dddd", b);
        Category e = new Category("eeee", c);
        List<Category> expected = List.of(a, b, c, d, e);
        categoryRepository.saveAll(expected);
        List<Category> result = categoryRepository.findAll();
        assertEquals(expected, result);
    }

    @Test
    public void testFindAllTreeCategories() {
        Category a = new Category("aaaa", null);
        Category b = new Category("bbbb", null);
        Category c = new Category("cccc", a);
        Category d = new Category("dddd", b);
        Category e = new Category("eeee", c);

        c.setParent(a);
        a.setChildren(new ArrayList<>());
        a.getChildren().add(c);
        d.setParent(b);
        b.setChildren(new ArrayList<>());
        b.getChildren().add(d);
        e.setParent(c);
        c.setChildren(new ArrayList<>());
        c.getChildren().add(e);
        List<Category> dataInput = List.of(a, b);
        List<Category> expected = List.of(a, b, c, d, e);
        categoryRepository.saveAll(dataInput);
        List<Category> result = categoryRepository.findAll();
        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
    }
}