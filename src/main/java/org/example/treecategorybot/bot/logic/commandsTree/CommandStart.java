package org.example.treecategorybot.bot.logic.commandsTree;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_START_MENU;


@Component
public class CommandStart extends AbstractCommand {

    public CommandStart() {
        super(START);
    }

    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        bot.execute(new SendMessage(chatId, TEXT_START_MENU));
    }
}
