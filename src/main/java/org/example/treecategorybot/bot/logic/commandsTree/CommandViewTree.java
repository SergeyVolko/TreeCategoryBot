package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.VIEW_TREE;

@Component
public class CommandViewTree extends AbstractCommand {
    private final CategoryTreeServicesImpl categoryTreeServices;


    public CommandViewTree(CategoryTreeServicesImpl categoryTreeServices) {
        super(VIEW_TREE);
        this.categoryTreeServices = categoryTreeServices;
    }

    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        bot.execute(new SendMessage(chatId, categoryTreeServices.viewTree()));
    }
}
