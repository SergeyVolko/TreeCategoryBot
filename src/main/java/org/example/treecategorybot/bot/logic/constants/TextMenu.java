package org.example.treecategorybot.bot.logic.constants;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;

public class TextMenu {

    public static final String TEXT_START_MENU = String.format("""
            Добро пожаловать в бот управляющим иерархическим деревом категорий.
            Введите команду %s для просмотра возможностей бота.
            """, HELP);
    public static final String TEXT_HELP_MENU = String.format(
            """
            Эта инструкция предоставляет основные команды управления ботом:
            %s - Отображения дерева в структурированном виде
            %s <название элемента> - добавление корневого элемента
            %s <родительский элемент> <дочерний элемент> - добавление элемента родительскому элементу
            %s <название элемента> - удаление элемента вместе с дочерними элементами
            %s - вызов текущего руководства ботом
            %s - скачать документ excel с деревом категорий
            %s - загрузить категории в базу через excel документ
            """, VIEW_TREE, ADD_ELEMENT, ADD_ELEMENT, REMOVE_ELEMENT, HELP, DOWNLOAD, UPLOAD);

    public static final String TEXT_ADD_ELEMENT_MENU = String.format("""
                    Введенные данные не соответствуют шаблону:
                    %s <название элемента> - шаблон для добавления корневого элемента
                    %s <родительский элемент> <дочерний элемент> - шаблон добавления дочернего элемента
                    """, ADD_ELEMENT, ADD_ELEMENT);

    public static final String TEXT_REMOVE_ELEMENT_MENU = String.format("""
             Введенные данные не соответствуют шаблону:
             %s <название элемента> - шаблон для удаления элемента
            """, REMOVE_ELEMENT);

    public static final String TEXT_UPLOAD_DOCUMENT = String.format("""
            Загрузите документ excel в формате по шаблону:
            %s
            %s - отказаться загружать документ
            """, getTemplate(), EXIT_UPLOAD);

    private static String getTemplate() {
        StringBuilder table = new StringBuilder();
        table.append("<pre>");
        table.append("┌───────────┬───────────────────┐\n");
        table.append("│ Категория │ Категория родитель│\n");
        table.append("└───────────┴───────────────────┘\n");
        table.append("</pre>");
        return table.toString();
    }
}
