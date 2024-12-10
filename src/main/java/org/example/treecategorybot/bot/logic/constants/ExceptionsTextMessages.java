package org.example.treecategorybot.bot.logic.constants;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.UPLOAD;

public class ExceptionsTextMessages {
    public static final String HEADER_EXCEPTION_MESSAGE
            = String.format("""
            Заголовок загружаемого документа не соответствует шаблону. 
            Выберете команду %s для повторной загрузки""", UPLOAD);
    public static final String REPEAT_EXCEPTION_MESSAGE
            = String.format("""
            В документе в колонке категория есть повторяющиеся категории.
            Выберете команду %s для повторной загрузки.
            """, UPLOAD);
    public static final String PARENT_EXCEPTION_MESSAGE =
            String.format("""
            Наименование родителя отсутствует в ранее добавленных категориях
            Выберете команду %s для повторной загрузки.
            """, UPLOAD);
    public static final String EMPTY_VALUE_IN_CELL_MESSAGE =
            String.format("""
            Не заполнена ячейка в колонке категория.
            Выберете команду %s для повторной загрузки.
            """, UPLOAD);
    public static final String EMPTY_VALUE_IN_CELL_TITLE_MESSAGE =
            String.format("""
            Не заполнена ячейка в шапке документа.
            Выберете команду %s для повторной загрузки.
            """, UPLOAD);
}
