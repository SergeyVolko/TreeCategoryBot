package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.logic.constants.TextCommands;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_START_MENU;

/**
 * Команда, отвечающая за отправку стартового меню пользователю.
 *
 * <p>
 * Этот класс наследует от {@link AbstractCommand} и реализует
 * команду для отправки стартового меню пользователю, когда
 * он взаимодействует с ботом.
 * </p>
 */
@Component
public class CommandStart extends AbstractCommand {

    /**
     * Конструктор для создания экземпляра команды стартового меню.
     *
     * <p>
     * Этот конструктор вызывает конструктор суперкласса {@link AbstractCommand}
     * с параметром {@link TextCommands#START}, что инициализирует команду
     * для обработки стартового меню в боте.
     * </p>
     */
    public CommandStart() {
        super(START);
    }

    /**
     * Выполняет команду отправки стартового меню в чат.
     *
     * <p>
     * Этот метод отправляет стартовое меню пользователю в ответ на
     * входящее сообщение. Он использует {@link TelegramLongPollingBot}
     * для отправки сообщения с текстом стартового меню. Метод
     * формирует объект {@link SendMessage} с идентификатором чата и
     * текстом стартового меню, а затем выполняет его через бота.
     * </p>
     *
     * @param chatId Идентификатор чата, в который будет отправлено
     * стартовое меню.
     * @param message Сообщение, которое инициировало выполнение команды.
     * В данном случае оно не используется, так как метод просто отправляет
     * стартовое меню.
     * @param bot Экземпляр {@link TelegramLongPollingBot}, используемый
     * для отправки сообщения в Telegram.
     * @throws TelegramApiException Если возникает ошибка при отправке
     * сообщения через Telegram API.
     */
    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        bot.execute(new SendMessage(chatId, TEXT_START_MENU));
    }
}
