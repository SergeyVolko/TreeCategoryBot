package org.example.treecategorybot.bot.services;

import org.example.treecategorybot.bot.dto.CategoryDTO;
import org.example.treecategorybot.bot.logic.exceptions.DownloadDocumentException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Интерфейс для сервиса чтения данных из Excel файлов.
 *
 * <p>
 * Этот интерфейс определяет метод для чтения Excel файла и преобразования
 * его содержимого в список объектов {@link CategoryDTO}. Он предназначен
 * для использования в приложениях, которые требуют извлечения данных
 * из Excel документов.
 * </p>
 */
public interface ExcelReaderService {
    /**
     * Читает данные из предоставленного входного потока Excel файла
     * и возвращает список объектов {@link CategoryDTO}.
     *
     * <p>
     * Метод может выбрасывать исключения, если возникают проблемы
     * при чтении файла или если файл не соответствует ожидаемому формату.
     * </p>
     *
     * @param inputStream Входной поток, содержащий данные Excel файла.
     * @return Список объектов {@link CategoryDTO}, представляющих данные
     *         из Excel файла.
     * @throws IOException При возникновении ошибок ввода-вывода.
     * @throws DownloadDocumentException Если файл не соответствует ожидаемому формату
     *                                   или произошла другая ошибка при обработке.
     */
    List<CategoryDTO> readExcelFile(InputStream inputStream) throws IOException, DownloadDocumentException;
}
