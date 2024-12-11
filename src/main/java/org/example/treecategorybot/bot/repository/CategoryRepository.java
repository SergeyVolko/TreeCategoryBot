package org.example.treecategorybot.bot.repository;

import org.example.treecategorybot.bot.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями {@link Category}.
 *
 * <p>
 * Этот интерфейс расширяет {@link JpaRepository}, предоставляя стандартные методы для
 * выполнения операций CRUD (создание, чтение, обновление, удаление) с сущностями
 * {@link Category}. Он также включает метод для поиска категории по имени.
 * </p>
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
}
