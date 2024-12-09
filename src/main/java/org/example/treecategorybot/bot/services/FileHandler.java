package org.example.treecategorybot.bot.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.treecategorybot.bot.dto.CategoryDTO;
import org.example.treecategorybot.bot.entities.Category;
import org.example.treecategorybot.bot.logic.commandsTree.AbstractCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import static org.example.treecategorybot.bot.logic.constants.TitleHeaders.HEADER_CATEGORY;
import static org.example.treecategorybot.bot.logic.constants.TitleHeaders.HEADER_PARENT_CATEGORY;

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

    private List<CategoryDTO> readExcelFile(InputStream inputStream) {
        List<CategoryDTO> categories = new ArrayList<>();
        Set<String> parents = new HashSet<>();
        Set<String> categoriesName = new HashSet<>();
        parents.add(null);
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String categoryTitleName = row.getCell(0).getStringCellValue().trim();
                String categoryTitleParent = row.getCell(1).getStringCellValue().trim();
                if (!HEADER_CATEGORY.equals(categoryTitleName)
                        || !HEADER_PARENT_CATEGORY.equals(categoryTitleParent)) {
                    return Collections.emptyList();
                }
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String categoryName = row.getCell(0).getStringCellValue().trim();
                if (categoriesName.contains(categoryName)) {
                    return Collections.emptyList();
                }
                Cell cell = row.getCell(1);
                String parent = null;
                if (cell != null) {
                    parent = row.getCell(1).getStringCellValue().trim();
                }
                if (!parents.contains(parent)) {
                    return Collections.emptyList();
                }
                categoriesName.add(categoryName);
                parents.add(categoryName);
                if (parent == null) {
                    categories.add(new CategoryDTO(categoryName, null));
                } else {
                    categories.add(new CategoryDTO(categoryName, parent));
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
        return convertDtoToCategory(readExcelFile(inputStream));
    }

    private List<Category> convertDtoToCategory(List<CategoryDTO> categoryDTOS) {
        Map<String, Category> categoryListMap = new HashMap<>();
        List<Category> roots = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryDTOS) {
            String name = categoryDTO.name();
            String parentName = categoryDTO.parent();
            Category category = categoryListMap.computeIfAbsent(name, n -> new Category(n, null));
            if (parentName == null) {
                roots.add(category);
            } else {
                Category parentCategory = categoryListMap
                        .computeIfAbsent(parentName, n -> new Category(parentName, null));

                if (parentCategory.getChildren() == null) {
                    parentCategory.setChildren(new ArrayList<>());
                }
                parentCategory.getChildren().add(category);
                category.setParent(parentCategory);
            }
        }
        return roots;
    }
}
