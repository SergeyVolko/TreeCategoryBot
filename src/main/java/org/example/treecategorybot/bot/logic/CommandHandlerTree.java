package org.example.treecategorybot.bot.logic;

import org.example.treecategorybot.bot.logic.commandsTree.AbstractCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaBotMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;

@Component
public class CommandHandlerTree {
    private static final int HISTORY_CAPACITY = 10;
    private final Map<String, AbstractCommand> commandMap;
    private final LinkedList<AbstractCommand> commandHistory = new LinkedList<>();


    public CommandHandlerTree(List<AbstractCommand> commands) {
        this.commandMap = commands.stream()
                .collect(Collectors.toMap(
                        AbstractCommand::getNameCommand,
                        Function.identity()
                ));
        commandHistory.add(commandMap.get(START));
    }

    public void  executeCommand(Update update, TelegramLongPollingBot bot) throws TelegramApiException {
        AbstractCommand command = getCommand(update);
        String chatId = update.getMessage().getChatId().toString();
        Message message = update.getMessage();
        if (command == null) {
            bot.execute(new SendMessage(chatId, "Введена не правильная команда"));
            return;
        }
        commandHistory.addLast(command);
        if (commandHistory.size() == HISTORY_CAPACITY) {
            commandHistory.removeFirst();
        }
        command.execute(chatId, message, bot);
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

    public AbstractCommand getCommand(Update update) {
        AbstractCommand command = null;
        Message message = update.getMessage();
        Set<String> commandsText = commandHistory.getLast().getChildCommands();
        Map<String, AbstractCommand> currentCommands = commandMap.entrySet().stream()
                .filter(e -> commandsText.contains(e.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
        if (message.hasText()) {
            String commandText = trimToFirstSpace(message.getText());
            command = currentCommands.getOrDefault(commandText, commandMap.get(DEFAULT_TEXT));

        } else if (message.hasDocument()) {
            command = currentCommands.get(DEFAULT_DOCUMENT);
        }
        return command;
    }

}
