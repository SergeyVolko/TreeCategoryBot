package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.logic.constants.TextCommands;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.EXIT_UPLOAD;

/**
 * Команда для отмены загрузки документа.
 *
 * <p>
 * Класс, реализующий команду для отмены загрузки документа.
 * Этот класс реализует команду, которая отправляет сообщение пользователю о том, что загрузка документа была отменена.
 * </p>
 */
@Component
public class CommandExitUpload extends AbstractCommand {

    /**
     * Конструктор для создания экземпляра команды отмены загрузки документа.
     *
     * <p>
     * Инициализирует новый объект {@link CommandExitUpload} с текстом команды,
     * определенным в {@link TextCommands#EXIT_UPLOAD}.
     * </p>
     */
    public CommandExitUpload() {
        super(EXIT_UPLOAD);
    }

    /**
     * Выполняет команду отмены загрузки документа.
     *
     * <p>
     * Этот метод отправляет сообщение пользователю в чат, информируя его о том, что загрузка документа была отменена.
     * </p>
     *
     * @param chatId Идентификатор чата, в который будет отправлено сообщение об отмене загрузки.
     * @param message Сообщение, вызвавшее выполнение команды (может быть использовано для дополнительной информации).
     * @param bot Объект бота, который используется для отправки сообщения в Telegram.
     * @throws TelegramApiException Если возникает ошибка при взаимодействии с Telegram API, например, если
     * не удается отправить сообщение.
     */
    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
         bot.execute(new SendMessage(chatId, "Отменили загрузку документа"));
    }
}
