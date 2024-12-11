package org.example.treecategorybot.bot;

import org.example.treecategorybot.bot.logic.CommandHandlerTree;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Класс, представляющий бота TreeCategoriesBot, который наследуется от {@link TelegramLongPollingBot}.
 * <p>
 * Этот бот отвечает за обработку обновлений, полученных от Telegram, и взаимодействие с пользователями.
 * Он использует {@link CommandHandlerTree} для выполнения команд, отправленных пользователями.
 *
 * <p>
 * Бот инициализируется с использованием токена, полученного из конфигурации приложения.
 * </p>
 */
@Component
public class TreeCategoriesBot extends TelegramLongPollingBot {

    private final CommandHandlerTree commandHandlerTree;

    /**
     * Конструктор для создания экземпляра TreeCategoriesBot.
     *
     * @param botToken токен бота, используемый для аутентификации в Telegram API.
     * @param commandHandlerTree обработчик команд, который будет использоваться для обработки обновлений.
     */
    public TreeCategoriesBot(@Value("${telegrambots.token}") String botToken,
                             CommandHandlerTree commandHandlerTree) {
        super(botToken);
        this.commandHandlerTree = commandHandlerTree;
    }

    /**
     * Обрабатывает полученные обновления от Telegram.
     * <p>
     * Этот метод вызывается, когда бот получает новое обновление, такое как сообщение от пользователя.
     * Он передает обновление в {@link CommandHandlerTree} для обработки команды, связанной с обновлением.
     * Если возникает ошибка при выполнении команды, выбрасывается исключение {@link RuntimeException}.
     *
     * @param update объект {@link Update}, содержащий информацию о новом сообщении или событии,
     *               которое бот должен обработать. Включает данные о сообщении, отправителе,
     *               чате и других параметрах.
     *
     * @throws RuntimeException если возникает ошибка при выполнении команды,
     *                          вызванная {@link TelegramApiException} в процессе обработки.
     */
    @Override
    public void onUpdateReceived(Update update) {
        try {
            commandHandlerTree.executeCommand(update, this);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает имя бота, используемое для его идентификации в Telegram.
     * <p>
     * Этот метод возвращает строку, представляющую имя бота, которое будет
     * отображаться в Telegram и использоваться для взаимодействия с пользователями.
     * Имя бота должно быть уникальным в пределах платформы Telegram.
     *
     * @return имя бота в виде строки, например, "tree_category_rusobraz_bot".
     */
    @Override
    public String getBotUsername() {
        return "tree_category_rusobraz_bot";
    }
}
