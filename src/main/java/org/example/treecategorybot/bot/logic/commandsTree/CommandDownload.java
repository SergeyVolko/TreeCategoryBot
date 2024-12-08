package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.entities.Category;
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

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;

@Component
public class CommandDownload extends AbstractCommand {
    private final ExcelGenerator excelGenerator;
    private final CategoryTreeServicesImpl categoryTreeServices;

    public CommandDownload(ExcelGenerator excelGenerator, CategoryTreeServicesImpl categoryTreeServices) {
        super(DOWNLOAD);
        this.excelGenerator = excelGenerator;
        this.categoryTreeServices = categoryTreeServices;
    }

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
