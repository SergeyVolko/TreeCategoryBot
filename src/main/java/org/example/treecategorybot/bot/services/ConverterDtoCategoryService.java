package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.dto.CategoryDTO;
import org.example.treecategorybot.bot.entities.Category;
import java.util.List;

/**
 * Сервис для преобразования объектов {@link CategoryDTO} в сущности {@link Category}.
 *
 * <p>
 * Этот интерфейс определяет метод для конвертации списка объектов {@link CategoryDTO}
 * в иерархическую структуру категорий {@link Category}.
 * </p>
 */
public interface ConverterDtoCategoryService {

    /**
     * Преобразует список объектов {@link CategoryDTO} в список сущностей {@link Category}.
     *
     * <p>
     * Метод создает соответствующие объекты {@link Category} и устанавливает
     * родительские и дочерние связи между ними на основе информации из {@link CategoryDTO}.
     * </p>
     *
     * @param categoryDTOS Список объектов {@link CategoryDTO}, которые необходимо преобразовать.
     * @return Список объектов {@link Category}, представляющих иерархическую структуру категорий.
     */
    List<Category> convertDtoToCategory(List<CategoryDTO> categoryDTOS);
}
