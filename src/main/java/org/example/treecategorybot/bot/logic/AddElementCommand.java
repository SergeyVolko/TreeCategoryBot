package org.example.treecategorybot.bot.logic;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AddElementCommand implements Command {
    @Override
    public String nameCommand() {
        return "/addElement";
    }

    @Override
    public SendMessage execute(String chatId, Message message) throws TelegramApiException {
        String messageText = message.getText().trim();
        if (!isValidateInputCategories(messageText)) {
            return new SendMessage(chatId, "Не верный формат ввода.");
        }
        String[] elements = messageText.substring(messageText.indexOf("<"), messageText.length() - 1)
                .split("> <");
        if (elements.length == 1) {
            return new SendMessage(chatId, "Этот элемент будет корневым, если у него нет родителя.");
        }
        return new SendMessage(chatId, "Добавление дочернего элемента");
    }

    private boolean isValidateInputCategories(String text) {
        String regex = "/addElement\\s+<[^<>]+>(\\s+<[^<>]+>)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
