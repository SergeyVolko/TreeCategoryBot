package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.ADD_ELEMENT;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.VIEW_TREE;

@Component
public class CommandViewTree extends AbstractCommand {
    private final CategoryTreeServicesImpl categoryTreeServices;


    public CommandViewTree(CategoryTreeServicesImpl categoryTreeServices) {
        super(VIEW_TREE);
        this.categoryTreeServices = categoryTreeServices;
    }

    @Override
    public SendMessage execute(String chatId, Message message) {
        return new SendMessage(chatId, categoryTreeServices.viewTree());
    }
}
