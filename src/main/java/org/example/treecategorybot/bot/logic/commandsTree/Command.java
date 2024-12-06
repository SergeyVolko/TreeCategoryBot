package org.example.treecategorybot.bot.logic.commandsTree;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {
    SendMessage execute(String chatId, Message message);
}
