package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.REMOVE_ELEMENT;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_REMOVE_ELEMENT_MENU;

/**
 * Команда для удаления элемента (категории) из дерева категорий.
 *
 * <p>
 * Этот класс обрабатывает команды на удаление категорий, проверяет правильность
 * формата команды и взаимодействует с сервисом управления деревом категорий для
 * выполнения операции удаления.
 * </p>
 */
@Component
public class CommandRemoveElement extends AbstractCommand {
    /**
     * Регулярное выражение для проверки формата команды удаления элемента.
     * <p>
     * Формат команды должен соответствовать шаблону:
     * "/removeElement <имя_категории>", где "имя_категории" — это строка, заключенная в угловые скобки.
     * </p>
     */
    private static final String REGEX = REMOVE_ELEMENT + "\\s+<[^<>]+>";
    /**
     * Сервис для управления деревом категорий.
     * <p>
     * Этот объект используется для выполнения операций удаления категорий из дерева.
     * Он инкапсулирует логику работы с данными категорий и обеспечивает
     * взаимодействие с хранилищем данных.
     * </p>
     */
    private final CategoryTreeServicesImpl categoryTreeServices;

    /**
     * Конструктор для создания экземпляра команды удаления элемента.
     *
     * <p>
     * Этот конструктор инициализирует новый объект команды удаления элемента,
     * устанавливая текст команды и связывая его с сервисом управления деревом категорий.
     * </p>
     *
     * @param categoryTreeServices Сервис для управления деревом категорий,
     * необходимый для выполнения операций удаления категорий.
     * Он предоставляет методы для взаимодействия с данными категорий и
     * обработки логики удаления.
     */
    public CommandRemoveElement(CategoryTreeServicesImpl categoryTreeServices) {
        super(REMOVE_ELEMENT);
        this.categoryTreeServices = categoryTreeServices;
    }

    /**
     * Выполняет команду удаления категории из дерева категорий.
     *
     * <p>
     * Этот метод обрабатывает входящее сообщение, проверяет его на соответствие
     * ожидаемому формату команды удаления элемента, извлекает имя категории
     * и вызывает сервис для удаления указанной категории. В зависимости от
     * результата операции, отправляет соответствующее сообщение в чат.
     * </p>
     *
     * @param chatId Идентификатор чата, в который будет отправлено сообщение
     * о результате выполнения команды.
     * @param message Сообщение, которое инициировало выполнение команды.
     * Содержит текст с указанием категории для удаления.
     * @param bot Экземпляр {@link TelegramLongPollingBot}, используемый для
     * отправки сообщений в Telegram.
     * @throws TelegramApiException Если возникает ошибка при отправке сообщения
     * через Telegram API.
     */
    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        String text = message.getText();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            bot.execute(new SendMessage(chatId, TEXT_REMOVE_ELEMENT_MENU));
        }
        String nameCategory = text.substring(text.indexOf("<") + 1, text.length() - 1);
        Category category = categoryTreeServices.removeElement(nameCategory);
        if (category == null) {
            bot.execute(new SendMessage(chatId, "Категорию удалить не получилось"));
        } else {
            bot.execute(new SendMessage(chatId, String.format("Удалена категория: %s", category.getName())));
        }
    }
}
