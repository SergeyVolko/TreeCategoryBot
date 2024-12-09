package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.REMOVE_All;

@Component
public class CommandRemoveAll extends AbstractCommand {
    private final CategoryTreeServicesImpl categoryTreeServices;

    public CommandRemoveAll(CategoryTreeServicesImpl categoryTreeServices) {
        super(REMOVE_All);
        this.categoryTreeServices = categoryTreeServices;
    }

    @Override
    public void execute(String chatId, Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        categoryTreeServices.removeAll();
        bot.execute(new SendMessage(chatId, "Все категории удалены"));
    }
}
