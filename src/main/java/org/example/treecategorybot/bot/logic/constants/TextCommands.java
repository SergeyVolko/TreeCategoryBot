package org.example.treecategorybot.bot.logic.constants;

/**
 * Класс, содержащий текстовые команды, используемые в боте.
 *
 * <p>
 * Этот класс предоставляет статические константы, представляющие команды,
 * которые могут быть использованы пользователями для взаимодействия с ботом.
 * Команды включают в себя действия, такие как просмотр дерева категорий,
 * добавление и удаление элементов, загрузка и скачивание документов,
 * а также получение справки.
 * </p>
 */
public class TextCommands {
    /** Команда для начала взаимодействия с ботом. */
    public static final String START = "/start";

    /** Команда для просмотра дерева категорий. */
    public static final String VIEW_TREE = "/viewTree";

    /** Команда для добавления нового элемента в дерево категорий. */
    public static final String ADD_ELEMENT = "/addElement";

    /** Команда для удаления элемента из дерева категорий. */
    public static final String REMOVE_ELEMENT = "/removeElement";

    /** Команда для получения справки о доступных командах. */
    public static final String HELP = "/help";

    /** Команда для скачивания документа. */
    public static final String DOWNLOAD = "/download";

    /** Команда для загрузки документа. */
    public static final String UPLOAD = "/upload";

    /** Текст по умолчанию для сообщений. */
    public static final String DEFAULT_TEXT = "text";

    /** Название документа по умолчанию. */
    public static final String DEFAULT_DOCUMENT = "document";

    /** Команда для выхода из режима загрузки. */
    public static final String EXIT_UPLOAD = "/exitUpload";

    /** Команда для удаления всех элементов из дерева категорий. */
    public static final String REMOVE_All = "/removeAll";
}
