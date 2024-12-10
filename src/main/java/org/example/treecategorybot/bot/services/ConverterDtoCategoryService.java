package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.dto.CategoryDTO;
import org.example.treecategorybot.bot.entities.Category;

import java.util.List;

public interface ConverterDtoCategoryService {
    List<Category> convertDtoToCategory(List<CategoryDTO> categoryDTOS);
}
