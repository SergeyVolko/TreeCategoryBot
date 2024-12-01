package org.example.treecategorybot.bot.logic;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ViewTreeCommand implements Command {
    @Override
    public String nameCommand() {
        return "/viewTree";
    }

    @Override
    public SendMessage execute(String chatId, Message message) throws TelegramApiException {
        return new SendMessage(chatId, "Отобразить дерево");
    }
}
