package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.entities.Category;
import java.util.List;

/**
 * Сервис для управления деревом категорий.
 *
 * <p>
 * Этот интерфейс определяет методы для работы с деревом категорий, включая
 * добавление, удаление и просмотр категорий.
 * </p>
 */
public interface CategoriesTreeService {
    /**
     * Возвращает представление дерева категорий в виде строки.
     *
     * @return Строковое представление дерева категорий.
     */
    String viewTree();

    /**
     * Добавляет новую категорию в дерево категорий.
     *
     * @param nameCategory Имя категории, которую необходимо добавить.
     * @return Объект {@link Category}, который был добавлен в дерево.
     */
    Category addElement(String nameCategory);

    /**
     * Добавляет новую категорию как дочерний элемент к указанной родительской категории.
     *
     * @param parentNameCategory Имя родительской категории.
     * @param nameCategory Имя категории, которую необходимо добавить.
     * @return Объект {@link Category}, который был добавлен как дочерний элемент.
     * @throws IllegalArgumentException Если родительская категория не найдена.
     */
    Category addElementToParent(String parentNameCategory, String nameCategory);

    /**
     * Удаляет категорию из дерева категорий.
     *
     * @param nameCategory Имя категории, которую необходимо удалить.
     * @return Объект {@link Category}, который был удален.
     * @throws IllegalArgumentException Если категория не найдена.
     */
    Category removeElement(String nameCategory);

    /**
     * Получает список всех категорий в дереве.
     *
     * @return Список объектов {@link Category}, представляющих все категории.
     */
    List<Category> getAllCategories();

    /**
     * Сохраняет все переданные категории в хранилище.
     *
     * @param categories Список категорий, которые необходимо сохранить.
     */
    void saveAll(List<Category> categories);

    /**
     * Удаляет все категории из дерева.
     */
    void removeAll();
}
