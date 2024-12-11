package org.example.treecategorybot.bot.logic.commandsTree;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Интерфейс для представления команды, которая может быть выполнена
 * в контексте бота Telegram.
 *
 * <p>
 * Каждая команда должна реализовывать метод {@link #execute(String, Message, TelegramLongPollingBot)},
 * который определяет логику выполнения команды. Этот метод принимает идентификатор чата,
 * сообщение и экземпляр бота, что позволяет командам взаимодействовать с пользователями
 * и отправлять им ответы.
 * </p>
 */
public interface Command {
    /**
     * Выполняет команду в контексте указанного чата и сообщения.
     *
     * <p>
     * Метод должен содержать логику, необходимую для обработки команды.
     * Он может отправлять сообщения в чат, обновлять состояние бота и
     * выполнять другие действия в зависимости от специфики команды.
     * </p>
     *
     * @param chatId идентификатор чата, в котором была отправлена команда.
     * @param message сообщение, содержащее команду и дополнительные данные.
     * @param bot экземпляр бота, который позволяет взаимодействовать с Telegram API.
     * @throws TelegramApiException если произошла ошибка при взаимодействии с Telegram API.
     */
    void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException;
}
