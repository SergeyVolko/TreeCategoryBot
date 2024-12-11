package org.example.treecategorybot.bot.logic.commandsTree;

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
import java.util.Set;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_UPLOAD_DOCUMENT;

@Component
public class CommandUpload extends AbstractCommand{
    private final ExcelGenerator excelGenerator;
    private static Set<String> childCommands = Set.of(
            DEFAULT_DOCUMENT,
            EXIT_UPLOAD,
            START
    );

    public CommandUpload(ExcelGenerator excelGenerator) {
        super(UPLOAD, childCommands);
        this.excelGenerator = excelGenerator;
    }

    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(chatId,TEXT_UPLOAD_DOCUMENT);
        sendMessage.enableHtml(true);
        bot.execute(sendMessage);
    }
}
