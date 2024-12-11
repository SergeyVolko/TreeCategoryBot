package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.logic.constants.TextCommands;
import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.ADD_ELEMENT;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_ADD_ELEMENT_MENU;

/**
 * Команда для добавления элемента в категорию.
 *
 * <p>
 * Этот класс реализует логику команды, которая позволяет пользователю
 * добавлять категории в структуру категорий. Команда принимает текстовое
 * сообщение, проверяет его на соответствие определенному шаблону и
 * выполняет добавление элемента в категорию или в родительскую категорию.
 * </p>
 */
@Component
public class CommandAddElement extends AbstractCommand {
    /**
     * Регулярное выражение для проверки формата команды добавления элемента.
     * <p>
     * Шаблон ожидает, что команда будет содержать ключевое слово {@link TextCommands#ADD_ELEMENT},
     * за которым следует пробел и один или два элемента, заключенных в угловые скобки.
     * Пример корректного формата: "/addElement &lt;elementName&gt;"
     * или "/addElement &lt;elementName&gt; &lt;parentElementName&gt".
     * </p>
     */
    private static final String REGEX = ADD_ELEMENT + "\\s+<[^<>]+>(\\s+<[^<>]+>)?";

    /**
     * Сервис для работы с деревом категорий.
     * <p>
     * Этот объект предоставляет методы для добавления элементов в структуру категорий,
     * включая возможность добавления элемента в родительскую категорию.
     * Он используется в методах данного класса для выполнения операций над категориями.
     * </p>
     */
    private final CategoryTreeServicesImpl categoryTreeServices;

    /**
     * Конструктор для создания экземпляра команды добавления элемента.
     *
     * @param categoryTreeServices Сервис для работы с деревом категорий.
     *                             Этот объект используется для выполнения операций
     *                             по добавлению элементов в структуру категорий.
     */
    public CommandAddElement(CategoryTreeServicesImpl categoryTreeServices) {
        super(ADD_ELEMENT);
        this.categoryTreeServices = categoryTreeServices;
    }

    /**
     * Выполняет команду добавления элемента в дерево категорий.
     *
     * <p>
     * Метод принимает идентификатор чата и сообщение от пользователя, проверяет формат
     * команды с помощью регулярного выражения, и добавляет новый элемент в соответствующую
     * категорию. Если формат команды неверный, пользователю отправляется сообщение с меню
     * добавления элемента.
     * </p>
     *
     * @param chatId Идентификатор чата, куда будет отправлено сообщение.
     * @param message Сообщение, содержащее команду добавления элемента.
     * @param bot Объект бота для отправки сообщений пользователю.
     * @throws TelegramApiException Если возникает ошибка при взаимодействии с Telegram API.
     */
    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        String text = message.getText();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            bot.execute(new SendMessage(chatId, TEXT_ADD_ELEMENT_MENU));
            return;
        }
        String[] elements = text.substring(text.indexOf("<") + 1, text.length() - 1)
                .split(">\\s+<");
        Category category;
        if (elements.length == 1) {
            category = categoryTreeServices.addElement(elements[0]);
        } else {
            category = categoryTreeServices.addElementToParent(elements[0], elements[1]);
        }
        if (category == null) {
            bot.execute(new SendMessage(chatId, "Данная категория ранее добавлялась " +
                    "или отсутствует родительская категория"));
        } else {
            bot.execute(new SendMessage(chatId, String.format("Добавлена категория: %s", category.getName())));
        }
    }
}
