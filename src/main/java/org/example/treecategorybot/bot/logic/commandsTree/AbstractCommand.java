package org.example.treecategorybot.bot.logic.commandsTree;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Set;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;

@Getter
public abstract class AbstractCommand implements Command {
    private final String nameCommand;
    private Set<String> childCommands = Set.of(
            START,
            VIEW_TREE,
            ADD_ELEMENT,
            REMOVE_ELEMENT,
            DOWNLOAD,
            UPLOAD,
            HELP,
            REMOVE_All
    );

    public AbstractCommand(String nameCommand) {
        this.nameCommand = nameCommand;
    }

    public AbstractCommand(String nameCommand, Set<String> childCommands) {
        this.nameCommand = nameCommand;
        this.childCommands = childCommands;
    }

}
