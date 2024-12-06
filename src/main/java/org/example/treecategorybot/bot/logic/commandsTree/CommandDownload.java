package org.example.treecategorybot.bot.logic.commandsTree;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Set;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;

@Component
public class CommandDownload extends AbstractCommand {
    private static final Set<String> includingCommands = Set.of(
            DEFAULT_DOCUMENT,
            EXIT_DOWNLOAD
    );

    public CommandDownload() {
        super(DOWNLOAD, includingCommands);
    }

    @Override
    public SendMessage execute(String chatId, Message message) {
        return new SendMessage(chatId, "Команда начала загрузки документа");
    }
}
