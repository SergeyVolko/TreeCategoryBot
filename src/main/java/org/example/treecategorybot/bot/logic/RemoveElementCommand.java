package org.example.treecategorybot.bot.logic;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RemoveElementCommand implements Command {
    @Override
    public String nameCommand() {
        return "/removeElement";
    }

    @Override
    public SendMessage execute(String chatId, Message message) throws TelegramApiException {
        String messageText = message.getText().trim();
        if (!isValidateInputCategories(messageText)) {
            return new SendMessage(chatId, "Не верный шаблон ввода");
        }
        String element = messageText.substring(messageText.indexOf("<") + 1, messageText.length() - 1);
        return new SendMessage(chatId, "Удаление элемента:" + element);
    }

    private boolean isValidateInputCategories(String text) {
        String regex = "/removeElement\\s+<[^<>]+>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
