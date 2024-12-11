package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.logic.exceptions.DownloadDocumentException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Компонент для обработки файлов Excel и извлечения категорий.
 *
 * <p>
 * Этот класс предоставляет функциональность для получения категорий из
 * Excel файлов, используя различные сервисы для чтения данных и конвертации
 * их в нужный формат. Он интегрируется с Telegram API для получения файлов.
 * </p>
 */
@Component
public class FileHandler {
    /**
     * Сервис для получения входного потока из файла по его идентификатору.
     */
    private final GenerateInputStreamBot generateInputStreamBot;

    /**
     * Конвертер для преобразования объектов DTO в категории.
     */
    private final ConverterDtoCategoryImp converter;

    /**
     * Сервис для чтения данных из Excel файлов.
     */
    private final ExcelReaderServiceImpl excelReaderService;

    /**
     * Конструктор для создания экземпляра FileHandler.
     *
     * @param generateInputStreamBot Сервис для получения входного потока из файла.
     * @param converter Конвертер для преобразования объектов DTO в сущности категории.
     * @param excelReaderService Сервис для чтения данных из Excel файлов.
     */
    public FileHandler(GenerateInputStreamBot generateInputStreamBot, ConverterDtoCategoryImp converter, ExcelReaderServiceImpl excelReaderService) {
        this.generateInputStreamBot = generateInputStreamBot;
        this.converter = converter;
        this.excelReaderService = excelReaderService;
    }

    /**
     * Извлекает категории из Excel файла, идентифицируемого по ID файла.
     *
     * <p>
     * Метод получает входной поток Excel файла, читает данные с помощью
     * сервиса ExcelReaderService и конвертирует их в объекты категории.
     * </p>
     *
     * @param fileId Идентификатор файла, используемый для получения входного потока.
     * @return Список объектов {@link Category}, представляющих категории из Excel файла.
     * @throws IOException При возникновении ошибок ввода-вывода.
     * @throws TelegramApiException При возникновении ошибок при взаимодействии с Telegram API.
     * @throws DownloadDocumentException Если файл не соответствует ожидаемому формату
     *                                     или произошла ошибка при обработке.
     */
    public  List<Category> getCategoriesFromExcel(String fileId)
            throws IOException, TelegramApiException, DownloadDocumentException {
        InputStream inputStream = generateInputStreamBot.getInputStream(fileId);
        return converter.convertDtoToCategory(excelReaderService.readExcelFile(inputStream));
    }
}
