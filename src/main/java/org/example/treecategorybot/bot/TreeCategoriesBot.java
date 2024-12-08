package org.example.treecategorybot.bot;

import org.example.treecategorybot.bot.logic.CommandHandlerTree;
import org.example.treecategorybot.bot.logic.commandsTree.AbstractCommand;
import org.example.treecategorybot.bot.logic.commandsTree.Command;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TreeCategoriesBot extends TelegramLongPollingBot {

    private final CommandHandlerTree commandHandlerTree;

    public TreeCategoriesBot(@Value("${telegrambots.token}") String botToken,
                             CommandHandlerTree commandHandlerTree) {
        super(botToken);
        this.commandHandlerTree = commandHandlerTree;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        try {
            commandHandlerTree.executeCommand(update, this);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "tree_category_rusobraz_bot";
    }
}
