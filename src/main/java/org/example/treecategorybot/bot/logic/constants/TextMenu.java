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
            """, VIEW_TREE, ADD_ELEMENT, ADD_ELEMENT, REMOVE_ELEMENT, HELP);

    public static final String TEXT_ADD_ELEMENT_MENU = String.format("""
                    Введенные данные не соответствуют шаблону:
                    %s <название элемента> - шаблон для добавления корневого элемента
                    %s <родительский элемент> <дочерний элемент> - шаблон добавления дочернего элемента
                    """, ADD_ELEMENT, ADD_ELEMENT);

    public static final String TEXT_REMOVE_ELEMENT_MENU = String.format("""
             Введенные данные не соответствуют шаблону:
             %s <название элемента> - шаблон для удаления элемента
            """, REMOVE_ELEMENT);
}
