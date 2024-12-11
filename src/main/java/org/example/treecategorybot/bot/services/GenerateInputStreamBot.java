package org.example.treecategorybot.bot.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Сервис для получения входного потока файлов из Telegram.
 *
 * <p>
 * Этот класс предоставляет функциональность для извлечения файлов из Telegram
 * по их идентификатору и возвращает их в виде входного потока. Он использует
 * Telegram API для получения файлов и работает в контексте бота, который
 * реализует интерфейс {@link TelegramLongPollingBot}.
 * </p>
 */
@Service
public class GenerateInputStreamBot implements GenerateInputStreamService{
    /**
     * Бот, используемый для взаимодействия с Telegram API.
     */
    private final TelegramLongPollingBot bot;

    /**
     * Конструктор для создания экземпляра GenerateInputStreamBot.
     *
     * <p>
     * Этот конструктор инициализирует новый экземпляр класса
     * {@link GenerateInputStreamBot} с заданным ботом, который будет использоваться
     * для взаимодействия с Telegram API. Использование аннотации {@link Lazy}
     * позволяет отложить инициализацию бота до момента, когда он будет действительно
     * нужен, что может помочь избежать проблем с зависимостями при старте
     * приложения.
     * </p>
     *
     * @param bot Бот, использующийся для выполнения запросов к Telegram API.
     *            Данный параметр должен быть экземпляром {@link TelegramLongPollingBot}.
     */
    public GenerateInputStreamBot(@Lazy TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    /**
     * Получает входной поток файла по его идентификатору.
     *
     * <p>
     * Этот метод создает запрос к Telegram API для получения файла по его идентификатору
     * и открывает поток для чтения содержимого файла. Метод использует {@link GetFile}
     * для формирования запроса и {@link URL} для получения содержимого файла по URL.
     * </p>
     *
     * @param param Идентификатор файла, который необходимо получить.
     *              Этот идентификатор должен быть действительным и существовать на сервере Telegram.
     * @return Входной поток {@link InputStream}, представляющий содержимое файла.
     * @throws TelegramApiException При возникновении ошибок при взаимодействии с Telegram API,
     *                              например, если файл не найден или произошла ошибка сети.
     * @throws IOException При возникновении ошибок ввода-вывода, например, если не удалось
     *                     открыть поток для чтения содержимого файла.
     */
    @Override
    public InputStream getInputStream(String param) throws TelegramApiException, IOException {
        GetFile getFile = new GetFile();
        getFile.setFileId(param);
        File file = bot.execute(getFile);
        String filePath = file.getFilePath();
        return new URL("https://api.telegram.org/file/bot" + bot.getBotToken() + "/" + filePath).openStream();
    }
}
