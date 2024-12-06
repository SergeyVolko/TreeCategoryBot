package org.example.treecategorybot.bot.logic.enums;

import static org.example.treecategorybot.bot.logic.constants.TextCommands.START;

public enum CommandMenu {
    START_MENU_COMMAND(START, "Text");
    private String name;
    private String textMenu;

    CommandMenu(String name, String textMenu) {

    }
}
