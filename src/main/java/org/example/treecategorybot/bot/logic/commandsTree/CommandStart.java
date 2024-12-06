package org.example.treecategorybot.bot.logic.commandsTree;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_START_MENU;

@Component
public class CommandStart extends AbstractCommand {

    public CommandStart() {
        super(START);
    }

    @Override
    public SendMessage execute(String chatId, Message message) {
        return new SendMessage(chatId, TEXT_START_MENU);
    }
}
