package org.example.treecategorybot.bot;

import org.example.treecategorybot.bot.logic.CommandHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TreeCategoriesBot extends TelegramLongPollingBot {

    private final CommandHandler commandHandler;

    public TreeCategoriesBot(@Value("${telegrambots.token}") String botToken, CommandHandler commandHandler) {
        super(botToken);
        this.commandHandler = commandHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(commandHandler.executeCommand(update));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "tree_category_rusobraz_bot";
    }
}
