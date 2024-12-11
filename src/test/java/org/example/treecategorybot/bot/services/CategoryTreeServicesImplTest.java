package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryTreeServicesImplTest {
    private static final String NAME_FRUITS = "Фрукты";
    private static final String NAME_APPLES = "Яблоки";
    private static final String NAME_GRENEY_SMITH = "Грени Смит";
    private static final String NAME_FUJI = "Фуджи";
    private static final String NAME_GALA = "Гала";
    private static final String NAME_CITRUS = "Цитрусовые";
    private static final String NAME_ORANGE = "Апельсины";
    private static final String NAME_LEMONS = "Лимоны";
    private static final String NAME_VEGETABLES = "Овощи";
    private static final String NAME_LEAFY = "Листовые";
    private static final String NAME_SPINACH = "Шпинат";
    private static final String NAME_SALAD = "Салат";
    private static final String NAME_ROOTS = "Корнеплоды";
    private static final String NAME_CARROT = "Морковь";
    private static final String NAME_BEET = "Свекла";
    private Category fruits = new Category(NAME_FRUITS, null);
    private Category apples = new Category(NAME_APPLES, fruits);
    private Category greneySmith = new Category(NAME_GRENEY_SMITH, apples);
    private Category fuji = new Category(NAME_FUJI, apples);
    private Category gala = new Category(NAME_GALA, apples);
    private Category citrus = new Category(NAME_CITRUS, fruits);
    private Category orange = new Category(NAME_ORANGE, citrus);
    private Category lemons = new Category(NAME_LEMONS, citrus);
    private Category vegetables = new Category(NAME_VEGETABLES, null);
    private Category leafy = new Category(NAME_LEAFY, vegetables);
    private Category spinach = new Category(NAME_SPINACH, leafy);
    private Category salad = new Category(NAME_SALAD, leafy);
    private Category roots = new Category(NAME_ROOTS, vegetables);
    private Category carrot = new Category(NAME_CARROT, roots);
    private Category beet = new Category(NAME_BEET, roots);

    private final CategoryTreeServicesImpl categoryTreeServicesImpl;
    private final CategoryRepository categoryRepository;

    @BeforeEach
    public void init() {
        List<Category> dataInput = List.of(fruits, apples, greneySmith, fuji, gala, citrus, orange,
                lemons, vegetables, leafy, spinach, salad, roots, carrot, beet
        );
        categoryTreeServicesImpl.saveAll(dataInput);
    }

    @AfterEach
    public void deInit() {
        categoryTreeServicesImpl.removeAll();
    }

    @Autowired
    CategoryTreeServicesImplTest(CategoryTreeServicesImpl categoryTreeServicesImpl, CategoryRepository categoryRepository) {
        this.categoryTreeServicesImpl = categoryTreeServicesImpl;
        this.categoryRepository = categoryRepository;
    }


    @Test
    void whenAddElement() {
        String nameCategory = "Test Category";
        categoryTreeServicesImpl.addElement(nameCategory);
        Category category = categoryRepository.findCategoryByName(nameCategory);
        assertNotNull(category);
    }

    @Test
    void addElementToParent() {
        String nameCategory = "Картошка";
        String nameParent = "Корнеплоды";
        String expected = """
                Текущее дерево категорий:
                Фрукты
                     -Яблоки
                          -Грени Смит
                          -Фуджи
                          -Гала
                     -Цитрусовые
                          -Апельсины
                          -Лимоны
                Овощи
                     -Листовые
                          -Шпинат
                          -Салат
                     -Корнеплоды
                          -Морковь
                          -Свекла
                          -Картошка
                """;
        categoryTreeServicesImpl.addElementToParent(nameParent, nameCategory);
        String actual = categoryTreeServicesImpl.viewTree();
        assertEquals(expected, actual);
    }

    @Test
    void removeElement() {
        categoryTreeServicesImpl.removeElement(NAME_FRUITS);
        String expected = """
                Текущее дерево категорий:
                Овощи
                     -Листовые
                          -Шпинат
                          -Салат
                     -Корнеплоды
                          -Морковь
                          -Свекла
                """;
        String actual = categoryTreeServicesImpl.viewTree();
        assertEquals(expected, actual);
    }

    @Test
    public void viewTreeCategories() {
        String expected = """
                Текущее дерево категорий:
                Фрукты
                     -Яблоки
                          -Грени Смит
                          -Фуджи
                          -Гала
                     -Цитрусовые
                          -Апельсины
                          -Лимоны
                Овощи
                     -Листовые
                          -Шпинат
                          -Салат
                     -Корнеплоды
                          -Морковь
                          -Свекла
                """;
        String actual = categoryTreeServicesImpl.viewTree();
        assertEquals(expected, actual);
    }
}