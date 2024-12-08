package org.example.treecategorybot.bot.logic.commandsTree;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.EXIT_UPLOAD;

@Component
public class CommandExitUpload extends AbstractCommand {

    public CommandExitUpload() {
        super(EXIT_UPLOAD);
    }

    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
         bot.execute(new SendMessage(chatId, "Отменили загрузку документа"));
    }
}
