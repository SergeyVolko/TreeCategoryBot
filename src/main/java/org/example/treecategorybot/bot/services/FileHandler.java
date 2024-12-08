package org.example.treecategorybot.bot.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.treecategorybot.bot.entities.Category;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Component
public class FileHandler {

    private  InputStream getFileInputStream(String fileId, TelegramLongPollingBot bot) throws TelegramApiException,
            IOException {
        GetFile getFile = new GetFile();
        getFile.setFileId(fileId);
        File file = bot.execute(getFile);
        String filePath = file.getFilePath();
        return new URL("https://api.telegram.org/file/bot" + bot.getBotToken() + "/" + filePath).openStream();
    }

    private  List<Category> readExcelFile(InputStream inputStream) {
        List<Category> categories = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getLastCellNum() == -1) {
                    continue;
                }
                String categoryName = row.getCell(0).getStringCellValue();
                Cell cell = row.getCell(1);
                String parent = null;
                if (cell != null) {
                    parent = row.getCell(1).getStringCellValue();
                }
                if (categoryName.isEmpty()) {
                    continue;
                }
                if (parent == null) {
                    categories.add(new Category(categoryName));
                } else {
                    categories.add(new Category(categoryName, new Category(parent)));
                }
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
        return categories;
    }

    public  List<Category> getCategoriesFromExcel(String fileId, TelegramLongPollingBot bot)
            throws IOException, TelegramApiException {
        InputStream inputStream = getFileInputStream(fileId, bot);
        return readExcelFile(inputStream);
    }


}
