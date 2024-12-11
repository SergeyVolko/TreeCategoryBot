package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.logic.constants.TextCommands;
import org.example.treecategorybot.bot.logic.constants.TextMenu;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.Set;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_UPLOAD_DOCUMENT;

/**
 * Команда, отвечающая за загрузку документа через бота.
 *
 * <p>
 * Этот класс наследует от {@link AbstractCommand} и реализует
 * команду для показа меню загрузки документа пользователем.
 * </p>
 */
@Component
public class CommandUpload extends AbstractCommand{
    /**
     * Набор дочерних команд, доступных в контексте команды загрузки документа.
     *
     * <p>
     * Этот набор содержит команды, которые могут быть выполнены
     * пользователем после инициации загрузки документа.
     * Включает в себя следующие команды:
     * <ul>
     *     <li>{@link TextCommands#DEFAULT_DOCUMENT} - команда для
     *     загрузки документа по умолчанию.</li>
     *     <li>{@link TextCommands#EXIT_UPLOAD} - команда для выхода
     *     из режима загрузки.</li>
     *     <li>{@link TextCommands#START} - команда для начала
     *     нового взаимодействия с ботом.</li>
     * </ul>
     * </p>
     */
    private static Set<String> childCommands = Set.of(
            DEFAULT_DOCUMENT,
            EXIT_UPLOAD,
            START
    );

    /**
     * Конструктор для создания экземпляра команды загрузки документа.
     *
     * <p>
     * Этот конструктор инициализирует команду загрузки документа,
     * задавая ей уникальный идентификатор {@code UPLOAD} и набор
     * дочерних команд {@code childCommands}.
     * </p>
     *
     * <p>
     * Конструктор вызывает родительский конструктор {@link AbstractCommand#AbstractCommand(String, Set)}
     * для инициализации базовых полей класса {@link AbstractCommand}.
     * </p>
     */
    public CommandUpload() {
        super(UPLOAD, childCommands);
    }

    /**
     * Выполняет команду загрузки документа.
     *
     * <p>
     * Этот метод отправляет пользователю сообщение с текстом {@link TextMenu#TEXT_UPLOAD_DOCUMENT}
     * в формате HTML, информируя его о необходимости загрузить документ.
     * </p>
     *
     * @param chatId Идентификатор чата, в который будет отправлено сообщение.
     * @param message Сообщение, которое вызвало выполнение этой команды.
     *                Может содержать дополнительную информацию о состоянии
     *                взаимодействия с пользователем.
     * @param bot Экземпляр {@link TelegramLongPollingBot}, используемый для
     *            отправки сообщения пользователю.
     * @throws TelegramApiException Если происходит ошибка при отправке сообщения
     *                               через Telegram API.
     */
    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(chatId,TEXT_UPLOAD_DOCUMENT);
        sendMessage.enableHtml(true);
        bot.execute(sendMessage);
    }
}
