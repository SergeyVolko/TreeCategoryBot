package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.VIEW_TREE;

/**
 * Команда для отображения дерева категорий.
 *
 * <p>
 * Этот класс отвечает за выполнение команды, которая отправляет пользователю
 * текущее состояние дерева категорий. Он наследуется от {@link AbstractCommand}
 * и использует {@link CategoryTreeServicesImpl} для получения данных о дереве.
 * </p>
 */
@Component
public class CommandViewTree extends AbstractCommand {
    /**
     * Сервис для работы с деревом категорий.
     *
     * <p>
     * Это поле представляет собой экземпляр {@link CategoryTreeServicesImpl},
     * который используется для получения и обработки данных о дереве категорий.
     * Он предоставляет методы для извлечения информации о категориях,
     * что позволяет команде отображать актуальное состояние дерева.
     * </p>
     */
    private final CategoryTreeServicesImpl categoryTreeServices;

    /**
     * Конструктор для создания экземпляра команды отображения дерева категорий.
     *
     * <p>
     * Этот конструктор инициализирует команду, устанавливая текстовую метку команды
     * с помощью {@link AbstractCommand#AbstractCommand(String)} и
     * связывая сервис для работы с деревом категорий.
     * </p>
     *
     * @param categoryTreeServices Сервис для работы с деревом категорий, который
     *                            предоставляет методы для получения и обработки
     *                            данных о категориях.
     */
    public CommandViewTree(CategoryTreeServicesImpl categoryTreeServices) {
        super(VIEW_TREE);
        this.categoryTreeServices = categoryTreeServices;
    }

    /**
     * Выполняет команду отображения дерева категорий и отправляет результат в чат.
     *
     * <p>
     * Этот метод принимает идентификатор чата и сообщение, а затем использует
     * {@link TelegramLongPollingBot} для отправки сообщения с представлением
     * дерева категорий в указанный чат.
     * </p>
     *
     * @param chatId Идентификатор чата, в который будет отправлено сообщение.
     * @param message Сообщение, вызвавшее выполнение команды.
     *                В данном методе это сообщение не используется,
     *                но может быть полезно для дальнейшей обработки.
     * @param bot Экземпляр {@link TelegramLongPollingBot}, используемый для
     *            отправки сообщения в чат.
     * @throws TelegramApiException Если происходит ошибка при отправке сообщения
     *                               через Telegram API.
     */
    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        bot.execute(new SendMessage(chatId, categoryTreeServices.viewTree()));
    }
}
