package org.example.treecategorybot.bot.logic;

import org.example.treecategorybot.bot.logic.commandsTree.AbstractCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import static org.example.treecategorybot.bot.logic.constants.TextCommands.*;

/**
 * Обработчик команд для взаимодействия с ботом Telegram.
 *
 * <p>
 * Этот класс отвечает за выполнение команд, поступающих от пользователей,
 * и управление историей команд. Он использует коллекцию команд,
 * которые могут быть выполнены, и обеспечивает обработку текстовых сообщений и документов.
 * </p>
 */
@Component
public class CommandHandlerTree {
    /**
     * Максимальная емкость истории команд.
     *
     * <p>
     * Эта константа определяет максимальное количество команд, которые могут быть сохранены в истории.
     * Когда количество команд в истории достигает этого значения,
     * самая старая команда будет удалена, чтобы освободить место для новой.
     * Значение по умолчанию установлено на 10.
     * </p>
     */
    private static final int HISTORY_CAPACITY = 10;

    /**
     * Карта, сопоставляющая названия команд с их соответствующими объектами команд.
     *
     * <p>
     * Эта переменная хранит команды, которые могут быть выполнены ботом.
     * Ключом является строка, представляющая название команды,
     * а значением — соответствующий объект команды, производный от {@link AbstractCommand}.
     * <p>
     * Используется для быстрого доступа к командам при обработке пользовательских запросов.
     * </p>
     */
    private final Map<String, AbstractCommand> commandMap;

    /**
     * История выполненных команд.
     *
     * <p>
     * Эта переменная представляет собой связный список, который хранит последние выполненные команды.
     * Используется для отслеживания последовательности команд, выполненных ботом,
     * и позволяет реализовать функциональность, такую как возврат к предыдущим командам.
     * <p>
     * При добавлении новой команды в список, если размер списка превышает
     * {@link #HISTORY_CAPACITY}, самая старая команда будет удалена.
     * </p>
     */
    private final LinkedList<AbstractCommand> commandHistory = new LinkedList<>();

    /**
     * Создает экземпляр {@link CommandHandlerTree} и инициализирует карту команд и историю команд.
     *
     * <p>
     * Конструктор принимает список команд, преобразует его в карту, где ключом является название команды,
     * а значением — соответствующий объект команды.
     * Затем добавляет начальную команду в историю команд.
     *
     * @param commands Список команд, которые будут добавлены в обработчик команд.
     * @throws NullPointerException если список команд равен {@code null} или
     * если любая команда в списке имеет {@code null} название.
     */
    public CommandHandlerTree(List<AbstractCommand> commands) {
        this.commandMap = commands.stream()
                .collect(Collectors.toMap(
                        AbstractCommand::getNameCommand,
                        Function.identity()
                ));
        commandHistory.add(commandMap.get(START));
    }

    /**
     * Выполняет указанную команду на основе полученного обновления.
     *
     * <p>
     * Метод извлекает команду из обновления и выполняет её, передавая необходимые параметры.
     * Если команда не найдена, пользователю отправляется сообщение об ошибке.
     * При выполнении команды, она добавляется в историю команд. Если размер истории
     * достигает максимальной емкости {@link #HISTORY_CAPACITY}, самая старая команда
     * удаляется из истории.
     * </p>
     *
     * @param update Объект обновления, содержащий информацию о сообщении и чате.
     * @param bot Объект бота, используемый для отправки сообщений и выполнения команд.
     * @throws TelegramApiException если возникает ошибка при выполнении команды через Telegram API.
     */
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

    /**
     * Обрезает строку до первого пробела.
     *
     * <p>
     * Этот метод принимает строку и возвращает её часть, которая предшествует первому пробелу.
     * Если строка равна {@code null} или пуста, метод возвращает её без изменений.
     * Если пробел не найден, возвращается вся строка.
     * </p>
     *
     * @param input Строка, которую необходимо обрезать.
     * @return Подстрока, содержащая часть строки до первого пробела,
     *         или {@code null} / пустую строку, если входная строка {@code null} или пуста.
     */
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


    /**
     * Извлекает команду из обновления на основе текста сообщения или вложенного документа.
     *
     * <p>
     * Метод анализирует сообщение, полученное в обновлении, и определяет,
     * какая команда должна быть выполнена. Если текст сообщения содержит команду,
     * она ищется в текущих командах, полученных из истории команд. Если текст не найден,
     * возвращается команда по умолчанию. Если сообщение содержит документ,
     * возвращается команда для обработки документов.
     * </p>
     *
     * @param update Объект обновления, содержащий информацию о сообщении.
     * @return Объект {@link AbstractCommand}, соответствующий команде,
     *         извлеченной из сообщения, или {@code null}, если команда не найдена.
     */
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
