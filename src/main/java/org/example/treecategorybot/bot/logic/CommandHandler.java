package org.example.treecategorybot.bot.logic;

import org.example.treecategorybot.bot.TreeCategoriesBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CommandHandler {
    private final Map<String, Command> commandMap;

    public CommandHandler(List<Command> commands) {
        this.commandMap = commands.stream().collect(Collectors.toMap(Command::nameCommand,
                Function.identity()));
    }

    public SendMessage executeCommand(Update update) throws TelegramApiException {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        if (message.hasText()) {
            String command = trimToFirstSpace(message.getText());
            return commandMap.get(command).execute(chatId, message);
        } else if (message.hasDocument()) {
            return new SendMessage(chatId, "Обработка документа.");
        }
        return new SendMessage(chatId, "Данный вид сообщений не обрабатывается");
    }

    private String trimToFirstSpace(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            return input;
        }
        return input.substring(0, spaceIndex);
    }
}
