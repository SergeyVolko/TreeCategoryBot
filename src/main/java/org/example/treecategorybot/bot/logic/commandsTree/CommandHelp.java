package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.logic.constants.TextCommands;
import org.example.treecategorybot.bot.logic.constants.TextMenu;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.HELP;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_HELP_MENU;

/**
 * Команда для предоставления пользователю справочной информации.
 *
 * <p>
 * Этот класс реализует команду, которая отправляет пользователю меню помощи
 * с информацией о доступных командах и их использовании.
 * </p>
 */
@Component
public class CommandHelp extends AbstractCommand {

    /**
     * Конструктор команды помощи.
     *
     * <p>
     * Инициализирует новый объект {@link CommandHelp} с текстом команды, определенным в {@link TextCommands#HELP}.
     * </p>
     */
    public CommandHelp() {
        super(HELP);
    }

    /**
     * Выполняет команду, отправляя сообщение с помощью в указанный чат.
     *
     * <p>
     * Этот метод отправляет пользователю сообщение, содержащее текст меню помощи,
     * который определяется в {@link TextMenu#TEXT_HELP_MENU}.
     * Метод использует объект бота для выполнения операции отправки сообщения.
     * </p>
     *
     * @param chatId Идентификатор чата, в который будет отправлено сообщение с помощью.
     * @param message Сообщение, вызвавшее выполнение команды (может быть использовано для дополнительной информации).
     * @param bot Объект бота, который используется для отправки сообщения в Telegram.
     * @throws TelegramApiException Если возникает ошибка при взаимодействии с Telegram API,
     * например, если не удается отправить сообщение.
     */
    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        bot.execute(new SendMessage(chatId, TEXT_HELP_MENU));
    }
}
