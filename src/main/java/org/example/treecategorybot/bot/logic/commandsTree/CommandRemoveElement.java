package org.example.treecategorybot.bot.logic.commandsTree;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.services.CategoryTreeServicesImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.REMOVE_ELEMENT;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.START;
import static org.example.treecategorybot.bot.logic.constants.TextMenu.TEXT_REMOVE_ELEMENT_MENU;

@Component
public class CommandRemoveElement extends AbstractCommand {
    private static final String REGEX = REMOVE_ELEMENT + "\\s+<[^<>]+>";
    private final CategoryTreeServicesImpl categoryTreeServices;

    public CommandRemoveElement(CategoryTreeServicesImpl categoryTreeServices) {
        super(REMOVE_ELEMENT);
        this.categoryTreeServices = categoryTreeServices;
    }

    @Override
    public SendMessage execute(String chatId, Message message) {
        String text = message.getText();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            return new SendMessage(chatId, TEXT_REMOVE_ELEMENT_MENU);
        }
        String nameCategory = text.substring(text.indexOf("<") + 1, text.length() - 1);
        Category category = categoryTreeServices.removeElement(nameCategory);
        if (category == null) {
            return new SendMessage(chatId, "Категорию удалить не получилось");
        }
        return new SendMessage(chatId, String.format("Удалена категория: %s", category.getName()));
    }
}
