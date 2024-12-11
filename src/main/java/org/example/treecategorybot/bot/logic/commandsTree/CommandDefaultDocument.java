package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.logic.exceptions.DownloadDocumentException;
import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.example.treecategorybot.bot.services.FileHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.List;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.DEFAULT_DOCUMENT;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.UPLOAD;

/**
 * Команда для загрузки документа по умолчанию и добавления категорий из него.
 *
 * <p>
 * Этот класс обрабатывает загрузку документа, извлекает категории из загруженного
 * файла и сохраняет их в дерево категорий. В случае ошибок при загрузке файла
 * или добавлении категорий, пользователю отправляется соответствующее сообщение.
 * </p>
 */
@Component
public class CommandDefaultDocument extends AbstractCommand {
    /**
     * Обработчик файлов, используемый для загрузки и обработки документов.
     */
    private final FileHandler fileHandler;
    /**
     * Сервис для работы с деревом категорий, обеспечивающий сохранение и управление категориями.
     */
    private final CategoryTreeServicesImpl categoryTreeServices;

    /**
     * Конструктор для создания экземпляра команды загрузки документа по умолчанию.
     *
     * <p>
     * Этот конструктор инициализирует необходимые зависимости для работы команды, включая
     * обработчик файлов для загрузки документов и сервис для управления категориями.
     * </p>
     *
     * @param fileHandler Объект, отвечающий за обработку файлов, включая загрузку и извлечение данных из документов.
     * @param categoryTreeServices Сервис, обеспечивающий управление и сохранение категорий в дереве.
     */
    public CommandDefaultDocument(FileHandler fileHandler, CategoryTreeServicesImpl categoryTreeServices) {
        super(DEFAULT_DOCUMENT);
        this.fileHandler = fileHandler;
        this.categoryTreeServices = categoryTreeServices;
    }

    /**
     * Выполняет команду загрузки документа и добавления категорий из него в структуру.
     *
     * <p>
     * Этот метод обрабатывает входящее сообщение, проверяет наличие документа,
     * загружает категории из документа Excel и сохраняет их в сервисе категорий.
     * Если загрузка успешна, отправляется сообщение об успешной загрузке.
     * В случае возникновения ошибок отправляются соответствующие сообщения об ошибках.
     * </p>
     *
     * @param chatId Идентификатор чата, в который будет отправлено сообщение о результате выполнения команды.
     * @param message Сообщение, содержащее документ, который необходимо обработать.
     * @param bot Экземпляр бота, используемый для отправки сообщений в Telegram.
     * @throws TelegramApiException Если возникает ошибка при взаимодействии с Telegram API.
     */
    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        if (message.hasDocument()) {
            String fileId = message.getDocument().getFileId();
            try {
                List<Category> categories = fileHandler.getCategoriesFromExcel(fileId);
                categoryTreeServices.saveAll(categories);
                bot.execute(new SendMessage(chatId, "Документ успешно загружен."));

            } catch (DownloadDocumentException e) {
                SendMessage sendMessage = new SendMessage(chatId, e.getMessage());
                bot.execute(sendMessage);
            } catch (Exception e) {
                SendMessage sendMessage = new SendMessage(chatId, "Ошибка добавления категорий. " +
                        "Проверьте расширение и правильность заполнения передаваемого документа. " +
                        "Возможно некоторые категории уже добавлены. Выберете команду " + UPLOAD
                        + " для повторной загрузки.");
                bot.execute(sendMessage);
            }
        }
    }
}
