package org.example.treecategorybot.bot.logic.commandsTree;

import lombok.Getter;
import org.example.treecategorybot.bot.logic.constants.TextCommands;

import java.util.Set;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;

/**
 * Абстрактный класс {@link AbstractCommand}, представляющий команду в системе командного дерева.
 * <p>
 * Этот класс реализует интерфейс {@link Command} и предоставляет общую
 * функциональность для всех команд, включая имя команды и набор дочерних команд.
 * </p>
 */
@Getter
public abstract class AbstractCommand implements Command {

    /**
     * Имя команды. Это поле содержит название команды, которая будет использоваться
     * для идентификации команды в системе.
     *
     * <p>
     * Значение этого поля устанавливается при создании экземпляра класса
     * {@link AbstractCommand} и не может быть изменено после этого.
     * </p>
     */
    private final String nameCommand;

    /**
     * Набор дочерних команд, связанных с данной командой.
     *
     * <p>
     * Этот набор содержит предопределенные команды, такие как {@link TextCommands#START},
     * {@link TextCommands#VIEW_TREE}, {@link TextCommands#ADD_ELEMENT},
     * {@link TextCommands#REMOVE_ELEMENT}, {@link TextCommands#DOWNLOAD},
     * {@link TextCommands#UPLOAD}, {@link TextCommands#HELP}, и
     * {@link TextCommands#REMOVE_All}. Данный набор может быть изменен
     * при создании экземпляра класса с использованием соответствующего конструктора.
     * </p>
     */
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

    /**
     * Конструктор, создающий экземпляр команды с заданным именем.
     *
     * <p>
     * Этот конструктор инициализирует поле {@link #nameCommand} значением,
     * предоставленным при создании экземпляра класса. Имя команды должно быть
     * уникальным и не должно быть {@code null} или пустым.
     * </p>
     *
     * @param nameCommand имя команды. Не должно быть {@code null} или пустым.
     * @throws IllegalArgumentException если {@code nameCommand} равен {@code null} или пустой строке.
     */
    public AbstractCommand(String nameCommand) {
        if (nameCommand == null || nameCommand.isEmpty()) {
            throw new IllegalArgumentException("Имя команды не должно быть null или пустым.");
        }
        this.nameCommand = nameCommand;
    }

    /**
     * Конструктор, создающий экземпляр команды с заданным именем и набором дочерних команд.
     *
     * <p>
     * Этот конструктор инициализирует поле {@link #nameCommand} значением,
     * предоставленным при создании экземпляра класса, а также устанавливает
     * набор дочерних команд {@link #childCommands}. Имя команды должно быть
     * уникальным и не должно быть {@code null} или пустым. Набор дочерних команд
     * может быть {@code null}, в этом случае будет использован пустой набор.
     * </p>
     *
     * @param nameCommand имя команды. Не должно быть {@code null} или пустым.
     * @param childCommands набор дочерних команд. Может быть {@code null},
     *                     в противном случае не должен содержать {@code null} значений.
     * @throws IllegalArgumentException если {@code nameCommand} равен {@code null} или пустой строке.
     */
    public AbstractCommand(String nameCommand, Set<String> childCommands) {
        if (nameCommand == null || nameCommand.isEmpty()) {
            throw new IllegalArgumentException("Имя команды не должно быть null или пустым.");
        }
        this.nameCommand = nameCommand;
        this.childCommands = childCommands != null ? childCommands : Set.of();
    }
}
