package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.ADD_ELEMENT;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.START;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_ADD_ELEMENT_MENU;

@Component
public class CommandAddElement extends AbstractCommand {
    private static final String REGEX = ADD_ELEMENT + "\\s+<[^<>]+>(\\s+<[^<>]+>)?";
    private final CategoryTreeServicesImpl categoryTreeServices;

    public CommandAddElement(CategoryTreeServicesImpl categoryTreeServices) {
        super(ADD_ELEMENT);
        this.categoryTreeServices = categoryTreeServices;
    }

    @Override
    public SendMessage execute(String chatId, Message message) {
        String text = message.getText();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            return new SendMessage(chatId, TEXT_ADD_ELEMENT_MENU);
        }
        String[] elements = text.substring(text.indexOf("<") + 1, text.length() - 1)
                .split(">\\s+<");
        Category category;
        if (elements.length == 1) {
            category = categoryTreeServices.addElement(elements[0]);
        } else {
            category = categoryTreeServices.addElementToParent(elements[0], elements[1]);
        }
        if (category == null) {
            return new SendMessage(chatId, "Данная категория ранее добавлялась " +
                    "или отсутствует родительская категория");
        }
        return new SendMessage(chatId, String.format("Добавлена категория: %s", category.getName()));
    }
}
