package org.example.treecategorybot.bot.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.treecategorybot.bot.dto.CategoryDTO;
import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.logic.exceptions.DownloadDocumentException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import static org.example.treecategorybot.bot.logic.constants.ExceptionsTextMessages.*;
import static org.example.treecategorybot.bot.logic.constants.TitleHeaders.HEADER_CATEGORY;
import static org.example.treecategorybot.bot.logic.constants.TitleHeaders.HEADER_PARENT_CATEGORY;

@Component
public class FileHandler {

    private final GenerateInputStreamBot generateInputStreamBot;
    private final ConverterDtoCategoryImp converter;
    private final ExcelReaderServiceImpl excelReaderService;

    public FileHandler(GenerateInputStreamBot generateInputStreamBot, ConverterDtoCategoryImp converter, ExcelReaderServiceImpl excelReaderService) {
        this.generateInputStreamBot = generateInputStreamBot;
        this.converter = converter;
        this.excelReaderService = excelReaderService;
    }

    public  List<Category> getCategoriesFromExcel(String fileId, TelegramLongPollingBot bot)
            throws IOException, TelegramApiException, DownloadDocumentException {
        InputStream inputStream = generateInputStreamBot.getInputStream(fileId);
        return converter.convertDtoToCategory(excelReaderService.readExcelFile(inputStream));
    }
}
