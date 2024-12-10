package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.dto.CategoryDTO;
import org.example.treecategorybot.bot.entities.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConverterDtoCategoryImp implements ConverterDtoCategoryService {
    @Override
    public List<Category> convertDtoToCategory(List<CategoryDTO> categoryDTOS) {
        Map<String, Category> categoryListMap = new HashMap<>();
        List<Category> roots = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryDTOS) {
            String name = categoryDTO.name();
            String parentName = categoryDTO.parent();
            Category category = categoryListMap.computeIfAbsent(name, n -> new Category(n, null));
            if (parentName == null) {
                roots.add(category);
            } else {
                Category parentCategory = categoryListMap
                        .computeIfAbsent(parentName, n -> new Category(parentName, null));

                if (parentCategory.getChildren() == null) {
                    parentCategory.setChildren(new ArrayList<>());
                }
                parentCategory.getChildren().add(category);
                category.setParent(parentCategory);
            }
        }
        return roots;
    }
}
