package org.example.treecategorybot.bot.services;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Интерфейс для сервиса, предоставляющего функциональность получения входного потока файлов.
 *
 * <p>
 * Этот интерфейс определяет метод для извлечения файлов в виде входного потока
 * из различных источников, таких как Telegram. Реализации этого интерфейса
 * должны обеспечивать конкретную логику получения файлов, включая обработку
 * исключений, связанных с API и вводом-выводом.
 * </p>
 */
public interface GenerateInputStreamService {

    /**
     * Получает входной поток файла по его идентификатору.
     *
     * <p>
     * Метод извлекает файл по его идентификатору и возвращает его содержимое
     * в виде входного потока {@link InputStream}. Реализация этого метода
     * должна обрабатывать возможные ошибки, возникающие при взаимодействии
     * с API и при вводе-выводе.
     * </p>
     *
     * @param param Идентификатор файла, который необходимо получить.
     *              Этот идентификатор должен быть действительным и существовать
     *              в источнике.
     * @return Входной поток {@link InputStream}, представляющий содержимое файла.
     * @throws TelegramApiException При возникновении ошибок при взаимодействии
     *                              с Telegram API.
     * @throws IOException При возникновении ошибок ввода-вывода.
     */
    InputStream getInputStream(String param) throws TelegramApiException, IOException;
}
