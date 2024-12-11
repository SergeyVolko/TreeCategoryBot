package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.logic.constants.TextCommands;
import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.REMOVE_All;

/**
 * Команда для удаления всех категорий из дерева категорий.
 *
 * <p>
 * Этот класс реализует команду, которая удаляет все категории
 * и отправляет пользователю сообщение об успешном выполнении операции.
 * </p>
 */
@Component
public class CommandRemoveAll extends AbstractCommand {
    /**
     * Сервис для управления деревом категорий.
     *
     * <p>
     * Это поле используется для выполнения операций, связанных с деревом категорий,
     * таких как удаление всех категорий. Сервис инжектируется через конструктор
     * и позволяет классу {@link CommandRemoveAll} взаимодействовать с логикой
     * управления категориями.
     * </p>
     */
    private final CategoryTreeServicesImpl categoryTreeServices;

    /**
     * Конструктор для создания экземпляра команды удаления всех категорий.
     *
     * <p>
     * Этот конструктор инициализирует команду с заданным текстом команды
     * {@link TextCommands#REMOVE_All} и принимает сервис для управления
     * деревом категорий, который будет использоваться для выполнения
     * операций удаления.
     * </p>
     *
     * @param categoryTreeServices Сервис для управления деревом категорий,
     * необходимый для выполнения операции удаления всех категорий.
     */
    public CommandRemoveAll(CategoryTreeServicesImpl categoryTreeServices) {
        super(REMOVE_All);
        this.categoryTreeServices = categoryTreeServices;
    }

    /**
     * Выполняет команду удаления всех категорий.
     *
     * <p>
     * Этот метод вызывает сервис для удаления всех категорий и отправляет
     * сообщение в чат с подтверждением успешного выполнения операции.
     * </p>
     *
     * @param chatId Идентификатор чата, в который будет отправлено сообщение
     * о результате выполнения команды.
     * @param message Сообщение, которое инициировало выполнение команды.
     * Может содержать дополнительную информацию о контексте выполнения.
     * @param bot Экземпляр {@link TelegramLongPollingBot}, который используется
     * для отправки сообщений в Telegram.
     * @throws TelegramApiException Если возникает ошибка при отправке сообщения
     * через Telegram API.
     */
    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        categoryTreeServices.removeAll();
        bot.execute(new SendMessage(chatId, "Все категории удалены"));
    }
}
