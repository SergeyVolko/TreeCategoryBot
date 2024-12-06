package org.example.treecategorybot.bot.logic.commandsTree;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.HELP;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_HELP_MENU;

@Component
public class CommandHelp extends AbstractCommand {

    public CommandHelp() {
        super(HELP);
    }

    @Override
    public SendMessage execute(String chatId, Message message) {
        return new SendMessage(chatId, TEXT_HELP_MENU);
    }
}
