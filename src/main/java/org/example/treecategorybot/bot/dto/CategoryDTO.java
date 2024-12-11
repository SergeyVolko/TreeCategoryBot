package org.example.treecategorybot.bot.dto;

/**
 * DTO (Data Transfer Object) для категории.
 * <p>
 * Этот класс представляет собой простой объект, который содержит информацию о категории,
 * включая ее имя и родительскую категорию. Используется для передачи данных между слоями
 * приложения.
 *
 * @param name имя категории.
 * @param parent имя родительской категории. Может быть {@code null}, если у категории нет родителя.
 */
public record CategoryDTO(String name, String parent) {
}
