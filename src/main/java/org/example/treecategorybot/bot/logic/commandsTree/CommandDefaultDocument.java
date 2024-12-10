package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.logic.exceptions.DownloadDocumentException;
import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.example.treecategorybot.bot.services.ExcelGenerator;
import org.example.treecategorybot.bot.services.FileHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.DEFAULT_DOCUMENT;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.UPLOAD;

@Component
public class CommandDefaultDocument extends AbstractCommand {
    private final FileHandler fileHandler;
    private final CategoryTreeServicesImpl categoryTreeServices;

    public CommandDefaultDocument(FileHandler fileHandler, CategoryTreeServicesImpl categoryTreeServices) {
        super(DEFAULT_DOCUMENT);
        this.fileHandler = fileHandler;
        this.categoryTreeServices = categoryTreeServices;
    }

    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        if (message.hasDocument()) {
            String fileId = message.getDocument().getFileId();
            try {
                List<Category> categories = fileHandler.getCategoriesFromExcel(fileId, bot);
                categoryTreeServices.saveAll(categories);
                bot.execute(new SendMessage(chatId, "Документ успешно загружен."));

            } catch (DownloadDocumentException e) {
                SendMessage sendMessage = new SendMessage(chatId, e.getMessage());
                bot.execute(sendMessage);
            } catch (Exception e) {
                SendMessage sendMessage = new SendMessage(chatId, "Ошибка добавления категорий. " +
                        "Проверьте правильность заполнения передаваемого документа. " +
                        "Возможно некоторые категории уже добавлены. Выберете команду " + UPLOAD
                        + " для повторной загрузки.");
                bot.execute(sendMessage);
            }
        }
    }


}
