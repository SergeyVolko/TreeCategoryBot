package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.repository.CategoryRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сервиса для работы с деревом категорий.
 * Предоставляет методы для управления и отображения категорий.
 */
@Service
public class CategoryTreeServicesImpl implements CategoriesTreeService {

    /**
     * Репозиторий для работы с категориями.
     * Используется для выполнения операций сохранения, поиска и удаления категорий в хранилище данных.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Конструктор для инициализации услуги категорий с заданным репозиторием.
     *
     * @param categoryRepository репозиторий для работы с категориями
     */
    public CategoryTreeServicesImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    /**
     * Возвращает строковое представление дерева категорий.
     * <p>
     * Этот метод извлекает все категории из базы данных. Если категории отсутствуют,
     * возвращается сообщение, указывающее, что дерево категорий пустое. В противном случае
     * метод формирует дерево категорий и возвращает его в виде многострочной строки.
     *
     * @return строковое представление дерева категорий. Если дерево пустое,
     * возвращается сообщение "Дерево категорий пустое.";
     */
    @Override
    public String viewTree() {
        List<Category> categoriesFromDB = getAllCategories();
        if (categoriesFromDB.isEmpty()) {
            return "Дерево категорий пустое.";
        }
        List<Category> categories = getTreeCategories(categoriesFromDB);
        StringBuilder builder = new StringBuilder("Текущее дерево категорий:\n");
        recurseTree(categories, 0, builder);
        return builder.toString();
    }

    /**
     * Добавляет новую категорию с указанным именем, если категория с таким именем не существует.
     * <p>
     * Метод проверяет наличие категории с данным именем в репозитории. Если категория
     * уже существует, метод возвращает null. Если категория не найдена, создается новая
     * категория и сохраняется в репозитории.
     *
     * @param nameCategory имя добавляемой категории, не должно быть пустым или null.
     * @return новую категорию, если она была успешно добавлена, или null, если категория
     * с таким именем уже существует.
     *
     * @throws IllegalArgumentException если nameCategory пустой или null.
     */
    @Override
    public Category addElement(String nameCategory) {
        if (categoryRepository.findCategoryByName(nameCategory) != null) {
            return null;
        }
        return categoryRepository.save(new Category(nameCategory));
    }

    /**
     * Добавляет новую категорию в указанную родительскую категорию, если родительская категория
     * существует и категория с таким именем еще не создана.
     * <p>
     * Метод проверяет, существует ли родительская категория с указанным именем. Если
     * родительская категория не найдена или категория с указанным именем уже существует,
     * метод возвращает null. В противном случае создается новая категория,
     * которая становится дочерней для указанной родительской категории.
     *
     * @param parentNameCategory имя родительской категории, в которую нужно добавить новую.
     * @param nameCategory имя новой категории, которую необходимо добавить.
     * @return Добавленная категория, если она была успешно создана, или null, если
     * родительская категория не найдена или категория с таким именем уже существует.
     *
     * @throws IllegalArgumentException если parentNameCategory или nameCategory пустые
     * или null.
     */
    @Transactional
    @Override
    public Category addElementToParent(String parentNameCategory, String nameCategory) {
        Category parentCategory = categoryRepository.findCategoryByName(parentNameCategory);
        Category category = categoryRepository.findCategoryByName(nameCategory);
        if (parentCategory == null || category != null) {
            return null;
        }
        Category addedCategory = new Category(nameCategory, parentCategory);
        parentCategory.getChildren().add(addedCategory);

        return categoryRepository.save(addedCategory);
    }

