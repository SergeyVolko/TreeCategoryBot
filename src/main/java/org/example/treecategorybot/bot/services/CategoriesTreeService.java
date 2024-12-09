package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.entities.Category;

import java.util.List;

public interface CategoriesTreeService {
    String viewTree();
    Category addElement(String nameCategory);
    Category addElementToParent(String parentNameCategory, String nameCategory);
    Category removeElement(String nameCategory);
    void getHelp();
    List<Category> getAllCategories();
    void saveAll(List<Category> categories);
    void removeAll();
}
