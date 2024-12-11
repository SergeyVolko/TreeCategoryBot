package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.example.treecategorybot.bot.services.ExcelGenerator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.File;
import java.io.IOException;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;

/**
 * Команда для выгрузки документа Excel с категориями.
 *
 * <p>
 * Этот класс отвечает за генерацию Excel-документа с категориями и его отправку пользователю в Telegram.
 * Он использует {@link ExcelGenerator} для создания документа и {@link CategoryTreeServicesImpl} для работы с категориями.
 * </p>
 */
@Component
public class CommandDownload extends AbstractCommand {
    /**
     * Генератор Excel-документов, используемый для создания Excel-файлов с категориями.
     *
     * <p>
     * Это поле отвечает за взаимодействие с логикой генерации Excel-документов.
     * Используется в методе {@link #execute(String, Message, TelegramLongPollingBot)} для создания
     * Excel-документа, который будет отправлен пользователю.
     * </p>
     */
    private final ExcelGenerator excelGenerator;

    /**
     * Конструктор для создания экземпляра команды загрузки документа.
     *
     * <p>
     * Инициализирует новый объект {@link CommandDownload} с заданным генератором Excel.
     * </p>
     *
     * @param excelGenerator Генератор Excel-документов, который будет использоваться для
     *                       создания Excel-файлов с категориями.
     */
    public CommandDownload(ExcelGenerator excelGenerator) {
        super(DOWNLOAD);
        this.excelGenerator = excelGenerator;
    }

    /**
     * Выполняет команду загрузки документа, генерируя Excel-файл и отправляя его в чат.
     *
     * <p>
     * Этот метод использует {@link ExcelGenerator} для создания Excel-документа и отправляет его
     * пользователю в Telegram через указанный {@link TelegramLongPollingBot}. Если возникает ошибка
     * при генерации или отправке документа, пользователю отправляется сообщение об ошибке.
     * </p>
     *
     * @param chatId Идентификатор чата, в который будет отправлен документ.
     * @param message Сообщение, вызвавшее выполнение команды (может быть использовано для дополнительной информации).
     * @param bot Объект бота, который используется для отправки документа и сообщений.
     * @throws TelegramApiException Если возникает ошибка при взаимодействии с Telegram API.
     */
    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        try {
            String filePath = excelGenerator.generateExcel();
            SendDocument sendDocument = new SendDocument(chatId, new InputFile(new File(filePath)));
            bot.execute(sendDocument);
        } catch (IOException e) {
            bot.execute(new SendMessage(chatId, "Ошибка выгрузки документа."));
        }
    }
}
