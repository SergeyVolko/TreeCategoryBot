package org.example.treecategorybot.bot.repository;

import org.example.treecategorybot.bot.entities.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void initDB() {
        Category a = new Category("aaaa", null);
        Category b = new Category("bbbb", null);
        Category c = new Category("cccc", a);
        Category d = new Category("dddd", b);
        Category e = new Category("eeee", c);
        List<Category> expected = List.of(a, b, c, d, e);
        categoryRepository.saveAll(expected);
    }

    @AfterEach
    public void deinitDB() {
        categoryRepository.deleteAll();
    }

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
        int expected = 5;
        List<Category> result = categoryRepository.findAll();
        assertEquals(expected, result.size());
    }

    @Test
    public void testFindAllTreeCategories() throws InterruptedException {
        categoryRepository.deleteAll();
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
        categoryRepository.saveAll(dataInput);
        List<Category> expected = Stream.of(a, b, c, d, e)
                .peek(category -> category.setChildren(null))
                .toList();
        List<Category> result = categoryRepository.findAll().stream()
                .peek(category -> category.setChildren(null))
                .toList();
        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testRemoveAddedElementWithChildren() {
        categoryRepository.deleteAll();
        Category a = new Category("aaaa", null);
        Category b = new Category("bbbb", null);
        Category c = new Category("cccc", a);
        Category d = new Category("dddd", b);
        Category e = new Category("eeee", c);
        a.setChildren(new ArrayList<>());
        a.getChildren().add(c);
        c.setChildren(new ArrayList<>());
        c.getChildren().add(e);
        b.setChildren(new ArrayList<>());
        b.getChildren().add(d);
        List<Category> dataInput = List.of(a, b);
        categoryRepository.saveAll(dataInput);
        List<Category> expected = Stream.of(b, d)
                .peek(category -> category.setChildren(null))
                .toList();
        categoryRepository.delete(a);
        List<Category> result = categoryRepository.findAll().stream()
                .peek(category -> category.setChildren(null))
                .toList();
        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testRemoveAddedElementWithInCenterChildren() {
        categoryRepository.deleteAll();
        Category a = new Category("aaaa", null);
        Category b = new Category("bbbb", null);
        Category c = new Category("cccc", a);
        Category d = new Category("dddd", b);
        Category e = new Category("eeee", c);
        List<Category> dataInput = List.of(a, b, c, d, e);
        categoryRepository.saveAll(dataInput);
        //c = categoryRepository.findCategoryByName("cccc");
        //c.setParent(null);
        //a.getChildren().remove(c);
        //categoryRepository.save(c);
        categoryRepository.delete(c);
        List<Category> expected = Stream.of(a, b, c, d, e)
                .peek(category -> category.setChildren(null))
                .toList();
        List<Category> result = categoryRepository.findAll().stream()
                .peek(category -> category.setChildren(null))
                .toList();
        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
    }
}