    /**
     * Удаляет категорию с указанным именем из репозитория.
     * <p>
     * Метод осуществляет поиск категории по имени в репозитории. Если категория не
     * найдена, возвращает null. Если категория была найдена и имеет родительскую
     * категорию, метод сначала отсоединяет её от родительской категории, устанавливая
     * родительскую категорию в null, а затем удаляет категорию из репозитория.
     *
     * @param nameCategory имя категории, которую необходимо удалить.
     * @return Удаленная категория, если категория с указанным именем была найдена и
     * успешно удалена, или null, если категория не найдена.
     *
     * @throws IllegalArgumentException если nameCategory пустое или null.
     */
    @Override
    public Category removeElement(String nameCategory) {
        Category deletedCategory = categoryRepository.findCategoryByName(nameCategory);
        if (deletedCategory == null) {
            return null;
        }
        if (deletedCategory.getParent() != null) {
            deletedCategory.setParent(null);
            categoryRepository.save(deletedCategory);
        }
        categoryRepository.deleteById(deletedCategory.getId());
        return deletedCategory;
    }

    /**
     * Получает все категории из репозитория.
     * <p>
     * Этот метод предназначен для извлечения списка всех доступных категорий,
     * хранящихся в базе данных. Если нет категорий, будет возвращен пустой список.
     *
     * @return список всех категорий. Никогда не возвращает null.
     * @throws DataAccessException если произошла ошибка доступа к данным
     *                              при извлечении категорий из репозитория.
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Сохраняет все переданные категории в репозитории.
     * <p>
     * Этот метод принимает список категорий и сохраняет их в базе данных.
     * Если категория с таким же идентификатором уже существует, она будет
     * обновлена. В противном случае будет создана новая категория.
     *
     * @param categories список категорий, которые необходимо сохранить.
     *                   Значение не должно быть null. Если передан пустой список,
     *                   метод выполнится без ошибок, однако категории не будут сохранены.
     * @throws DataAccessException если произошла ошибка доступа к данным
     *                              при сохранении категорий в репозитории.
     */
    @Override
    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    /**
     * Удаляет все категории из репозитория.
     * <p>
     * Этот метод предназначен для удаления всех категорий, хранящихся
     * в базе данных. После вызова данного метода все записи категорий
     * будут удалены, и восстановить их будет невозможно.
     *
     * @throws DataAccessException если произошла ошибка доступа к данным
     *                              при удалении категорий из репозитория.
     */
    @Override
    public void removeAll() {
        categoryRepository.deleteAll();
    }

    /**
     * Рекурсивно обходит и строит представление иерархии категорий в виде строки.
     * <p>
     * Этот метод принимает список категорий и формирует представление
     * их иерархии. Каждая категория будет отображаться с соответствующим
     * отступом на основе её уровня в иерархии. Если категория имеет родительскую
     * категорию, она будет предваряться знаком тире (-).
     *
     * @param categories список категорий для обхода. Не должен быть null.
     * @param level текущий уровень вложенности (например, 0 для корневых категорий).
     *              Используется для определения отступов в выводе.
     * @param builder объект StringBuilder, в который будет записываться
     *                результирующее представление категорий. Не должен быть null.
     */
    private void recurseTree(List<Category> categories, int level, StringBuilder builder) {
        for (Category category : categories) {
            if (category.getParent() != null) {
                builder.append("     ".repeat(level));
                builder.append("-");
            }
            builder.append(category.getName()).append("\n");
            List<Category> children = category.getChildren();
            if (!children.isEmpty()) {
                recurseTree(children, level + 1, builder);
            }
        }
    }

    /**
     * Строит иерархическую структуру категорий из плоского списка категорий.
     * <p>
     * Этот метод принимает список категорий и создает иерархию, устанавливая
     * для каждой категории её дочерние элементы. Категории, у которых нет
     * родительских категорий, будут возвращены в виде корневых категорий.
     * В процессе выполнения методу назначаются дочерние категории через
     * соответствующие родительские категории.
     *
     * @param categories список категорий для построения иерархии. Не должен быть null.
     * @return список корневых категорий, без родительских категорий.
     *         Корневые категории содержат ссылки на свои дочерние элементы.
     */
    private List<Category> getTreeCategories(List<Category> categories) {
        return categories.stream()
                .peek(category -> {
                    category.setChildren(new ArrayList<>());
                    Category parent = category.getParent();
                    if (parent != null) {
                        parent.getChildren().add(category);
                    }
                }).filter(category -> category.getParent() == null)
                .toList();
    }
}
