package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.dto.CategoryDTO;
import org.example.treecategorybot.bot.entities.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConverterDtoCategoryImpTest {

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

    private CategoryDTO fruitsDto = new CategoryDTO(NAME_FRUITS, null);
    private CategoryDTO applesDto = new CategoryDTO(NAME_APPLES, NAME_FRUITS);
    private CategoryDTO greneySmithDto = new CategoryDTO(NAME_GRENEY_SMITH, NAME_APPLES);
    private CategoryDTO fujiDto = new CategoryDTO(NAME_FUJI, NAME_APPLES);
    private CategoryDTO galaDto = new CategoryDTO(NAME_GALA, NAME_APPLES);
    private CategoryDTO citrusDto = new CategoryDTO(NAME_CITRUS, NAME_FRUITS);
    private CategoryDTO orangeDto = new CategoryDTO(NAME_ORANGE, NAME_CITRUS);
    private CategoryDTO lemonsDto = new CategoryDTO(NAME_LEMONS, NAME_CITRUS);
    private CategoryDTO vegetablesDto = new CategoryDTO(NAME_VEGETABLES, null);
    private CategoryDTO leafyDto = new CategoryDTO(NAME_LEAFY, NAME_VEGETABLES);
    private CategoryDTO spinachDto = new CategoryDTO(NAME_SPINACH, NAME_LEAFY);
    private CategoryDTO saladDto = new CategoryDTO(NAME_SALAD, NAME_LEAFY);
    private CategoryDTO rootsDto = new CategoryDTO(NAME_ROOTS, NAME_VEGETABLES);
    private CategoryDTO carrotDto = new CategoryDTO(NAME_CARROT, NAME_ROOTS);
    private CategoryDTO beetDto = new CategoryDTO(NAME_BEET, NAME_ROOTS);

    List<CategoryDTO> dataInput = List.of(fruitsDto, applesDto, greneySmithDto, fujiDto, galaDto, citrusDto, orangeDto,
            lemonsDto, vegetablesDto, leafyDto, spinachDto, saladDto, rootsDto, carrotDto, beetDto
    );

    private final ConverterDtoCategoryImp converterDtoCategoryImp;

    @Autowired
    ConverterDtoCategoryImpTest(ConverterDtoCategoryImp converterDtoCategoryImp) {
        this.converterDtoCategoryImp = converterDtoCategoryImp;
    }

    @Test
    void convertDtoToCategory() {
        List<Category> result = converterDtoCategoryImp.convertDtoToCategory(dataInput);
        assertEquals(2, result.size());

        String actual = result.get(0).getName();
        assertEquals(NAME_FRUITS, actual);
        actual = result.get(0).getChildren().get(0).getName();
        assertEquals(NAME_APPLES, actual);
        actual = result.get(0).getChildren().get(0).getChildren().get(0).getName();
        assertEquals(NAME_GRENEY_SMITH, actual);
        actual = result.get(1).getChildren().get(0).getChildren().get(0).getName();
        assertEquals(NAME_SPINACH, actual);
    }
}