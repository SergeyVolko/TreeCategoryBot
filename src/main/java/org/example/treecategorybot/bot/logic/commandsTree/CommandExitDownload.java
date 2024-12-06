package org.example.treecategorybot.bot.logic.commandsTree;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.DEFAULT_DOCUMENT;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.EXIT_DOWNLOAD;

@Component
public class CommandExitDownload extends AbstractCommand {

    public CommandExitDownload() {
        super(EXIT_DOWNLOAD);
    }

    @Override
    public SendMessage execute(String chatId, Message message) {
        return new SendMessage(chatId, "Отменили загрузку документа");
    }
}
